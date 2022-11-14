package com.example.nodes2022.movies;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@RelationshipProperties
public final class Actor {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	@TargetNode
	@JsonIgnore
	private final Person person;

	private final List<String> roles;

	public Actor(Person person, List<String> roles) {
		this.person = person;
		this.roles = List.copyOf(roles);
	}

	public Person getPerson() {
		return person;
	}

	@JsonProperty
	public String getName() {
		return person.getName();
	}

	public List<String> getRoles() {
		return List.copyOf(roles);
	}
}