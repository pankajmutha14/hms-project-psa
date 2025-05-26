package com.notificationservice.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.notificationservice.dto.EmailRequest;

@Service
public class EmailConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "send_email", groupId = "notification-group")
    public void consume(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());

        mailSender.send(message);
    }
}

