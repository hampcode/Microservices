package com.hampcode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hampcode.model.entity.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
