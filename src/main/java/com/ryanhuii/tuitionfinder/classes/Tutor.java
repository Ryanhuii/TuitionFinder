package com.ryanhuii.tuitionfinder.classes;

public class Tutor {
    private String uid;
    private String name;
    private String gender;
    private int age;
    private String tutorType;
    private int experience;
    private String[] subjects;
    private String education;

    public Tutor(String uid, String name, String gender, int age, String tutorType, int experience, String[] subjects, String education) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.tutorType = tutorType;
        this.experience = experience;
        this.subjects = subjects;
        this.education = education;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTutorType() {
        return tutorType;
    }

    public void setTutorType(String tutorType) {
        this.tutorType = tutorType;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
