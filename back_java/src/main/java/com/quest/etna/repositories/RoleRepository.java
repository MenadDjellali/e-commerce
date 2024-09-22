package com.quest.etna.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quest.etna.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
