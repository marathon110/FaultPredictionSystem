package main.scala

import org.apache.spark.sql.SparkSession
/**
  *
  * @author yangzheng10
  * @version $Id: DataTransform.java, v 0.1 2019年03月23日 19:38:47 yangzheng10 Exp $
  *
  * Desc: 行转列
  */
object DataTransformV2 {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "D:\\Soft")

    val spark = SparkSession
      .builder()
      .master("local[1]")
      .appName(this.getClass.getName)
      .getOrCreate()

    val lines = spark.read.textFile("D:\\Materials\\毕设\\data\\input").rdd

    lines
      .map(line => {
        val fields = line.split("\\|")
        val device0 = Device00(fields(0), fields(1),fields(2),fields(3),fields(4),fields(5),fields(6),fields(7))
        Device01(fields(0),fields(1),fields(2),fields(3),fields(5),fields(6),fields(7))
      })
        .map(line => {
          (line.time + "_" + line.device_id, line)
        })
        .groupByKey()
        .map(t => {
          val group = t._2
          var device02 = new Device02V2
          var i = 0
          group.foreach(l => {
            if (i == 0) {
              device02.setDevice_id(l.device_id)
              device02.setVc_id(l.vc_id)
              device02.setVd_id(l.vd_id)
              device02.setTime(l.time)
              device02.setDate(l.date)
              i += 1
            }

            val v = l.index_value

            l.index_name match {
              case "cpu.usage.average" => device02.setCpu_usage_average(v)
              case "datastore.maxTotalLatency.latest" => device02.setDatastore_maxTotalLatency_latest(v)
              case "mem.usage.average" => device02.setMem_usage_average(v)
              case "net.bytesRx.average" => device02.setNet_bytesRx_average(v)
              case "net.bytesTx.average" => device02.setNet_bytesTx_average(v)
              case "net.droppedRx.summation" => device02.setNet_droppedRx_summation(v)
              case "net.droppedTx.summation" => device02.setNet_droppedTx_summation(v)
              case "net.packetsRx.summation" => device02.setNet_packetsRx_summation(v)
              case "net.packetsTx.summation" => device02.setNet_packetsTx_summation(v)
              case "net.usage.average" => {
                val value_str = device02.getNet_usage_average()
                if (value_str == null) {
                  device02.setNet_usage_average(v)
                } else {
                  val res = value_str.toFloat + v.toFloat
                  device02.setNet_usage_average(res.toString)
                }
              }
              case "virtualDisk.numberReadAveraged.average" => {
                val value_str = device02.getVirtualDisk_numberReadAveraged_average()
                if (value_str == null) {
                  device02.setVirtualDisk_numberReadAveraged_average(v)
                } else {
                  val res = value_str.toFloat + v.toFloat
                  device02.setVirtualDisk_numberReadAveraged_average(res.toString)
                }
              }
              case "virtualDisk.numberWriteAveraged.average" => {
                val value_str = device02.getVirtualDisk_numberWriteAveraged_average()
                if (value_str == null) {
                  device02.setVirtualDisk_numberWriteAveraged_average(v)
                } else {
                  val res = value_str.toFloat + v.toFloat
                  device02.setVirtualDisk_numberWriteAveraged_average(res.toString)
                }
              }
              case "virtualDisk.read.average" => device02.setVirtualDisk_read_average(v)
              case "virtualDisk.write.average" => device02.setVirtualDisk_write_average(v)
            }
          })
          device02
        })
      .sortBy(l => l)
//        .foreach(println)
        .saveAsTextFile("D:\\Materials\\毕设\\data\\output\\data_transform_outV2")

    spark.close()
  }

}


///**
//  * 将一个指标的多个tag与指标合并，作为多个指标
//  * @param device_id
//  * @param vc_id
//  * @param vd_id
//  * @param index_name
//  * @param index_value
//  * @param time
//  * @param date
//  */
//case class Device01(device_id: String,
//                    vc_id: String,
//                    vd_id: String,
//                    index_name: String,
//                    index_value: String,
//                    time: String,
//                    date: String)
//
///**
//  * 原始日志
//  * @param device_id
//  * @param vc_id
//  * @param vd_id
//  * @param index_name
//  * @param index_tag
//  * @param index_value
//  * @param time
//  * @param date
//  */
//case class Device00(device_id: String,
//                  vc_id: String,
//                  vd_id: String,
//                  index_name: String,
//                  index_tag: String,
//                  index_value: String,
//                  time: String,
//                  date: String)