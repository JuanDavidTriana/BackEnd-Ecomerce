package com.coltis.ecomerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coltis.ecomerce.models.product;
import com.coltis.ecomerce.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ProductService {

	ProductRepository productRepo;
	
	public product createProduct(String name, Double price, Integer amount ,String description, String imageUrl){
		product newProduct = new product(0, name, price, amount, description, imageUrl);
		return productRepo.save(newProduct);
	}
	
	public product readProduct(Integer id) {
		return productRepo.findById(id).get();
	}

	public List<product> readAllProducts() {
		return productRepo.findAll();
	}
	
	public product updateItem(product i) {
		
		product updateItem = productRepo.findById(i.getProductId()).get();
		
		updateItem.setName(i.getName());
		updateItem.setPrice(i.getPrice());
		updateItem.setDescription(i.getDescription());
		updateItem.setImageUrl(i.getImageUrl());
		
		
		return productRepo.save(updateItem);
	}
	
	public String deleteItem(Integer id) {
		product i = productRepo.findById(id).get();
		productRepo.delete(i);
		
		return "Item has been deleted";
	}
}
