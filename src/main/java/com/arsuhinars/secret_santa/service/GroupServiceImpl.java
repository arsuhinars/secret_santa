package com.arsuhinars.secret_santa.service;

import com.arsuhinars.secret_santa.exception.ConflictException;
import com.arsuhinars.secret_santa.exception.NotFoundException;
import com.arsuhinars.secret_santa.model.Group;
import com.arsuhinars.secret_santa.model.Participant;
import com.arsuhinars.secret_santa.repository.GroupRepository;
import com.arsuhinars.secret_santa.schema.GroupCreateSchema;
import com.arsuhinars.secret_santa.schema.GroupSchema;
import com.arsuhinars.secret_santa.schema.GroupUpdateSchema;
import org.springframework.stereotype.Service;

import javax.naming.ConfigurationException;
import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;

    public GroupServiceImpl(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public GroupSchema create(GroupCreateSchema schema) {
        var group = new Group();
        group.setName(schema.getName());
        group.setDescription(schema.getDescription());
        group.setParticipants(Set.of());

        return new GroupSchema(repository.save(group));
    }

    @Override
    public List<GroupSchema> getAll() {
        return StreamSupport
            .stream(repository.findAll().spliterator(), false)
            .map(GroupSchema::new)
            .toList();
    }

    @Override
    public Optional<GroupSchema> getById(Integer id) {
        return repository.findById(id).map(GroupSchema::new);
    }

    @Override
    public GroupSchema updateById(Integer id, GroupUpdateSchema schema) throws NotFoundException {
        var group = repository.findById(id).orElse(null);
        if (group == null) {
            throw new NotFoundException();
        }

        group.setName(schema.getName());
        group.setDescription(schema.getDescription());

        return new GroupSchema(repository.save(group));
    }

    @Override
    public void deleteById(Integer id) throws NotFoundException {
        var group = repository.findById(id).orElse(null);
        if (group == null) {
            throw new NotFoundException();
        }

        group.getParticipants().clear();

        repository.delete(group);
    }

    @Override
    public GroupSchema tossParticipants(Integer groupId) throws NotFoundException, ConflictException {
        var group = repository.findById(groupId).orElse(null);
        if (group == null) {
            throw new NotFoundException();
        }

        if (group.getParticipants().size() < 3) {
            throw new ConflictException();
        }

        var remainedParticipants = new HashSet<>(group.getParticipants());
        var random = new Random();

        while (!remainedParticipants.isEmpty()) {
            var it = remainedParticipants.iterator();

            var curr = it.next();
            Participant pair = new Participant();

            int offset = random.nextInt(1, remainedParticipants.size());
            for (int i = 0; i < offset; ++i) {
                pair = it.next();
            }

            remainedParticipants.remove(curr);
            remainedParticipants.remove(pair);

            curr.setRecipient(pair);
            pair.setRecipient(curr);
        }

        return new GroupSchema(repository.save(group));
    }
}
