package com.coltis.ecomerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltis.ecomerce.exceptions.ICException;
import com.coltis.ecomerce.exceptions.UAEException;
import com.coltis.ecomerce.models.receipt;
import com.coltis.ecomerce.models.user;
import com.coltis.ecomerce.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserService {

	private UserRepository userRepo;
	
	public user registerUser(String firstName, String lastName, String email, String password, String address, 
			String phoneNumber) {
		List<receipt> receipts = new ArrayList<>();
		user u = new user(0, firstName, lastName, email, password, receipts ,address, phoneNumber);
		
		try {
			user newU = userRepo.save(u);
			return newU;
		}catch(Exception e) {
			throw new UAEException();
		}
	}	
	
	public user loginUser(String email, String password) {
		user u = userRepo.getByEmail(email).orElseThrow(ICException::new);
		if(!u.getPassword().equals(password)) {
			throw new ICException();
		}
		return u;
	}
	
	public user readUser(Integer id) {
		return userRepo.findById(id).get();
	}
	
	public user updateUser(user updateUser) {
		user prevUser = userRepo.findById(updateUser.getUserId()).get();
		
		prevUser.setFirstName(updateUser.getFirstName());
		prevUser.setLastName(updateUser.getLastName());
		prevUser.setEmail(updateUser.getEmail());
		prevUser.setAddress(updateUser.getAddress());
		prevUser.setPhoneNumber(updateUser.getPhoneNumber());
		updateUser.setPassword(prevUser.getPassword());
		
		return userRepo.save(updateUser);
		
	}
	
	public String deleteUser(Integer id) {
		user deleteUser = userRepo.findById(id).get();
		userRepo.delete(deleteUser);
		return "User has been deleted";
	}
}
	