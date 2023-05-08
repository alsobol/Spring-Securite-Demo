package com.example.sptingsecuritydemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sptingsecuritydemo.model.Developer;
import com.example.sptingsecuritydemo.service.DeveloperService;

@RestController
@RequestMapping("api/v1/developers")
public class DeveloperRestControllerV1 {


	private DeveloperService developerService;
	
	@Autowired
	public DeveloperRestControllerV1(DeveloperService developerService) {
		this.developerService = developerService;
	}

	@GetMapping
	public List<Developer> getAll() {
		return developerService.getList();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public Developer getById(@PathVariable Long id) {
		return developerService.getById(id).stream().filter(developer -> developer.getId().equals(id)).findFirst().orElse(null);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('developers:write')")
	public Developer create(@RequestBody Developer developer) {
		this.developerService.save(developer);
		return developer;
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void deleteById(@PathVariable Long id) {
		this.developerService.delete(id);
	}
}
