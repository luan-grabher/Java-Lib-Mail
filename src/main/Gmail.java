package main;

import java.util.Calendar;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Gmail {

    private String user;
    private String password;

    public Gmail(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public boolean send(String to, String subject, String htmlMessage) {

        // Sender's email ID needs to be mentioned
        String from = user;

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            String[] toList = to.split(",");
            for (String toL : toList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toL));
            }

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            //message.setText("This is actual message");
            message.setContent(htmlMessage, "text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("Send message failed!");
            return false;
        }

    }

    public boolean enviaZac(String to, String subject, String htmlMessage) {
        String hello;
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour < 13) {
            hello = "Bom dia!<br><br>";
        } else if (hour < 19) {
            hello = "Boa Tarde!<br><br>";
        } else {
            hello = "Boa noite!<br><br>";
        }
        
        htmlMessage = hello + htmlMessage;
        
        
        //Signature
        String signature = "<br><br>Atenciosamente,<br><br>";
        String src = "https://lh3.googleusercontent.com/UePjEKYKW8yfFo02zzJ_-sYdRbOPFRh-Jv0sw_BJmi4bm9GVow4u1Ipcg20clmv_4e2E3Sajzz5BH_EoH41MSfLPVyL2aJ8cIP3GgPng4JJPVb5wn5TBD_X8q-H8-W4dxD0V1NLy=w2400";
        signature += "<img src='" + src + "' height=\"auto\" width=\"800\"></img>";
        
        return send(to, subject, htmlMessage + signature);
    }
}
