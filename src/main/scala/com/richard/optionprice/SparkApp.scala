package com.richard.optionprice

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SparkApp extends App {

   val conf = new SparkConf().setAppName("OptionPriceSparkApp")
   printf("config:" + conf.toDebugString + "\n")
   val sc = new SparkContext(conf)

   val n = 10000
   /*
   val spotPrice = 30
   val strikePrice = 20
   val riskFreeRate = 0.01
   val volatility = 0.2
   val timeToExpire = 0.5
   */

   val list = sc.parallelize(for (i <- 1 to n) yield i)
   
   val price = list.map((a:Int)=>{
        val spotPrice = 30
        val strikePrice = 20
        val riskFreeRate = 0.01
        val volatility = 0.2
        val timeToExpire = 0.5
        print(s"Task $a -- "); val r = MonteCarlo.callPrice(5000,spotPrice,strikePrice,riskFreeRate,volatility,timeToExpire); print(s"$r\n"); r}).reduce((a,b)=>{a+b})/n

   print(s"Result Price: $price \n")

   sc.stop

}
