package com.example.nodes2022.movies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public final class Movie {

	@Id
	private final String title;

	@Property("tagline")
	private final String description;

	@Relationship(value = "ACTED_IN", direction = Direction.INCOMING)
	private final List<Actor> actors;

	@Relationship(value = "DIRECTED", direction = Direction.INCOMING)
	private final List<Person> directors;

	private Integer released;

	public Movie(String title, String description) {
		this.title = title;
		this.description = description;
		this.actors = new ArrayList<>();
		this.directors = new ArrayList<>();
	}

	@PersistenceCreator
	public Movie(String title, String description, List<Actor> actors, List<Person> directors) {
		this.title = title;
		this.description = description;
		this.actors = actors == null ? new ArrayList<>() : new ArrayList<>(actors);
		this.directors = directors == null ? new ArrayList<>() : new ArrayList<>(directors);
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public List<Actor> getActors() {
		return List.copyOf(this.actors);
	}

	public List<Person> getDirectors() {
		return List.copyOf(this.directors);
	}

	public Integer getReleased() {
		return released;
	}

	public void setReleased(Integer released) {
		this.released = released;
	}

	public Movie addActors(Collection<Actor> actors) {
		this.actors.addAll(actors);
		return this;
	}

	public Movie addDirectors(Collection<Person> directors) {
		this.directors.addAll(directors);
		return this;
	}
}