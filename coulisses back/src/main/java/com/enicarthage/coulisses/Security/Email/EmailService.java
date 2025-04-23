package com.enicarthage.coulisses.Security.Email;

public interface EmailService {
    void sendPasswordResetEmail(String toEmail, String resetToken);
    void sendEmail(String to, String subject, String text);
}
