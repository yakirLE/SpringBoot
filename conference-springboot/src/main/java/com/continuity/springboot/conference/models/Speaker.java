package com.continuity.springboot.conference.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "speakers")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLaztInitializer", "handler"}) // no need in the newer versions - comes to fix the ByteBuddyInterceptor
public class Speaker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "speaker_id")
	private Long speakerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String title;
	
	private String company;
	
	@Column(name = "speaker_bio")
	private String speakerBio;
	
	@Column(name = "speaker_photo")
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] speakerPhoto;
	
	@ManyToMany(mappedBy = "speakers")
	@JsonIgnore
	private List<Session> sessions;
	
//	@ManyToMany
//	@JoinTable(name = "session_speakers",
//			   joinColumns = @JoinColumn(name = "speaker_id"),
//			   inverseJoinColumns = @JoinColumn(name = "session_id"))
//	private List<Session> sessions;
}
