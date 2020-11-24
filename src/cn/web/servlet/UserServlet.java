package cn.web.servlet;

import cn.web.service.UserService;
import cn.web.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
@WebServlet(name = "UserServlet", urlPatterns = "/servlet/user")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 判断用户是否注册
     */
    public void checkPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String phone = request.getParameter("phone");
        String jsonStr = userService.findPhone(phone);
        System.out.println(jsonStr);
        response.getWriter().write(jsonStr);

    }
}
