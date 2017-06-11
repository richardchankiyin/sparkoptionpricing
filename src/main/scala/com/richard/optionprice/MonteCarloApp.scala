package com.richard.optionprice

object MonteCarloApp extends App {
  val callPrice = MonteCarlo.callPrice(1000,30.14,15,0.01,0.332,1.0)
  val putPrice = MonteCarlo.putPrice(1000,30.14,45,0.01,0.332,1.0)
  print(s"call price: $callPrice put price: $putPrice")  

}
