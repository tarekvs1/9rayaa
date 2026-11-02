
package com.example.studify;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimetableActivity extends AppCompatActivity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;

    private LinearLayout root;
    private LinearLayout dayButtonsContainer;
    private LinearLayout lessonsContainer;

    private final String[] days = {
            "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"
    };

    private final String[][] timetable = {
            {"08:30 - 10:00", "Java Programming", "Room A12"},
            {"10:15 - 11:45", "Database Systems", "Room B04"},
            {"13:00 - 14:30", "Web Development", "Lab 2"},
            {"14:45 - 16:15", "Mathematics", "Room C08"}
    };

    private final String[][][] weeklyLessons = {
            {
                    {"08:30 - 10:00", "Java Programming", "Room A12"},
                    {"10:15 - 11:45", "Database Systems", "Room B04"},
                    {"13:00 - 14:30", "Web Development", "Lab 2"}
            },
            {
                    {"08:30 - 10:00", "Mathematics", "Room C08"},
                    {"10:15 - 11:45", "Computer Networks", "Room B11"},
                    {"13:00 - 14:30", "English", "Room A03"}
            },
            {
                    {"08:30 - 10:00", "Software Engineering", "Room A14"},
                    {"10:15 - 11:45", "Java Programming", "Lab 1"},
                    {"14:00 - 15:30", "Statistics", "Room C02"}
            },
            {
                    {"08:30 - 10:00", "Database Systems", "Room B04"},
                    {"10:15 - 11:45", "Web Development", "Lab 2"},
                    {"13:00 - 14:30", "Project Management", "Room A09"}
            },
            {
                    {"08:30 - 10:00", "Computer Networks", "Room B11"},
                    {"10:15 - 11:45", "Software Engineering", "Room A14"},
                    {"13:00 - 14:30", "Mathematics", "Room C08"}
            },
            {
                    {"09:00 - 10:30", "English", "Room A03"},
                    {"10:45 - 12:15", "Study Session", "Library"}
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTimetableScreen();
    }

    private void createTimetableScreen() {

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(BACKGROUND);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(22), dp(20), dp(25));

        scrollView.addView(root);

        // Back button
        Button backButton = new Button(this);
        backButton.setText("< Back to Dashboard");
        backButton.setAllCaps(false);
        backButton.setTextColor(PRIMARY);
        backButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(BACKGROUND)
        );

        root.addView(backButton);
        backButton.setOnClickListener(v -> finish());

        // Heading
        TextView title = new TextView(this);
        title.setText("My Timetable");
        title.setTextSize(28);
        title.setTextColor(TEXT);
        title.setTypeface(null, Typeface.BOLD);
        title.setPadding(0, dp(12), 0, dp(5));
        root.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Your weekly classes at a glance.");
        subtitle.setTextSize(14);
        subtitle.setTextColor(MUTED);
        subtitle.setPadding(0, 0, 0, dp(20));
        root.addView(subtitle);

        // Day selector
        TextView dayLabel = new TextView(this);
        dayLabel.setText("Select a day");
        dayLabel.setTextSize(17);
        dayLabel.setTextColor(TEXT);
        dayLabel.setTypeface(null, Typeface.BOLD);
        dayLabel.setPadding(0, 0, 0, dp(10));
        root.addView(dayLabel);

        HorizontalScrollView dayScroll = new HorizontalScrollView(this);
        dayScroll.setHorizontalScrollBarEnabled(false);

        dayButtonsContainer = new LinearLayout(this);
        dayButtonsContainer.setOrientation(LinearLayout.HORIZONTAL);

        dayScroll.addView(dayButtonsContainer);
        root.addView(dayScroll);

        for (int i = 0; i < days.length; i++) {
            final int dayIndex = i;

            Button dayButton = new Button(this);
            dayButton.setText(days[i]);
            dayButton.setTextSize(12);
            dayButton.setAllCaps(false);
            dayButton.setMinWidth(0);
            dayButton.setPadding(dp(10), 0, dp(10), 0);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            dp(44)
                    );
            params.setMargins(0, 0, dp(7), dp(15));

            dayButtonsContainer.addView(dayButton, params);

            dayButton.setOnClickListener(v -> showDay(dayIndex));
        }

        // Lessons heading
        TextView lessonsTitle = new TextView(this);
        lessonsTitle.setText("Class Schedule");
        lessonsTitle.setTextSize(19);
        lessonsTitle.setTextColor(TEXT);
        lessonsTitle.setTypeface(null, Typeface.BOLD);
        lessonsTitle.setPadding(0, dp(8), 0, dp(12));
        root.addView(lessonsTitle);

        lessonsContainer = new LinearLayout(this);
        lessonsContainer.setOrientation(LinearLayout.VERTICAL);
        root.addView(lessonsContainer);

        // Display Monday by default
        showDay(0);

        setContentView(scrollView);
    }

    private void showDay(int dayIndex) {

        lessonsContainer.removeAllViews();

        for (int i = 0; i < dayButtonsContainer.getChildCount(); i++) {
            Button button = (Button) dayButtonsContainer.getChildAt(i);

            int backgroundColor =
                    i == dayIndex ? PRIMARY : WHITE;

            int textColor =
                    i == dayIndex ? WHITE : TEXT;

            button.setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(
                            backgroundColor
                    )
            );
            button.setTextColor(textColor);
        }

        String[][] lessons = weeklyLessons[dayIndex];

        if (lessons.length == 0) {
            TextView empty = new TextView(this);
            empty.setText("No classes scheduled for this day.");
            empty.setTextSize(15);
            empty.setTextColor(MUTED);
            empty.setGravity(Gravity.CENTER);
            empty.setPadding(0, dp(30), 0, dp(30));
            lessonsContainer.addView(empty);
            return;
        }

        for (String[] lesson : lessons) {
            addLessonCard(lesson[0], lesson[1], lesson[2]);
        }
    }

    private void addLessonCard(
            String time,
            String subject,
            String room
    ) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp(15), dp(16), dp(15), dp(16));
        card.setBackground(makeBackground(WHITE, 15));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        cardParams.setMargins(0, 0, 0, dp(12));

        // Time block
        TextView timeText = new TextView(this);
        timeText.setText(time);
        timeText.setTextSize(12);
        timeText.setTextColor(PRIMARY);
        timeText.setTypeface(null, Typeface.BOLD);
        timeText.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams timeParams =
                new LinearLayout.LayoutParams(dp(105),
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        timeParams.setMargins(0, 0, dp(12), 0);

        card.addView(timeText, timeParams);

        // Subject and room
        LinearLayout details = new LinearLayout(this);
        details.setOrientation(LinearLayout.VERTICAL);

        TextView subjectText = new TextView(this);
        subjectText.setText(subject);
        subjectText.setTextSize(15);
        subjectText.setTextColor(TEXT);
        subjectText.setTypeface(null, Typeface.BOLD);
        details.addView(subjectText);

        TextView roomText = new TextView(this);
        roomText.setText(room);
        roomText.setTextSize(13);
        roomText.setTextColor(MUTED);
        roomText.setPadding(0, dp(5), 0, 0);
        details.addView(roomText);

        card.addView(details, new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        ));

        lessonsContainer.addView(card, cardParams);
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
