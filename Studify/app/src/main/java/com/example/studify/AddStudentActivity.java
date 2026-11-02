
package com.example.studify;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentActivity extends Activity {

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int BACKGROUND = Color.rgb(245, 247, 252);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText phoneInput;
    private EditText classInput;
    private EditText birthDateInput;
    private EditText genderInput;
    private EditText addressInput;

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
        root.setPadding(dp(22), dp(18), dp(22), dp(24));

        scrollView.addView(root);

        Button backButton = new Button(this);
        backButton.setText("←  Back to Students");
        backButton.setAllCaps(false);
        backButton.setTextColor(PRIMARY);
        backButton.setBackgroundColor(Color.TRANSPARENT);
        backButton.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        backButton.setOnClickListener(v -> finish());

        root.addView(backButton, new LinearLayout.LayoutParams(
                -1, dp(48)));

        TextView title = new TextView(this);
        title.setText("Add New Student");
        title.setTextSize(27);
        title.setTextColor(TEXT);
        title.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(14);
        root.addView(title, titleParams);

        TextView subtitle = new TextView(this);
        subtitle.setText("Enter the student's information below.");
        subtitle.setTextSize(14);
        subtitle.setTextColor(MUTED);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(6);
        subtitleParams.bottomMargin = dp(20);
        root.addView(subtitle, subtitleParams);

        firstNameInput = addField(root, "First Name", "Enter first name");
        lastNameInput = addField(root, "Last Name", "Enter last name");
        emailInput = addField(root, "Email Address", "Enter email");
        emailInput.setInputType(
                android.text.InputType.TYPE_CLASS_TEXT |
                        android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        );

        phoneInput = addField(root, "Phone Number", "Enter phone number");
        phoneInput.setInputType(
                android.text.InputType.TYPE_CLASS_PHONE);

        classInput = addField(root, "Class", "Example: Class A");
        birthDateInput = addField(
                root, "Date of Birth", "Example: 15/06/2005");
        genderInput = addField(
                root, "Gender", "Example: Male or Female");
        addressInput = addField(root, "Address", "Enter address");

        Button saveButton = new Button(this);
        saveButton.setText("Save Student");
        saveButton.setAllCaps(false);
        saveButton.setTextSize(16);
        saveButton.setTypeface(null, Typeface.BOLD);
        saveButton.setTextColor(Color.WHITE);
        saveButton.setBackground(background(PRIMARY, 12));

        LinearLayout.LayoutParams saveParams =
                new LinearLayout.LayoutParams(-1, dp(52));
        saveParams.topMargin = dp(14);
        root.addView(saveButton, saveParams);

        saveButton.setOnClickListener(v -> saveStudent());

        setContentView(scrollView);
    }

    private EditText addField(
            LinearLayout root,
            String label,
            String hint
    ) {
        TextView textLabel = new TextView(this);
        textLabel.setText(label);
        textLabel.setTextColor(TEXT);
        textLabel.setTextSize(14);
        textLabel.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams labelParams =
                new LinearLayout.LayoutParams(-1, -2);
        labelParams.topMargin = dp(10);
        root.addView(textLabel, labelParams);

        EditText input = new EditText(this);
        input.setSingleLine(true);
        input.setTextSize(15);
        input.setTextColor(TEXT);
        input.setHint(hint);
        input.setHintTextColor(MUTED);
        input.setPadding(dp(12), 0, dp(12), 0);
        input.setBackground(background(Color.WHITE, 10));

        LinearLayout.LayoutParams inputParams =
                new LinearLayout.LayoutParams(-1, dp(50));
        inputParams.topMargin = dp(6);
        root.addView(input, inputParams);

        return input;
    }

    private void saveStudent() {
        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String studentClass = classInput.getText().toString().trim();

        if (firstName.isEmpty() ||
                lastName.isEmpty() ||
                email.isEmpty() ||
                studentClass.isEmpty()) {

            Toast.makeText(this,
                    "Please fill in first name, last name, email and class.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Enter a valid email address");
            return;
        }

        String id = generateStudentId();

        Student student = new Student(
                id,
                firstName,
                lastName,
                email,
                phone,
                studentClass,
                birthDateInput.getText().toString().trim(),
                genderInput.getText().toString().trim(),
                addressInput.getText().toString().trim()
        );

        AppData.addStudent(student);

        Toast.makeText(this,
                "Student added successfully!",
                Toast.LENGTH_SHORT).show();

        finish();
    }

    private String generateStudentId() {
        int nextNumber = AppData.getStudents().size() + 1;
        String id;

        do {
            id = String.format(
                    java.util.Locale.US, "STU%03d", nextNumber++);
        } while (containsStudentId(id));

        return id;
    }

    private boolean containsStudentId(String id) {
        for (Student student : AppData.getStudents()) {
            if (student.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
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
