package com.richard.optionprice

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object Test2App extends App {
   //val random = new scala.util.Random() 
   val conf = new SparkConf().setAppName("OptionPriceSparkApp")
   printf("config:" + conf.toDebugString + "\n")
   val sc = new SparkContext(conf)

   val nor = 1000

   /*
   val spotPrice = 30
   val strikePrice = 20
   val riskFreeRate = 0.01
   val volatility = 0.2
   val timeToExpire = 0.5
   */

   val list = sc.parallelize(for (i <- 1 to nor) yield i)
   
   val price = list.map((a:Int)=>{
            val spotPrice = 30
            val strikePrice = 20
            val riskFreeRate = 0.01
            val volatility = 0.2
            val timeToExpire = 0.5
            print(s"Task $a -- $nor -- ")
            val r = callPrice(spotPrice,strikePrice,riskFreeRate,volatility,timeToExpire)
            print(s"$r\n"); r}
   ).reduce((a,b)=>{a+b})/nor

   print(s"Result Price: $price \n")

   sc.stop

   def simPriceCompound (s:Double,r:Double,sigma:Double,t:Double) : Double = {
     var price = s
     val random = new scala.util.Random();
     for (i <- 1 to 1000) {
       price = price * Math.exp(r*(t/1) - 0.5*sigma*sigma*(t/1) + sigma*random.nextGaussian*Math.sqrt(t/1))
     }
     price
   } 

   def callPrice (s:Double,x:Double,r:Double,sigma:Double,t:Double) : Double = {
     val fp = simPriceCompound(s,r,sigma,t)
     if (fp > x) Math.exp(-r*t) * (fp-x) else 0
   }

}
