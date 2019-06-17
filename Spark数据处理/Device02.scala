package main.scala

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.beans.BeanProperty

/**
  *
  * @author yangzheng10
  * @version $Id: Device02.java, v 0.1 2019年03月25日 12:58:25 yangzheng10 Exp $
  */
class Device02 extends Ordered[Device02] with Serializable {
  @BeanProperty
  var device_id: String = null
  @BeanProperty
  var vc_id: String = null
  @BeanProperty
  var vd_id: String = null
  // 指标
  @BeanProperty
  var net_droppedTx_summation: String = null
  @BeanProperty
  var net_packetsTx_summation: String = null
  @BeanProperty
  var virtualDisk_numberReadAveraged_average_scsi0_0: String = null
  @BeanProperty
  var virtualDisk_numberReadAveraged_average_scsi0_1: String = null
  @BeanProperty
  var virtualDisk_numberWriteAveraged_average_scsi0_0: String = null
  @BeanProperty
  var virtualDisk_numberWriteAveraged_average_scsi0_1: String = null
  @BeanProperty
  var virtualDisk_write_average: String = null
//  var disk_usage_none: String
  @BeanProperty
  var virtualDisk_read_average: String = null
//  var disk_usage_average: String
  @BeanProperty
  var datastore_maxTotalLatency_latest: String = null
  @BeanProperty
  var cpu_usage_average: String = null
  @BeanProperty
  var mem_usage_average: String = null
  @BeanProperty
  var net_usage_average: String = null
  @BeanProperty
  var net_usage_average_4000: String = null
  @BeanProperty
  var net_usage_average_4001: String = null
  @BeanProperty
  var net_usage_average_vmnic0: String = null
  @BeanProperty
  var net_usage_average_vmnic1: String = null
  @BeanProperty
  var net_usage_average_vmnic2: String = null
  @BeanProperty
  var net_usage_average_vmnic3: String = null
  @BeanProperty
  var net_usage_average_vmnic4: String = null
  @BeanProperty
  var net_usage_average_vmnic5: String = null
  @BeanProperty
  var net_packetsRx_summation: String = null
  @BeanProperty
  var net_droppedRx_summation: String = null
  @BeanProperty
  var net_bytesRx_average: String = null
  @BeanProperty
  var net_bytesTx_average: String = null
  // end
  @BeanProperty
  var time: String = null
  @BeanProperty
  var date: String = null

  override def toString=
    s"$device_id," +
      s"$vc_id," +
      s"$vd_id," +
      s"$net_droppedTx_summation," +
      s"$net_packetsTx_summation," +
      s"$virtualDisk_numberReadAveraged_average_scsi0_0," + //f
      s"$virtualDisk_numberReadAveraged_average_scsi0_1," +
      s"$virtualDisk_numberWriteAveraged_average_scsi0_0," +
      s"$virtualDisk_numberWriteAveraged_average_scsi0_1," +
      s"$virtualDisk_write_average," +
      s"$virtualDisk_read_average," + // flag
      s"$datastore_maxTotalLatency_latest," +
      s"$cpu_usage_average," +
      s"$mem_usage_average," +
      s"$net_usage_average," +
      s"$net_usage_average_4000," +
      s"$net_usage_average_4001," +
      s"$net_usage_average_vmnic0," +
      s"$net_usage_average_vmnic1," +
      s"$net_usage_average_vmnic2," +
      s"$net_usage_average_vmnic3," +
      s"$net_usage_average_vmnic4," +
      s"$net_usage_average_vmnic5," +
      s"$net_packetsRx_summation," +
      s"$net_droppedRx_summation," +
      s"$net_bytesRx_average," +
      s"$net_bytesTx_average," +
      s"$time," +
      s"$date"

  override def compare(that: Device02): Int = {
    // 先比较日期，日期相等再比较时间
    if (this.date == that.date) {
      compareTime(this.time, that.time)
    } else {
      compareDate(this.date, that.date)
    }
  }

  /**
    * 比较日期
    * @param date1
    * @param date2
    * @return
    */
  def compareDate(date1: String, date2: String): Int = {
    var days = 0
    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val startDate = simpleDateFormat.parse(date1)
    val endDate = simpleDateFormat.parse(date2)
    val startCalendar = Calendar.getInstance
    startCalendar.setTime(startDate)
    val endCalendar = Calendar.getInstance
    endCalendar.setTime(endDate)
    //计算两个日期相差的天数
    //startCalendar.getTime().getTime()返回long毫秒数形式,毫秒转为秒所以除以1000
    //1天=24小时，1小时=60分，1分=60秒，所以两个时间的差再除以60 * 60 * 24换算成天的形式
    days = ((startCalendar.getTime.getTime / 1000).toInt - (endCalendar.getTime.getTime / 1000).toInt) / (60 * 60 * 24)
    return days
  }


  /**
    * 比较时间
    * @param time1
    * @param time2
    * @return
    */
  def compareTime(time1: String, time2: String): Int = {
    val df = new SimpleDateFormat("HH:mm:ss")
    val dt1 = df.parse(time1.split(" ")(1));
    val dt2 = df.parse(time2.split(" ")(1));
    if(dt1.getTime()>dt2.getTime()) 1
    else -1
  }
}
