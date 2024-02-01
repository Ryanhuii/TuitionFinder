package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;
    @Autowired
    private AssignmentApplicationService assignmentApplicationService;

    // Create
    public Tutor createTutor(Tutor tutor) {
        return repository.save(tutor);
    }

    // Read
    public Tutor getTutorByUid(String uid) {
        Optional<Tutor> optionalTutor = repository.findById(uid);
        if (optionalTutor.isPresent()) {
            return repository.findById(uid).get();
        } else {
            return null;
        }
        //return repository.findById(uid).get();
    }

    public List<Tutor> getAllTutors() {
        return repository.findAll();
    }

    public void verifyExistence() {
        System.out.println("This Account Service Exists");
    }

    public List<Tutor> getTutorsThatAppliedToThisAssignment(Assignment assignment) {
        // Assignment -> get the assignment application object -> get the tutor id from the application

        List<Tutor> tutorsThatApplied = new ArrayList<>();
        for (String assignmentApplicationID : assignment.getAssignmentApplications()) {
            AssignmentApplication application = assignmentApplicationService.getAssignmentById(assignmentApplicationID);
            Tutor tutor = repository.findById(application.getTutorID()).get();
            tutorsThatApplied.add(tutor);
        }
        return tutorsThatApplied;
    }

    //public Tutor updateTutor(Tutor tutor) {}
    //public Tutor deleteTutor(Tutor tutor) {}
}
