package main.scala

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.beans.BeanProperty

/**
  *
  * @author yangzheng10
  * @version $Id: Device02.java, v 0.1 2019年03月25日 12:58:25 yangzheng10 Exp $
  */
class Device02OneDay extends Ordered[Device02OneDay] with Serializable {
  @BeanProperty
  var device_id: String = null
  // 指标
  @BeanProperty
  var net_packetsTx_summation: Double = 0
  @BeanProperty
  var cpu_usage_average: Double = 0
  @BeanProperty
  var mem_usage_average: Double = 0
  @BeanProperty
  var net_usage_average: Double = 0
  @BeanProperty
  var net_packetsRx_summation: Double = 0
  @BeanProperty
  var net_bytesRx_average: Double = 0
  @BeanProperty
  var net_bytesTx_average: Double = 0
  // end
  @BeanProperty
  var date: String = null

//  override def toString=
//    s"$device_id," +
//      s"$date," +
//      s"$net_packetsTx_summation," +
//      s"$cpu_usage_average," +
//      s"$mem_usage_average," +
//      s"$net_usage_average," +
//      s"$net_packetsRx_summation," +
//      s"$net_bytesRx_average," +
//      s"$net_bytesTx_average"

  override def compare(that: Device02OneDay): Int = {
      compareDate(this.date, that.date)
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

  override def toString= {
    import java.text.DecimalFormat
    val df = new DecimalFormat("0.00")

//    date + "," + device_id + "," + df.format(net_packetsTx_summation) + "," + df.format(cpu_usage_average) + "," +
//      df.format(mem_usage_average) + "," +
//      df.format(net_usage_average) + "," + df.format(net_packetsRx_summation) + "," + df.format(net_bytesRx_average) +
//      "," + df.format(net_bytesTx_average)

    date + "," + device_id + "," + formatDouble(net_packetsTx_summation) + "," + formatDouble(cpu_usage_average) + "," +
      formatDouble(mem_usage_average) + "," +
      formatDouble(net_usage_average) + "," + formatDouble(net_packetsRx_summation) + "," + formatDouble(net_bytesRx_average) +
      "," + formatDouble(net_bytesTx_average)

//    date + "," + device_id + "," + net_packetsTx_summation + "," + cpu_usage_average + "," + mem_usage_average + "," +
//      net_usage_average + "," + net_packetsRx_summation + "," + net_bytesRx_average + "," + net_bytesTx_average
  }


  import java.text.NumberFormat

  def formatDouble(value: Double): String = {
    val format = NumberFormat.getInstance
    format.setMinimumFractionDigits(0)
    format.setMaximumFractionDigits(2)
    format.setGroupingUsed(false)
    var retValue = format.format(value)
    retValue = retValue.replaceAll(",", "")
    retValue
  }




}
