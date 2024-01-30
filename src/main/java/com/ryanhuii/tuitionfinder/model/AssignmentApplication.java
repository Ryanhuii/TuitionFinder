package com.ryanhuii.tuitionfinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collection = "AssignmentApplications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AssignmentApplication {
    @Id
    String application_id;

    private String tutorID;
    private String tutorRate;
    List<String> lessonSchedule = new ArrayList<>();
    private String whyChooseMe;

    private String applicationStatus = "Pending"; // application status can be "Pending", "Rejected", or "Accepted"

    public String printDetails() {
        return "AssignmentApplication{" +
                "application_id='" + application_id + '\'' +
                ", tutorID='" + tutorID + '\'' +
                ", tutorRate='" + tutorRate + '\'' +
                ", lessonSchedule=" + lessonSchedule +
                ", whyChooseMe='" + whyChooseMe + '\'' +
                ", applicationStatus='" + applicationStatus + '\'' +
                '}';
    }
}
