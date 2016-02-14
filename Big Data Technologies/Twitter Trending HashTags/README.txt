Objective:
To download the tweets for a particular subject and find the Top 10 trending hashtags.

Code Details:
Use the twitter4j API to download tweets for a particular (Hardcoded "US Presidency") topic into HDFS (TwitterDownload.java)
and then run the MapReduce job on it to fetch the TOP 10 trending hashtags (TrendingHashTags.java)