package cn.web.servlet;

import cn.web.entity.Cart;
import cn.web.entity.User;
import cn.web.service.CartService;
import cn.web.service.impl.CartServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
@WebServlet(name = "CartServlet", urlPatterns = "/servlet/cart")
public class CartServlet extends BaseServlet {

    private CartService cartService = new CartServiceImpl();

    public void addGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 还未登录 先登录
        if (user == null) {
            String referer = request.getHeader("referer");
            request.getSession().setAttribute("url", referer);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String goodId = request.getParameter("goodId");
        System.out.println("goodId: " + goodId);
        int id = user.getId();
        cartService.updateCart(id, goodId);

        response.sendRedirect(request.getContextPath() + "/buyorcart.jsp");

    }

    /**
     * 查看购物车
     */
    public void listCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            int id = user.getId();
            List<Cart> carts = cartService.listCartByUid(id);
            request.setAttribute("carts", carts);
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } else {
            String referer = request.getHeader("referer");
            request.getSession().setAttribute("url", referer);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

    }

    public void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputVal = request.getParameter("inputVal");
        String gid = request.getParameter("gid");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int id = user.getId();
            Cart cart = new Cart();
            cart.setGid(Integer.valueOf(gid));
            cart.setUid(id);
            cart.setBuynum(Integer.valueOf(inputVal));
            String json = cartService.updateCartNum(cart);
            response.getWriter().write(json);

        } else {
            String referer = request.getHeader("referer");
            request.getSession().setAttribute("url", referer);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

    }

}
