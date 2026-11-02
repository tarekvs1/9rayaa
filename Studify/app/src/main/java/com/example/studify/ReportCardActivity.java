
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

public class ReportCardActivity extends Activity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;
    private final int GREEN = Color.rgb(34, 150, 100);
    private final int RED = Color.rgb(220, 80, 80);
    private final int ORANGE = Color.rgb(210, 135, 35);

    private final String[] subjects = {
            "Java Programming",
            "Database Systems",
            "Web Development",
            "Mathematics",
            "Computer Networks",
            "Software Engineering"
    };

    private final double[] grades = {
            16.5, 14.0, 17.0, 12.5, 15.0, 18.0
    };

    private final int[] coefficients = {
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

        // Header
        TextView title = new TextView(this);
        title.setText("Academic Report Card");
        title.setTextColor(TEXT);
        title.setTextSize(27);
        title.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        TextView subtitle = new TextView(this);
        subtitle.setText("Your academic performance at a glance.");
        subtitle.setTextColor(MUTED);
        subtitle.setTextSize(14);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(6);
        subtitleParams.bottomMargin = dp(20);
        root.addView(subtitle, subtitleParams);

        // Student information
        LinearLayout studentCard = createCard();

        TextView studentHeading = createText(
                "STUDENT INFORMATION", 12, MUTED, true);
        studentCard.addView(studentHeading);

        addInfoRow(studentCard, "Student Name", "Student Account");
        addInfoRow(studentCard, "Student ID", "STU001");
        addInfoRow(studentCard, "Class", "Class A");
        addInfoRow(studentCard, "Academic Year", "2026–2027");
        addInfoRow(studentCard, "Semester", "Semester 1");

        LinearLayout.LayoutParams studentParams =
                new LinearLayout.LayoutParams(-1, -2);
        studentParams.bottomMargin = dp(18);
        root.addView(studentCard, studentParams);

        // Calculate weighted average
        double average = calculateAverage();

        // Average card
        LinearLayout averageCard = createCard();
        averageCard.setGravity(Gravity.CENTER);
        averageCard.setPadding(dp(20), dp(22), dp(20), dp(22));

        TextView averageLabel = createText(
                "WEIGHTED AVERAGE", 12, MUTED, true);
        averageCard.addView(averageLabel);

        TextView averageValue = createText(
                String.format(Locale.getDefault(), "%.2f / 20", average),
                34, PRIMARY, true);

        LinearLayout.LayoutParams averageValueParams =
                new LinearLayout.LayoutParams(-1, -2);
        averageValueParams.topMargin = dp(8);
        averageCard.addView(averageValue, averageValueParams);

        TextView result = createText(
                average >= 10 ? "PASSED" : "NOT PASSED",
                14, average >= 10 ? GREEN : RED, true);

        LinearLayout.LayoutParams resultParams =
                new LinearLayout.LayoutParams(-1, -2);
        resultParams.topMargin = dp(8);
        averageCard.addView(result, resultParams);

        LinearLayout.LayoutParams averageCardParams =
                new LinearLayout.LayoutParams(-1, -2);
        averageCardParams.bottomMargin = dp(22);
        root.addView(averageCard, averageCardParams);

        // Grades heading
        TextView gradesHeading = createText(
                "Subject Results", 20, TEXT, true);

        LinearLayout.LayoutParams gradesHeadingParams =
                new LinearLayout.LayoutParams(-1, -2);
        gradesHeadingParams.bottomMargin = dp(12);
        root.addView(gradesHeading, gradesHeadingParams);

        // Grades table
        LinearLayout table = createCard();
        table.setPadding(dp(12), dp(12), dp(12), dp(12));

        LinearLayout headerRow = new LinearLayout(this);
        headerRow.setOrientation(LinearLayout.HORIZONTAL);
        headerRow.setPadding(0, dp(8), 0, dp(12));

        addTableCell(headerRow, "Subject", 2, true);
        addTableCell(headerRow, "Grade", 1, true);
        addTableCell(headerRow, "Coef.", 1, true);

        table.addView(headerRow);
        addDivider(table);

        for (int i = 0; i < subjects.length; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setPadding(0, dp(12), 0, dp(12));

            addTableCell(row, subjects[i], 2, false);

            TextView gradeCell = addTableCell(
                    row,
                    String.format(Locale.getDefault(), "%.1f", grades[i]),
                    1,
                    false
            );
            gradeCell.setTextColor(colorForGrade(grades[i]));
            gradeCell.setTypeface(null, Typeface.BOLD);

            addTableCell(row, String.valueOf(coefficients[i]), 1, false);

            table.addView(row);

            if (i < subjects.length - 1) {
                addDivider(table);
            }
        }

        root.addView(table);

        // Footer
        TextView footer = createText(
                "This report card contains sample academic data.",
                12, MUTED, false);
        footer.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams footerParams =
                new LinearLayout.LayoutParams(-1, -2);
        footerParams.topMargin = dp(20);
        root.addView(footer, footerParams);

        setContentView(scrollView);
    }

    private LinearLayout createCard() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(18), dp(18), dp(18), dp(18));
        card.setBackground(background(WHITE, 16));
        return card;
    }

    private void addInfoRow(
            LinearLayout parent,
            String label,
            String value
    ) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, dp(10), 0, dp(10));

        TextView labelView = createText(label, 13, MUTED, false);
        TextView valueView = createText(value, 13, TEXT, true);
        valueView.setGravity(Gravity.END);

        row.addView(labelView, new LinearLayout.LayoutParams(
                0, -2, 1));

        row.addView(valueView, new LinearLayout.LayoutParams(
                0, -2, 1));

        parent.addView(row);
    }

    private TextView addTableCell(
            LinearLayout row,
            String value,
            int weight,
            boolean isHeader
    ) {
        TextView cell = createText(
                value,
                isHeader ? 11 : 12,
                isHeader ? MUTED : TEXT,
                isHeader
        );

        cell.setGravity(
                isHeader ? Gravity.START : Gravity.CENTER_VERTICAL
        );

        row.addView(cell, new LinearLayout.LayoutParams(
                0, -2, weight));

        return cell;
    }

    private void addDivider(LinearLayout parent) {
        android.view.View divider = new android.view.View(this);
        divider.setBackgroundColor(Color.rgb(230, 233, 242));

        parent.addView(divider, new LinearLayout.LayoutParams(
                -1, dp(1)));
    }

    private double calculateAverage() {
        double weightedTotal = 0;
        int totalCoefficients = 0;

        for (int i = 0; i < grades.length; i++) {
            weightedTotal += grades[i] * coefficients[i];
            totalCoefficients += coefficients[i];
        }

        if (totalCoefficients == 0) {
            return 0;
        }

        return weightedTotal / totalCoefficients;
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

    private TextView createText(
            String value,
            float size,
            int color,
            boolean bold
    ) {
        TextView textView = new TextView(this);
        textView.setText(value);
        textView.setTextSize(size);
        textView.setTextColor(color);

        if (bold) {
            textView.setTypeface(null, Typeface.BOLD);
        }

        return textView;
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
