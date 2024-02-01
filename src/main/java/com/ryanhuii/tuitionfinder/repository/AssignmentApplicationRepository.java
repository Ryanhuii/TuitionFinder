package com.ryanhuii.tuitionfinder.repository;

import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssignmentApplicationRepository extends MongoRepository<AssignmentApplication, String> {
    public List<AssignmentApplication> findAssignmentApplicationsByTutorID(String tutorID);
}
