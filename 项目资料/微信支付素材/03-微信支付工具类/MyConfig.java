package com.itheima.web;

import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class MyConfig implements WXPayConfig {

	//��ȡ App ID����ҵ�����ں�Id��
	@Override
	public String getAppID() {
		return "wx8397f8696b538317";
	}
	 //��ȡ API ��Կ
	@Override
	public String getKey() {
		return "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";
	}
	//��ȡ Mch ID���̻��˺ţ�
	@Override
	public String getMchID() {
		return "1473426802";
	}

	//��ȡ�̻�֤�����ݣ��������ﲻ��Ҫ֤�飩
	@Override
	public InputStream getCertStream() {
		// TODO Auto-generated method stub
		return null;
	}

	//HTTP(S) ���ӳ�ʱʱ�䣬��λ����
	@Override
	public int getHttpConnectTimeoutMs() {
		// TODO Auto-generated method stub
		return 8000;
	}

	//HTTP(S) �����ݳ�ʱʱ�䣬��λ����
	@Override
	public int getHttpReadTimeoutMs() {
		return 10000;
	}

}
