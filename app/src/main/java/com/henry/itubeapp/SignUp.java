package com.henry.itubeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {
    Button createButton;
    EditText userFullName, userUserName, userPassword, userConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createButton = findViewById(R.id.buttonCreate);
        userFullName = findViewById(R.id.fullName);
        userUserName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.password);
        userConfirmPassword = findViewById(R.id.confirmPassword);

        createButton.setOnClickListener(v -> {
            DataBaseHelper myDB = new DataBaseHelper(SignUp.this);
            String fullName = userFullName.getText().toString().trim();
            String usersName = userUserName.getText().toString().trim();
            String passwordUser = userPassword.getText().toString().trim();
            String confirmPasswordUser = userConfirmPassword.getText().toString().trim();

        if (fullName.isEmpty() || usersName.isEmpty() || passwordUser.isEmpty() || confirmPasswordUser.isEmpty()) {
            Toast.makeText(SignUp.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordUser.equals(confirmPasswordUser)) {
            Toast.makeText(SignUp.this, "Passwords not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (myDB.checkUserExists(usersName)) {
            Toast.makeText(SignUp.this, "Username is already exits", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean users = myDB.addUser(fullName, usersName, passwordUser);
            if (users) {
                Toast.makeText(SignUp.this, "Create account successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(SignUp.this, "Faild to create account", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
