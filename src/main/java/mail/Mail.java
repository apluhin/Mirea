package mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import students.Student;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by aleksejpluhin on 05.04.16.
 */
public class Mail {
    //Настроен на mail.ru. Google не хочет работать.
    private Logger logger = LoggerFactory.getLogger(Mail.class);
    static String SMTP_AUTH_USER = "mail";
    static  String SMTP_AUTH_PWD = "pass";




    private void sendMessage(Student student, String pass) throws IOException, MessagingException {
        String SMTP_AUTH_USER = "pk-feso@mail.ru";

        String password = SMTP_AUTH_PWD + pass;
        String  messageText = student.getMessage();
        InternetAddress mail = null;
        try {
            mail = new InternetAddress(student.getMail());
        } catch (AddressException e) {
            logger.error("Ошибка адреса");
        }

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_AUTH_USER);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.sendpartial", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        Transport transport = null;
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            logger.debug("Ошибка TCP");
        }
        try {
            transport.connect("smtp.mail.ru", 465, SMTP_AUTH_USER, password);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pk-feso@mail.ru"));
            message.setSubject("тестовое письмо!");

            message.addRecipient(Message.RecipientType.TO, mail);
            message.setSentDate(new Date());

            message.setText(messageText);
          //  Мнимая рассылка
          //  transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            student.setIsSendMail();
        } catch (MessagingException e) {
            logger.debug("Ошибка отправки");
            throw new MessagingException();

        }


    }
    public void distributionMessage(List<Student> students) throws IOException {
       // event.setMessageText(students);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Пароль");

            String pass = reader.readLine();

        for(Student student : students) {
            Mail mail = new Mail();
            try {
                try {
                    mail.sendMessage(student, pass);
                } catch (MessagingException e) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

//        final String username = "a.pluhin@gmail.com";
//        final String password = "035497451241885967475043Aa";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.sendpartial", "true");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//        session.setDebug(true);
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("a.pluhin@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("pk-feso@mail.ru"));
//            message.setSubject("Testing Subject");
//            message.setText("Dear Mail Crawler,"
//                    + "\n\n No spam to my email, please!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
    }

