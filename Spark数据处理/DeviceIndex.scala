package main.scala

import scala.beans.BeanProperty

/**
  *
  * @author yangzheng10
  * @version $Id: Index.java, v 0.1 2019年03月25日 12:58:25 yangzheng10 Exp $
  */
class DeviceIndex extends Serializable {
  @BeanProperty
  var net_droppedTx_summation: Float = 0
  @BeanProperty
  var net_packetsTx_summation: Float = 0
  @BeanProperty
  var virtualDisk_numberReadAveraged_average_scsi0_0: Float = 0
  @BeanProperty
  var virtualDisk_numberReadAveraged_average_scsi0_1: Float = 0
  @BeanProperty
  var virtualDisk_numberWriteAveraged_average_scsi0_0: Float = 0
  @BeanProperty
  var virtualDisk_numberWriteAveraged_average_scsi0_1: Float = 0
  @BeanProperty
  var virtualDisk_write_average: Float = 0
  @BeanProperty
  var virtualDisk_read_average: Float = 0
  @BeanProperty
  var datastore_maxTotalLatency_latest: Float = 0
  @BeanProperty
  var cpu_usage_average: Float = 0
  @BeanProperty
  var mem_usage_average: Float = 0
  @BeanProperty
  var net_usage_average: Float = 0
  @BeanProperty
  var net_usage_average_4000: Float = 0
  @BeanProperty
  var net_usage_average_4001: Float = 0
  @BeanProperty
  var net_usage_average_vmnic0: Float = 0
  @BeanProperty
  var net_usage_average_vmnic1: Float = 0
  @BeanProperty
  var net_usage_average_vmnic2: Float = 0
  @BeanProperty
  var net_usage_average_vmnic3: Float = 0
  @BeanProperty
  var net_usage_average_vmnic4: Float = 0
  @BeanProperty
  var net_usage_average_vmnic5: Float = 0
  @BeanProperty
  var net_packetsRx_summation: Float = 0
  @BeanProperty
  var net_droppedRx_summation: Float = 0
  @BeanProperty
  var net_bytesRx_average: Float = 0
  @BeanProperty
  var net_bytesTx_average: Float = 0

  override def toString=
    s"$net_droppedTx_summation," +
      s"$net_packetsTx_summation," +
      s"$virtualDisk_numberReadAveraged_average_scsi0_0," +
      s"$virtualDisk_numberReadAveraged_average_scsi0_1," +
      s"$virtualDisk_numberWriteAveraged_average_scsi0_0," +
      s"$virtualDisk_numberWriteAveraged_average_scsi0_1," +
      s"$virtualDisk_write_average," +
      s"$virtualDisk_read_average," +
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
      s"$net_bytesTx_average,"

  def set(
    net_droppedTx_summation                         : Float,
    net_packetsTx_summation                         : Float,
    virtualDisk_numberReadAveraged_average_scsi0_0  : Float,
    virtualDisk_numberReadAveraged_average_scsi0_1  : Float,
    virtualDisk_numberWriteAveraged_average_scsi0_0 : Float,
    virtualDisk_numberWriteAveraged_average_scsi0_1 : Float,
    virtualDisk_write_average                       : Float,
    virtualDisk_read_average                        : Float,
    datastore_maxTotalLatency_latest                : Float,
    cpu_usage_average                               : Float,
    mem_usage_average                               : Float,
    net_usage_average                               : Float,
    net_usage_average_4000                          : Float,
    net_usage_average_4001                          : Float,
    net_usage_average_vmnic0                        : Float,
    net_usage_average_vmnic1                        : Float,
    net_usage_average_vmnic2                        : Float,
    net_usage_average_vmnic3                        : Float,
    net_usage_average_vmnic4                        : Float,
    net_usage_average_vmnic5                        : Float,
    net_packetsRx_summation                         : Float,
    net_droppedRx_summation                         : Float,
    net_bytesRx_average                             : Float,
    net_bytesTx_average                             : Float
         ): Unit = {
    this.net_droppedTx_summation                         = net_droppedTx_summation
    this.net_packetsTx_summation                         = net_packetsTx_summation
    this.virtualDisk_numberReadAveraged_average_scsi0_0  = virtualDisk_numberReadAveraged_average_scsi0_0
    this.virtualDisk_numberReadAveraged_average_scsi0_1  = virtualDisk_numberReadAveraged_average_scsi0_1
    this.virtualDisk_numberWriteAveraged_average_scsi0_0 = virtualDisk_numberWriteAveraged_average_scsi0_0
    this.virtualDisk_numberWriteAveraged_average_scsi0_1 = virtualDisk_numberWriteAveraged_average_scsi0_1
    this.virtualDisk_write_average                       = virtualDisk_write_average
    this.virtualDisk_read_average                        = virtualDisk_read_average
    this.datastore_maxTotalLatency_latest                = datastore_maxTotalLatency_latest
    this.cpu_usage_average                               = cpu_usage_average
    this.mem_usage_average                               = mem_usage_average
    this.net_usage_average                               = net_usage_average
    this.net_usage_average_4000                          = net_usage_average_4000
    this.net_usage_average_4001                          = net_usage_average_4001
    this.net_usage_average_vmnic0                        = net_usage_average_vmnic0
    this.net_usage_average_vmnic1                        = net_usage_average_vmnic1
    this.net_usage_average_vmnic2                        = net_usage_average_vmnic2
    this.net_usage_average_vmnic3                        = net_usage_average_vmnic3
    this.net_usage_average_vmnic4                        = net_usage_average_vmnic4
    this.net_usage_average_vmnic5                        = net_usage_average_vmnic5
    this.net_packetsRx_summation                         = net_packetsRx_summation
    this.net_droppedRx_summation                         = net_droppedRx_summation
    this.net_bytesRx_average                             = net_bytesRx_average
    this.net_bytesTx_average                             = net_bytesTx_average
  }

}