package dao;

import bean.DeviceBean;
import utils.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 面向接口编程
 */
public class DeviceDAO {

    public List<DeviceBean> queryNet_packetsTx_summation() {
        List<DeviceBean> list = new ArrayList<DeviceBean>();
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            connection = MySQLUtils.getConnection();
            String sql = "select * from device_2400_day";
            psmt = connection.prepareStatement(sql);

            rs = psmt.executeQuery();

            DeviceBean domain = null;
            while(rs.next()) {
                domain = new DeviceBean();

                domain.setDate_str(rs.getString("date_str"));
                domain.setNet_droppedTx_summation(rs.getString("net_droppedTx_summation"));
                domain.setNet_packetsTx_summation(rs.getString("net_packetsTx_summation"));
                domain.setVirtualDisk_numberReadAveraged_average(rs.getString("virtualDisk_numberReadAveraged_average"));
                domain.setVirtualDisk_numberWriteAveraged_average(rs.getString("virtualDisk_numberWriteAveraged_average"));
                domain.setVirtualDisk_write_average(rs.getString("virtualDisk_write_average"));
                domain.setVirtualDisk_read_average(rs.getString("virtualDisk_read_average"));
                domain.setDatastore_maxTotalLatency_latest(rs.getString("datastore_maxTotalLatency_latest"));
                domain.setCpu_usage_average(rs.getString("cpu_usage_average"));
                domain.setMem_usage_average(rs.getString("mem_usage_average"));
                domain.setNet_usage_average(rs.getString("net_usage_average"));
                domain.setNet_packetsRx_summation(rs.getString("net_packetsRx_summation"));
                domain.setNet_droppedRx_summation(rs.getString("net_droppedRx_summation"));
                domain.setNet_bytesRx_average(rs.getString("net_bytesRx_average"));
                domain.setNet_bytesTx_average(rs.getString("net_bytesTx_average"));

                list.add(domain);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.release(connection, psmt, rs);
        }
        return list;
    }

    public static void main(String[] args) {
        DeviceDAO dao = new DeviceDAO();
        List<DeviceBean> list = dao.queryNet_packetsTx_summation();
        for(DeviceBean result: list) {
            System.out.println(result.getDate_str() + " , " + result.getNet_packetsTx_summation());
        }
    }
}
