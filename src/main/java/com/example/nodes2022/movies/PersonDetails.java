package com.example.nodes2022.movies;

import java.util.List;

public record PersonDetails(String name, Integer born, List<Movie> actedIn, List<Movie> directed,
                            List<Person> related) {

	public PersonDetails {
		actedIn = List.copyOf(actedIn);
		directed = List.copyOf(directed);
		related = List.copyOf(related);
	}
}
