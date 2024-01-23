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
    String duration;
    String gender;
    String rates; // lists out the rates that the parent is willing to pay
    Map<String, Boolean> availability;
    String parentNote;

    List<String> assignmentApplications; // it's just a list of application IDs.
    // properties set by tutor when confirmed
    String tutorUID;
    int rate; // the rate payable to the tutor
    Map<String, Boolean> lessonSchedule; // will be set by the tutor ONCE CONFIRMED
}
