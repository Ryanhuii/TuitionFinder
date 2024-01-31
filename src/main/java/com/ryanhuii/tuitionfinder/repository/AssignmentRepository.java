package com.ryanhuii.tuitionfinder.repository;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    public Optional<Assignment> findAssignmentByAssignmentApplicationsContaining(String application_uid);

    public Optional<List<Assignment>> findAssignmentsByTutorUIDAndStatus(String tutorUID, String status);
}
