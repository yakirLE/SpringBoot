package com.continuity.springboot.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.continuity.springboot.conference.models.Speaker;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

}
