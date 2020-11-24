package com.itcast.filter;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeRequest extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	public EncodeRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		//��ȡԭ�з����ķ���ֵ
		String parameter = request.getParameter(name);
		//���ַ��������ع�
		String value = null;
		try {
			if(parameter!=null){
				value = new String(parameter.getBytes("iso-8859-1"),"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		//��ȡԭ�ж���ķ���ֵ
		String[] params = request.getParameterValues(name);
		if(params!=null){
			String[] newParams = new String[params.length];
			try {
				for(int i=0;i<params.length;i++){
					newParams[i] = new String(params[i].getBytes("iso-8859-1"),"utf-8");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newParams;
		}else{
			return null;
		}
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		//��ȡԭ�ж���ķ���ֵ
		Map<String, String[]> parameterMap = request.getParameterMap();
		if(parameterMap!=null){
			Map<String, String[]> newParameterMap = new HashMap<String, String[]>();
			try {
				for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
					String key = entry.getKey();
					String[] params = entry.getValue();
					String[] newParams = new String[params.length];
					for(int i=0;i<params.length;i++){
						newParams[i] = new String(params[i].getBytes("iso-8859-1"),"utf-8");
					}
					//���������������´�ŵ��µ�map��
					newParameterMap.put(key, newParams);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return newParameterMap;
		}else{
			return null;
		}
	}

}
