package com.ryanhuii.tuitionfinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Tutor {
    private String uid;
    private String name;
    private String gender;
    private int age;
    private String tutorType;
    private int experience;
    private List<String> subjects;
    private String education;

    @Override
    public String toString() {
        return "Tutor{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", tutorType='" + tutorType + '\'' +
                ", experience=" + experience +
                ", subjects=" + subjects.toString() +
                ", education='" + education + '\'' +
                '}';
    }
}
