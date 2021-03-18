package screens;
import java.util.Properties;
import javax.activation.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailling{
	public void sendMail(){
		String recepient = "rahulshanmugam2000@gmail.com";
		String body="GO AND SLEEP";
		System.out.print("starting to send");
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		String myMail="kaarthiraina2001@gmail.com";
		String password="kailashgeetha";
		
		Session session=Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myMail,password);
				
			}
		});
		Message message=prepareMessage(session,myMail,recepient,body);
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Message sent successfully");
		
		
		
	}
	
	private static Message prepareMessage(Session session,String myMail,String recepient,String body) {
		try {
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress(myMail));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient) );
			message.setSubject("regd. your transaction");
			message.setText(body);
			return message;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args)  {
		new Mailling().sendMail();
	}
	
}