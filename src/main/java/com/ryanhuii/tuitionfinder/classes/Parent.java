package com.ryanhuii.tuitionfinder.classes;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Parent {
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

    // when account first created
    public Parent(String parentID, String postalCode, String streetName, String blockOrApartmentNumber) {
        this.uid = parentID;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.blockOrApartmentNumber = blockOrApartmentNumber;
        // initialize a blank list of assignments
        this.assignmentList = new ArrayList<>();
    }
}
