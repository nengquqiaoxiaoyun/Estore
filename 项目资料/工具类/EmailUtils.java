package com.itcast.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

	//�����ʼ�
	//mail:�ռ���   mailMsg:�ʼ�����  subject:�ʼ�����
	public static void sendMail(String mail,String mailMsg,String subject) throws AddressException, MessagingException{
		
		
		//1.����session���󣬽���java�������ʼ�������֮�������
		Properties pro = new Properties();
		
		pro.setProperty("mail.transport.protocol", "SMTP");//�������������Э��
		pro.setProperty("mail.host", "localhost");//���÷��ʼ��������������ַ
		pro.setProperty("mail.smtp.auth", "true");// ��֤��������˻���������
		//������֤��
		Authenticator auth = new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//��һ���������˻���		�ڶ�������������   �����˵��û���������
				return new PasswordAuthentication("zhangsan","123");
			}
			
		};
		
		//����һ�����������������ĻỰ����Session
		Session session = Session.getInstance(pro, auth);
		//2����һ����Ҫ���͵��ʼ�����
		Message message = new MimeMessage(session);//����һ��message�������������ʼ�����
		message.setFrom(new InternetAddress("zhangsan@itheima21.com"));//���÷�����
		message.setRecipient(RecipientType.TO, new InternetAddress(mail));//���÷�����ʽ�ͽ�����
		message.setSubject(subject);//���ñ���
		message.setContent(mailMsg, "text/html;charset=utf-8");//�����ʼ�����
		//3.ִ�з���
		Transport.send(message);
		
	}
	
	public static void main(String[] args) throws AddressException, MessagingException {
		sendMail("huangxingcheng@itcast.cn", "���տ���", "ף��");
	}
}
