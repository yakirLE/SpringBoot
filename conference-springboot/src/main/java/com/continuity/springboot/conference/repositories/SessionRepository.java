package com.continuity.springboot.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.continuity.springboot.conference.models.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
