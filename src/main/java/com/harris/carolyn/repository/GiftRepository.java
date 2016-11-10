package com.harris.carolyn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harris.carolyn.beans.Gift;
import com.harris.carolyn.beans.Recipient;

@Repository
public interface GiftRepository extends CrudRepository<Gift, Long> {
	
	public List<Gift> findByRecipientId(long recipientId);
}
