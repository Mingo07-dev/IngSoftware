package farmacie.miglioriconnoi.Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    public static void sendEmail(String sendToThisMail, String password_toSend){

        final String username = "farmaciemiglioriconnoi@gmail.com";
        final String password = "rryoyiydlhijojeo";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("farmaciemiglioriconnoi@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(sendToThisMail)
            );
            message.setSubject("Recupero Credenziali");
            message.setText("Ecco le tue credenziali di accesso a 'Farmacie Migliori Con Noi': " +
                    "\nMail: " + sendToThisMail +
                    "\nPassword: " + password_toSend);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}