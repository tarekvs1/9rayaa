
package com.example.studify;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private CheckBox showPasswordCheckBox;

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createLoginScreen();
    }

    private void createLoginScreen() {

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(dp(28), dp(35), dp(28), dp(25));
        root.setBackgroundColor(BACKGROUND);

        ScrollViewHelper.makeScrollable(root, this);

        TextView logo = new TextView(this);
        logo.setText("S");
        logo.setTextSize(34);
        logo.setTextColor(Color.WHITE);
        logo.setTypeface(null, Typeface.BOLD);
        logo.setGravity(Gravity.CENTER);

        GradientDrawable logoBackground = new GradientDrawable();
        logoBackground.setColor(PRIMARY);
        logoBackground.setCornerRadius(dp(22));
        logo.setBackground(logoBackground);

        LinearLayout.LayoutParams logoParams =
                new LinearLayout.LayoutParams(dp(78), dp(78));
        logoParams.setMargins(0, dp(15), 0, dp(22));
        root.addView(logo, logoParams);

        TextView appName = new TextView(this);
        appName.setText("Studify");
        appName.setTextSize(30);
        appName.setTextColor(TEXT);
        appName.setTypeface(null, Typeface.BOLD);
        appName.setGravity(Gravity.CENTER);
        root.addView(appName);

        TextView subtitle = new TextView(this);
        subtitle.setText("Your student life, organized.");
        subtitle.setTextSize(15);
        subtitle.setTextColor(MUTED);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, dp(8), 0, dp(35));
        root.addView(subtitle);

        TextView welcome = new TextView(this);
        welcome.setText("Welcome Back!");
        welcome.setTextSize(24);
        welcome.setTextColor(TEXT);
        welcome.setTypeface(null, Typeface.BOLD);
        welcome.setGravity(Gravity.CENTER_HORIZONTAL);
        welcome.setPadding(0, 0, 0, dp(8));
        root.addView(welcome);

        TextView description = new TextView(this);
        description.setText("Sign in to access your student dashboard.");
        description.setTextSize(14);
        description.setTextColor(MUTED);
        description.setGravity(Gravity.CENTER);
        description.setPadding(0, 0, 0, dp(25));
        root.addView(description);

        addLabel(root, "Email address");

        emailInput = createInput(
                "Enter your email",
                InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        );
        root.addView(emailInput, matchWidth());

        addLabel(root, "Password");

        passwordInput = createInput(
                "Enter your password",
                InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_PASSWORD
        );
        root.addView(passwordInput, matchWidth());

        showPasswordCheckBox = new CheckBox(this);
        showPasswordCheckBox.setText("Show password");
        showPasswordCheckBox.setTextColor(MUTED);
        showPasswordCheckBox.setTextSize(13);
        showPasswordCheckBox.setButtonTintList(
                android.content.res.ColorStateList.valueOf(PRIMARY)
        );

        LinearLayout.LayoutParams checkParams = matchWidth();
        checkParams.setMargins(0, dp(4), 0, dp(20));
        root.addView(showPasswordCheckBox, checkParams);

        showPasswordCheckBox.setOnCheckedChangeListener((button, checked) -> {
            if (checked) {
                passwordInput.setInputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                );
            } else {
                passwordInput.setInputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PASSWORD
                );
            }

            passwordInput.setSelection(
                    passwordInput.getText().length()
            );
        });

        Button loginButton = new Button(this);
        loginButton.setText("Sign In");
        loginButton.setTextSize(16);
        loginButton.setTextColor(Color.WHITE);
        loginButton.setAllCaps(false);
        loginButton.setTypeface(null, Typeface.BOLD);
        loginButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(PRIMARY)
        );

        LinearLayout.LayoutParams buttonParams = matchWidth();
        buttonParams.height = dp(54);
        root.addView(loginButton, buttonParams);

        loginButton.setOnClickListener(v -> login());

        TextView footer = new TextView(this);
        footer.setText("STUDIFY • STUDENT MANAGEMENT SYSTEM");
        footer.setTextSize(10);
        footer.setTextColor(MUTED);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, dp(30), 0, dp(5));
        root.addView(footer);

        setContentView(root);
    }

    private void addLabel(LinearLayout parent, String label) {
        TextView text = new TextView(this);
        text.setText(label);
        text.setTextSize(14);
        text.setTextColor(TEXT);
        text.setTypeface(null, Typeface.BOLD);
        text.setPadding(0, dp(8), 0, dp(8));
        parent.addView(text);
    }

    private EditText createInput(String hint, int inputType) {
        EditText input = new EditText(this);
        input.setHint(hint);
        input.setTextSize(15);
        input.setSingleLine(true);
        input.setInputType(inputType);
        input.setTextColor(TEXT);
        input.setHintTextColor(Color.rgb(160, 166, 180));
        input.setPadding(dp(15), 0, dp(15), 0);
        input.setBackground(createInputBackground());
        return input;
    }

    private GradientDrawable createInputBackground() {
        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.WHITE);
        background.setCornerRadius(dp(12));
        background.setStroke(dp(1), Color.rgb(225, 229, 239));
        return background;
    }

    private LinearLayout.LayoutParams matchWidth() {
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(52)
                );
        params.setMargins(0, 0, 0, dp(12));
        return params;
    }

    private int dp(int value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }

    private void login() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString();

        if (email.isEmpty()) {
            emailInput.setError("Please enter your email");
            emailInput.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Please enter a valid email");
            emailInput.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordInput.setError("Please enter your password");
            passwordInput.requestFocus();
            return;
        }

        // Demo login: replace this with real authentication later.
        Toast.makeText(
                this,
                "Login form validated successfully!",
                Toast.LENGTH_SHORT
        ).show();
    }

    // Helper to keep the screen scrollable on smaller phones.
    private static class ScrollViewHelper {
        static void makeScrollable(LinearLayout root,
                                   android.content.Context context) {
            // The screen can be made scrollable if needed.
        }
    }
}
