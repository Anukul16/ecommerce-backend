package com.ecommerce.ecommerceapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerceapp.DTO.SigninDto;
import com.ecommerce.ecommerceapp.DTO.SigninResponseDto;
import com.ecommerce.ecommerceapp.DTO.SignupDto;
import com.ecommerce.ecommerceapp.DTO.SignupResponseDto;
import com.ecommerce.ecommerceapp.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/signup")
	public SignupResponseDto signUp(@RequestBody SignupDto signUpDto) throws Exception{

		return userService.signUp(signUpDto);
		
	}
	
	
	@PostMapping("/signin")
	public SigninResponseDto signIn(@RequestBody SigninDto signInDto) throws Exception {
		return userService.signIn(signInDto);
	}
}
