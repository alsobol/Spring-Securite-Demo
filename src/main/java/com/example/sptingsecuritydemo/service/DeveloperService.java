package com.example.sptingsecuritydemo.service;

import java.util.List;
import java.util.Optional;

import com.example.sptingsecuritydemo.model.Developer;

public interface DeveloperService {

	List<Developer> getList();
	
	Optional<Developer> getById(Long id);
	
	void delete(Long id);
	
	void update(Developer developer);
	
	Developer save(Developer developer);
 }
