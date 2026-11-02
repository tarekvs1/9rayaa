
package com.example.studify;

public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String studentClass;
    private String dateOfBirth;
    private String gender;
    private String address;

    // Constructor
    public Student(
            String id,
            String firstName,
            String lastName,
            String email,
            String phone,
            String studentClass
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.studentClass = studentClass;

        this.dateOfBirth = "";
        this.gender = "";
        this.address = "";
    }

    // Full constructor
    public Student(
            String id,
            String firstName,
            String lastName,
            String email,
            String phone,
            String studentClass,
            String dateOfBirth,
            String gender,
            String address
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.studentClass = studentClass;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
