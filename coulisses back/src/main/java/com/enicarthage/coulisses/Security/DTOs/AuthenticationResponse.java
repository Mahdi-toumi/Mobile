package com.enicarthage.coulisses.Security.DTOs;

import com.enicarthage.coulisses.User.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private userDTO user ;
    private String token;
}