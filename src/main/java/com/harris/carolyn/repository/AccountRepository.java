package com.harris.carolyn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harris.carolyn.beans.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	
	Account findOneByEmail(String name);
}
