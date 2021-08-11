package com.continuity.springboot.conference.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.continuity.springboot.conference.models.Session;
import com.continuity.springboot.conference.repositories.SessionRepository;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@GetMapping
	public List<Session> list() {
		return sessionRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Session get(@PathVariable Long id) {
		return sessionRepository.findById(id).get();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Session create(@RequestBody final Session session) {
		return sessionRepository.saveAndFlush(session);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		//TODO: check for children records (cascades) before deleting
		sessionRepository.deleteById(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Session update(@PathVariable Long id, @RequestBody final Session session) {
		//NOTE: because this is a PUT method, we expect all attributes to be passed in. A PATCH method would only need a portion.
		//TODO: add validation that all attributes are passed in, otherwise return a 400 bad payload
		Session existingSession = sessionRepository.findById(id).get();
		BeanUtils.copyProperties(session, existingSession, "session_id");
		return sessionRepository.saveAndFlush(existingSession);
	}
	
//	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
//	public Session updatePartially(@PathVariable Long id, @RequestBody final Session session) {
//		// find all attributes that are not used, and add them to the ignore parameter
//	}
}
