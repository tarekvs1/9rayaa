
package com.example.studify;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;

    private LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDashboard();
    }

    private void createDashboard() {

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(BACKGROUND);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(20), dp(25), dp(20), dp(25));

        scrollView.addView(content);

        // Header
        TextView appName = new TextView(this);
        appName.setText("STUDIFY");
        appName.setTextSize(14);
        appName.setTextColor(PRIMARY);
        appName.setTypeface(null, Typeface.BOLD);
        appName.setLetterSpacing(0.15f);
        content.addView(appName);

        TextView heading = new TextView(this);
        heading.setText("Student Dashboard");
        heading.setTextSize(28);
        heading.setTextColor(TEXT);
        heading.setTypeface(null, Typeface.BOLD);
        heading.setPadding(0, dp(10), 0, dp(5));
        content.addView(heading);

        TextView subtitle = new TextView(this);
        subtitle.setText("Manage your academic life in one place.");
        subtitle.setTextSize(14);
        subtitle.setTextColor(MUTED);
        subtitle.setPadding(0, 0, 0, dp(22));
        content.addView(subtitle);

        // Welcome card
        LinearLayout welcomeCard = new LinearLayout(this);
        welcomeCard.setOrientation(LinearLayout.VERTICAL);
        welcomeCard.setPadding(dp(20), dp(20), dp(20), dp(20));
        welcomeCard.setBackground(makeBackground(PRIMARY, 18));

        TextView welcomeTitle = new TextView(this);
        welcomeTitle.setText("Welcome back!");
        welcomeTitle.setTextSize(23);
        welcomeTitle.setTextColor(WHITE);
        welcomeTitle.setTypeface(null, Typeface.BOLD);
        welcomeCard.addView(welcomeTitle);

        TextView welcomeDescription = new TextView(this);
        welcomeDescription.setText(
                "Stay organized, track your progress, and reach your goals."
        );
        welcomeDescription.setTextSize(14);
        welcomeDescription.setTextColor(WHITE);
        welcomeDescription.setPadding(0, dp(8), 0, 0);
        welcomeCard.addView(welcomeDescription);

        LinearLayout.LayoutParams welcomeParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        welcomeParams.setMargins(0, 0, 0, dp(24));
        content.addView(welcomeCard, welcomeParams);

        // Statistics title
        addSectionTitle("Overview");

        // Statistics cards
        LinearLayout statsRow = new LinearLayout(this);
        statsRow.setOrientation(LinearLayout.HORIZONTAL);

        statsRow.addView(
                createStatCard("12", "Students"),
                new LinearLayout.LayoutParams(0, dp(110), 1)
        );

        View space = new View(this);
        statsRow.addView(
                space,
                new LinearLayout.LayoutParams(dp(12), 1)
        );

        statsRow.addView(
                createStatCard("8", "Subjects"),
                new LinearLayout.LayoutParams(0, dp(110), 1)
        );

        LinearLayout.LayoutParams statsParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        statsParams.setMargins(0, 0, 0, dp(25));
        content.addView(statsRow, statsParams);

        // Menu title
        addSectionTitle("Academic Management");

        // Menu buttons
        addMenuButton("Manage Students", "StudentsActivity");
        addMenuButton("Add Student", "AddStudentActivity");
        addMenuButton("Grades", "GradesActivity");
        addMenuButton("Statistics", "StatisticsActivity");
        addMenuButton("Timetable", "TimetableActivity");
        addMenuButton("Attendance", "AttendanceActivity");
        addMenuButton("Assignments", "AssignmentsActivity");
        addMenuButton("Announcements", "AnnouncementsActivity");
        addMenuButton("Report Card", "ReportCardActivity");
        addMenuButton("My Profile", "ProfileActivity");
        addMenuButton("Settings", "SettingsActivity");

        // Logout button
        Button logoutButton = new Button(this);
        logoutButton.setText("Log Out");
        logoutButton.setAllCaps(false);
        logoutButton.setTextSize(15);
        logoutButton.setTextColor(Color.rgb(205, 60, 75));
        logoutButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(WHITE)
        );

        LinearLayout.LayoutParams logoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(50)
                );
        logoutParams.setMargins(0, dp(20), 0, dp(10));
        content.addView(logoutButton, logoutParams);

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    LoginActivity.class
            );
            startActivity(intent);
            finish();
        });

        setContentView(scrollView);
    }

    private LinearLayout createStatCard(String number, String label) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(Gravity.CENTER);
        card.setBackground(makeBackground(WHITE, 16));
        card.setPadding(dp(10), dp(15), dp(10), dp(15));

        TextView numberText = new TextView(this);
        numberText.setText(number);
        numberText.setTextSize(27);
        numberText.setTextColor(PRIMARY);
        numberText.setTypeface(null, Typeface.BOLD);
        card.addView(numberText);

        TextView labelText = new TextView(this);
        labelText.setText(label);
        labelText.setTextSize(13);
        labelText.setTextColor(MUTED);
        labelText.setPadding(0, dp(5), 0, 0);
        card.addView(labelText);

        return card;
    }

    private void addSectionTitle(String title) {

        TextView sectionTitle = new TextView(this);
        sectionTitle.setText(title);
        sectionTitle.setTextSize(18);
        sectionTitle.setTextColor(TEXT);
        sectionTitle.setTypeface(null, Typeface.BOLD);
        sectionTitle.setPadding(0, 0, 0, dp(12));

        content.addView(sectionTitle);
    }

    private void addMenuButton(String label, String activityName) {

        Button button = new Button(this);
        button.setText(label);
        button.setTextSize(15);
        button.setTextColor(TEXT);
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        button.setPadding(dp(16), 0, dp(16), 0);
        button.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(WHITE)
        );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(55)
                );
        params.setMargins(0, 0, 0, dp(9));

        content.addView(button, params);

        button.setOnClickListener(v -> {
            try {
                Class<?> activityClass = Class.forName(
                        getPackageName() + "." + activityName
                );

                Intent intent = new Intent(
                        MainActivity.this,
                        activityClass
                );
                startActivity(intent);

            } catch (ClassNotFoundException e) {
                Toast.makeText(
                        MainActivity.this,
                        label + " screen is not created yet.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
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
