package cn.web.servlet;

import cn.web.entity.User;
import cn.web.service.UserService;
import cn.web.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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
        response.getWriter().write(jsonStr);
    }

    /**
     * 注册功能
     *
     * @param request
     * @param response
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        userService.register(user);
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        User user = userService.findUser(phone, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30);
            session.setAttribute("user", user);

            if ("on".equals(remember)) {
                Cookie cookie = new Cookie("phone", phone);
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(60 * 30);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("phone", phone);
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            request.setAttribute("msg", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }
}
