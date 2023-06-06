package com.coltis.ecomerce.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.coltis.ecomerce.models.user;
import com.coltis.ecomerce.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

	private UserService uServ;
	
	@PostMapping("/register")
	  public user register(@RequestBody LinkedHashMap<String, String> body) {
	    String firstName = body.get("firstName");
	    String lastName = body.get("lastName");
	    String email = body.get("email");
	    String password = body.get("password");
	    String address = "";
	    String phoneNumber = "";

	    return uServ.registerUser(firstName, lastName, email, password, address, phoneNumber);
	  }

	@GetMapping("/user")
	  public user readUser(@RequestParam(name = "id") Integer id) {
	    return uServ.readUser(id);
	  }

	  @PutMapping("/update")
	  public user update(@RequestBody user user) {
	    return uServ.updateUser(user);
	  }

	  @DeleteMapping("/delete")
	  public String delete(@RequestBody LinkedHashMap<String, Integer> body) {
	    return uServ.deleteUser(body.get("id"));
	  }

	  @PostMapping("/login")
	  public user login(@RequestBody LinkedHashMap<String, String> body) {
	    String email = body.get("email");
	    String password = body.get("password");

	    return uServ.loginUser(email, password);
	  }
}
