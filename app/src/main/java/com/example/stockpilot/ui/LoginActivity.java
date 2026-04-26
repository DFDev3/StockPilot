package com.example.stockpilot.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockpilot.R;

public class LoginActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "stockpilot_prefs";
    private static final String LOGGED_KEY = "logged_in";
    private static final String DEMO_USER = "Fabian";
    private static final String DEMO_PASS = "123456";

    private EditText usernameEditText;
    private EditText passwordEditText;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();
        preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        if (preferences.getBoolean(LOGGED_KEY, false)) {
            startActivity(new Intent(this, ProductListActivity.class));
            finish();
        }
    }

    private void bindViews() {
        usernameEditText = findViewById(R.id.et_username);
        passwordEditText = findViewById(R.id.et_password);
    }

    public void onLoginClick(View view) {
        String typedUser = usernameEditText.getText().toString().trim();
        String typedPassword = passwordEditText.getText().toString().trim();

        if (isValidCredentials(typedUser, typedPassword)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(LOGGED_KEY, true);
            editor.apply();

            startActivity(new Intent(this, ProductListActivity.class));
            finish();
        } else {
            Toast.makeText(this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return DEMO_USER.equals(username) && DEMO_PASS.equals(password);
    }
}
