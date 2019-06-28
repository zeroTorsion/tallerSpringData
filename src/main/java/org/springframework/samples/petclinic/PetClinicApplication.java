/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.repository.SpecialityRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.service.PetClinicService;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {
	@Autowired
	VetRepository vetRepository;
	
	@Autowired
	SpecialityRepository specialityRepository;
	
	@Autowired
	PetClinicService petClinicService;
	private static final Logger log = LoggerFactory.getLogger(PetClinicApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PetClinicApplication.class, args);
    }
    
    @Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialityRepository specialityRepository) {
		return (args) -> {
			log.info("*****************************************************");
			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
			log.info("*****************************************************");
			
			log.info("Nuevo objeto vet, guardado en BD, consultamos su id y vemos si funciona: ");
			String primerNombre = "Carlos";
			String apellidos = "García Pérez"; 
			String nombreEspecialidad="Radiology";
			
			List<Vet> listaVet = PetClinicService.findByFirstNameAndLastName(primerNombre,apellidos);
			if(listaVet==null||listaVet.size()>0) {
				
				petClinicService.altaVeterinario(primerNombre,apellidos,nombreEspecialidad);
				
				
//				Vet vete = new Vet();
//				vete.setFirstName(primerNombre);
//				vete.setLastName(apellidos);
//				//aquí funciona tanto metiendo vete = vetRepository.save(vete); como vetRepository.save(vete);
//				vetRepository.save(vete);
//				
//				Specialty specialty = specialityRepository.findFirstByName("Radiology");
//				vete.addSpecialty(specialty);
//				vetRepository.save(vete);
//				
//				//cuando lo guardamos el objeto y lo guardamos se añade la fila en la BBDD, a partir de eso se crea su propio id
//				Vet buscaVet = vetRepository.findOne(vete.getId());
//				Alta veterinario
			}
			//List<Vet> radiologos= vetRepository.findBySpecialityName("Radiology");
			//for(Vet vet1 : radiologos) {
				//log.info(vet1.toString());
			//}
			
			//aquí pinta los veterinarios
			List<Vet> vets = vetRepository.findAll();
			
			for(Vet vet : vets) {
				log.info(vet.toString());
			}
			
		};
	}
    
}
