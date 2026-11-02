
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

public class AnnouncementsActivity extends Activity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;

    private LinearLayout announcementContainer;

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
        title.setText("Announcements");
        title.setTextColor(TEXT);
        title.setTextSize(28);
        title.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        // Subtitle
        TextView subtitle = new TextView(this);
        subtitle.setText("Stay informed about the latest news and events.");
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
        summaryLabel.setText("LATEST UPDATES");
        summaryLabel.setTextColor(MUTED);
        summaryLabel.setTextSize(12);
        summaryLabel.setTypeface(null, Typeface.BOLD);

        TextView summaryNumber = new TextView(this);
        summaryNumber.setText("4 announcements");
        summaryNumber.setTextColor(PRIMARY);
        summaryNumber.setTextSize(23);
        summaryNumber.setTypeface(null, Typeface.BOLD);

        summaryCard.addView(summaryLabel);
        summaryCard.addView(summaryNumber);

        LinearLayout.LayoutParams summaryParams =
                new LinearLayout.LayoutParams(-1, -2);
        summaryParams.bottomMargin = dp(22);
        root.addView(summaryCard, summaryParams);

        // Section heading
        TextView sectionTitle = new TextView(this);
        sectionTitle.setText("Recent Announcements");
        sectionTitle.setTextColor(TEXT);
        sectionTitle.setTextSize(19);
        sectionTitle.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams sectionParams =
                new LinearLayout.LayoutParams(-1, -2);
        sectionParams.bottomMargin = dp(12);
        root.addView(sectionTitle, sectionParams);

        // Announcement list
        announcementContainer = new LinearLayout(this);
        announcementContainer.setOrientation(LinearLayout.VERTICAL);

        root.addView(announcementContainer);

        addAnnouncement(
                "Exam Schedule",
                "The examination timetable is available. Please check your subjects and exam dates.",
                "Academic Notice",
                "28 October 2026",
                Color.rgb(82, 103, 232)
        );

        addAnnouncement(
                "Project Submission",
                "Students should submit their university projects before the announced deadline.",
                "Important",
                "26 October 2026",
                Color.rgb(231, 151, 52)
        );

        addAnnouncement(
                "Programming Workshop",
                "A Java programming workshop will take place in the computer laboratory.",
                "Upcoming Event",
                "24 October 2026",
                Color.rgb(45, 165, 115)
        );

        addAnnouncement(
                "Library Information",
                "Remember to return borrowed books and respect the library opening hours.",
                "General Notice",
                "20 October 2026",
                Color.rgb(135, 91, 205)
        );

        setContentView(scrollView);
    }

    private void addAnnouncement(
            String heading,
            String description,
            String category,
            String date,
            int accentColor
    ) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(16), dp(16), dp(16));
        card.setBackground(background(WHITE, 14));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(14);

        announcementContainer.addView(card, cardParams);

        // Category label
        TextView categoryView = new TextView(this);
        categoryView.setText(category);
        categoryView.setTextColor(accentColor);
        categoryView.setTextSize(12);
        categoryView.setTypeface(null, Typeface.BOLD);
        categoryView.setPadding(dp(10), dp(6), dp(10), dp(6));
        categoryView.setBackground(background(
                Color.rgb(245, 247, 252), 8));

        card.addView(categoryView, new LinearLayout.LayoutParams(
                -2, -2));

        // Announcement heading
        TextView headingView = new TextView(this);
        headingView.setText(heading);
        headingView.setTextColor(TEXT);
        headingView.setTextSize(18);
        headingView.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams headingParams =
                new LinearLayout.LayoutParams(-1, -2);
        headingParams.topMargin = dp(12);
        card.addView(headingView, headingParams);

        // Description
        TextView descriptionView = new TextView(this);
        descriptionView.setText(description);
        descriptionView.setTextColor(MUTED);
        descriptionView.setTextSize(14);
        descriptionView.setLineSpacing(dp(3), 1.0f);

        LinearLayout.LayoutParams descriptionParams =
                new LinearLayout.LayoutParams(-1, -2);
        descriptionParams.topMargin = dp(8);
        card.addView(descriptionView, descriptionParams);

        // Date
        TextView dateView = new TextView(this);
        dateView.setText("Published: " + date);
        dateView.setTextColor(MUTED);
        dateView.setTextSize(12);

        LinearLayout.LayoutParams dateParams =
                new LinearLayout.LayoutParams(-1, -2);
        dateParams.topMargin = dp(14);
        card.addView(dateView, dateParams);
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
