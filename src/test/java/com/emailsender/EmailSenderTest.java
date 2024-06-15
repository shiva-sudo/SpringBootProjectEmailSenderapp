package com.emailsender;

import com.emailsender.services.EmailServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.File;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailServices emailServices;

    @Test
    void emailSenderTest(){
        System.out.println("Sending mail...");
        emailServices.sendEmail("emailsenderapptest@gmail.com","email for spring app testing","this email is sent by Springboot while creating email service");
    }
//    @Test
//    void emailSenderToMultiPersonTest(){
//        System.out.println("Sending mail...");
//        emailServices.sendEmail("emailsenderapptest@gmail.com","email for spring app testing","this email is sent by Springboot while creating email service");
//    }


    @Test
    void sendHtmlInEmail(){
        String html = ""+
                "<h1 Style = 'color:red; border : 1px solid red;'>Welcome to Spring Boot World </h1>" +
                "";
        emailServices.sendEmailWithHtml("emailsenderapptest@gmail.com","email for spring app testing",html);
    }
    @Test
    void sendEmailWithFile(){
        System.out.println("Sending mail...");
        emailServices.sendEmail("emailsenderapptest@gmail.com",
                "email for spring app testing",
                "This email contain file");
                new File("C:/Project/EmailSenderApp/EmailSenderApp/src/main/resources/images/tomb.jpeg");
    }

}
