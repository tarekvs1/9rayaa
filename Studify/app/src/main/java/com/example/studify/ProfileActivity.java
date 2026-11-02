
package com.example.studify;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createProfile();
    }

    private void createProfile() {

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(BACKGROUND);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(25), dp(22), dp(25));

        scrollView.addView(root);

        // Page title
        TextView pageTitle = new TextView(this);
        pageTitle.setText("My Profile");
        pageTitle.setTextSize(28);
        pageTitle.setTextColor(TEXT);
        pageTitle.setTypeface(null, Typeface.BOLD);
        pageTitle.setPadding(0, 0, 0, dp(25));
        root.addView(pageTitle);

        // Profile avatar
        TextView avatar = new TextView(this);
        avatar.setText("S");
        avatar.setTextSize(38);
        avatar.setTextColor(WHITE);
        avatar.setTypeface(null, Typeface.BOLD);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackground(makeBackground(PRIMARY, 60));

        LinearLayout.LayoutParams avatarParams =
                new LinearLayout.LayoutParams(dp(105), dp(105));
        avatarParams.gravity = Gravity.CENTER_HORIZONTAL;
        avatarParams.setMargins(0, 0, 0, dp(15));
        root.addView(avatar, avatarParams);

        // Student name
        TextView name = new TextView(this);
        name.setText("Student Account");
        name.setTextSize(23);
        name.setTextColor(TEXT);
        name.setTypeface(null, Typeface.BOLD);
        name.setGravity(Gravity.CENTER);

        root.addView(name);

        // Student role
        TextView role = new TextView(this);
        role.setText("Student");
        role.setTextSize(15);
        role.setTextColor(PRIMARY);
        role.setGravity(Gravity.CENTER);
        role.setPadding(0, dp(7), 0, dp(25));
        root.addView(role);

        // Information card
        LinearLayout infoCard = new LinearLayout(this);
        infoCard.setOrientation(LinearLayout.VERTICAL);
        infoCard.setPadding(dp(18), dp(8), dp(18), dp(8));
        infoCard.setBackground(makeBackground(WHITE, 18));

        addInfoRow(infoCard, "Full Name", "Student Account");
        addDivider(infoCard);

        addInfoRow(infoCard, "Email Address", "student@example.com");
        addDivider(infoCard);

        addInfoRow(infoCard, "Student ID", "STU001");
        addDivider(infoCard);

        addInfoRow(infoCard, "Class", "Class A");
        addDivider(infoCard);

        addInfoRow(infoCard, "Institution", "Studify University");

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        cardParams.setMargins(0, 0, 0, dp(22));

        root.addView(infoCard, cardParams);

        // Edit profile button
        Button editButton = new Button(this);
        editButton.setText("Edit Profile");
        editButton.setTextSize(16);
        editButton.setAllCaps(false);
        editButton.setTextColor(WHITE);
        editButton.setTypeface(null, Typeface.BOLD);
        editButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(PRIMARY)
        );

        LinearLayout.LayoutParams editParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(52)
                );
        editParams.setMargins(0, 0, 0, dp(12));

        root.addView(editButton, editParams);

        editButton.setOnClickListener(v ->
                Toast.makeText(
                        ProfileActivity.this,
                        "Profile editing will be added soon.",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // Back button
        Button backButton = new Button(this);
        backButton.setText("Back to Dashboard");
        backButton.setTextSize(15);
        backButton.setAllCaps(false);
        backButton.setTextColor(TEXT);
        backButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(WHITE)
        );

        root.addView(backButton, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(52)
        ));

        backButton.setOnClickListener(v -> finish());

        setContentView(scrollView);
    }

    private void addInfoRow(
            LinearLayout parent,
            String label,
            String value
    ) {

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.VERTICAL);
        row.setPadding(0, dp(12), 0, dp(12));

        TextView labelText = new TextView(this);
        labelText.setText(label);
        labelText.setTextSize(13);
        labelText.setTextColor(MUTED);

        row.addView(labelText);

        TextView valueText = new TextView(this);
        valueText.setText(value);
        valueText.setTextSize(16);
        valueText.setTextColor(TEXT);
        valueText.setTypeface(null, Typeface.BOLD);
        valueText.setPadding(0, dp(5), 0, 0);

        row.addView(valueText);

        parent.addView(row);
    }

    private void addDivider(LinearLayout parent) {
        TextView divider = new TextView(this);
        divider.setBackgroundColor(Color.rgb(235, 237, 243));

        parent.addView(divider, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(1)
        ));
    }

    private GradientDrawable makeBackground(int color, int radius) {
        GradientDrawable background = new GradientDrawable();
        background.setColor(color);
        background.setCornerRadius(dp(radius));
        return background;
    }

    private int dp(int value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }
}
