package com.ecommerce.ecommerceapp.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceapp.DTO.SigninDto;
import com.ecommerce.ecommerceapp.DTO.SigninResponseDto;
import com.ecommerce.ecommerceapp.DTO.SignupDto;
import com.ecommerce.ecommerceapp.DTO.SignupResponseDto;
import com.ecommerce.ecommerceapp.models.AuthToken;
import com.ecommerce.ecommerceapp.models.User;
import com.ecommerce.ecommerceapp.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthTokenService authTokenService;
	
	@Transactional
	public SignupResponseDto signUp(SignupDto signUpDto) throws Exception {
		
		// Checking user is present or not 
		if(Objects.nonNull(userRepo.findByEmail(signUpDto.getEmail()))) {
			throw new Exception("User is Already Present");
		}
		
		// encrypt the password
		String password=signUpDto.getPassword();
		
		try {
			password=encryptPassword(password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// save user
		User user=new User();
		user.setFirstName(signUpDto.getFirstName());
		user.setLastName(signUpDto.getLastName());
		user.setEmail(signUpDto.getEmail());
		user.setPassword(password);
		
		userRepo.save(user);
		
		// generate token
		final AuthToken authToken=new AuthToken(user);
		authTokenService.saveToken(authToken);
		
		return new SignupResponseDto("success", authToken.toString());
		
	}
	
	// text password to encrypt
	public String encryptPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[]digest=md.digest();
		String hashPassword=DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hashPassword;
	}

	public SigninResponseDto signIn(SigninDto signInDto) throws Exception {
		// checking user is present or not
		User user=userRepo.findByEmail(signInDto.getEmail());
		if(! Objects.nonNull(user)) {
			throw new Exception("User Not Present");
		}
		
		// checking the password
		try {
			if(! user.getPassword().equals(encryptPassword(signInDto.getPassword()))) {
				throw new Exception("Wrong Password");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// getting the token
		
		AuthToken token=authTokenService.getToken(user);
		
		return new SigninResponseDto("success", token.getToken());
		
	}
}
