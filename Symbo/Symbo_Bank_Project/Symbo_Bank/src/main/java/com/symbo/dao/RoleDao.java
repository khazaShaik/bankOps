package com.symbo.dao;

import org.springframework.data.repository.CrudRepository;

import com.symbo.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {

	Role findByName(String name);
}
