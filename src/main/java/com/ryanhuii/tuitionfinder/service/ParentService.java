package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentService {
    @Autowired
    private ParentRepository repository;

    // Create
    public Parent createParent(Parent parent) {
        return repository.save(parent);
    }

    public Parent getParentByUid(String uid) {
        return repository.findById(uid).get();
    }
}
