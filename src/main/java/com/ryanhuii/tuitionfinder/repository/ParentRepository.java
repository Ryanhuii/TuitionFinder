package com.ryanhuii.tuitionfinder.repository;

import com.ryanhuii.tuitionfinder.model.Parent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParentRepository extends MongoRepository<Parent, String> {
    public Parent findParentByAssignmentListContaining(String assignment_id);
}
