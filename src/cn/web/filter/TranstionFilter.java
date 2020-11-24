package cn.web.filter;

import cn.web.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
@WebFilter(urlPatterns = "/servlet/*")
public class TranstionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter执行了");
        try {
            chain.doFilter(request, response);
            JdbcUtils.commit();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollback();
        }
    }

    @Override
    public void destroy() {

    }
}
