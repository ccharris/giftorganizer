package com.harris.carolyn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harris.carolyn.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findOneByEmail(String name);

}
