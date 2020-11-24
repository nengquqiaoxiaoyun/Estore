package com.itcast.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodeFilter
 */
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
		
		final HttpServletRequest req = (HttpServletRequest)request;
		response.setContentType("text/html;charset=utf-8");
		//��ȡ����ķ�ʽ
		String method = req.getMethod();
		if("get".equalsIgnoreCase(method)){
			//get����Ĵ�����Ҫ��ԭ�еķ���������ǿ
			
			/**
			 * �Է���������ǿ��
			 * 	1.�̳�
			 * 	2.װ����ģʽ
			 * 		1.��װ���ߺ�װ����Ҫʵ��һ���Ľӿ�
			 * 		2.��װ����Ҫ��Ϊһ���������ݸ�װ����
			 * 		3.������Ҫ��ǿ�ķ�����ǿ������
			 *  3.��̬����
			 */
			//��ȡԭ��request����ǿ����
			EncodeRequest encodeRequest = new EncodeRequest(req);
			
			
			
			//����ǿ���request���ݸ�servlet
			chain.doFilter(encodeRequest, response);
		}else if("post".equalsIgnoreCase(method)){
			//�����post����
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
