package com.harris.carolyn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harris.carolyn.beans.EventRecipient;

@Repository
public interface EventRecipientRepository extends CrudRepository<EventRecipient, Long> {

}
