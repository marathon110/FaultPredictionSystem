package main.scala

import org.apache.spark.sql.SparkSession
/**
  *
  * @author yangzheng10
  * @version $Id: DataTransform.java, v 0.1 2019年03月23日 19:38:47 yangzheng10 Exp $
  *
  * Desc: 行转列
  */
object DataTransformOneDay {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .master("local[1]")
      .appName(this.getClass.getName)
      .getOrCreate()

    if (args.length < 1) {
      System.err.println("缺少输入输出路径参数")
      System.exit(1)
    }

    val input = args(0)
    val output = args(1)

//    val lines = spark.read.textFile("/home/zeno/workspace/GraduationProject/data/one_day").rdd
    val lines = spark.read.textFile(input).rdd

    lines
      .map(line => {
        val fields = line.split("\\|")
        Device01(fields(0),fields(1),fields(2),fields(3),fields(5),fields(6),fields(7))
      })
        .map(line => {
          (line.time + "_" + line.device_id, line)
        })
        .groupByKey()
        .map(t => {
          val group = t._2
          var device02 = new Device02OneDay
          var i = 0
          group.foreach(l => {
            if (i == 0) {
              device02.setDevice_id(l.device_id)
              device02.setDate(l.date)
              i += 1
            }

            val v = l.index_value.toDouble

            l.index_name match {
              case "cpu.usage.average" => device02.setCpu_usage_average(v)
              case "datastore.maxTotalLatency.latest" => {}
              case "mem.usage.average" => device02.setMem_usage_average(v)
              case "net.bytesRx.average" => device02.setNet_bytesRx_average(v)
              case "net.bytesTx.average" => device02.setNet_bytesTx_average(v)
              case "net.droppedRx.summation" => {}
              case "net.droppedTx.summation" => {}
              case "net.packetsRx.summation" => device02.setNet_packetsRx_summation(v)
              case "net.packetsTx.summation" => device02.setNet_packetsTx_summation(v)
              case "net.usage.average" => {
                val value = device02.getNet_usage_average()

                val res = value + v
                device02.setNet_usage_average(res)
              }
              case "virtualDisk.numberReadAveraged.average" => {}
              case "virtualDisk.numberWriteAveraged.average" => {}
              case "virtualDisk.read.average" => {}
              case "virtualDisk.write.average" => {}
            }
          })
          (device02.date, device02)
        })
      .groupByKey()
      .map(t => {
        val group = t._2
        var device02Sum = new Device02OneDay
        device02Sum.setDate(t._1)
        device02Sum.setNet_packetsTx_summation(0)
        device02Sum.setCpu_usage_average(0)
        device02Sum.setMem_usage_average(0)
        device02Sum.setNet_usage_average(0)
        device02Sum.setNet_packetsRx_summation(0)
        device02Sum.setNet_bytesRx_average(0)
        device02Sum.setNet_bytesTx_average(0)

        var i = 0
        group.foreach(l => {
          if (i == 0) {
            device02Sum.setDevice_id(l.getDevice_id)
            i += 1
          }
          device02Sum.setNet_packetsTx_summation(l.getNet_packetsTx_summation + device02Sum.getNet_packetsTx_summation)
          device02Sum.setCpu_usage_average(l.getCpu_usage_average + device02Sum.getCpu_usage_average)
          device02Sum.setMem_usage_average(l.getMem_usage_average + device02Sum.getMem_usage_average)
          device02Sum.setNet_usage_average(l.getNet_usage_average + device02Sum.getNet_usage_average)
          device02Sum.setNet_packetsRx_summation(l.getNet_packetsRx_summation + device02Sum.getNet_packetsRx_summation)
          device02Sum.setNet_bytesRx_average(l.getNet_bytesRx_average + device02Sum.getNet_bytesRx_average)
          device02Sum.setNet_bytesTx_average(l.getNet_bytesTx_average + device02Sum.getNet_bytesTx_average)
        })
        device02Sum
      })
//        .foreach(println)
//        .saveAsTextFile("/home/zeno/workspace/GraduationProject/data/one_day_out")
      .saveAsTextFile(output)

    spark.close()
  }

}


/**
  * 将一个指标的多个tag与指标合并，作为多个指标
  * @param device_id
  * @param vc_id
  * @param vd_id
  * @param index_name
  * @param index_value
  * @param time
  * @param date
  */
case class Device01(device_id: String,
                    vc_id: String,
                    vd_id: String,
                    index_name: String,
                    index_value: String,
                    time: String,
                    date: String)

/**
  * 原始日志
  * @param device_id
  * @param vc_id
  * @param vd_id
  * @param index_name
  * @param index_tag
  * @param index_value
  * @param time
  * @param date
  */
case class Device00(device_id: String,
                  vc_id: String,
                  vd_id: String,
                  index_name: String,
                  index_tag: String,
                  index_value: String,
                  time: String,
                  date: String)