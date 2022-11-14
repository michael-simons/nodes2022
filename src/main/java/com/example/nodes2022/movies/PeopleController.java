package com.example.nodes2022.movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael J. Simons
 */
@RestController
@RequestMapping("/api/people")
public class PeopleController {

	private final PeopleRepository peopleRepository;

	public PeopleController(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	Person createNewPerson(@RequestBody Person person) {

		return peopleRepository.save(person);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> get(@PathVariable("id") Long id) {
		return peopleRepository.findById(id)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
