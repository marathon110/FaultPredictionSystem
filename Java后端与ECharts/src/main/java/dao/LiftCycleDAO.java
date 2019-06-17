package dao;



import bean.LiftCycleBean;
import utils.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 面向接口编程
 */
public class LiftCycleDAO {
    /**
     * 按照地市一天中访问的次数统计：
     * @param day
     */
    public List<LiftCycleBean> query() {
        List<LiftCycleBean> list = new ArrayList<LiftCycleBean>();
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            connection = MySQLUtils.getConnection();
//            String sql = "select city,sum(times) times  from  day_class_city_access_topn_stat where day =? " +
//                    "and city != '全球' group by city order by sum(times) desc limit 5";

            String sql = "select device_id, date, leftcycle from predict";
            psmt = connection.prepareStatement(sql);
//            psmt.setString(1, day);

            rs = psmt.executeQuery();

            LiftCycleBean domain = null;
            while(rs.next()) {
                domain = new LiftCycleBean();
                domain.setDevice_id(rs.getString("device_id"));
                domain.setDate(rs.getString("date"));
                domain.setLeftcycle(rs.getString("leftcycle"));
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
        LiftCycleDAO dao = new LiftCycleDAO();
        List<LiftCycleBean> list = dao.query();
        for(LiftCycleBean result: list) {
            System.out.println(result.getDevice_id() + ", " + result.getDate() + ", " + result.getLeftcycle());
        }
    }
}
