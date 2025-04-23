package com.enicarthage.coulisses.Security.Service;

import com.enicarthage.coulisses.Security.DTOs.AuthenticationRequest;
import com.enicarthage.coulisses.Security.DTOs.AuthenticationResponse;
import com.enicarthage.coulisses.Security.DTOs.RegisterRequest;
import com.enicarthage.coulisses.Security.JwtUtil;
import com.enicarthage.coulisses.User.Model.User;
import com.enicarthage.coulisses.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .tel(request.getTel())
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .build();

        userRepository.save(user);

        var jwtToken = jwtUtil.generateToken(user.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getMotDePasse()

                )

        );
        System.out.println("Mot de passe encodé en DB: " + request.getMotDePasse());
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        System.out.println("Mot de passe encodé en DB: " + user);

        var jwtToken = jwtUtil.generateToken(user.getEmail());
        return AuthenticationResponse.builder()
                .user(user.convertToUserDTO(user))  //ici user to user dto
                .token(jwtToken)
                .build();
    }


}
