package BigDataAssgnmt2.BigDataAssgnmt2;

import java.io.IOException;
/*import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import Map;*/
import java.util.regex.Pattern;
import java.util.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class TrendingHashTags extends Configured implements Tool {
	//private static final Logger LOG = Logger.getLogger(WordCount.class);
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new TrendingHashTags(), args);
		System.exit(res);
	}

	public int run(String[] args) throws Exception {
		Job job = Job.getInstance(getConf(), "TrendingHashTags");
		job.setJarByClass(this.getClass());
		// Use TextInputFormat, the default unless job.setInputFormatClass is used
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		FileSystem fs = FileSystem.get(getConf());
		/*Check if output path (args[1])exist or not*/
		if(fs.exists(new Path(args[1]))){
		   /*If exist delete the output path*/
		   fs.delete(new Path(args[1]),true);
		}
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapperClass(TrendingHashTagsMap.class);
		//job.setCombinerClass(TrendingHashTagsReduce.class);
		job.setNumReduceTasks(1);
		job.setReducerClass(TrendingHashTagsReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static class TrendingHashTagsMap extends Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		private boolean caseSensitive = false;
		//private static final Pattern WORD_BOUNDARY = Pattern.compile("\\s*\\b\\s*");
		private static final Pattern WORD_BOUNDARY = Pattern.compile("#");

		protected void setup(Mapper.Context context)
		  throws IOException,
			InterruptedException {
		  Configuration config = context.getConfiguration();
		  this.caseSensitive = config.getBoolean("wordcount.case.sensitive", false);
		}

		public void map(LongWritable offset, Text lineText, Context context) throws IOException, InterruptedException {
			String line = lineText.toString();
			if (!caseSensitive)
				line = line.toLowerCase();
			Text currentWord = new Text();

			int index=line.lastIndexOf('#');
			while(index!=-1){
				String str=FilterHash(line.substring(index));
				if(str.length()>1)
					context.write(new Text(str),one);
				line=line.substring(0,index);
				index=line.lastIndexOf('#');
			}
		}	
		
		//filter unwanted characters from hashtags
		static String FilterHash(String str){
			if(str.indexOf('@')!=-1)
				str=str.substring(0,str.indexOf('@'));
			if(str.indexOf(':')!=-1)
				str=str.substring(0,str.indexOf(':'));
			if(str.indexOf(',')!=-1)
				str=str.substring(0,str.indexOf(','));
			if(str.indexOf('?')!=-1)
				str=str.substring(0,str.indexOf('?'));
			if(str.indexOf('/')!=-1)
				str=str.substring(0,str.indexOf('/'));
			if(str.indexOf(' ')!=-1)
				str=str.substring(0,str.indexOf(' '));
			if(str.indexOf('.')!=-1)
				str=str.substring(0,str.indexOf('.'));
			if(str.indexOf('}')!=-1)
				str=str.substring(0,str.indexOf('}'));
			if(str.indexOf('{')!=-1)
				str=str.substring(0,str.indexOf('{'));
			if(str.indexOf(')')!=-1)
				str=str.substring(0,str.indexOf(')'));
			if(str.indexOf('(')!=-1)
				str=str.substring(0,str.indexOf('('));
			return str.trim();
		}
	}
  
	public static class TrendingHashTagsReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		
		private Map<Text, IntWritable> countMap = new HashMap<>();
		
		@Override
		public void reduce(Text key, Iterable<IntWritable> counts, Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable count : counts)
				sum += count.get();
			//context.write(word, new IntWritable(sum));
			
			countMap.put(new Text(key), new IntWritable(sum));
		}
    
	    @Override
	    protected void cleanup(Context context) throws IOException, InterruptedException {
	
	        Map<Text, IntWritable> sortedMap = sortByValues(countMap);
	
	        int counter = 0;
	        for (Text key : sortedMap.keySet()) {
	            if (counter++ == 10) {
	                break;
	            }
	            context.write(key, sortedMap.get(key));
	        }
	    }
	}
	
	private static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {

            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K, V> sortedMap = new LinkedHashMap<K, V>();

        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
	
	public static class TrendingHashTagsCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            // computes the number of occurrences of a single word
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            context.write(key, new IntWritable(sum));
        }
    }
}
