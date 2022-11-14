package com.example.nodes2022.movies;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.neo4j.repository.Neo4jRepository;

interface MovieRepository extends Neo4jRepository<Movie, UUID> {

	Optional<Movie> findByTitle(String title);
}