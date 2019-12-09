package com.spring.dev2chuc.nutritious_food.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceIml implements  MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String mail, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail);

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }
}
