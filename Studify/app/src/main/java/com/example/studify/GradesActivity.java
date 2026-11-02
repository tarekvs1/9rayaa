
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

import java.util.Locale;

public class GradesActivity extends Activity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;
    private final int GREEN = Color.rgb(34, 150, 100);
    private final int ORANGE = Color.rgb(210, 135, 35);
    private final int RED = Color.rgb(220, 80, 80);

    private TextView averageText;
    private TextView resultText;
    private LinearLayout gradesContainer;

    private final String[] subjects = {
            "Java Programming",
            "Database Systems",
            "Web Development",
            "Mathematics",
            "Computer Networks",
            "Software Engineering"
    };

    // Sample grades out of 20
    private final double[] grades = {
            16.5, 14.0, 17.0, 12.5, 15.0, 18.0
    };

    private final double[] coefficients = {
            3, 2, 2, 2, 2, 3
    };

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
        title.setText("My Grades");
        title.setTextColor(TEXT);
        title.setTextSize(28);
        title.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        TextView subtitle = new TextView(this);
        subtitle.setText("Review your academic performance by subject.");
        subtitle.setTextColor(MUTED);
        subtitle.setTextSize(14);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(6);
        subtitleParams.bottomMargin = dp(20);
        root.addView(subtitle, subtitleParams);

        // Average summary card
        LinearLayout summaryCard = new LinearLayout(this);
        summaryCard.setOrientation(LinearLayout.VERTICAL);
        summaryCard.setPadding(dp(20), dp(20), dp(20), dp(20));
        summaryCard.setBackground(background(WHITE, 16));

        TextView summaryLabel = new TextView(this);
        summaryLabel.setText("WEIGHTED AVERAGE");
        summaryLabel.setTextColor(MUTED);
        summaryLabel.setTextSize(12);
        summaryLabel.setTypeface(null, Typeface.BOLD);

        averageText = new TextView(this);
        averageText.setTextColor(PRIMARY);
        averageText.setTextSize(36);
        averageText.setTypeface(null, Typeface.BOLD);

        resultText = new TextView(this);
        resultText.setTextColor(GREEN);
        resultText.setTextSize(14);
        resultText.setTypeface(null, Typeface.BOLD);

        summaryCard.addView(summaryLabel);

        LinearLayout.LayoutParams averageParams =
                new LinearLayout.LayoutParams(-1, -2);
        averageParams.topMargin = dp(8);
        summaryCard.addView(averageText, averageParams);

        LinearLayout.LayoutParams resultParams =
                new LinearLayout.LayoutParams(-1, -2);
        resultParams.topMargin = dp(4);
        summaryCard.addView(resultText, resultParams);

        LinearLayout.LayoutParams summaryParams =
                new LinearLayout.LayoutParams(-1, -2);
        summaryParams.bottomMargin = dp(22);
        root.addView(summaryCard, summaryParams);

        // Subjects heading
        TextView sectionTitle = new TextView(this);
        sectionTitle.setText("Subject Results");
        sectionTitle.setTextColor(TEXT);
        sectionTitle.setTextSize(20);
        sectionTitle.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams sectionParams =
                new LinearLayout.LayoutParams(-1, -2);
        sectionParams.bottomMargin = dp(12);
        root.addView(sectionTitle, sectionParams);

        gradesContainer = new LinearLayout(this);
        gradesContainer.setOrientation(LinearLayout.VERTICAL);
        root.addView(gradesContainer);

        for (int i = 0; i < subjects.length; i++) {
            addGradeCard(i);
        }

        updateAverage();

        setContentView(scrollView);
    }

    private void addGradeCard(int index) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(16), dp(16), dp(16));
        card.setBackground(background(WHITE, 14));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(14);
        gradesContainer.addView(card, cardParams);

        // Subject name
        TextView subjectView = new TextView(this);
        subjectView.setText(subjects[index]);
        subjectView.setTextColor(TEXT);
        subjectView.setTextSize(17);
        subjectView.setTypeface(null, Typeface.BOLD);

        card.addView(subjectView);

        // Score and coefficient
        LinearLayout infoRow = new LinearLayout(this);
        infoRow.setOrientation(LinearLayout.HORIZONTAL);
        infoRow.setGravity(Gravity.CENTER_VERTICAL);

        TextView scoreView = new TextView(this);
        scoreView.setText(String.format(
                Locale.getDefault(), "%.1f / 20", grades[index]));
        scoreView.setTextColor(colorForGrade(grades[index]));
        scoreView.setTextSize(22);
        scoreView.setTypeface(null, Typeface.BOLD);

        TextView coefficientView = new TextView(this);
        coefficientView.setText("Coefficient: "
                + (int) coefficients[index]);
        coefficientView.setTextColor(MUTED);
        coefficientView.setTextSize(12);
        coefficientView.setGravity(Gravity.END);

        infoRow.addView(scoreView, new LinearLayout.LayoutParams(
                0, -2, 1));
        infoRow.addView(coefficientView, new LinearLayout.LayoutParams(
                0, -2, 1));

        LinearLayout.LayoutParams infoParams =
                new LinearLayout.LayoutParams(-1, -2);
        infoParams.topMargin = dp(12);
        card.addView(infoRow, infoParams);

        // Progress bar
        android.widget.ProgressBar progressBar =
                new android.widget.ProgressBar(
                        this, null,
                        android.R.attr.progressBarStyleHorizontal);

        progressBar.setMax(20);
        progressBar.setProgress((int) Math.round(grades[index]));
        progressBar.setProgressTintList(
                android.content.res.ColorStateList.valueOf(
                        colorForGrade(grades[index])));
        progressBar.setProgressBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        Color.rgb(232, 235, 244)));

        LinearLayout.LayoutParams progressParams =
                new LinearLayout.LayoutParams(-1, dp(8));
        progressParams.topMargin = dp(12);
        card.addView(progressBar, progressParams);

        // Grade status
        TextView statusView = new TextView(this);
        statusView.setText(getGradeStatus(grades[index]));
        statusView.setTextColor(colorForGrade(grades[index]));
        statusView.setTextSize(12);
        statusView.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams statusParams =
                new LinearLayout.LayoutParams(-1, -2);
        statusParams.topMargin = dp(8);
        card.addView(statusView, statusParams);
    }

    private void updateAverage() {
        double weightedTotal = 0;
        double totalCoefficients = 0;

        for (int i = 0; i < grades.length; i++) {
            weightedTotal += grades[i] * coefficients[i];
            totalCoefficients += coefficients[i];
        }

        double average = totalCoefficients == 0
                ? 0
                : weightedTotal / totalCoefficients;

        averageText.setText(String.format(
                Locale.getDefault(), "%.2f / 20", average));

        resultText.setText(average >= 10
                ? "Passing average"
                : "Average below 10/20");

        resultText.setTextColor(average >= 10 ? GREEN : RED);
    }

    private String getGradeStatus(double grade) {
        if (grade >= 16) {
            return "Excellent";
        } else if (grade >= 14) {
            return "Very Good";
        } else if (grade >= 12) {
            return "Good";
        } else if (grade >= 10) {
            return "Pass";
        } else {
            return "Needs Improvement";
        }
    }

    private int colorForGrade(double grade) {
        if (grade >= 14) {
            return GREEN;
        } else if (grade >= 10) {
            return ORANGE;
        } else {
            return RED;
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
