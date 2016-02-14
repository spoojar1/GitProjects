package BigDataAssgnmt2.BigDataAssgnmt2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class TwitterDownload {
	static String convertDate(String str){ 
		return str.substring(0,4)+"-"+str.substring(4,6)+"-"+str.substring(6);
	}

	static long convertDateLong(String str) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date d = formatter.parse(str);
		return d.getTime();
	}
	static Long decrementLongDate(Long l,int i){
		Calendar c = Calendar.getInstance();
        c.setTime(new Date(l));
        c.add(Calendar.DATE,i);
        return c.getTime().getTime();
	}
	public static void main(String[] args) throws ParseException, IOException, URISyntaxException, TwitterException {	
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	        /*
	        .setOAuthConsumerKey("M4Y4o3HcW3xp9SOICHfFbue7m")
	        .setOAuthConsumerSecret("VQFecU4bQcZKIb5YoFj86rFn7imaaohrz2bNSJ3dQuzikXkRoq")
	        .setOAuthAccessToken("4843585585-XDWkuRaeUqmseEIbJ277SwyNFPTZvXyeR4XjwYm")
	        .setOAuthAccessTokenSecret("k7cuxcXLHB30nyuszQGS4GgQ2inyQSxMy0iyFnfZ4LjTN");
	        */
		    .setOAuthConsumerKey("Tm27BshpxbpAMlRsEbQJge0JC")
	        .setOAuthConsumerSecret("G4BrlP7qcIi1aDnpgyIvH6eoLhYo7wc4LOQ3L4Q8ILruUcjVeU")
	        .setOAuthAccessToken("4640057743-KQZ8hEwSCZHgmPFKXC1NwmGWLufxVAhGauTTlYH")
	        .setOAuthAccessTokenSecret("vwucvxL2GDCV1iPbzQZqSmC1yAe85Pf1BO1k40T0bDY5M");
	        
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
	    
	    try {
	    	int count=1;
	        Query query = new Query("US Presidency");
	    	
	        System.out.println("Downloading Tweets...");
	        query.setSince(convertDate(args[0]));
	        query.setUntil(convertDate(args[1]));
	        
	        long endtimestamp=convertDateLong(convertDate(args[0]));
	        //System.out.println(endtimestamp);

	        Long timestamp=decrementLongDate(convertDateLong(convertDate(args[1])),-1);
	        //System.out.println(timestamp);

		    Configuration conf = new Configuration();
		    	conf.addResource(new Path("/usr/local/hadoop-2.4.1/etc/hadoop/core-site.xml"));
		    	conf.addResource(new Path("/usr/local/hadoop-2.4.1/etc/hadoop/hdfs-site.xml"));
		    FileSystem hdfs = FileSystem.get( new URI(args[2]),conf);
		    Path file = new Path(args[2]+"BigData_part"+(count++)+".txt");
		    if(hdfs.exists(file))
		    	hdfs.delete(file,true); 
		    
		    OutputStream out = hdfs.create(file);
		   
		    while(timestamp>=endtimestamp){
		    		
	        	RateLimitStatus status=twitter.getRateLimitStatus().get("/search/tweets");
	        	//System.out.println(status.getRemaining());
		        if(status.getRemaining()<5){
		        	System.out.println("Rate Almost Exceeded. Come back after "+status.getSecondsUntilReset()+" seconds");
		        	System.exit(1);
		        }
		        
		        query.setCount(100);
		        QueryResult result = twitter.search(query);
		        
	            List<Status> tweets = result.getTweets();
	            
	            //System.out.print("# Tweets:\t" + tweets.size());
	            Long minId = Long.MAX_VALUE;
				
				if(tweets.size()==0){
					out.close();
					break;
				}
				
	            for (Status tweet : tweets) {  
	            	//System.out.println(tweet.getCreatedAt().getTime()+" "+timestamp);
	            	if(tweet.getCreatedAt().getTime()-timestamp<0){		//} || tweetcount>100){
	            		timestamp=decrementLongDate(timestamp,-1);
	            		out.close();
	            		if(timestamp<endtimestamp){
		            		out.close();
		            		System.out.println("Download Complete");
		            		System.exit(1);
		            	}
	            		file = new Path(args[2]+"BigData_part"+(count++)+".txt");
	            		if(hdfs.exists(file))
	        		    	hdfs.delete(file,true); 
	            		out = hdfs.create(file);
        	    	}  

	                if (tweet.getId() < minId)
	                	minId = tweet.getId();
	                //System.out.println(tweet.getCreatedAt());
	                String data="@"+tweet.getUser().getName()+" : "+tweet.getText();
	            	out.write(data.getBytes());   	
	            }
	            
	            query.setMaxId(minId-1);
	        }
		    System.out.println("Download Complete");
	    }catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	    }
	}
}
