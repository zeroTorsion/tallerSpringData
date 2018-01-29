package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;

public interface PetService {
	
	void save(Pet pet);
	
	Pet findById(Integer id);
	
	List<PetType> findPetTypes();


}
