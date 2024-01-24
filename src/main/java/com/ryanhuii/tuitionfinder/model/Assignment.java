package com.ryanhuii.tuitionfinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "Assignments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Assignment {

    @Id
    String assignment_id;

    String status; // can be "Pending", "Ongoing", or "Completed". Initially set to be pending.

    String subject;
    String level; // primary 4, secondary 1 etc.

    int frequency; // XX days a week
    int duration; // assignment lasts for XX months

    String gender;
    String rates; // lists out the rates that the parent is willing to pay
    List<String> availability;
    String parentNote;

    List<String> assignmentApplications; // it's just a list of application IDs.
    // properties set by tutor when confirmed
    String tutorUID;
    int rate; // the rate payable to the tutor

    // will be set by the tutor ONCE CONFIRMED
    // it's probably just the uhm, same as the availability but ya know,
    // I leave the option to play around with this. If I have the time.
    // I probably don't.
    List<String> lessonSchedule;

    public String getFormDetails() {
        return "Assignment{" +
                "subject='" + subject + '\'' +
                ", level='" + level + '\'' +
                ", frequency=" + frequency +
                ", duration=" + duration +
                ", gender='" + gender + '\'' +
                ", rates='" + rates + '\'' +
                ", availability=" + availability +
                ", parentNote='" + parentNote + '\'' +
                '}';
    }
}
