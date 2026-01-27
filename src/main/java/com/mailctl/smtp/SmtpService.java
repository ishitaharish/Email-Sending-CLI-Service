package com.mailctl.smtp;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.Console;
import java.util.Properties;

public class SmtpService {

    private final String host;
    private final int port;
    private final String user;
    private final String pass;

    public SmtpService() {
        Console console = System.console();

        if (console == null) {
            throw new RuntimeException("No console available. Run this from the terminal.");
        }

        // Prompt user for SMTP details
        host = console.readLine("SMTP Host: ");
        String portStr = console.readLine("SMTP Port (e.g., 587): ");
        port = Integer.parseInt(portStr);
        user = console.readLine("Email Username: ");
        char[] passwordChars = console.readPassword("Email App Password: ");
        pass = new String(passwordChars); // convert char[] to String
    }

    public boolean sendEmail(String toEmail, String subject, String body, String attachmentsCsv) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", String.valueOf(port));

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body); // For simplicity, plain text

            // TODO: Handle attachments if attachmentsCsv != null

            Transport.send(message);
            System.out.println("Email sent to: " + toEmail);
            return true;

        } catch (MessagingException e) {
            System.err.println("Failed to send email to: " + toEmail);
            e.printStackTrace();
            return false;
        }
    }
}
