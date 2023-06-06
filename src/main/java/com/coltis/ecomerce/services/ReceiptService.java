package com.coltis.ecomerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.coltis.ecomerce.models.product;
import com.coltis.ecomerce.models.receipt;
import com.coltis.ecomerce.models.user;
import com.coltis.ecomerce.repository.ProductRepository;
import com.coltis.ecomerce.repository.ReceiptRepository;
import com.coltis.ecomerce.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ReceiptService {

	private ReceiptRepository rRepo;
	private UserRepository uRepo;
	private ProductRepository pRepo;
	
	public receipt createReceipt(String email, List<product> items, Double total) {
		user u = uRepo.getByEmail(email).get();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String now = LocalDateTime.now().format(format);
		Integer amountOfItems = items.size();
		receipt rec = new receipt(u, items, total, now, amountOfItems);
		return rRepo.save(rec);
	}
	
	public receipt createReceipt(Integer id, List<product> products, Integer amountOfItems) {
		user u = uRepo.findById(id).get();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String now = LocalDateTime.now().format(format);
		List<product> receiptItems = new ArrayList<>();
		Double total = 00.00;
		
		for(product i : products) {
			product addItem = pRepo.findById(i.getProductId()).get();
			addItem.setAmount(i.getAmount());
			total += addItem.getPrice() * i.getAmount();
			receiptItems.add(addItem);
		}
		
		total *= 1.59;
		
		if(total < 952.38) {
			total += 2;
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		total = Double.parseDouble(df.format(total));
		
		Integer amountOfItemsOnReceipt = receiptItems.size();
		receipt rec = new receipt(u, receiptItems, total, now, amountOfItemsOnReceipt);
		return rRepo.save(rec);
	}
	
	public receipt readReceipt(Integer id) {
		return rRepo.findById(id).get();
	}
	
	public receipt updateReceipt(receipt r) {
		receipt updateReceipt = rRepo.findById(r.getReceiptNumer()).get();
		
		updateReceipt.setAmountOfItems(r.getAmountOfItems());
		updateReceipt.setDateTime(r.getDateTime());
		updateReceipt.setProducts(r.getProducts());
		updateReceipt.setTotal(r.getTotal());
		updateReceipt.setUser(r.getUser());
		
		return rRepo.save(updateReceipt);
	}
	
	public String deleteReceipt(Integer id) {
		rRepo.delete(rRepo.findById(id).get());
		return "Receipt has been deleted";
	}
	
	public List<receipt> getReceiptByUser(Integer u) {
		List<receipt> all = rRepo.findAll();
		List<receipt> result = new ArrayList<>();
		for(receipt r : all) {
			if(r.getUser().getUserId() == u) {
				result.add(r);
			}
		}		
		return result;
	}
}
