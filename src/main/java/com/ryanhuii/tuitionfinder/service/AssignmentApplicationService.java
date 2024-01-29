package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.repository.AssignmentApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentApplicationService {
    @Autowired
    private AssignmentApplicationRepository repository;
    @Autowired
    AssignmentService assignmentService;

    public void submitAssignmentApplication(AssignmentApplication application, Assignment assignment) {
        // upload this object to the AssignmentApplication collection
        repository.save(application);
        // also update the corresponding Assignment object
        List<String> newApplicationsList = assignment.getAssignmentApplications();
        newApplicationsList.add(application.getApplication_id());
        assignment.setAssignmentApplications(newApplicationsList);
        assignmentService.updateAssignment(assignment);

        System.out.println("Application service okay!");
    }
}
