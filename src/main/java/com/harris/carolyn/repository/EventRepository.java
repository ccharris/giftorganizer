package com.harris.carolyn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harris.carolyn.beans.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

	public List<Event> findByUserId(long id);
	public List<Event> findByNameContainsOrBudgetContainsAllIgnoreCase(String namePart, String budgetPart);
	}
