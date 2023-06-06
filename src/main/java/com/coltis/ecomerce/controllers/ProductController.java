package com.coltis.ecomerce.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coltis.ecomerce.models.product;
import com.coltis.ecomerce.services.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("products")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ProductController {

	private ProductService pServ;
	
	@PostMapping("/create")
	public product create(@RequestBody LinkedHashMap<String, Object> body) {
		String name = (String) body.get("name");
		Double price = (Double) body.get("price");
		Integer amount = 1;
		String description = (String) body.get("description");
		String imageUrl = (String) body.get("imageUrl");
		
		return pServ.createProduct(name, price, amount, description, imageUrl);
	}
	
	
	@GetMapping("/read")
	public product read(@RequestBody LinkedHashMap<String, Integer> body) {
		return pServ.readProduct(body.get("id"));
	}
	
	@GetMapping("/read/all")
	public List<product> readAll() {
		return pServ.readAllProducts();
	}
	
	@PutMapping("/update")
	public product update(@RequestBody product product) {
		return pServ.updateItem(product);
	}
	
	@DeleteMapping("/delete") 
	public String delete(@RequestBody LinkedHashMap<String, Integer> body) {
		return pServ.deleteItem(body.get("id"));
	}
}
