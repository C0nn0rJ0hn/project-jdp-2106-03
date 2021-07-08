package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return  groupRepository.findAll();
    }

    public Optional<Group> getGroup(final Long id) {
        return groupRepository.findById(id);
    }

    public Group saveGroup(final Group group) {
        return  groupRepository.save(group);
    }

    public void deleteGroup(final Long id) {
        groupRepository.deleteById(id);
    }
}
