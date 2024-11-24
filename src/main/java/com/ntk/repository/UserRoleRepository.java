package com.ntk.repository;

import org.springframework.data.repository.CrudRepository;

import com.ntk.model.UserRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleModel, Long> {
	
	UserRoleModel findByName(String name);
}
