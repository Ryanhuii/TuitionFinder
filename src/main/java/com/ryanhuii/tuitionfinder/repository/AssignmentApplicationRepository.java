package com.ryanhuii.tuitionfinder.repository;

import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.model.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssignmentApplicationRepository extends MongoRepository<AssignmentApplication, String> {
}
