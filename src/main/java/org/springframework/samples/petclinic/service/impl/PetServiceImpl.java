package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	PetRepository petRepository;
	
	public void save(Pet pet) {
		petRepository.save(pet);
	}	
	
	public Pet findById(Integer id) {
		return petRepository.findOne(id);
	}
	public List<PetType> findPetTypes() {
		return petRepository.findPetTypes();
	}


}
