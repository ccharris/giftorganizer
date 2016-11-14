package com.harris.carolyn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harris.carolyn.beans.Recipient;


@Repository
public interface RecipientRepository extends CrudRepository<Recipient, Long> {

	public List<Recipient> findByUserId(long userId);
	public List<Recipient> findByLastNameContainsOrFirstNameContainsOrEmailContainsOrBirthdayContainsOrNotesContainsOrGroupTagContainsAllIgnoreCase(String firstNamePart, String lastNamePart, String emailPart, String birthdayPart, String notesPart, String groupTagPart);
}
