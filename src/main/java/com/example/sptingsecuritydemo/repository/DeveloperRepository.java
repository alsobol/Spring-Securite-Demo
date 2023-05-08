package com.example.sptingsecuritydemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.sptingsecuritydemo.model.Developer;

public interface DeveloperRepository extends CrudRepository<Developer, Long>{

	
}
