package com.emailsender.services;

import java.io.File;

public interface EmailServices {
    // send email to single person
    void sendEmail(String to, String subject, String message);

    // send mail to multiple person
    void sendEmail(String []to, String subject, String message);

    //send mail with html
    void sendEmailWithHtml(String to , String subject, String htmlcontent);

    // send email with file
    void sendEmailWithFile(String to, String subject, String message, File file);

}
