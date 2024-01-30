package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository repository;

    // Create an assignment
    public Assignment createAssignment(Assignment assignment) {
        return repository.save(assignment);
    }

    public Assignment getAssignmentById(String id) {
        return repository.findById(id).get();
    }

    public List<Assignment> getParentAssignments(List<String> assignmentList) {
        List<Assignment> objectList = new ArrayList<>();
        for (String assignment_id : assignmentList) {
            Assignment assignment = getAssignmentById(assignment_id);
            objectList.add(assignment);
        }

        return objectList;
    }

    public void deleteAssignment(Assignment assignment) {
        System.out.println("deleting assignment: " + assignment.getParentNote());
        repository.delete(assignment);
        System.out.println("Assignment deleted form database");
    }

    // Special function for tutor to see all available assignments in TuitionFinder
    public List<Assignment> getAllAssignments() {
        return repository.findAll();
    }



    public void updateAssignment(Assignment assignment) {
        repository.save(assignment);
        System.out.println("Assignment service okay!");
    }

    public List<Assignment> getAssignmentsAppliedTo(List<AssignmentApplication> pendingApplications) {
        List<Assignment> assignmentsAppliedTo = new ArrayList<>();
        for (AssignmentApplication application : pendingApplications) {
            assignmentsAppliedTo.add(repository.findAssignmentByAssignmentApplicationsContaining(application.getApplication_id()));
        }
        System.out.println("We found " + assignmentsAppliedTo.size() + " assignments linked with said applications");
        return assignmentsAppliedTo;
    }
}
