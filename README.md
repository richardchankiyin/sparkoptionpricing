# sparkoptionpricing
using monte carlo method to price option riding on spark map reduce

Requirement
-------------
Scala 2.11.7 or above
Spark 2.1.1 for grid computing

Main Program: com.richard.optionprice.SparkApp
Monte Carlo functions: com.richard.optionprice.MonteCarlo

Spark master script: /sbin/start-master.sh
Spark worker script: SPARK_WORKER_INSTANCES=4 SPARK_WORKER_CORES=1 ./start-slave.sh spark://richard-laptop-ubuntu:7077
Spark submit script: ./spark-submit --class com.richard.optionprice.SparkApp --master spark://richard-laptop-ubuntu:7077 /home/richard/spark_optionprice/workspace/target/scala-2.11/sparkoptionprice_2.11-0.1.jar --executor-memory 12G 100
