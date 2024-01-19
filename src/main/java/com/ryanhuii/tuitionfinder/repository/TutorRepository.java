package com.ryanhuii.tuitionfinder.repository;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorRepository extends MongoRepository<Tutor, String> {
}
