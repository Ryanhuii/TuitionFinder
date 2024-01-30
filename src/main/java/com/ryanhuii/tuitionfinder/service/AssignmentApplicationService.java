package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.repository.AssignmentApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<AssignmentApplication> getPendingApplications(String tutorID) {
        List<AssignmentApplication> allApplications = repository.findAssignmentApplicationsByTutorID(tutorID);
        List<AssignmentApplication> pendingApplications = new ArrayList<>();
        for(AssignmentApplication application : allApplications) {
            if (application.getApplicationStatus().equals("Pending")) pendingApplications.add(application);
            System.out.println(application.printDetails());
        }
        System.out.println("We found " + pendingApplications.size() + " pending applications");
        return pendingApplications;
    }

    public AssignmentApplication getAssignmentById(String assignmentApplicationID) {
        return repository.findById(assignmentApplicationID).get();
    }

    public List<AssignmentApplication> getAssignmentApplications(Assignment assignment) {
        List<AssignmentApplication> applications = new ArrayList<>();
        for (String application_id : assignment.getAssignmentApplications()) {
            applications.add(repository.findById(application_id).get());
        }
        return applications;
    }
}
