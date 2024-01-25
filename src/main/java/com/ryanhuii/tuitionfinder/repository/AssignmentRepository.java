package com.ryanhuii.tuitionfinder.repository;

import com.ryanhuii.tuitionfinder.model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {

}
