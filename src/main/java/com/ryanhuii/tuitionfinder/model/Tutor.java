package com.ryanhuii.tuitionfinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Tutors")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Tutor {

    @Id // for mongo, need to manually generate the id from our side.
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
