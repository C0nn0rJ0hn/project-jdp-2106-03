package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/group")

public class GroupController {

    @GetMapping("getGroups")
    public List<GroupDto> getGroups() {
        //Fixed return object for test purpose only
        List<GroupDto> groupList = new ArrayList<>();
        groupList.add(new GroupDto(1L, "Ubrania"));
        groupList.add(new GroupDto(2L, "Dodatki"));
        groupList.add(new GroupDto(3L, "Biżuteria"));
        groupList.add(new GroupDto(4L, "Obuwie"));
        return groupList;
    }

    @GetMapping("getGroup")
    public GroupDto getGroup(@RequestParam Long taskId) {
        //Fixed return object for test purpose only
        GroupDto groupDto1 = new GroupDto(1L, "Ubrania");
        GroupDto groupDto2 = new GroupDto(2L, "Dodatki");
        GroupDto groupDto3 = new GroupDto(3L, "Biżuteria");
        GroupDto groupDto4 = new GroupDto(4L, "Obuwie");
        GroupDto returnGroupDto = new GroupDto(0L, "Brak grupy");

        if (taskId == 0) returnGroupDto = groupDto1;
        if (taskId == 1) returnGroupDto = groupDto2;
        if (taskId == 2) returnGroupDto = groupDto3;
        if (taskId == 3) returnGroupDto = groupDto4;

        return returnGroupDto;
    }

    @DeleteMapping("deleteGroup")
    public void deleteGroup(@RequestParam Long taskId) {
    }

    @PutMapping("updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        //Fixed return object for test purpose only
        GroupDto returnGroupDto = new GroupDto(0L, "Brak grupy");
        GroupDto groupDto1 = new GroupDto(1L, "Ubrania");
        GroupDto groupDto2 = new GroupDto(2L, "Dodatki");
        GroupDto groupDto3 = new GroupDto(3L, "Biżuteria");
        GroupDto groupDto4 = new GroupDto(4L, "Obuwie");

        if (groupDto.equals(groupDto1)) returnGroupDto = groupDto1;
        if (groupDto.equals(groupDto2)) returnGroupDto = groupDto2;
        if (groupDto.equals(groupDto3)) returnGroupDto = groupDto3;
        if (groupDto.equals(groupDto4)) returnGroupDto = groupDto4;

        return returnGroupDto;
    }

    @PostMapping("createGroup")
    public void createGroup(@RequestBody GroupDto groupDto) {
    }
}
