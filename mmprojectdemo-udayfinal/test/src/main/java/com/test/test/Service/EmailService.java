package com.test.test.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String ACCOUNT_SID = "AC8d3effeca7823596d4ff4bd0d1f66919";
    private static final String AUTH_TOKEN = "5f470f6584e15d3aa8effce9b021ea34";
    private static final String TWILIO_PHONE_NUMBER = "+12314270782";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("makineni.uday7@gmail.com");

            mailSender.send(message);
            System.out.println("Email sent to " + toEmail);

        } catch (Exception e) {
            System.out.println("Failed to send email to " + toEmail + ". Error: " + e.getMessage());
        }
    }
    public void sendSms(String toPhoneNumber, String messageBody) {
        try {
            Message message = Message.creator(
                    new PhoneNumber("+91" + toPhoneNumber),  // Ensure this is in E.164 format
                    new PhoneNumber(TWILIO_PHONE_NUMBER),    // Your Twilio phone number
                    messageBody
            ).create();
            System.out.println("SMS sent to " + toPhoneNumber);
        } catch (Exception e) {
            System.out.println("Failed to send SMS to " + toPhoneNumber + ". Error: " + e.getMessage());
        }
    }

}
