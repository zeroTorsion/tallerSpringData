package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.repository.BillRepository;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;
	
	public List<Bill> findAll() {
		return billRepository.findAll();
	}	
	public Bill findById(Integer id) {
		return billRepository.findOne(id);
	}
	
	
	public Bill save(Bill b) {
		return billRepository.save(b);
	}

	
	public void deleteBill(Bill b) {
		billRepository.delete(b);;
	}

	public void deleteAll() {
		billRepository.deleteAll();
	}



}
