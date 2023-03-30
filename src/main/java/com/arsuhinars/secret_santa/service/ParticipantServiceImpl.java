package com.arsuhinars.secret_santa.service;

import com.arsuhinars.secret_santa.exception.NotFoundException;
import com.arsuhinars.secret_santa.model.Participant;
import com.arsuhinars.secret_santa.repository.GroupRepository;
import com.arsuhinars.secret_santa.repository.ParticipantRepository;
import com.arsuhinars.secret_santa.schema.ParticipantCreateSchema;
import com.arsuhinars.secret_santa.schema.ParticipantSchema;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private final GroupRepository groupRepository;
    private final ParticipantRepository repository;

    public ParticipantServiceImpl(GroupRepository groupRepository, ParticipantRepository repository) {
        this.groupRepository = groupRepository;
        this.repository = repository;
    }

    @Override
    public ParticipantSchema create(Integer groupId, ParticipantCreateSchema schema) throws NotFoundException {
        var group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            throw new NotFoundException();
        }

        var participant = new Participant();
        participant.setName(schema.getName());
        participant.setWish(schema.getWish());
        participant.setGroup(group);

        return new ParticipantSchema(repository.save(participant));
    }

    @Override
    public Optional<ParticipantSchema> getById(Integer groupId, Integer participantId) {
        return repository.findByGroupIdAndId(groupId, participantId).map(ParticipantSchema::new);
    }

    @Override
    public ParticipantSchema getRecipient(Integer groupId, Integer participantId) throws NotFoundException {
        var participant = repository.findByGroupIdAndId(groupId, participantId).orElse(null);
        if (participant == null) {
            throw new NotFoundException();
        }

        return new ParticipantSchema(participant.getRecipient());
    }

    @Override
    public void deleteById(Integer groupId, Integer participantId) throws NotFoundException {
        var participant = repository.findByGroupIdAndId(groupId, participantId).orElse(null);
        if (participant == null) {
            throw new NotFoundException();
        }

        var group = participant.getGroup();
        group.getParticipants().remove(participant);

        groupRepository.save(group);
    }
}
