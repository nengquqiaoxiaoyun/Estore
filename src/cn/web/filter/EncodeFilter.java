package cn.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet Filter implementation class EncodeFilter
 */
@WebFilter(urlPatterns = "/")
public class EncodeFilter implements Filter {

    /**
     * Default constructor.
     */
    public EncodeFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        response.setContentType("text/html;charset=utf-8");
        //获取请求的方式
        String method = req.getMethod();
        if ("post".equalsIgnoreCase(method)) {
            //如果是post请求
            request.setCharacterEncoding("utf-8");
            chain.doFilter(request, response);
        }

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
