package com.ryanhuii.tuitionfinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Parents")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Parent {

    @Id // for mongo
    private String uid;

    private String postalCode;
    private String streetName;
    private String blockOrApartmentNumber;
    private List<String> assignmentList;

    private void addAssignment(String assignment_id) {
        this.assignmentList.add(assignment_id);
    }

    private void removeAssignment(String assignment_id) {
        this.assignmentList.remove(assignment_id);
    }

    // when parent account first created
    public Parent(String parentID, String postalCode, String streetName, String blockOrApartmentNumber) {
        this.uid = parentID;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.blockOrApartmentNumber = blockOrApartmentNumber;
        // initialize a blank list of assignments
        this.assignmentList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Parent{" +
                "uid='" + uid + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", streetName='" + streetName + '\'' +
                ", blockOrApartmentNumber='" + blockOrApartmentNumber + '\'' +
                ", assignmentList=" + assignmentList.toString() +
                '}';
    }
}
