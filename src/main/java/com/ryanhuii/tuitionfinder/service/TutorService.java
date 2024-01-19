package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.repository.AccountRepository;
import com.ryanhuii.tuitionfinder.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;

    // Create
    public Tutor createTutor(Tutor tutor) {
        return repository.save(tutor);
    }

    // Read
    public Tutor getTutorByUid(String uid) {
        return repository.findById(uid).get();
    }

    public void verifyExistence() {
        System.out.println("This Account Service Exists");
    }

    //public Tutor updateTutor(Tutor tutor) {}
    //public Tutor deleteTutor(Tutor tutor) {}
}
