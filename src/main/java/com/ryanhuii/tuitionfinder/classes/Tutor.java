package com.ryanhuii.tuitionfinder.classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter @Setter @NoArgsConstructor
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

    @Override
    public String toString() {
        return "Tutor{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", tutorType='" + tutorType + '\'' +
                ", experience=" + experience +
                ", subjects=" + Arrays.toString(subjects) +
                ", education='" + education + '\'' +
                '}';
    }
}
