package com.arsuhinars.secret_santa.service;

import com.arsuhinars.secret_santa.exception.NotFoundException;
import com.arsuhinars.secret_santa.schema.ParticipantCreateSchema;
import com.arsuhinars.secret_santa.schema.ParticipantSchema;

import java.util.Optional;

public interface ParticipantService {
    ParticipantSchema create(Integer groupId, ParticipantCreateSchema schema) throws NotFoundException;

    Optional<ParticipantSchema> getById(Integer groupId, Integer participantId);

    ParticipantSchema getRecipient(Integer groupId, Integer participantId) throws NotFoundException;

    void deleteById(Integer groupId, Integer participantId) throws NotFoundException;
}
