package com.continuity.springboot.conference.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "sessions")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLaztInitializer", "handler"}) // no need in the newer versions - comes to fix the ByteBuddyInterceptor
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "session_id")
	private Long sessionId;
	
	@Column(name = "session_name")
	private String sessionName;
	
	@Column(name = "session_description")
	private String sessionDescription;
	
	@Column(name = "session_length")
	private Integer sessionLength;

	@ManyToMany
	@JoinTable(name = "session_speakers",
			   joinColumns = @JoinColumn(name = "session_id"),
			   inverseJoinColumns = @JoinColumn(name = "speaker_id"))
	private List<Speaker> speakers;
	
//	@ManyToMany(mappedBy = "sessions")
//	@JsonIgnore
//	private List<Speaker> speakers;
}
