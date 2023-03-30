package com.arsuhinars.secret_santa.repository;

import com.arsuhinars.secret_santa.model.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Integer> {

}
