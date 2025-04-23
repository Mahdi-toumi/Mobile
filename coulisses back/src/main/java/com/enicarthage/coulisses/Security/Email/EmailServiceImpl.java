package com.enicarthage.coulisses.Security.Email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        String subject = "Réinitialisation de votre mot de passe";
        String resetLink = "http://votre-app.com/reset-password?token=" + resetToken;
        String text = "Pour réinitialiser votre mot de passe, cliquez sur ce lien : " + resetLink;

        sendEmail(toEmail, subject, text);
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@votredomaine.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
