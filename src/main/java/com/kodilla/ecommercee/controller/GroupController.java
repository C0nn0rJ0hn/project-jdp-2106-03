package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/group")

public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupController(GroupService groupService, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @GetMapping("getGroups")
    public List<GroupDto> getGroups() {
        List<Group> groups = groupService.getAllGroups();
        return groupMapper.mapToGroupDtoList(groups);
    }

    @GetMapping("getGroup")
    public GroupDto getGroup(@RequestParam Long id) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(
                groupService.getGroup(id).orElseThrow(() -> new GroupNotFoundException("Group not found"))
        );
    }

    @DeleteMapping("deleteGroup")
    public void deleteGroup(@RequestParam Long id) {
        groupService.deleteGroup(id);
    }

    @PutMapping("updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group savedGroup = groupService.saveGroup(group);
        return groupMapper.mapToGroupDto(savedGroup);
    }

    @PostMapping(value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupService.saveGroup(group);
    }
}
