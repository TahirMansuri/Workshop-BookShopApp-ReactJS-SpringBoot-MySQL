package com.infogalaxy.workshopbookstoreapp.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class JMSUtil {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String to,String subject, String body){
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("oldapolda38@gmail.com");
//        simpleMailMessage.setTo(to);
//        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(body);
        try {
            MimeMessage mimeMsg = javaMailSender.createMimeMessage();
            MimeMessageHelper msgHelper = null;
            msgHelper = new MimeMessageHelper(mimeMsg, false, "utf-8");
            msgHelper.addTo(to);
            msgHelper.setSubject(subject);
            boolean isHTML = true;
            msgHelper.setText(body, isHTML);
            mimeMsg.saveChanges();
            javaMailSender.send(mimeMsg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
