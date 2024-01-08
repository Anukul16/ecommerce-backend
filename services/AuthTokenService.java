package com.ecommerce.ecommerceapp.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceapp.models.AuthToken;
import com.ecommerce.ecommerceapp.models.User;
import com.ecommerce.ecommerceapp.repository.AuthTokenRepo;

@Service
public class AuthTokenService {

	@Autowired
	private AuthTokenRepo authTokenRepo;
	
	public void saveToken(AuthToken authToken) {
		// TODO Auto-generated method stub
		
		authTokenRepo.save(authToken);
		
	}

	public AuthToken getToken(User user) {
		
		return authTokenRepo.findByUser(user);
	}
	
	public void authenticate(String token) throws Exception {
		
		if(!Objects.nonNull(token)) {
			throw new Exception("Token not present");
		}
		
		if(Objects.isNull(getUserByToken(token))) {
			throw new Exception("Token is not valid");
		}
	}
	
	public User getUserByToken(String token) throws Exception {
		AuthToken authToken=authTokenRepo.findByToken(token);
		if(!Objects.nonNull(authToken)) {
			throw new Exception("Token is not valid");
		}
		return authToken.getUser();
	}

}
