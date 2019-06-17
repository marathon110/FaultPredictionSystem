package servlet;


import bean.DeviceBean;
import bean.LiftCycleBean;
import dao.Device7361DAO;
import dao.Device7363DAO;
import dao.DeviceDAO;
import dao.LiftCycleDAO;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WebServlet extends HttpServlet {
    private LiftCycleDAO liftCycleDao;
    private DeviceDAO deviceDAO;
    private Device7361DAO device7361DAO;
    private Device7363DAO device7363DAO;

    @Override
    public void init() throws ServletException {
        liftCycleDao = new LiftCycleDAO();
        deviceDAO = new DeviceDAO();
        device7361DAO = new Device7361DAO();
        device7363DAO = new Device7363DAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getParameter("opt");

        if (opt.equals("liftcycle")) {
            liftCycle(resp);
        } else if (opt.equals("device")) {
            device(resp);
        } else if (opt.equals("device7361")) {
            device7361(resp);
        } else if (opt.equals("device7363")) {
            device7363(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void device(HttpServletResponse resp) throws IOException  {
        List<DeviceBean> results =  deviceDAO.queryNet_packetsTx_summation();
        JSONArray json = JSONArray.fromObject(results);

        resp.setContentType("text/html;charset=utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();
    }


    protected void device7361(HttpServletResponse resp) throws IOException  {
        List<DeviceBean> results =  device7361DAO.queryNet_packetsTx_summation();
        JSONArray json = JSONArray.fromObject(results);

        resp.setContentType("text/html;charset=utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();
    }


    protected void device7363(HttpServletResponse resp) throws IOException  {
        List<DeviceBean> results =  device7363DAO.queryNet_packetsTx_summation();
        JSONArray json = JSONArray.fromObject(results);

        resp.setContentType("text/html;charset=utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();
    }

    protected void liftCycle(HttpServletResponse resp) throws IOException  {
        List<LiftCycleBean> results =  liftCycleDao.query();
        JSONArray json = JSONArray.fromObject(results);

        resp.setContentType("text/html;charset=utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();

        // resp.sendRedirect("index2.jsp");
    }
}
