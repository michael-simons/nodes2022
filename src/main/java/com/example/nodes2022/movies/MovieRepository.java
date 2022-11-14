package com.example.nodes2022.movies;

import org.springframework.data.neo4j.repository.Neo4jRepository;

interface MovieRepository extends Neo4jRepository<Movie, String> {
}