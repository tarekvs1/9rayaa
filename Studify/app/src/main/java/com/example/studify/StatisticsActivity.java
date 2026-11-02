
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

public class StatisticsActivity extends Activity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;
    private final int GREEN = Color.rgb(34, 150, 100);
    private final int ORANGE = Color.rgb(230, 155, 45);
    private final int PURPLE = Color.rgb(140, 95, 210);
    private final int BLUE = Color.rgb(65, 145, 220);

    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createScreen();
    }

    private void createScreen() {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(BACKGROUND);

        root = new LinearLayout(this);
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
        TextView title = createText(
                "Statistics", 28, TEXT, true);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        TextView subtitle = createText(
                "An overview of your academic progress.",
                14, MUTED, false);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(6);
        subtitleParams.bottomMargin = dp(22);
        root.addView(subtitle, subtitleParams);

        // Main overview card
        LinearLayout overviewCard = createCard();

        TextView overviewLabel = createText(
                "ACADEMIC OVERVIEW", 12, MUTED, true);
        overviewCard.addView(overviewLabel);

        TextView overviewDescription = createText(
                "Your performance at a glance",
                16, TEXT, true);

        LinearLayout.LayoutParams descriptionParams =
                new LinearLayout.LayoutParams(-1, -2);
        descriptionParams.topMargin = dp(8);
        overviewCard.addView(overviewDescription, descriptionParams);

        TextView semesterText = createText(
                "Academic Year 2026–2027  •  Semester 1",
                12, MUTED, false);

        LinearLayout.LayoutParams semesterParams =
                new LinearLayout.LayoutParams(-1, -2);
        semesterParams.topMargin = dp(5);
        overviewCard.addView(semesterText, semesterParams);

        LinearLayout.LayoutParams overviewParams =
                new LinearLayout.LayoutParams(-1, -2);
        overviewParams.bottomMargin = dp(18);
        root.addView(overviewCard, overviewParams);

        // Statistic cards in a two-column layout
        LinearLayout firstRow = new LinearLayout(this);
        firstRow.setOrientation(LinearLayout.HORIZONTAL);

        addMetricCard(firstRow, "Students", "12",
                "Registered students", PRIMARY);

        addMetricCard(firstRow, "Subjects", "8",
                "Available subjects", PURPLE);

        LinearLayout.LayoutParams rowParams =
                new LinearLayout.LayoutParams(-1, -2);
        rowParams.bottomMargin = dp(12);
        root.addView(firstRow, rowParams);

        LinearLayout secondRow = new LinearLayout(this);
        secondRow.setOrientation(LinearLayout.HORIZONTAL);

        addMetricCard(secondRow, "Attendance", "92%",
                "Average attendance", GREEN);

        addMetricCard(secondRow, "Assignments", "80%",
                "Completion rate", ORANGE);

        LinearLayout.LayoutParams secondRowParams =
                new LinearLayout.LayoutParams(-1, -2);
        secondRowParams.bottomMargin = dp(24);
        root.addView(secondRow, secondRowParams);

        // Progress section
        TextView progressTitle = createText(
                "Performance Overview", 20, TEXT, true);

        LinearLayout.LayoutParams progressTitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        progressTitleParams.bottomMargin = dp(12);
        root.addView(progressTitle, progressTitleParams);

        LinearLayout progressCard = createCard();

        addProgressRow(progressCard, "Java Programming",
                85, PRIMARY);

        addProgressRow(progressCard, "Database Systems",
                72, PURPLE);

        addProgressRow(progressCard, "Web Development",
                90, GREEN);

        addProgressRow(progressCard, "Mathematics",
                65, ORANGE);

        addProgressRow(progressCard, "Computer Networks",
                78, BLUE);

        root.addView(progressCard);

        // Attendance section
        TextView attendanceTitle = createText(
                "Attendance Summary", 20, TEXT, true);

        LinearLayout.LayoutParams attendanceTitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        attendanceTitleParams.topMargin = dp(24);
        attendanceTitleParams.bottomMargin = dp(12);
        root.addView(attendanceTitle, attendanceTitleParams);

        LinearLayout attendanceCard = createCard();

        addProgressRow(attendanceCard, "Present",
                92, GREEN);

        addProgressRow(attendanceCard, "Absent",
                8, Color.rgb(220, 80, 80));

        root.addView(attendanceCard);

        // Assignments section
        TextView assignmentTitle = createText(
                "Assignment Summary", 20, TEXT, true);

        LinearLayout.LayoutParams assignmentTitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        assignmentTitleParams.topMargin = dp(24);
        assignmentTitleParams.bottomMargin = dp(12);
        root.addView(assignmentTitle, assignmentTitleParams);

        LinearLayout assignmentCard = createCard();

        addProgressRow(assignmentCard, "Completed",
                80, GREEN);

        addProgressRow(assignmentCard, "Pending",
                20, ORANGE);

        root.addView(assignmentCard);

        // Footer
        TextView footer = createText(
                "Statistics are illustrative sample data.",
                12, MUTED, false);
        footer.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams footerParams =
                new LinearLayout.LayoutParams(-1, -2);
        footerParams.topMargin = dp(22);
        root.addView(footer, footerParams);

        setContentView(scrollView);
    }

    private void addMetricCard(
            LinearLayout parent,
            String label,
            String value,
            String description,
            int accentColor
    ) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(14), dp(16), dp(14), dp(16));
        card.setBackground(background(WHITE, 14));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(0, -1, 1);
        cardParams.leftMargin = dp(4);
        cardParams.rightMargin = dp(4);
        parent.addView(card, cardParams);

        TextView labelView = createText(
                label, 13, MUTED, true);
        card.addView(labelView);

        TextView valueView = createText(
                value, 27, accentColor, true);

        LinearLayout.LayoutParams valueParams =
                new LinearLayout.LayoutParams(-1, -2);
        valueParams.topMargin = dp(10);
        card.addView(valueView, valueParams);

        TextView descriptionView = createText(
                description, 11, MUTED, false);

        LinearLayout.LayoutParams descriptionParams =
                new LinearLayout.LayoutParams(-1, -2);
        descriptionParams.topMargin = dp(5);
        card.addView(descriptionView, descriptionParams);
    }

    private void addProgressRow(
            LinearLayout parent,
            String label,
            int progress,
            int color
    ) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.VERTICAL);
        row.setPadding(0, dp(10), 0, dp(10));

        LinearLayout labelRow = new LinearLayout(this);
        labelRow.setOrientation(LinearLayout.HORIZONTAL);
        labelRow.setGravity(Gravity.CENTER_VERTICAL);

        TextView labelView = createText(
                label, 13, TEXT, true);

        TextView percentageView = createText(
                String.format(Locale.getDefault(), "%d%%", progress),
                13, color, true);
        percentageView.setGravity(Gravity.END);

        labelRow.addView(labelView, new LinearLayout.LayoutParams(
                0, -2, 1));

        labelRow.addView(percentageView, new LinearLayout.LayoutParams(
                -2, -2));

        row.addView(labelRow);

        android.widget.ProgressBar progressBar =
                new android.widget.ProgressBar(
                        this,
                        null,
                        android.R.attr.progressBarStyleHorizontal
                );

        progressBar.setMax(100);
        progressBar.setProgress(progress);

        progressBar.setProgressTintList(
                android.content.res.ColorStateList.valueOf(color));

        progressBar.setProgressBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        Color.rgb(232, 235, 244)));

        LinearLayout.LayoutParams barParams =
                new LinearLayout.LayoutParams(-1, dp(8));
        barParams.topMargin = dp(10);
        row.addView(progressBar, barParams);

        parent.addView(row);
    }

    private LinearLayout createCard() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(18), dp(18), dp(18), dp(18));
        card.setBackground(background(WHITE, 16));
        return card;
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
