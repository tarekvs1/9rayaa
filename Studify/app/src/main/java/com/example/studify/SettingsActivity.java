
package com.example.studify;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;

    private LinearLayout root;
    private TextView languageValue;
    private TextView themeValue;

    private boolean notificationsEnabled = true;
    private boolean emailUpdatesEnabled = false;
    private boolean darkModeEnabled = false;
    private String selectedLanguage = "English";

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
        TextView title = createText("Settings", 28, TEXT, true);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        TextView subtitle = createText(
                "Personalize your Studify experience.",
                14, MUTED, false);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(6);
        subtitleParams.bottomMargin = dp(22);
        root.addView(subtitle, subtitleParams);

        // Account section
        addSectionTitle("Account");

        LinearLayout accountCard = createCard();

        addNavigationRow(
                accountCard,
                "Profile Information",
                "Manage your personal details",
                () -> showMessage("Open My Profile to view your details.")
        );

        addDivider(accountCard);

        addNavigationRow(
                accountCard,
                "Account Security",
                "Password and account protection",
                () -> showMessage("Account security settings are a demo feature.")
        );

        root.addView(accountCard);

        // Notifications section
        addSectionTitle("Notifications");

        LinearLayout notificationCard = createCard();

        addSwitchRow(
                notificationCard,
                "Push Notifications",
                "Receive important academic updates",
                true
        );

        addDivider(notificationCard);

        addSwitchRow(
                notificationCard,
                "Email Updates",
                "Receive announcements by email",
                false
        );

        root.addView(notificationCard);

        // Appearance section
        addSectionTitle("Appearance");

        LinearLayout appearanceCard = createCard();

        themeValue = createText(
                darkModeEnabled ? "Dark" : "Light",
                13, PRIMARY, true);

        addNavigationRow(
                appearanceCard,
                "App Theme",
                "Current theme: " + (darkModeEnabled ? "Dark" : "Light"),
                this::showThemeDialog
        );

        addDivider(appearanceCard);

        languageValue = createText(
                selectedLanguage, 13, PRIMARY, true);

        addNavigationRow(
                appearanceCard,
                "Language",
                "Choose your preferred language",
                this::showLanguageDialog
        );

        root.addView(appearanceCard);

        // About section
        addSectionTitle("About");

        LinearLayout aboutCard = createCard();

        addNavigationRow(
                aboutCard,
                "About Studify",
                "Version 1.0.0",
                () -> showAboutDialog()
        );

        addDivider(aboutCard);

        addNavigationRow(
                aboutCard,
                "Privacy Policy",
                "How your information is handled",
                () -> showMessage("Privacy policy demonstration screen.")
        );

        root.addView(aboutCard);

        // Reset button
        Button resetButton = new Button(this);
        resetButton.setText("Reset Settings");
        resetButton.setAllCaps(false);
        resetButton.setTextColor(Color.rgb(220, 80, 80));
        resetButton.setBackground(background(WHITE, 12));

        LinearLayout.LayoutParams resetParams =
                new LinearLayout.LayoutParams(-1, dp(50));
        resetParams.topMargin = dp(22);
        root.addView(resetButton, resetParams);

        resetButton.setOnClickListener(v -> confirmReset());

        TextView footer = createText(
                "Studify  •  Version 1.0.0",
                12, MUTED, false);
        footer.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams footerParams =
                new LinearLayout.LayoutParams(-1, -2);
        footerParams.topMargin = dp(20);
        root.addView(footer, footerParams);

        setContentView(scrollView);
    }

    private void addSectionTitle(String title) {
        TextView heading = createText(title, 18, TEXT, true);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = dp(18);
        params.bottomMargin = dp(10);

        root.addView(heading, params);
    }

    private LinearLayout createCard() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(14), dp(4), dp(14), dp(4));
        card.setBackground(background(WHITE, 14));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = dp(4);

        root.addView(card, params);

        return card;
    }

    private void addNavigationRow(
            LinearLayout parent,
            String title,
            String description,
            Runnable action
    ) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(4), dp(14), dp(4), dp(14));

        LinearLayout textContainer = new LinearLayout(this);
        textContainer.setOrientation(LinearLayout.VERTICAL);

        TextView titleView = createText(title, 14, TEXT, true);
        TextView descriptionView = createText(
                description, 12, MUTED, false);

        textContainer.addView(titleView);

        LinearLayout.LayoutParams descriptionParams =
                new LinearLayout.LayoutParams(-1, -2);
        descriptionParams.topMargin = dp(4);
        textContainer.addView(descriptionView, descriptionParams);

        TextView arrow = createText("›", 26, MUTED, false);
        arrow.setGravity(Gravity.CENTER);

        row.addView(textContainer, new LinearLayout.LayoutParams(
                0, -2, 1));

        row.addView(arrow, new LinearLayout.LayoutParams(
                dp(28), dp(40)));

        row.setOnClickListener(v -> action.run());

        parent.addView(row);
    }

    private void addSwitchRow(
            LinearLayout parent,
            String title,
            String description,
            boolean isPushNotification
    ) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(4), dp(12), dp(4), dp(12));

        LinearLayout textContainer = new LinearLayout(this);
        textContainer.setOrientation(LinearLayout.VERTICAL);

        TextView titleView = createText(title, 14, TEXT, true);
        TextView descriptionView = createText(
                description, 12, MUTED, false);

        textContainer.addView(titleView);

        LinearLayout.LayoutParams descriptionParams =
                new LinearLayout.LayoutParams(-1, -2);
        descriptionParams.topMargin = dp(4);
        textContainer.addView(descriptionView, descriptionParams);

        Switch settingSwitch = new Switch(this);
        settingSwitch.setChecked(
                isPushNotification
                        ? notificationsEnabled
                        : emailUpdatesEnabled
        );

        settingSwitch.setOnCheckedChangeListener((button, checked) -> {
            if (isPushNotification) {
                notificationsEnabled = checked;
            } else {
                emailUpdatesEnabled = checked;
            }

            showMessage(title + (checked ? " enabled" : " disabled"));
        });

        row.addView(textContainer, new LinearLayout.LayoutParams(
                0, -2, 1));

        row.addView(settingSwitch);

        parent.addView(row);
    }

    private void showThemeDialog() {
        String[] themes = {"Light", "Dark"};

        new AlertDialog.Builder(this)
                .setTitle("Choose App Theme")
                .setSingleChoiceItems(
                        themes,
                        darkModeEnabled ? 1 : 0,
                        (dialog, which) -> {
                            darkModeEnabled = which == 1;

                            if (themeValue != null) {
                                themeValue.setText(
                                        darkModeEnabled ? "Dark" : "Light");
                            }

                            dialog.dismiss();

                            showMessage(
                                    "Theme preference updated. Full theme switching is not implemented yet."
                            );
                        })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showLanguageDialog() {
        String[] languages = {"English", "French", "Arabic"};

        int selectedIndex = 0;

        for (int i = 0; i < languages.length; i++) {
            if (languages[i].equals(selectedLanguage)) {
                selectedIndex = i;
                break;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle("Choose Language")
                .setSingleChoiceItems(
                        languages,
                        selectedIndex,
                        (dialog, which) -> {
                            selectedLanguage = languages[which];

                            if (languageValue != null) {
                                languageValue.setText(selectedLanguage);
                            }

                            dialog.dismiss();

                            showMessage(
                                    "Language preference changed to "
                                            + selectedLanguage
                                            + ". App translations are not implemented yet."
                            );
                        })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About Studify")
                .setMessage(
                        "Studify Student Manager\n\n"
                                + "Version: 1.0.0\n"
                                + "Platform: Android\n\n"
                                + "Manage students, grades, attendance, "
                                + "assignments and academic information."
                )
                .setPositiveButton("Close", null)
                .show();
    }

    private void confirmReset() {
        new AlertDialog.Builder(this)
                .setTitle("Reset Settings")
                .setMessage("Restore all preferences to their default values?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Reset", (dialog, which) -> {
                    notificationsEnabled = true;
                    emailUpdatesEnabled = false;
                    darkModeEnabled = false;
                    selectedLanguage = "English";

                    recreate();
                })
                .show();
    }

    private void addDivider(LinearLayout parent) {
        android.view.View divider = new android.view.View(this);
        divider.setBackgroundColor(Color.rgb(230, 233, 242));

        parent.addView(divider, new LinearLayout.LayoutParams(
                -1, dp(1)));
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
