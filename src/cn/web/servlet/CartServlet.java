package cn.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
@WebServlet(name = "CartServlet", urlPatterns = "/servlet/cart")
public class CartServlet extends BaseServlet {

    public void addGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
