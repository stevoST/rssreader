package com.domain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailNotification {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("stefan.starosta@gmail.com");

        msg.setSubject("mooncake");
        msg.setText("Chookity \n Pok");

        javaMailSender.send(msg);
    }
}
