package com.harris.carolyn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harris.carolyn.beans.Gift;

@Repository
public interface GiftRepository extends CrudRepository<Gift, Long> {

}
