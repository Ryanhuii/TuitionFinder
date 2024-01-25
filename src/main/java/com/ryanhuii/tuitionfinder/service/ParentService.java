package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Parent addAssignmentToParentAssignmentList(String assignmentId, Parent parent) {
        Parent p = getParentByUid(parent.getUid());
        List<String> newList = p.getAssignmentList();
        newList.add(assignmentId);
        p.setAssignmentList(newList);

        return updateParent(p);
    }

    private Parent updateParent(Parent p) {
        return repository.save(p);
    }
}
