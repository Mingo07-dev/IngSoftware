
    //private static String password = "rvgecaztskxcidyq";
    import javax.mail.*;
    import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeMessage;
    import java.util.Properties;

    public class Email {

        public Email(){

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
                        InternetAddress.parse("mcsajeva7@gmail.com")
                );
                message.setSubject("Recupero Credenziali");
                message.setText("Ecco le tue credenziali di accesso a 'Farmacie Migliori Con Noi': " +
                        "Mail: " + "mail@gmail.com" +
                        "Password: " + "password");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }