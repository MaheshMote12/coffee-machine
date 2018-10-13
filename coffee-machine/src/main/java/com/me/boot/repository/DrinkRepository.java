package com.me.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.boot.model.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

}
