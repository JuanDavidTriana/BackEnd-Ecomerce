package com.coltis.ecomerce.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.coltis.ecomerce.models.product;
import com.coltis.ecomerce.models.receipt;
import com.coltis.ecomerce.services.ReceiptService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("receipts")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ReceiptController {

	private ReceiptService rServ;

	@PostMapping("/create")
	public receipt create(@RequestBody ReceiptRegisterObject rro) {
		Integer userId = rro.userId;
		List<product> products = rro.items;
		Integer amountOfItems = rro.amountOfItems;
		return rServ.createReceipt(userId, products, amountOfItems);
	}
	
	@GetMapping("/read")
	public receipt read(@RequestBody LinkedHashMap<String, Integer> body) {
		return rServ.readReceipt(body.get("id"));
	}
	
	@GetMapping("/readuser")
	public List<receipt> readUser(@RequestParam(name = "id") Integer id) {
		return rServ.getReceiptByUser(id);
	}
	
	@PutMapping("/update")
	public receipt update(@RequestBody receipt r) {
		return rServ.updateReceipt(r);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestBody LinkedHashMap<String, Integer> body) {
		return rServ.deleteReceipt(body.get("id"));
	}
	
}

class ReceiptRegisterObject {
	public Integer userId;
	public List<product> items;
	public Integer amountOfItems;
}