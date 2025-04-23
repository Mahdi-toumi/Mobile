package com.enicarthage.coulisses.network;

import com.enicarthage.coulisses.models.AuthenticationRequest;
import com.enicarthage.coulisses.models.AuthenticationResponse;
import com.enicarthage.coulisses.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApi {

    @POST("api/auth/register")
    Call<AuthenticationResponse> register(@Body RegisterRequest request);

    @POST("api/auth/login")
    Call<AuthenticationResponse> login(@Body AuthenticationRequest request);

    @POST("api/password/request-reset")
    Call<String> requestPasswordReset(@Query("email") String email);

    @POST("api/password/reset")
    Call<String> resetPassword(@Query("token") String token, @Body String newPassword);
}
