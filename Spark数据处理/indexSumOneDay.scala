package main.scala

import org.apache.spark.sql.SparkSession

/**
  *
  * @author yangzheng10
  * @version $Id: indexSumOneDay.java, v 0.1 2019年04月19日 18:57:52 yangzheng10 Exp $
  *
  * 现在手头有两个月的数据，如果以天为粒度的话，样本数只有60个左右，太少了。
  */
object indexSumOneDay {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\Soft")

    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName(this.getClass.getName)
      .getOrCreate()

    val lines = spark.read.textFile("D:\\Materials\\毕设\\data\\output\\group_device_out\\device_2400").rdd

    lines.map(l => {
      val fields = l.split("\t")(1).split(",")
      val index = new DeviceIndex
      val a = new Array[Float](24)
//      val a = Array[Float](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

      for (i <- 3 until fields.length - 2) {
        if (fields(i).equals("null")) {
          a(i-3) = 0
        } else {
          a(i-3) = fields(i).toFloat
        }
      }

      index.set(a(0), a(1), a(2), a(3), a(4), a(5), a(6), a(7), a(8), a(9), a(10), a(11),
        a(12), a(13), a(14), a(15), a(16), a(17), a(18), a(19), a(20), a(21), a(22), a(23))

      (fields(fields.length-1), index)
    })
      .groupByKey()
      .map(t => {
        val indexs = t._2
        val index_sum = new DeviceIndex
        val a = Array[Float](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        indexs.foreach(index => {
          a(0) += index.getNet_droppedTx_summation
          a(1) += index.getNet_packetsTx_summation
          a(2) += index.getVirtualDisk_numberReadAveraged_average_scsi0_0
          a(3) += index.getVirtualDisk_numberReadAveraged_average_scsi0_1
          a(4) += index.getVirtualDisk_numberWriteAveraged_average_scsi0_0
          a(5) += index.getVirtualDisk_numberWriteAveraged_average_scsi0_1
          a(6) += index.getVirtualDisk_write_average
          a(7) += index.getVirtualDisk_read_average
          a(8) += index.getDatastore_maxTotalLatency_latest
          a(9) += index.getCpu_usage_average
          a(10) += index.getMem_usage_average
          a(11) += index.getNet_usage_average
          a(12) += index.getNet_usage_average_4000
          a(13) += index.getNet_usage_average_4001
          a(14) += index.getNet_usage_average_vmnic0
          a(15) += index.getNet_usage_average_vmnic1
          a(16) += index.getNet_usage_average_vmnic2
          a(17) += index.getNet_usage_average_vmnic3
          a(18) += index.getNet_usage_average_vmnic4
          a(19) += index.getNet_usage_average_vmnic5
          a(20) += index.getNet_packetsRx_summation
          a(21) += index.getNet_droppedRx_summation
          a(22) += index.getNet_bytesRx_average
          a(23) += index.getNet_bytesTx_average
        })

        index_sum.set(a(0), a(1), a(2), a(3), a(4), a(5), a(6), a(7), a(8), a(9), a(10), a(11),
          a(12), a(13), a(14), a(15), a(16), a(17), a(18), a(19), a(20), a(21), a(22), a(23))
        (t._1, index_sum)
      })
       .saveAsTextFile("D:\\Materials\\毕设\\data\\output\\index_sum_one_day\\device_2400")
//        .foreach(println(_))

    spark.close()
  }
}
