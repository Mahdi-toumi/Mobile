package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.models.AuthenticationRequest;
import com.enicarthage.coulisses.models.AuthenticationResponse;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.models.User;
import com.enicarthage.coulisses.network.ApiClient;
import com.enicarthage.coulisses.network.AuthApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Spectacle spectacle;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get data from intent
        spectacle = getIntent().getParcelableExtra("spectacle");
        selectedCategory = getIntent().getStringExtra("category");

        // Initialize views
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView signUpPrompt = findViewById(R.id.signUpPrompt);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView forgotPassword = findViewById(R.id.forgotPassword);

        // Set up login button
        btnLogin.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            authenticateUser(userEmail, userPassword);
        });

        // Set up sign up navigation
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            if (spectacle != null) {
                intent.putExtra("spectacle", spectacle);
                intent.putExtra("category", selectedCategory);
            }
            startActivity(intent);
        });

        // Set up forgot password
        forgotPassword.setOnClickListener(v -> {
            // Implement password reset functionality
            Toast.makeText(this, "Password reset feature coming soon", Toast.LENGTH_SHORT).show();
        });
    }

    private void authenticateUser(String email, String password) {
        AuthApi api = ApiClient.getClient().create(AuthApi.class);
        AuthenticationRequest request = new AuthenticationRequest(email, password);

        Call<AuthenticationResponse> call = api.login(request);
        call.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Successful login
                    if (spectacle != null) {
                        // Proceed to ticket generation
                        generateTicket(response.body().getUser());
                    } else {
                        // Go to home
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed. Please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateTicket(User user) {
        // Implement ticket generation logic
        Intent intent = new Intent(this, TicketActivity.class);
        intent.putExtra("spectacle", spectacle);
        intent.putExtra("category", selectedCategory);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}