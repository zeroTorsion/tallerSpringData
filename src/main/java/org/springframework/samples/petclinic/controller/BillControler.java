package org.springframework.samples.petclinic.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.bill.Bill;
import org.springframework.samples.petclinic.model.owner.Owner;
import org.springframework.samples.petclinic.repository.BillRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BillControler
{
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "bills/createOrUpdateOwnerForm";
    
	@Autowired
	private BillService billService;
	
	@RequestMapping(value="/bills",method=RequestMethod.GET)
	public List<Bill> findAll(){
		return this.billService.findAll();
	}
	
	/*@RequestMapping(value="/bills/{idBill}",method=RequestMethod.GET)
	public Bill findById(@PathVariable("idBill") Integer id){
		Bill b = this.billService.findByI(id);
		return b;
	}*/
	
	@RequestMapping(value="/bills/{idBill}",method=RequestMethod.GET)
	public ResponseEntity<Bill> findById(@PathVariable("idBill") Integer id){
		Bill b = new Bill();
		b = this.billService.findById(id);
		if (b!=null)
			return ResponseEntity.status(HttpStatus.OK).body(b);
		else 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	  @RequestMapping(value = "/bills/new", method = RequestMethod.GET)
	    public String initCreationForm(Map<String, Object> model) {
	        Bill bill = new Bill();
	        model.put("factura", bill);
	        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	    }

	    @RequestMapping(value = "/bills", method = RequestMethod.POST)
	    public String processCreationForm(@Valid Bill bill, BindingResult result) {
	        if (result.hasErrors()) {
	            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	        } else {
	            this.billService.save(bill);
	            return "redirect:/bills/" + bill.getId();
	        }
		
	        
	    }
	
	    @RequestMapping(value = "/bills/{billId}", method = RequestMethod.PUT)
	    public Bill update(@PathVariable(value = "billId") Integer userId, 
	                       @RequestBody @Valid Bill bill) {
	    	Bill fromDB=this.billService.findById(userId);
	    	if(fromDB != null) {
	    		bill.setId(fromDB.getId());
	    		return this.billService.save(bill);
	        
	        }
	    	return null;
	    }

	    @RequestMapping(value = "/bills/{billId}", method = RequestMethod.DELETE)
	    public Bill delete(@PathVariable(value = "billId") Integer userId, 
	                       @RequestBody @Valid Bill bill) {
	    	Bill fromDB=this.billService.findById(userId);
	    	if(fromDB != null) {
	    		bill.setId(fromDB.getId());
	    		return this.billService.delete(bill);
	        
	        }
	    	return null;
	    }
	    
	    @RequestMapping(value = "/bills", method = RequestMethod.DELETE)
	    public  void deleteAll()  {
	    	
	    		 this.billService.deleteAll();
	        
	        }
	    	
	    @RequestMapping(method = RequestMethod.GET,value = "/bills", params = { "pagada" })
	    @ResponseBody
	    public List<Bill> pagadas(){
	    	@RequestParam(value = "pagada", defaultValue = False) int page {
	    		//find all
	    	}
	        return userService.list(page,size);

	    	
	    }

}
