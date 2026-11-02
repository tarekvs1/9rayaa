
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AttendanceActivity extends Activity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;
    private final int GREEN = Color.rgb(34, 150, 100);
    private final int RED = Color.rgb(220, 80, 80);
    private final int ORANGE = Color.rgb(210, 135, 35);

    private TextView attendanceRate;
    private TextView presentCount;
    private TextView absentCount;
    private TextView totalCount;
    private LinearLayout historyContainer;

    private final String[] subjects = {
            "Java Programming",
            "Database Systems",
            "Web Development",
            "Computer Networks",
            "Software Engineering"
    };

    private final String[] dates = {
            "27 October 2026",
            "26 October 2026",
            "24 October 2026",
            "23 October 2026",
            "22 October 2026"
    };

    // 1 = Present, 0 = Absent, -1 = Not marked
    private final int[] attendance = {1, 1, 0, 1, -1};

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

        // Title
        TextView title = new TextView(this);
        title.setText("Attendance");
        title.setTextColor(TEXT);
        title.setTextSize(28);
        title.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        // Subtitle
        TextView subtitle = new TextView(this);
        subtitle.setText("Track your attendance and class participation.");
        subtitle.setTextColor(MUTED);
        subtitle.setTextSize(14);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(6);
        subtitleParams.bottomMargin = dp(20);
        root.addView(subtitle, subtitleParams);

        // Statistics card
        LinearLayout statsCard = new LinearLayout(this);
        statsCard.setOrientation(LinearLayout.VERTICAL);
        statsCard.setPadding(dp(18), dp(18), dp(18), dp(18));
        statsCard.setBackground(background(WHITE, 16));

        TextView statsTitle = new TextView(this);
        statsTitle.setText("ATTENDANCE OVERVIEW");
        statsTitle.setTextColor(MUTED);
        statsTitle.setTextSize(12);
        statsTitle.setTypeface(null, Typeface.BOLD);

        attendanceRate = new TextView(this);
        attendanceRate.setTextColor(PRIMARY);
        attendanceRate.setTextSize(32);
        attendanceRate.setTypeface(null, Typeface.BOLD);

        TextView rateLabel = new TextView(this);
        rateLabel.setText("Attendance rate");
        rateLabel.setTextColor(MUTED);
        rateLabel.setTextSize(13);

        statsCard.addView(statsTitle);

        LinearLayout.LayoutParams rateParams =
                new LinearLayout.LayoutParams(-1, -2);
        rateParams.topMargin = dp(10);
        statsCard.addView(attendanceRate, rateParams);
        statsCard.addView(rateLabel);

        // Counts row
        LinearLayout countsRow = new LinearLayout(this);
        countsRow.setOrientation(LinearLayout.HORIZONTAL);

        presentCount = createCountView("Present", GREEN);
        absentCount = createCountView("Absent", RED);
        totalCount = createCountView("Recorded", PRIMARY);

        countsRow.addView(presentCount, new LinearLayout.LayoutParams(
                0, -2, 1));
        countsRow.addView(absentCount, new LinearLayout.LayoutParams(
                0, -2, 1));
        countsRow.addView(totalCount, new LinearLayout.LayoutParams(
                0, -2, 1));

        LinearLayout.LayoutParams countsParams =
                new LinearLayout.LayoutParams(-1, -2);
        countsParams.topMargin = dp(20);
        statsCard.addView(countsRow, countsParams);

        LinearLayout.LayoutParams statsParams =
                new LinearLayout.LayoutParams(-1, -2);
        statsParams.bottomMargin = dp(22);
        root.addView(statsCard, statsParams);

        // History heading
        TextView historyTitle = new TextView(this);
        historyTitle.setText("Attendance History");
        historyTitle.setTextColor(TEXT);
        historyTitle.setTextSize(20);
        historyTitle.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams historyTitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        historyTitleParams.bottomMargin = dp(12);
        root.addView(historyTitle, historyTitleParams);

        historyContainer = new LinearLayout(this);
        historyContainer.setOrientation(LinearLayout.VERTICAL);
        root.addView(historyContainer);

        for (int i = 0; i < subjects.length; i++) {
            addAttendanceCard(i);
        }

        updateStatistics();

        setContentView(scrollView);
    }

    private TextView createCountView(String label, int color) {
        TextView view = new TextView(this);
        view.setTextColor(color);
        view.setTextSize(13);
        view.setTypeface(null, Typeface.BOLD);
        view.setGravity(Gravity.CENTER);
        view.setText(label + "\n0");
        return view;
    }

    private void addAttendanceCard(int index) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(16), dp(16), dp(16));
        card.setBackground(background(WHITE, 14));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(14);
        historyContainer.addView(card, cardParams);

        TextView subjectView = new TextView(this);
        subjectView.setText(subjects[index]);
        subjectView.setTextColor(TEXT);
        subjectView.setTextSize(17);
        subjectView.setTypeface(null, Typeface.BOLD);
        card.addView(subjectView);

        TextView dateView = new TextView(this);
        dateView.setText("Date: " + dates[index]);
        dateView.setTextColor(MUTED);
        dateView.setTextSize(13);

        LinearLayout.LayoutParams dateParams =
                new LinearLayout.LayoutParams(-1, -2);
        dateParams.topMargin = dp(6);
        card.addView(dateView, dateParams);

        TextView statusView = new TextView(this);
        statusView.setTextSize(13);
        statusView.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams statusParams =
                new LinearLayout.LayoutParams(-1, -2);
        statusParams.topMargin = dp(10);
        card.addView(statusView, statusParams);

        updateStatus(statusView, index);

        // Buttons row
        LinearLayout buttonsRow = new LinearLayout(this);
        buttonsRow.setOrientation(LinearLayout.HORIZONTAL);

        Button presentButton = new Button(this);
        presentButton.setText("Present");
        presentButton.setTextColor(WHITE);
        presentButton.setTextSize(12);
        presentButton.setAllCaps(false);
        presentButton.setBackground(background(GREEN, 10));

        Button absentButton = new Button(this);
        absentButton.setText("Absent");
        absentButton.setTextColor(WHITE);
        absentButton.setTextSize(12);
        absentButton.setAllCaps(false);
        absentButton.setBackground(background(RED, 10));

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(0, dp(44), 1);
        buttonParams.topMargin = dp(10);
        buttonParams.rightMargin = dp(6);
        buttonsRow.addView(presentButton, buttonParams);

        LinearLayout.LayoutParams absentParams =
                new LinearLayout.LayoutParams(0, dp(44), 1);
        absentParams.topMargin = dp(10);
        absentParams.leftMargin = dp(6);
        buttonsRow.addView(absentButton, absentParams);

        card.addView(buttonsRow);

        presentButton.setOnClickListener(v -> {
            attendance[index] = 1;
            updateStatus(statusView, index);
            updateStatistics();

            Toast.makeText(this,
                    "Attendance marked Present.",
                    Toast.LENGTH_SHORT).show();
        });

        absentButton.setOnClickListener(v -> {
            attendance[index] = 0;
            updateStatus(statusView, index);
            updateStatistics();

            Toast.makeText(this,
                    "Attendance marked Absent.",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void updateStatus(TextView statusView, int index) {
        if (attendance[index] == 1) {
            statusView.setText("Status: Present");
            statusView.setTextColor(GREEN);
        } else if (attendance[index] == 0) {
            statusView.setText("Status: Absent");
            statusView.setTextColor(RED);
        } else {
            statusView.setText("Status: Not marked");
            statusView.setTextColor(ORANGE);
        }
    }

    private void updateStatistics() {
        int present = 0;
        int absent = 0;
        int recorded = 0;

        for (int status : attendance) {
            if (status == 1) {
                present++;
                recorded++;
            } else if (status == 0) {
                absent++;
                recorded++;
            }
        }

        int rate = recorded == 0
                ? 0
                : Math.round((present * 100f) / recorded);

        attendanceRate.setText(rate + "%");
        presentCount.setText("Present\n" + present);
        absentCount.setText("Absent\n" + absent);
        totalCount.setText("Recorded\n" + recorded);
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
