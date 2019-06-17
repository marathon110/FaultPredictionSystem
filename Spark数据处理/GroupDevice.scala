package main.scala

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.spark.sql.SparkSession

/**
  *
  * @author yangzheng10
  * @version $Id: GroupDevice.java, v 0.1 2019年04月19日 16:42:04 yangzheng10 Exp $
  */
object GroupDevice {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\Soft")

    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName(this.getClass.getName)
      .getOrCreate()

    val lines = spark.read.textFile("D:\\Materials\\毕设\\data\\input").rdd

    lines
      .map(line => {
        val fields = line.split("\\|")
        val device0 = Device00(fields(0), fields(1),fields(2),fields(3),fields(4),fields(5),fields(6),fields(7))
        if (device0.index_tag == "\\N") {
          Device01(fields(0),fields(1),fields(2),fields(3),fields(5),fields(6),fields(7))
        } else {
          Device01(fields(0),fields(1),fields(2),fields(3)+"_"+fields(4),fields(5),fields(6),fields(7))
        }
      })
      .map(line => {
        (line.time + "_" + line.device_id, line)
      })
      .groupByKey()
      .map(t => {
        val group = t._2
        var device02 = new Device02
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
            case "net.usage.average" => device02.setNet_usage_average(v)
            case "net.usage.average_4000" => device02.setNet_usage_average_4000(v)
            case "net.usage.average_4001" => device02.setNet_usage_average_4001(v)
            case "net.usage.average_vmnic0" => device02.setNet_usage_average_vmnic0(v)
            case "net.usage.average_vmnic1" => device02.setNet_usage_average_vmnic1(v)
            case "net.usage.average_vmnic2" => device02.setNet_usage_average_vmnic2(v)
            case "net.usage.average_vmnic3" => device02.setNet_usage_average_vmnic3(v)
            case "net.usage.average_vmnic4" => device02.setNet_usage_average_vmnic4(v)
            case "net.usage.average_vmnic5" => device02.setNet_usage_average_vmnic5(v)
            case "virtualDisk.numberReadAveraged.average_scsi0:0" =>
              device02.setVirtualDisk_numberReadAveraged_average_scsi0_0(v)
            case "virtualDisk.numberReadAveraged.average_scsi0:1" =>
              device02.setVirtualDisk_numberReadAveraged_average_scsi0_1(v)
            case "virtualDisk.numberWriteAveraged.average_scsi0:0" =>
              device02.setVirtualDisk_numberWriteAveraged_average_scsi0_0(v)
            case "virtualDisk.numberWriteAveraged.average_scsi0:1" =>
              device02.setVirtualDisk_numberWriteAveraged_average_scsi0_1(v)
            case "virtualDisk.read.average" => device02.setVirtualDisk_read_average(v)
            case "virtualDisk.write.average" => device02.setVirtualDisk_write_average(v)
          }
        })
        device02
      })
      .sortBy(l => l)
        .map(d => {
          (d.device_id, d.toString)
        })
        .saveAsHadoopFile("D:\\Materials\\毕设\\data\\output\\group_device_out",
          classOf[String],
          classOf[String],
          classOf[RDDMultipleTextOutputFormat[_, _]])
//        .groupByKey()
//        .saveAsTextFile("D:\\Materials\\毕设\\data\\output\\group_device_out")
      //        .foreach(println)
//      .saveAsTextFile("D:\\Materials\\毕设\\data\\output\\data_transform_out3")

    spark.close()


  }
}

class RDDMultipleTextOutputFormat[K, V]() extends MultipleTextOutputFormat[K, V]() {
  override def generateFileNameForKeyValue(key: K, value: V, name: String) : String = {
    ("device_"+key+"/"+name)
  }
}
