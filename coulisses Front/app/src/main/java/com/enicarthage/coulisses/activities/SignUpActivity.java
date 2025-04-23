package com.enicarthage.coulisses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.enicarthage.coulisses.R;
import com.enicarthage.coulisses.models.AuthenticationResponse;
import com.enicarthage.coulisses.models.RegisterRequest;
import com.enicarthage.coulisses.models.Spectacle;
import com.enicarthage.coulisses.models.User;
import com.enicarthage.coulisses.network.ApiClient;
import com.enicarthage.coulisses.network.AuthApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private Spectacle spectacle;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Get data from intent
        spectacle = getIntent().getParcelableExtra("spectacle");
        selectedCategory = getIntent().getStringExtra("category");

        // Initialize views
        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone);
        EditText password = findViewById(R.id.password);
        EditText confirmPassword = findViewById(R.id.confirmPassword);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView loginPrompt = findViewById(R.id.loginPrompt);
        Button btnLogin = findViewById(R.id.btnLogin);

        // Set up sign up button
        btnSignUp.setOnClickListener(v -> {
            String userFirstName = firstName.getText().toString().trim();
            String userLastName = lastName.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPhone = phone.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            String userConfirmPassword = confirmPassword.getText().toString().trim();

            if (userFirstName.isEmpty() || userLastName.isEmpty() || userEmail.isEmpty() ||
                    userPhone.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!userPassword.equals(userConfirmPassword)) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                return;
            }

            registerUser(userFirstName, userLastName, userEmail, userPhone, userPassword);
        });

        // Set up login navigation
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            if (spectacle != null) {
                intent.putExtra("spectacle", spectacle);
                intent.putExtra("category", selectedCategory);
            }
            startActivity(intent);
        });
    }

    private void registerUser(String firstName, String lastName, String email, String phone, String password) {
        AuthApi api = ApiClient.getClient().create(AuthApi.class);
        RegisterRequest request = new RegisterRequest(firstName, lastName, email, phone, password);

        Call<AuthenticationResponse> call = api.register(request);
        call.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Successful registration
                    User user = response.body().getUser();
                    Toast.makeText(SignUpActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                    if (spectacle != null) {
                        // Proceed to ticket generation
                        generateTicket(user);
                    } else {
                        // Go to home
                        startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                    }
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateTicket(User user) {
        Intent intent = new Intent(this, TicketActivity.class);
        intent.putExtra("spectacle", spectacle);
        intent.putExtra("category", selectedCategory);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}