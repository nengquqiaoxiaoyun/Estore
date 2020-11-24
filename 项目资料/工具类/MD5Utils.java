package com.itcast.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static String getPassword(String pwd){
		//���ܶ�������ȡ���ܵĶ���
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			//�����ݽ��м���,���ܵĶ����Ѿ����
			byte[] bs = digest.digest(pwd.getBytes());
			
			//�Լ��ܺ�Ľ���������Ż��������ܺ�Ľ������ת����������Ȼ��ת����16���Ƹ�ʽ
			String password = "";
			for (byte b : bs) {
				//ת��������
				//b����byte   ���� & 255 int�������ݣ��Զ���������
				//b��1111 1001
				//bת����int����֮��0000 0000 0000 0000 0000 0000 1111 1001�����λ���0֮��ת��������
				int temp = b & 255;
				//ת����16���Ƹ�ʽ
				String hexString = Integer.toHexString(temp);
				if(temp >=0 && temp < 16){
					password = password +"0"+ hexString;
				}else{
					password = password + hexString;
				}
				//��������ܵĽ����90150983cd24fb0d6963f7d28e17f72
				//��������ܵĽ����900150983cd24fb0d6963f7d28e17f72
				//mysql���ܽ���� 900150983cd24fb0d6963f7d28e17f72
			}
			
			return password;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		} 
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Utils.getPassword("abc"));
	}
}