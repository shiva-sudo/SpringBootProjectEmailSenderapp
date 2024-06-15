package com.emailsender.services.impl;
import com.emailsender.services.EmailServices;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class EmailServiceImpl implements EmailServices {

    //@Autowired
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("emailsenderapptest@gmail.com");
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent...");
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {

    SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
    simpleMailMessage.setTo(to);
    simpleMailMessage.setFrom("emailsenderapptest@gmail.com");
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(message);
    mailSender.send(simpleMailMessage);
    logger.info("Multiple Email has been sent...");
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("emailsenderapptest@gmail.com");
            helper.setText(htmlContent,true);
            mailSender.send(mimeMessage);
            logger.info("Html Content has been sent...");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage,true);
            helper.setTo(to);
            helper.setFrom("emailsenderapptest@gmail.com");
            helper.setSubject(subject);
            helper.setText(message);
            FileSystemResource fileSystemResource =  new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            mailSender.send(mimeMessage);
            logger.info("Email send Successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
