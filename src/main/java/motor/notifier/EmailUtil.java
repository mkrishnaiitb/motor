package motor.notifier;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Date;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String host, String port,
                                 final String username, final String password,
                                 String toAddress, String subject, String message) throws MessagingException {

        // Setup mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // Create a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);

        // Send the e-mail
        Transport.send(msg);
    }
}
