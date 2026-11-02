
package com.example.studify;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private final Context context;
    private final List<Student> students;

    private final int PRIMARY = Color.rgb(82, 103, 232);
    private final int TEXT = Color.rgb(35, 42, 65);
    private final int MUTED = Color.rgb(115, 123, 145);
    private final int WHITE = Color.WHITE;

    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Student student = getItem(position);

        LinearLayout card = new LinearLayout(context);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp(15), dp(15), dp(15), dp(15));
        card.setBackground(makeBackground(WHITE, 16));

        // Student avatar
        TextView avatar = new TextView(context);
        String initials = getInitials(student);
        avatar.setText(initials);
        avatar.setTextSize(17);
        avatar.setTextColor(WHITE);
        avatar.setTypeface(null, Typeface.BOLD);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackground(makeBackground(PRIMARY, 50));

        LinearLayout.LayoutParams avatarParams =
                new LinearLayout.LayoutParams(dp(52), dp(52));
        avatarParams.setMargins(0, 0, dp(14), 0);
        card.addView(avatar, avatarParams);

        // Student information
        LinearLayout details = new LinearLayout(context);
        details.setOrientation(LinearLayout.VERTICAL);

        TextView name = new TextView(context);
        name.setText(student.getFullName());
        name.setTextSize(16);
        name.setTextColor(TEXT);
        name.setTypeface(null, Typeface.BOLD);
        details.addView(name);

        TextView studentId = new TextView(context);
        studentId.setText("ID: " + student.getId());
        studentId.setTextSize(12);
        studentId.setTextColor(MUTED);
        studentId.setPadding(0, dp(4), 0, 0);
        details.addView(studentId);

        TextView email = new TextView(context);
        email.setText(student.getEmail());
        email.setTextSize(12);
        email.setTextColor(MUTED);
        email.setPadding(0, dp(3), 0, 0);
        details.addView(email);

        TextView studentClass = new TextView(context);
        studentClass.setText("Class: " + student.getStudentClass());
        studentClass.setTextSize(12);
        studentClass.setTextColor(PRIMARY);
        studentClass.setPadding(0, dp(5), 0, 0);
        details.addView(studentClass);

        card.addView(details, new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        ));

        return card;
    }

    private String getInitials(Student student) {
        String first = student.getFirstName();
        String last = student.getLastName();

        String firstInitial = first == null || first.trim().isEmpty()
                ? "" : first.trim().substring(0, 1).toUpperCase();

        String lastInitial = last == null || last.trim().isEmpty()
                ? "" : last.trim().substring(0, 1).toUpperCase();

        String result = firstInitial + lastInitial;
        return result.isEmpty() ? "S" : result;
    }

    private GradientDrawable makeBackground(int color, int radius) {
        GradientDrawable background = new GradientDrawable();
        background.setColor(color);
        background.setCornerRadius(dp(radius));
        return background;
    }

    private int dp(int value) {
        return (int) (value * context.getResources()
                .getDisplayMetrics().density + 0.5f);
    }
}
