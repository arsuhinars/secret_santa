package com.arsuhinars.secret_santa.controller;

import com.arsuhinars.secret_santa.exception.ConflictException;
import com.arsuhinars.secret_santa.exception.NotFoundException;
import com.arsuhinars.secret_santa.schema.*;
import com.arsuhinars.secret_santa.service.GroupService;
import com.arsuhinars.secret_santa.service.ParticipantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Validated
public class GroupController {
    private GroupService groupService;
    private ParticipantService participantService;

    public GroupController(GroupService groupService, ParticipantService participantService) {
        this.groupService = groupService;
        this.participantService = participantService;
    }

    @PostMapping("/group")
    public Integer createGroup(
        @RequestBody @Valid GroupCreateSchema group
    ) {
        return groupService.create(group).getId();
    }

    @GetMapping("/groups")
    public List<BriefGroupSchema> getAllGroups() {
        return groupService.getAll().stream().map(BriefGroupSchema::new).toList();
    }

    @GetMapping("/group/{id}")
    public GroupSchema getGroupById(
        @PathVariable @NotNull Integer id
    ) throws NotFoundException {
        var group = groupService.getById(id);
        if (group.isEmpty()) {
            throw new NotFoundException();
        }

        return group.get();
    }

    @PutMapping("/group/{id}")
    public void updateGroupById(
        @PathVariable @NotNull Integer id,
        @RequestBody @Valid GroupUpdateSchema group
    ) throws NotFoundException {
        groupService.updateById(id, group);
    }

    @DeleteMapping("/group/{id}")
    public void deleteGroupById(
        @PathVariable @NotNull Integer id
    ) throws NotFoundException {
        groupService.deleteById(id);
    }

    @PostMapping("/group/{id}/participant")
    public Integer addParticipantToGroup(
        @PathVariable @NotNull Integer groupId,
        @RequestBody ParticipantCreateSchema participant
    ) {
        return participantService.create(groupId, participant).getId();
    }

    @DeleteMapping("/group/{groupId}/participant/{participantId}")
    public void deleteParticipant(
        @PathVariable @NotNull Integer groupId,
        @PathVariable @NotNull Integer participantId
    ) {
        participantService.deleteById(groupId, participantId);
    }

    @PostMapping("/group/{id}/toss")
    public List<ParticipantSchema> tossParticipants(
        @PathVariable @NotNull Integer groupId
    ) throws NotFoundException, ConflictException {
        return groupService.tossParticipants(groupId).getParticipants();
    }

    @GetMapping("/group/{groupId}/participant/{participantId}/recipient")
    public BriefParticipantSchema getParticipantRecipient(
        @PathVariable @NotNull Integer groupId,
        @PathVariable @NotNull Integer participantId
    ) {
        return new BriefParticipantSchema(participantService.getRecipient(groupId, participantId));
    }
}
