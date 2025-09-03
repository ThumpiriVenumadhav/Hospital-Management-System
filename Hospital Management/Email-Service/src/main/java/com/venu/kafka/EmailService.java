package com.venu.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendAppointmentEmail(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(appointment.getEmail());
        message.setSubject("Payment Status - " + appointment.getAcceptStatus());
        message.setText("Your appointment booked successfully");

        mailSender.send(message);
    log.warn(" Email sent to : {} " , appointment.getEmail());
    }
}
