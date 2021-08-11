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

import com.continuity.springboot.conference.models.Speaker;
import com.continuity.springboot.conference.repositories.SpeakerRepository;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
	
	@Autowired
	private SpeakerRepository speakerRepository;
	
	@GetMapping
	public List<Speaker> list() {
		return speakerRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Speaker get(@PathVariable Long id) {
		return speakerRepository.findById(id).get();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Speaker create(@RequestBody final Speaker speaker) {
		return speakerRepository.saveAndFlush(speaker);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		//TODO: check for children records (cascades) before deleting
		speakerRepository.deleteById(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Speaker update(@PathVariable Long id, @RequestBody final Speaker speaker) {
		//NOTE: because this is a PUT method, we expect all attributes to be passed in. A PATCH method would only need a portion.
		//TODO: add validation that all attributes are passed in, otherwise return a 400 bad payload
		Speaker existingSpeaker = speakerRepository.findById(id).get();
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
		return speakerRepository.saveAndFlush(existingSpeaker);
	}
	
//	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
//	public Speaker updatePartially(@PathVariable Long id, @RequestBody final Speaker speaker) {
//		// find all attributes that are not used, and add them to the ignore parameter
//	}
}
