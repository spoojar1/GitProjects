package project

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD

object PredictRating {
//def predicRate(reviewText:String, positiveWords: RDD[(String,Int)],negativeWords: RDD[(String,Int)]) : Double={
  def processRecords(user:Integer,product:Integer,model: org.apache.spark.mllib.recommendation.MatrixFactorizationModel):Double={
    
    model.predict(user, product);
    
  }
  
  /* Prediction of the star rating*/
      def predicRate(reviewText:String, positiveWords: Array[String],negativeWords: Array[String]) : Double={
     
  /*  val conf = new SparkConf().setAppName("Simple Application");
    val   sc = new SparkContext(conf);*/
      val array1=reviewText.toLowerCase().split(" ");
      
/*            val postiveArray=positiveWords.toArray()
            val negativeArray=positiveWords.toArray()*/
      var x=0;
      var positiveCount=0; var negativeCount=0;
      for(x <- 0 to array1.length-1){
        if(x>0){
          if(positiveWords.contains(array1(x))){
            if(positiveWords.contains(array1(x-1))){
              positiveCount=positiveCount+2;
            }
            else{
               positiveCount=positiveCount+1;
            }
          }
          if(negativeWords.contains(array1(x))){
            if(positiveWords.contains(array1(x-1))){
              positiveCount=positiveCount-1;
              negativeCount=negativeCount+2;
            }
            else if(negativeWords.contains(array1(x-1))){
              negativeCount=negativeCount+2;
            }

            else{
               negativeCount=negativeCount+1;
            }
          }
          
        }
        else{
          if(positiveWords.contains(array1(x))){
               positiveCount=positiveCount+1;
            
          }
          if(positiveWords.contains(array1(x))){
               negativeCount=negativeCount+1;
            
          }
        }
      }
     //val positiveCount=array1.count {line=>positiveWords.contains(line)};
     //val negativeCount=array1.count {line=>negativeWords.contains(line)};
     // val positiveCount=10;
      //val negativeCount=5;
      //toSeq.reduceByKey(_+_);
      

      // val positiveCount=(wordsCount).join(positiveWords).map(line=>("count",line._2._1)).reduceByKey(_+_).values.first();
    //val negativeCount=(wordsCount).join(negativeWords).map(line=>("count",line._2._1)).reduceByKey(_+_).values.first();
      if((positiveCount==0) && (negativeCount==0)){
        val joinedTotalCount=0;
        joinedTotalCount;
      }
      else{

        
                val joinedTotalCount = Math.round(2.5+((positiveCount-negativeCount)*2.5/(positiveCount+negativeCount)));
        joinedTotalCount;
      }
    
   
      
      // joinedTotalCount;

    }
  case class MyRating(ratingId:String,user: String, restaurant: String,reviewText: String, stars: Double,predictedStar :Double){
    
  }
  def main(args: Array[String]) {
   val conf = new SparkConf().setAppName("Simple Application");
    val   sc = new SparkContext(conf);
    
    /* Reading positive words file */
      val positiveWords = sc.textFile(args(1)).map { line => (line) };
      /* Reading negative words file */
      val negativeWords = sc.textFile(args(2)).map { line => (line) };
     /* Reading review file */ 
    val reviews = sc.textFile(args(0));

    val positiveArray=positiveWords.toArray();
    val negativeArray=negativeWords.toArray();
    
/* extracting only requried fields from file */
    val userData =reviews.map(line=>line.split(",").take(10)).map(ratings=>MyRating(ratings(2),ratings(6),ratings(0),ratings(4),ratings(3).toDouble,predicRate(ratings(4),positiveArray,negativeArray)));
    
    
    
    
    val wordsCount=userData.map(line=>(line.ratingId));
 
    
    /* Creating unique Integer Id for user id*/
    val userId = userData.map(_.user).distinct().zipWithUniqueId();
    val userReverseMapping = userId.map { case (l, r) => (r, l) };
    
    
    /* Creating unique Integer Id for restaurant id*/
    val restId = userData.map(_.restaurant).distinct().zipWithUniqueId();
    val restReverseMapping = restId.map { case (l, r) => (r, l) };
    //                                                              ratingId(String),userid(string),userId(long),restaurantId(string),reviewText,stars
    //val userIdWithData1 = userData.keyBy(_.user).join(userId).map(r=>(r._1,r._2._1.ratingId,r._2._2,r._2._1.restaurant,r._2._1.reviewText,r._2._1.stars)).keyBy(_._2);
    //                                                              userid(string),ratingId(String),userId(long),restaurantId(string),reviewText,stars,predictedStars
    
    /* Records with Integer userId*/
    val userIdWithData = userData.keyBy(_.user).join(userId).map(r=>(r._1,r._2._2,r._2._1.restaurant,r._2._1.reviewText,r._2._1.stars,r._2._1.predictedStar));
   // val dataWithPredicted = userIdWithData1.join(joinedTotalCount);
    
   // userIdWithData.map(r=>println(r._1+","+r._2+","+r._3+","+r._4+","+r._5+","+r._6+"")).saveAsTextFile("PredictedStar");
    
    /* Saving the predicted star rating*/
    userIdWithData.map(r=>(r._1+","+r._3+","+r._4+","+r._5+","+r._6+"")).saveAsTextFile("PredictedStarOutput");
   // dataWithPredicted.top(10).map(r=>println(r._1+","+r._2._1._1+","+r._2._1._2+","+r._2._1._3+","+r._2._1._4+","+r._2._1._5+","+r._2._1._6+","+""))
          //                                                     userid(string),userId(long),restaurantId(string),restaurantId(long),reviewText,stars,predictedStar
    
    
    /* Records with Integer Restaurant Id*/
    val restIdWithData = userIdWithData.keyBy(_._3).join(restId).map(r=>(r._2._1._1,r._2._1._2,r._2._1._3,r._2._2,r._2._1._4,r._2._1._5,r._2._1._6));
    //.map(line=>(line._1+","+line._2._1))
    val successRecords=restIdWithData.filter(line=>(line._6-line._7)<=1.0).count();
    val CountOfDiff2=restIdWithData.filter(line=>((line._6-line._7)==2.0)).count();
    val CountOfDiff3=restIdWithData.filter(line=>((line._6-line._7)==3.0)).count();
    val CountOfDiff4=restIdWithData.filter(line=>((line._6-line._7)==4.0)).count();
    val CountOfDiff5=restIdWithData.filter(line=>((line._6-line._7)==5.0)).count();
    val totalRecords=restIdWithData.count();
    println("+++++++++++++++++++++++++++")
    println("`Less than or Equal to 1 difference :"+successRecords);
    println("2 Difference  :"+CountOfDiff2);
    println("3 Difference  :"+CountOfDiff3);
    println("4 Difference  :"+CountOfDiff4);
    println("5 Difference  :"+CountOfDiff5);
    println("Total Records :"+totalRecords);
    println("Accuracy :"+(successRecords.asInstanceOf[Double]/totalRecords.asInstanceOf[Double]));
    
    println("+++++++++++++++++++++++++++")
    
    

    
    val ratings = restIdWithData.map(r=>Rating(r._2.toInt,r._4.toInt,r._7));
    
    val model = ALS.train(ratings, 50, 10, 0.01);
    val usersProducts = restIdWithData.map{ line=> (line._2, line._4)};
    val userProductsActual = restIdWithData.map{ line=> ((line._1, line._3),line._7)};
    //val ratingPredictions = restIdWithData.map(line=>((line._1,line._3),model.predict(line._2.toInt,line._4.toInt))).join(userProductsActual);
    // val ratingPredictions = restIdWithData.map(line=>((line._1,line._3),processRecords(line._2.toInt, line._4.toInt, model))).join(userProductsActual);
    
    
    /* Finding the Mean square Error */
    val MSE=restIdWithData.map{line =>  math.pow((line._6 - line._7), 2)}.reduce(_ + _) /math.pow(restIdWithData.count(),2);
    // val SquareError = MSE.reduce(_ + _) / ratingPredictions.count
     println("+++++++++++++++++++++++++++");
 /* Finding the Root Mean square Error */
     val RMSE = math.sqrt(MSE);
          println(RMSE);
     println("+++++++++++++++++++++++++++");
    val puser = userId.lookup("PUFPaY9KxDAcGqfsorJp3Q").mkString.toInt
    val prest = restId.lookup("cE27W9VPgO88Qxe4ol6y_g").mkString.toInt
    
    val predictedRating = model.predict(puser,prest)
    println("Rating predicted ")
    println(predictedRating)
    val K = 10
    val topKRecs = model.recommendProducts(puser, K)
    println("\n Recommended ")
    println(topKRecs.mkString("\n"))

   //Rating(ratings);
    sc.setLogLevel("WARN");
   
    }
  }