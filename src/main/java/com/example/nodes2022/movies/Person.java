package com.example.nodes2022.movies;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import com.fasterxml.jackson.annotation.JsonCreator;

@Node
public final class Person {

	@Id @GeneratedValue
	private final Long id;

	private final String name;

	private Integer born;

	@PersistenceCreator
	private Person(Long id, String name, Integer born) {
		this.id = id;
		this.born = born;
		this.name = name;
	}

	@JsonCreator
	public Person(String name, Integer born) {
		this(null, name, born);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getBorn() {
		return born;
	}

	public void setBorn(Integer born) {
		this.born = born;
	}
}