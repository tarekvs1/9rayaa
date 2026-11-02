
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

public class StudentDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_ID = "student_id";

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String studentId = getIntent().getStringExtra(EXTRA_STUDENT_ID);
        Student student = findStudent(studentId);

        if (student == null) {
            Toast.makeText(this, "Student not found.",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        createDetailsScreen(student);
    }

    private Student findStudent(String studentId) {
        if (studentId == null) {
            return null;
        }

        for (Student student : AppData.getStudents()) {
            if (studentId.equals(student.getId())) {
                return student;
            }
        }

        return null;
    }

    private void createDetailsScreen(Student student) {

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(BACKGROUND);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(20), dp(22), dp(25));

        scrollView.addView(root);

        // Back button
        Button backButton = new Button(this);
        backButton.setText("< Back");
        backButton.setAllCaps(false);
        backButton.setTextColor(PRIMARY);
        backButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(BACKGROUND)
        );
        root.addView(backButton);

        backButton.setOnClickListener(v -> finish());

        // Title
        TextView title = new TextView(this);
        title.setText("Student Details");
        title.setTextSize(27);
        title.setTextColor(TEXT);
        title.setTypeface(null, Typeface.BOLD);
        title.setPadding(0, dp(15), 0, dp(20));
        root.addView(title);

        // Profile card
        LinearLayout profileCard = new LinearLayout(this);
        profileCard.setOrientation(LinearLayout.VERTICAL);
        profileCard.setGravity(Gravity.CENTER);
        profileCard.setPadding(dp(20), dp(22), dp(20), dp(22));
        profileCard.setBackground(makeBackground(WHITE, 18));

        TextView avatar = new TextView(this);
        avatar.setText(getInitials(student));
        avatar.setTextSize(28);
        avatar.setTextColor(WHITE);
        avatar.setTypeface(null, Typeface.BOLD);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackground(makeBackground(PRIMARY, 60));

        LinearLayout.LayoutParams avatarParams =
                new LinearLayout.LayoutParams(dp(90), dp(90));
        avatarParams.setMargins(0, 0, 0, dp(15));
        profileCard.addView(avatar, avatarParams);

        TextView name = new TextView(this);
        name.setText(student.getFullName());
        name.setTextSize(22);
        name.setTextColor(TEXT);
        name.setTypeface(null, Typeface.BOLD);
        name.setGravity(Gravity.CENTER);
        profileCard.addView(name);

        TextView id = new TextView(this);
        id.setText("Student ID: " + student.getId());
        id.setTextSize(14);
        id.setTextColor(MUTED);
        id.setGravity(Gravity.CENTER);
        id.setPadding(0, dp(8), 0, 0);
        profileCard.addView(id);

        LinearLayout.LayoutParams profileParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        profileParams.setMargins(0, 0, 0, dp(22));
        root.addView(profileCard, profileParams);

        // Information section
        TextView sectionTitle = new TextView(this);
        sectionTitle.setText("Personal Information");
        sectionTitle.setTextSize(18);
        sectionTitle.setTextColor(TEXT);
        sectionTitle.setTypeface(null, Typeface.BOLD);
        sectionTitle.setPadding(0, 0, 0, dp(12));
        root.addView(sectionTitle);

        LinearLayout infoCard = new LinearLayout(this);
        infoCard.setOrientation(LinearLayout.VERTICAL);
        infoCard.setPadding(dp(18), dp(6), dp(18), dp(6));
        infoCard.setBackground(makeBackground(WHITE, 16));

        addInfoRow(infoCard, "First Name", student.getFirstName());
        addDivider(infoCard);
        addInfoRow(infoCard, "Last Name", student.getLastName());
        addDivider(infoCard);
        addInfoRow(infoCard, "Email Address", student.getEmail());
        addDivider(infoCard);
        addInfoRow(infoCard, "Phone Number", student.getPhone());
        addDivider(infoCard);
        addInfoRow(infoCard, "Class", student.getStudentClass());
        addDivider(infoCard);
        addInfoRow(infoCard, "Date of Birth", student.getDateOfBirth());
        addDivider(infoCard);
        addInfoRow(infoCard, "Gender", student.getGender());
        addDivider(infoCard);
        addInfoRow(infoCard, "Address", student.getAddress());

        LinearLayout.LayoutParams infoParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        infoParams.setMargins(0, 0, 0, dp(22));
        root.addView(infoCard, infoParams);

        // Close button
        Button closeButton = new Button(this);
        closeButton.setText("Close");
        closeButton.setAllCaps(false);
        closeButton.setTextSize(15);
        closeButton.setTextColor(WHITE);
        closeButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(PRIMARY)
        );

        root.addView(closeButton, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(52)
        ));

        closeButton.setOnClickListener(v -> finish());

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
        valueText.setText(
                value == null || value.trim().isEmpty()
                        ? "Not provided" : value
        );
        valueText.setTextSize(15);
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
                LinearLayout.LayoutParams.MATCH_PARENT, dp(1)
        ));
    }

    private String getInitials(Student student) {
        String first = student.getFirstName();
        String last = student.getLastName();

        String firstInitial =
                first == null || first.trim().isEmpty()
                        ? "" : first.trim().substring(0, 1).toUpperCase();

        String lastInitial =
                last == null || last.trim().isEmpty()
                        ? "" : last.trim().substring(0, 1).toUpperCase();

        String initials = firstInitial + lastInitial;
        return initials.isEmpty() ? "S" : initials;
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
