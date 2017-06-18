package com.richard.optionprice

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object TestApp extends App {

   val conf = new SparkConf().setAppName("TestApp")
   printf("config:" + conf.toDebugString + "\n")
   val sc = new SparkContext(conf)

   val n = 10000

   val result = sc.parallelize(for (i<-1 to n) yield i).map((a:Int)=>{print(s"$a\n"); a*10}).reduce((a,b)=>{a+b})

   print(s"Result: $result \n")

   sc.stop

}
