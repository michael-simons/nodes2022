package com.example.nodes2022.movies;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public final class MoviesController {

	private final MovieRepository movieRepository;

	MoviesController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@GetMapping( {"", "/"})
	public List<Movie> get() {
		return movieRepository.findAll(Sort.by("title").ascending());
	}

	@GetMapping("/{title}")
	public ResponseEntity<Movie> get(@PathVariable("title") String title) {
		return movieRepository.findByTitle(title)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
