package com.example.nodes2022.movies;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import ac.simons.neo4j.migrations.springframework.boot.autoconfigure.MigrationsAutoConfiguration;

@DataNeo4jTest
@ImportAutoConfiguration(MigrationsAutoConfiguration.class)
class MoviesTests {
	private static Neo4j embeddedDatabaseServer;

	@DynamicPropertySource
	static void neo4jProperties(DynamicPropertyRegistry registry) {

		registry.add("spring.neo4j.uri", embeddedDatabaseServer::boltURI);
		registry.add("spring.neo4j.authentication.username", () -> "neo4j");
		registry.add("spring.neo4j.authentication.password", () -> null);
	}

	@Test
	void getDetailsByNameShouldWork(@Autowired PeopleRepository peopleRepository) {

		var optionalDetails = peopleRepository.getDetailsByName("Keanu Reeves");

		assertThat(optionalDetails).hasValueSatisfying(personDetails -> {
			assertThat(personDetails.name()).isEqualTo("Keanu Reeves");
			assertThat(personDetails.born()).isEqualTo(1964);
			assertThat(personDetails.actedIn())
				.hasSize(3)
				.extracting(Movie::getTitle).contains("The Matrix Reloaded");
			assertThat(personDetails.related()).hasSize(5);
		});

		assertThat(peopleRepository.getDetailsByName("foobar")).isEmpty();
	}

	@Test
	void shouldCreateMovie(@Autowired MovieRepository movieRepository) {
		var movie = movieRepository.save(new Movie("Bullet Train", "A movie about vengeance."));
		assertThat(movie.getId()).isNotNull();
	}

	@Test
	void findByTitleShouldWork(@Autowired MovieRepository movieRepository) {
		var title = "The Matrix";

		assertThat(movieRepository.findByTitle(title))
			.isPresent()
			.hasValueSatisfying(movie -> assertThat(movie.getTitle()).isEqualTo(title));
	}

	@BeforeAll
	static void initializeNeo4j() {

		embeddedDatabaseServer = Neo4jBuilders.newInProcessBuilder()
			.withDisabledServer()
			.build();
	}

	@AfterAll
	static void stopNeo4j() {

		embeddedDatabaseServer.close();
	}
}