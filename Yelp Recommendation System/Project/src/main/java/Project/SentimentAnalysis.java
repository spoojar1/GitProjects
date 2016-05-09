
package Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class SentimentAnalysis {
	public static class BusinessMap extends Mapper<LongWritable, Text, Text, IntWritable> {
		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			ArrayList<String> positiveWords = new ArrayList<String>();
			BufferedReader positiveWordsBufferedReader = null;
			try {
				String sCurrentLine;
				positiveWordsBufferedReader = new BufferedReader(new FileReader("/user/mxs154930/positive-words.txt"));
				while ((sCurrentLine = positiveWordsBufferedReader.readLine()) != null) {
					positiveWords.add(sCurrentLine);
					//context.write(new Text(sCurrentLine), new IntWritable(1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (positiveWordsBufferedReader != null)
						positiveWordsBufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			ArrayList<String> negativeWords = new ArrayList<String>();
			BufferedReader negativeWordsBufferedReader = null;
			try {
				String sCurrentLine;
				negativeWordsBufferedReader = new BufferedReader(new FileReader("/user/mxs154930/negative-words.txt"));
				while ((sCurrentLine = negativeWordsBufferedReader.readLine()) != null) {
					negativeWords.add(sCurrentLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (negativeWordsBufferedReader != null)
						negativeWordsBufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			int rating = 0;																																																																																			
			String delims = ",";
			String[] businessData = StringUtils.split(value.toString(), delims);
			int flag = 0;
			for (int iterator = 1; iterator < businessData.length; iterator++) {
				int positiveCount = 0;
				int negativeCount = 0;
				//System.out.println(eachRecord[4]);
				String words[] = businessData[4].split(" ");																																				
				rating = Integer.parseInt(businessData[3]);
				for (String word : words) {
					// System.out.println(word);
					word = word.toLowerCase();
					if (word.equals("not")) {
						flag = 1;
						break;
					}
					if (word.equals("very") || word.equals("too")) {
						flag = 2;
						break;
					}
					if (positiveWords.contains(word)) {
						if (flag == 0)
							positiveCount++;
						else if (flag == 1)
							negativeCount++;
						else if (flag == 2)
							positiveCount += 2;
					} else if (negativeWords.contains(word)) {
						if (flag == 0)
							negativeCount++;
						else if (flag == 1)
							positiveCount++;
						else if (flag == 2)
							negativeCount += 2;
					}
					flag = 0;
				}
				// System.out.println(positiveCount);			
				
				if (positiveCount > 0 | negativeCount > 0) {
					int totalWords = (positiveCount + negativeCount);
					// System.out.println(totalWords);
					rating = (int) Math.round(2.5 + (((positiveCount - negativeCount) * 2.5 / totalWords)));
				}
			}
			context.write(new Text(businessData[0]), new IntWritable(rating));
		}

		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
		}
	}

	public static class BusinessRatingReduce extends Reducer<Text, Text, Text, IntWritable> {
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		}
	}

	// Driver program
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs(); // get
																						// all
																						// args
		if (otherArgs.length != 2) {
			System.err.println("Usage: CountYelpBusiness <in> <out>");
			System.exit(2);
		}
		Job job = Job.getInstance(conf, "CountYelp");
		job.setJarByClass(SentimentAnalysis.class);
		job.setMapperClass(BusinessMap.class);
		// job.setReducerClass(BusinessRatingReduce.class);
		// uncomment the following line to add the Combiner
		// job.setCombinerClass(Reduce.class);
		// set output key type
		job.setOutputKeyClass(Text.class);
		// set output value type
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		// set the HDFS path of the input data
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		// set the HDFS path for the output
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		// Wait till job completion
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
