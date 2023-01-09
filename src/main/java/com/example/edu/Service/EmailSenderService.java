package com.example.edu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String toEmail,String subject ,String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("starksam3366@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

        System.out.println("Mail Sent Successfully");
    }
    public void approvalMail(String toEmail,String subject ,String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("thiravisamraj@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

        System.out.println("Mail Sent Successfully");
    }
    public void feesMail(String toEmail,String subject ,String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("thiravisamraj@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

        System.out.println("Mail Sent Successfully");
    }
}
