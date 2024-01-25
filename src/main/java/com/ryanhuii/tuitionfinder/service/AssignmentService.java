package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository repository;

    // Create an assignment
    public Assignment createAssignment(Assignment assignment) {
        return repository.save(assignment);
    }
}
