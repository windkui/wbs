package com.wbs.tools.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 发送邮件工具类
 * @author windkui
 *
 */
public class MailUtils {

	private static ApplicationContext context;
	private static JavaMailSenderImpl mailSender;
	private static String from;
	
	static {
		context = new ClassPathXmlApplicationContext("email.xml");

		mailSender = (JavaMailSenderImpl) context.getBean("sender");

		from = mailSender.getUsername();
	}
	
	private static MimeMessage initInfo(String to, String subject, String text, Set<String> files) {
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mime, true, "UTF-8");
			helper.setFrom(from);
			helper.setTo(to);
			if (subject != null) {
				helper.setSubject(subject);
			} else {
				helper.setSubject("系统信息");
			}
			if (text != null) {
				helper.setText(text, true);
			}

			if (files != null) {
				for (String file : files) {
					try {
						helper.addAttachment(
								MimeUtility.encodeWord(""
										+ file), new File(file));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}

		return mime;
	}

	public static boolean sendEmail(String to, String subject, String text,
			Set<String> files) {
		try{
			mailSender.send(initInfo(to, subject, text, files));
		}catch(MailException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean sendEmail(MailEntity entity){
		
		String to = entity.getTo();
		String subject = entity.getSubject();
		String text = entity.getText();
		Set<String> files = entity.getFileNames();
		
		MimeMessage message = initInfo(to, subject, text, files);
		if(message==null){
			return false;
		}
		mailSender.send(message);
		
		return true;
	}
}
