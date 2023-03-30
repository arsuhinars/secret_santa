package com.arsuhinars.secret_santa.repository;

import com.arsuhinars.secret_santa.model.Participant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParticipantRepository extends CrudRepository<Participant, Integer> {
    Optional<Participant> findByGroupIdAndId(Integer groupId, Integer id);
}
