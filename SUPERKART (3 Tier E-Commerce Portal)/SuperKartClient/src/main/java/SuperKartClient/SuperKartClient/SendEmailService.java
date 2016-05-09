package SuperKartClient.SuperKartClient;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailService {
	
	public void sendMail(String recipient){
	final String username = "chandani.madnani@gmail.com";
	final String password = "bpcg@123";

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
	new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	  });

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("chandani.madnani@gmail.com"));
			message.setRecipients(javax.mail.Message.RecipientType.TO,
				InternetAddress.parse(recipient));
			message.setSubject("Contact Query");
			message.setText("We have received your query. You will hear from us soon ");

			Transport.send(message);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
