#Command to run the python script
Python convert.py
#Command to run the spark script
spark-submit --class project.PredictRating --master local template.jar "/user/mxs153130/YelpInput/1000rows.csv" "/user/mxs153130/YelpInput/positive-words.txt" "/user/mxs153130/YelpInput/negative-words.txt" 
#Command to run pig Script
pig -x local predict.pig >PigOutput.log
#Command to run Mapreduce
hadoop jar Project-0.0.1-SNAPSHOT.jar Project.SentimentAnalysis /user/mxs154930/1000rows.csv /user/mxs154930/ProjectOutput/
