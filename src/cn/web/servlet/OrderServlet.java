package cn.web.servlet;

import cn.web.entity.Order;
import cn.web.entity.PCD;
import cn.web.entity.User;
import cn.web.service.CartService;
import cn.web.service.OrderService;
import cn.web.service.PCDService;
import cn.web.service.impl.CartServiceImpl;
import cn.web.service.impl.OrderServiceImpl;
import cn.web.service.impl.PCDServiceImpl;
import cn.web.utils.UUIDUtils;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/servlet/order")
public class OrderServlet extends BaseServlet {
    private PCDService pcdService = new PCDServiceImpl();
    private OrderService orderService = new OrderServiceImpl();


    public void listPCDByPid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pid = request.getParameter("pid");
        List<PCD> pcds = pcdService.listPCDbyPid(pid);
        String json = JSON.toJSONString(pcds);
        response.getWriter().write(json);
    }

    public void listOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            String referer = request.getHeader("referer");
            request.getSession().setAttribute("url", referer);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int id = user.getId();
        List<Order> orders = orderService.listOrders(id);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/orders.jsp").forward(request, response);
    }

    /**
     * 提交订单的流程：
     * 1. 保存订单信息
     * 2. 跟新库存
     * 3. 删除购物车
     * 4. 保存订单详情
     */
    public void subOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            String referer = request.getHeader("referer");
            request.getSession().setAttribute("url", referer);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }


        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String address = request.getParameter("detailAddress");
        String zipcode = request.getParameter("zipcode");
        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");

        String detailAddress = province + "省" + city + "市" + district +
                "区/县" + address + "邮编：" + zipcode + ",收件人：" + name + ",电话号码：" + telephone;


        Order order = new Order();
        order.setId(UUIDUtils.getUUID());
        order.setAddress(detailAddress);
        order.setCreatetime(new Date());
        order.setStatus(-1);
        order.setUid(user.getId());
        orderService.subOrder(order);

        response.sendRedirect(request.getContextPath() + "/servlet/order?methodName=listOrders");

    }

    public void orderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            String referer = request.getHeader("referer");
            request.getSession().setAttribute("url", referer);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        int id = user.getId();
        String oid = request.getParameter("oid");
        Order orderDetail = orderService.getOrderDetail(id, oid);
        request.setAttribute("orderDetail", orderDetail);

        request.getRequestDispatcher("/orders_detail.jsp").forward(request, response);
    }

    /**
     * 取消订单的步骤
     * 1. 删除中间表的信息（orderitems）
     * 2. 还原商品数量
     * 3. 删除订单表
     */
    public void cancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            String referer = request.getHeader("referer");
            request.getSession().setAttribute("url", referer);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        String oid = request.getParameter("oid");
        orderService.cancel(oid);

        response.sendRedirect("order?methodName=listOrders");
    }

}
