package org.springframework.samples.petclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/pets")
public class PetController {

	@Autowired
	PetService petService;

	@RequestMapping(value = "/pet/{id}")
	public ModelAndView petById(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();

		Pet pet = petService.findById(id);
		mv.addObject("pet", pet);

		mv.setViewName("petView");
		return mv;
	}

}
