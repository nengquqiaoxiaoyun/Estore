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

	//发送邮件
	//mail:收件人   mailMsg:邮件内容  subject:邮件标题
	public static void sendMail(String mail,String mailMsg,String subject) throws AddressException, MessagingException{
		
		
		//1.创建session对象，建立java程序到以邮件服务器之间的连接
		Properties pro = new Properties();
		
		pro.setProperty("mail.transport.protocol", "SMTP");//设置邮箱服务器协议
		pro.setProperty("mail.host", "localhost");//设置发邮件的邮箱服务器地址
		pro.setProperty("mail.smtp.auth", "true");// 验证发件箱的账户名和密码
		//创建验证器
		Authenticator auth = new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//第一个参数：账户名		第二个参数：密码   发件人的用户名和密码
				return new PasswordAuthentication("zhangsan","123");
			}
			
		};
		
		//创建一个程序和邮箱服务器的会话对象Session
		Session session = Session.getInstance(pro, auth);
		//2创建一个需要发送的邮件对象
		Message message = new MimeMessage(session);//创建一个message对象，用于设置邮件内容
		message.setFrom(new InternetAddress("zhangsan@itheima21.com"));//设置发件人
		message.setRecipient(RecipientType.TO, new InternetAddress(mail));//设置发件方式和接受者
		message.setSubject(subject);//设置标题
		message.setContent(mailMsg, "text/html;charset=utf-8");//设置邮件内容
		//3.执行发送
		Transport.send(message);
		
	}
	
	public static void main(String[] args) throws AddressException, MessagingException {
		sendMail("huangxingcheng@itcast.cn", "生日快乐", "祝福");
	}
}
