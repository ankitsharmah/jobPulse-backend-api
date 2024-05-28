package com.jobpulse.EmailUtil;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailUtils {
    @Autowired
    private JavaMailSender javaMailSender;

    public void gg(){
        System.out.println("printing");
    }
    public  void sendEmail(String email,String otp) throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify your otp");
        mimeMessageHelper.setText("your otp is sir"+otp);
        System.out.println("before sending");
        javaMailSender.send(mimeMessage);
        System.out.println("after sending");
    }
}
