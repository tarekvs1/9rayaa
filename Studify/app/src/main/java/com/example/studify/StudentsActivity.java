
package com.example.studify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentsActivity extends Activity {

    private static final int PRIMARY = Color.rgb(82, 103, 232);
    private static final int BACKGROUND = Color.rgb(245, 247, 252);
    private static final int TEXT = Color.rgb(35, 42, 65);
    private static final int MUTED = Color.rgb(115, 123, 145);
    private static final int WHITE = Color.WHITE;

    private StudentAdapter adapter;
    private ListView studentsListView;
    private TextView totalStudentsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createStudentsScreen();
    }

    private void createStudentsScreen() {

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(20), dp(20), dp(16));
        root.setBackgroundColor(BACKGROUND);

        // Header
        Button backButton = new Button(this);
        backButton.setText("←  Back to Dashboard");
        backButton.setTextColor(PRIMARY);
        backButton.setAllCaps(false);
        backButton.setTextSize(14);
        backButton.setBackgroundColor(Color.TRANSPARENT);
        backButton.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

        backButton.setOnClickListener(v -> finish());

        root.addView(backButton, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(48)
        ));

        // Page title
        TextView title = new TextView(this);
        title.setText("Manage Students");
        title.setTextColor(TEXT);
        title.setTextSize(27);
        title.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        // Subtitle
        TextView subtitle = new TextView(this);
        subtitle.setText("View student information and manage your list.");
        subtitle.setTextColor(MUTED);
        subtitle.setTextSize(14);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        subtitleParams.topMargin = dp(6);
        root.addView(subtitle, subtitleParams);

        // Summary card
        LinearLayout summaryCard = new LinearLayout(this);
        summaryCard.setOrientation(LinearLayout.VERTICAL);
        summaryCard.setPadding(dp(18), dp(16), dp(18), dp(16));
        summaryCard.setBackground(createBackground(WHITE, 16, true));

        TextView summaryLabel = new TextView(this);
        summaryLabel.setText("TOTAL STUDENTS");
        summaryLabel.setTextColor(MUTED);
        summaryLabel.setTextSize(12);
        summaryLabel.setTypeface(null, Typeface.BOLD);

        totalStudentsText = new TextView(this);
        totalStudentsText.setTextColor(PRIMARY);
        totalStudentsText.setTextSize(30);
        totalStudentsText.setTypeface(null, Typeface.BOLD);

        summaryCard.addView(summaryLabel);
        summaryCard.addView(totalStudentsText);

        LinearLayout.LayoutParams summaryParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        summaryParams.topMargin = dp(22);
        root.addView(summaryCard, summaryParams);

        // Add student button
        Button addButton = new Button(this);
        addButton.setText("+  Add New Student");
        addButton.setTextColor(WHITE);
        addButton.setTextSize(15);
        addButton.setAllCaps(false);
        addButton.setTypeface(null, Typeface.BOLD);
        addButton.setBackground(createBackground(PRIMARY, 12, false));

        LinearLayout.LayoutParams addButtonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(52)
                );
        addButtonParams.topMargin = dp(16);
        root.addView(addButton, addButtonParams);

        addButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(
                        StudentsActivity.this,
                        AddStudentActivity.class
                );
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(
                        StudentsActivity.this,
                        "Unable to open Add Student screen.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // Student list heading
        TextView listHeading = new TextView(this);
        listHeading.setText("Student List");
        listHeading.setTextColor(TEXT);
        listHeading.setTextSize(19);
        listHeading.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams headingParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        headingParams.topMargin = dp(22);
        headingParams.bottomMargin = dp(10);
        root.addView(listHeading, headingParams);

        // ListView
        studentsListView = new ListView(this);
        studentsListView.setDivider(null);
        studentsListView.setDividerHeight(dp(10));
        studentsListView.setCacheColorHint(Color.TRANSPARENT);
        studentsListView.setBackgroundColor(BACKGROUND);
        studentsListView.setSelector(android.R.color.transparent);

        adapter = new StudentAdapter(this, AppData.getStudents());
        studentsListView.setAdapter(adapter);

        LinearLayout.LayoutParams listParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        1
                );
        root.addView(studentsListView, listParams);

        // Open the selected student's details
        studentsListView.setOnItemClickListener((parent, view, position, id) -> {
            Student student = adapter.getItem(position);

            if (student == null) {
                Toast.makeText(
                        StudentsActivity.this,
                        "Student not found.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            Intent intent = new Intent(
                    StudentsActivity.this,
                    StudentDetailsActivity.class
            );

            intent.putExtra(
                    StudentDetailsActivity.EXTRA_STUDENT_ID,
                    student.getId()
            );

            startActivity(intent);
        });

        setContentView(root);
        updateStudentCount();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
            updateStudentCount();
        }
    }

    private void updateStudentCount() {
        if (totalStudentsText != null) {
            totalStudentsText.setText(
                    String.valueOf(AppData.getStudents().size())
            );
        }
    }

    private GradientDrawable createBackground(
            int color,
            int radius,
            boolean withBorder
    ) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radius));

        if (withBorder) {
            drawable.setStroke(dp(1), Color.rgb(230, 233, 242));
        }

        return drawable;
    }

    private int dp(float value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }
}
