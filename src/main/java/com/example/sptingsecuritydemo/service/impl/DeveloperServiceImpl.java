package com.example.sptingsecuritydemo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sptingsecuritydemo.model.Developer;
import com.example.sptingsecuritydemo.repository.DeveloperRepository;
import com.example.sptingsecuritydemo.service.DeveloperService;

@Service
public class DeveloperServiceImpl implements DeveloperService{

	private DeveloperRepository developerRepository;

	@Autowired
	public DeveloperServiceImpl(DeveloperRepository developerRepository) {
		this.developerRepository = developerRepository;
	}

	@Override
	public List<Developer> getList() {
		return (List<Developer>) developerRepository.findAll();
	}

	@Override
	public Optional<Developer> getById(Long id) {
		return developerRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		developerRepository.deleteById(id);
		
	}

	@Override
	public void update(Developer developer) {
		developerRepository.save(developer);
		
	}

	@Override
	public Developer save(Developer developer) {
		return developerRepository.save(developer);
	}
	
	
	
}
