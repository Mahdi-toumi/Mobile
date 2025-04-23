package com.enicarthage.coulisses.Security.Controller;

import com.enicarthage.coulisses.Security.Service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestParam String email) {
        passwordService.requestPasswordReset(email);
        return ResponseEntity.ok("Reset link sent to email");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(
            @RequestParam String token,
            @RequestBody String newPassword) {
        passwordService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }
}