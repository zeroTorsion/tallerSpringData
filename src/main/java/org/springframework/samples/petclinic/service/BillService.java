package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.bill.Bill;
import org.springframework.samples.petclinic.repository.BillRepository;
import org.springframework.stereotype.Service;


@Service
public class BillService{
	@Autowired 
	private BillRepository billRepository;
	public List<Bill> findAll(){
		return this.billRepository.findAll();
	}
	
	public Bill findById(Integer id) {
		return this.billRepository.findOne(id);
	}
	
	public Bill save(Bill bill) {
		 return this.billRepository.save(bill);
	}

	public Bill delete(Bill bill) {
		this.billRepository.delete(bill);
		return bill;
	}

	public void  deleteAll(){
		this.billRepository.deleteAll();
	}

	//public Bill update(Bill bill) {
	//	return this.billRepository.update(bill);
	//}

	
}
