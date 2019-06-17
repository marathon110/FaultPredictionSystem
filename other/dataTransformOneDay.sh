#!/bin/bash

# cd $SPARK_HOME/bin
# --master spark://master:7077 \

/apps/spark/bin/spark-submit \
--master spark://master:7077 \
--class main.scala.DataTransformOneDay \
--name DataTransformOneDay \
--executor-memory 2048M \
--driver-memory 2048M \
./GraduationProject-1.0.0.jar \
./data/one_day/input \
./data/one_day/output
