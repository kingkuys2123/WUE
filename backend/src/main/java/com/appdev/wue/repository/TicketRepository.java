package com.appdev.wue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appdev.wue.entity.TicketEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    
}

