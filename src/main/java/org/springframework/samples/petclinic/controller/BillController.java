package org.springframework.samples.petclinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
	
	@Autowired
	private BillService billService;

	@RequestMapping(value="/bills", method=RequestMethod.GET)
	public List<Bill> findAll() {
		return billService.findAll();
	}
	
	@RequestMapping(value="/bills/{idBill}", method=RequestMethod.GET)
	public Bill findById(@PathVariable("idBill") Integer id) {
		Bill b = billService.findById(id);
		return b;
	}
	
	
	// En este caso estamos devolviendo un ResponseEntity para indicar los posibles estado de la respuesta (OK, KO).
	// Lo más adecuado sería encapsular los errores en una clase y manejar las excepciones en caso de que sea necesario
//	@RequestMapping(value="/bills/{idBill}", method=RequestMethod.GET)
//	public ResponseEntity<Bill> findByIdOption2(@PathVariable("idBill") Integer id) {
//		Bill b = new Bill();
//		b = this.billService.findById(id);
//		if(b != null)
//			return ResponseEntity.status(HttpStatus.OK).body(b);
//		else
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//	}
	
	
	@RequestMapping(value="/bills", method=RequestMethod.POST)
	public Bill saveBill(@RequestBody Bill b) {
		if(b != null)
			return billService.save(b);
		return null;
	}
	
	
	// Ejercicio 5 del taller. No permitimos crear una factura en el caso de que nos pasen el id, ya que el verbo
	// que estamos utilizando es POST
//	@RequestMapping(value="/bills", method=RequestMethod.POST)
//	public ResponseEntity<Bill> saveBillOption2(@RequestBody Bill b) {
//		if(b != null)
//			if(b.getId() != null)
//				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//			else
//				return ResponseEntity.status(HttpStatus.OK).body(this.billService.save(b));
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//	}
	
	
	// Ejercicio 6
	@RequestMapping(value="/bills/{idBill}", method=RequestMethod.PUT) 
	public Bill updateBill(@PathVariable("idBill") Integer id, @RequestBody Bill b) {
		Bill fromDB = this.billService.findById(id);
		if(fromDB != null) {
			b.setId(fromDB.getId());
			return this.billService.save(b);
		}
		return null;			
	}
	
	
	//Ejercicio 7
	@RequestMapping(value="/bills/{idBill}", method=RequestMethod.DELETE) 
	public ResponseEntity<Bill> deleteBill(@PathVariable("idBill") Integer id) {
		Bill fromDB = billService.findById(id);
		if(fromDB != null) {
			billService.deleteBill(fromDB);
			return ResponseEntity.status(HttpStatus.OK).body(fromDB);			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);	
	}

	//Ejercicio 7
	@RequestMapping(value="/bills", method=RequestMethod.DELETE) 
	public void deleteAllBills() {
		billService.deleteAll();			
	}





}

