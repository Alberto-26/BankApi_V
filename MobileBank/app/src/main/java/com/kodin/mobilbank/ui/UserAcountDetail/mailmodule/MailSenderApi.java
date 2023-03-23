package com.kodin.mobilbank.ui.UserAcountDetail.mailmodule;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSenderApi {
    private  String stringSenderEmail;
    private  String stringReceiverEmail;
    private String transactionInfo;

    public MailSenderApi(String stringSenderEmail, String stringReceiverEmail, String transactionInfo) {
        this.stringSenderEmail = stringSenderEmail;
        this.stringReceiverEmail = stringReceiverEmail;
        this.transactionInfo=transactionInfo;
    }

    public String getStringSenderEmail() {
        return stringSenderEmail;
    }

    public void setStringSenderEmail(String stringSenderEmail) {
        this.stringSenderEmail = stringSenderEmail;
    }

    public String getStringReceiverEmail() {
        return stringReceiverEmail;
    }

    public void setStringReceiverEmail(String stringReceiverEmail) {
        this.stringReceiverEmail = stringReceiverEmail;
    }

    public void sendMailHtml(){
        try {
        String stringPasswordSenderEmail = "*****"; // nota para password de cada desarrollador seguir este video https://www.youtube.com/watch?v=RpSQQIGTpTM
        String stringHost = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", stringHost);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
            }
        });
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

            mimeMessage.setSubject("Subject: sending Funds");
          //  mimeMessage.setText("Hello Programmer, \n\nProgrammer World has sent you this 2nd email. \n\n Cheers!\nProgrammer World");
          //  System.out.println(this.transactionInfo);
            mimeMessage.setContent(
                    "<h1>This is actual message embedded sender transfer</h1>" +
                            "<h1>"+this.transactionInfo+"</h1>"+"<a target='_blank' href='https://kodigo.org/'><img src='https://kodigo.org/wp-content/uploads/2021/01/logo-kodigo-50.png' /> </a>",
                    "text/html");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}
