package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.SpecialityRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;



@Service
public class PetClinicService {
	@Autowired
	static
	VetRepository vetRepository;
	
	@Autowired
	SpecialityRepository specialityRepository;
	
	public Vet altaVeterinario(String first_name, String last_name, String specialityName) {
		
		Vet vete = new Vet();
		vete.setFirstName(first_name);
		vete.setLastName(last_name);
		//aquí funciona tanto metiendo vete = vetRepository.save(vete); como vetRepository.save(vete);
		vete = vetRepository.save(vete);
		
		Specialty specialty = specialityRepository.findFirstByName(specialityName);
		vete.addSpecialty(specialty);
		vetRepository.save(vete);
		
		//cuando lo guardamos el objeto y lo guardamos se añade la fila en la BBDD, a partir de eso se crea su propio id
		//Vet buscaVet = vetRepository.findOne(vete.getId());
		return vete;
		
	}

	public static List<Vet> findByFirstNameAndLastName(String primerNombre, String apellidos) {
		return vetRepository.findByFirstNameAndLastName(primerNombre, apellidos);
	}
}
