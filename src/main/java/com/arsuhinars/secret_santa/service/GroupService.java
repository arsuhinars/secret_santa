package com.arsuhinars.secret_santa.service;

import com.arsuhinars.secret_santa.exception.ConflictException;
import com.arsuhinars.secret_santa.exception.NotFoundException;
import com.arsuhinars.secret_santa.schema.GroupCreateSchema;
import com.arsuhinars.secret_santa.schema.GroupSchema;
import com.arsuhinars.secret_santa.schema.GroupUpdateSchema;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    GroupSchema create(GroupCreateSchema schema);

    List<GroupSchema> getAll();

    Optional<GroupSchema> getById(Integer id);

    GroupSchema updateById(Integer id, GroupUpdateSchema schema) throws NotFoundException;

    void deleteById(Integer id) throws NotFoundException;

    GroupSchema tossParticipants(Integer groupId) throws NotFoundException, ConflictException;
}
