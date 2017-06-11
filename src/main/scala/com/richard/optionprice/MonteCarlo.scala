package com.richard.optionprice

import scala.util.Random

object MonteCarlo {
   val random = new Random()

   def simPriceCompound (n:Int,s:Double,r:Double,sigma:Double,t:Double) : Double = {
     var price = s
     for (i <- 1 to n) {
       price = price * Math.exp(r*(t/n) - 0.5*sigma*sigma*(t/n) + sigma*random.nextGaussian*Math.sqrt(t/n))
     }
     price
   } 

   def callPrice (n:Int,s:Double,x:Double,r:Double,sigma:Double,t:Double) : Double = {
     val fp = simPriceCompound(n,s,r,sigma,t)
     if (fp > x) Math.exp(-r*t) * (fp-x) else 0
   }

   def putPrice (n:Int,s:Double,x:Double,r:Double,sigma:Double,t:Double) : Double = {
     val fp = simPriceCompound(n,s,r,sigma,t)
     if (x > fp) Math.exp(-r*t) * (x-fp) else 0
   }
}
