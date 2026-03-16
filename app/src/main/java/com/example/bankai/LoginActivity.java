package com.example.bankai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bankai.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = new Database(this);

        binding.loginBtn.setOnClickListener(view -> {
            if (binding.usernameInput.getText().toString().isEmpty() || binding.passwordInput.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String username = binding.usernameInput.getText().toString();
            String password = binding.passwordInput.getText().toString();

            if (database.login(username, password)) {
                startActivity(new Intent(this, MainActivity.class));
            }
            else {
                Toast.makeText(this, "Account does not exist!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.signUpBtn.setOnClickListener(view -> startActivity(new Intent(this, RegisterActivity.class)));
    }
}