package com.vlad.tms.diploma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    public void send(String emailTo, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);        //От кого отправляем
        mailMessage.setTo(emailTo);     //На какой емаил отправить
        mailMessage.setSubject(subject);//Тема письма
        mailMessage.setText(message);   //Текст сообщения

        mailSender.send(mailMessage);   //Отправляем сообщение, которое сформировали
    }
}
