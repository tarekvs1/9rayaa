
package com.example.studify;

import android.app.Activity;
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

public class AssignmentsActivity extends Activity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;

    private LinearLayout assignmentsContainer;

    private int completedCount = 0;
    private TextView totalText;
    private TextView completedText;

    private final String[] subjects = {
            "Java Programming",
            "Database Systems",
            "Web Development",
            "Software Engineering",
            "Computer Networks"
    };

    private final String[] titles = {
            "Object-Oriented Programming",
            "SQL Queries Exercise",
            "Personal Portfolio Website",
            "UML Class Diagram",
            "Network Configuration"
    };

    private final String[] descriptions = {
            "Complete exercises about classes, objects, inheritance and encapsulation.",
            "Write SQL queries to retrieve, insert and update database records.",
            "Create a responsive portfolio using HTML, CSS and JavaScript.",
            "Design a class diagram for a student management system.",
            "Complete the network configuration exercises."
    };

    private final String[] deadlines = {
            "05 November 2026",
            "07 November 2026",
            "10 November 2026",
            "12 November 2026",
            "15 November 2026"
    };

    private final boolean[] completed = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createScreen();
    }

    private void createScreen() {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(BACKGROUND);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(18), dp(20), dp(24));

        scrollView.addView(root);

        // Back button
        Button backButton = new Button(this);
        backButton.setText("←  Back to Dashboard");
        backButton.setTextColor(PRIMARY);
        backButton.setAllCaps(false);
        backButton.setBackgroundColor(Color.TRANSPARENT);
        backButton.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        backButton.setOnClickListener(v -> finish());

        root.addView(backButton, new LinearLayout.LayoutParams(
                -1, dp(48)));

        // Page title
        TextView title = new TextView(this);
        title.setText("My Assignments");
        title.setTextColor(TEXT);
        title.setTextSize(28);
        title.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        // Subtitle
        TextView subtitle = new TextView(this);
        subtitle.setText("Keep track of your coursework and deadlines.");
        subtitle.setTextColor(MUTED);
        subtitle.setTextSize(14);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(6);
        subtitleParams.bottomMargin = dp(20);
        root.addView(subtitle, subtitleParams);

        // Summary card
        LinearLayout summaryCard = new LinearLayout(this);
        summaryCard.setOrientation(LinearLayout.VERTICAL);
        summaryCard.setPadding(dp(18), dp(16), dp(18), dp(16));
        summaryCard.setBackground(background(WHITE, 16));

        TextView summaryLabel = new TextView(this);
        summaryLabel.setText("ASSIGNMENT OVERVIEW");
        summaryLabel.setTextColor(MUTED);
        summaryLabel.setTextSize(12);
        summaryLabel.setTypeface(null, Typeface.BOLD);

        totalText = new TextView(this);
        totalText.setTextColor(PRIMARY);
        totalText.setTextSize(25);
        totalText.setTypeface(null, Typeface.BOLD);

        completedText = new TextView(this);
        completedText.setTextColor(MUTED);
        completedText.setTextSize(14);

        summaryCard.addView(summaryLabel);
        summaryCard.addView(totalText);

        LinearLayout.LayoutParams completedParams =
                new LinearLayout.LayoutParams(-1, -2);
        completedParams.topMargin = dp(5);
        summaryCard.addView(completedText, completedParams);

        LinearLayout.LayoutParams summaryParams =
                new LinearLayout.LayoutParams(-1, -2);
        summaryParams.bottomMargin = dp(22);
        root.addView(summaryCard, summaryParams);

        // Section title
        TextView sectionTitle = new TextView(this);
        sectionTitle.setText("All Assignments");
        sectionTitle.setTextColor(TEXT);
        sectionTitle.setTextSize(20);
        sectionTitle.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams sectionParams =
                new LinearLayout.LayoutParams(-1, -2);
        sectionParams.bottomMargin = dp(12);
        root.addView(sectionTitle, sectionParams);

        // Assignment cards
        assignmentsContainer = new LinearLayout(this);
        assignmentsContainer.setOrientation(LinearLayout.VERTICAL);
        root.addView(assignmentsContainer);

        for (int i = 0; i < titles.length; i++) {
            addAssignmentCard(i);
        }

        updateSummary();

        setContentView(scrollView);
    }

    private void addAssignmentCard(int index) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(16), dp(16), dp(16));
        card.setBackground(background(WHITE, 14));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(14);

        assignmentsContainer.addView(card, cardParams);

        // Subject label
        TextView subjectView = new TextView(this);
        subjectView.setText(subjects[index]);
        subjectView.setTextColor(PRIMARY);
        subjectView.setTextSize(13);
        subjectView.setTypeface(null, Typeface.BOLD);

        card.addView(subjectView);

        // Assignment title
        TextView titleView = new TextView(this);
        titleView.setText(titles[index]);
        titleView.setTextColor(TEXT);
        titleView.setTextSize(18);
        titleView.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(8);
        card.addView(titleView, titleParams);

        // Description
        TextView descriptionView = new TextView(this);
        descriptionView.setText(descriptions[index]);
        descriptionView.setTextColor(MUTED);
        descriptionView.setTextSize(14);
        descriptionView.setLineSpacing(dp(3), 1.0f);

        LinearLayout.LayoutParams descriptionParams =
                new LinearLayout.LayoutParams(-1, -2);
        descriptionParams.topMargin = dp(8);
        card.addView(descriptionView, descriptionParams);

        // Deadline
        TextView deadlineView = new TextView(this);
        deadlineView.setText("Deadline: " + deadlines[index]);
        deadlineView.setTextColor(MUTED);
        deadlineView.setTextSize(13);

        LinearLayout.LayoutParams deadlineParams =
                new LinearLayout.LayoutParams(-1, -2);
        deadlineParams.topMargin = dp(12);
        card.addView(deadlineView, deadlineParams);

        // Status
        TextView statusView = new TextView(this);
        statusView.setText(completed[index] ? "Completed" : "Not completed");
        statusView.setTextColor(
                completed[index]
                        ? Color.rgb(34, 150, 100)
                        : Color.rgb(210, 135, 35)
        );
        statusView.setTextSize(13);
        statusView.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams statusParams =
                new LinearLayout.LayoutParams(-1, -2);
        statusParams.topMargin = dp(8);
        card.addView(statusView, statusParams);

        // Mark complete button
        Button completeButton = new Button(this);
        completeButton.setText(
                completed[index] ? "Mark as Not Completed" : "Mark as Completed"
        );
        completeButton.setAllCaps(false);
        completeButton.setTextColor(WHITE);
        completeButton.setTextSize(13);
        completeButton.setBackground(
                background(completed[index]
                        ? Color.rgb(115, 123, 145)
                        : PRIMARY, 10)
        );

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(-1, dp(46));
        buttonParams.topMargin = dp(10);
        card.addView(completeButton, buttonParams);

        completeButton.setOnClickListener(v -> {
            completed[index] = !completed[index];

            statusView.setText(
                    completed[index] ? "Completed" : "Not completed"
            );

            statusView.setTextColor(
                    completed[index]
                            ? Color.rgb(34, 150, 100)
                            : Color.rgb(210, 135, 35)
            );

            completeButton.setText(
                    completed[index]
                            ? "Mark as Not Completed"
                            : "Mark as Completed"
            );

            completeButton.setBackground(background(
                    completed[index]
                            ? Color.rgb(115, 123, 145)
                            : PRIMARY,
                    10
            ));

            updateSummary();

            Toast.makeText(
                    AssignmentsActivity.this,
                    completed[index]
                            ? "Assignment marked as completed."
                            : "Assignment marked as not completed.",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    private void updateSummary() {
        int count = 0;

        for (boolean isCompleted : completed) {
            if (isCompleted) {
                count++;
            }
        }

        completedCount = count;

        if (totalText != null) {
            totalText.setText(titles.length + " Assignments");
        }

        if (completedText != null) {
            completedText.setText(
                    completedCount + " of " + titles.length + " completed"
            );
        }
    }

    private GradientDrawable background(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radius));
        drawable.setStroke(dp(1), Color.rgb(230, 233, 242));
        return drawable;
    }

    private int dp(float value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }
}
