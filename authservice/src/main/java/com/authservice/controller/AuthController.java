package com.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.authservice.dto.APIResponse;
import com.authservice.dto.LoginDto;
import com.authservice.dto.UpdatePasswordDto;
import com.authservice.dto.UserDto;
import com.authservice.entity.User;
import com.authservice.repository.UserRepository;
import com.authservice.service.AuthService;
import com.authservice.service.JwtService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final UserRepository userRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;

    AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	 @PostMapping("/register")
	    public ResponseEntity<APIResponse<String>> register(@RequestBody UserDto dto) {
		    dto.setRole("ROLE_ADMIN");
	        APIResponse<String> response = authService.register(dto);
	        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
	    }
	 
	 @PutMapping("/update-password")
	 public ResponseEntity<APIResponse<String>> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto){
		 APIResponse<String> response = authService.setNewPassword(updatePasswordDto);
		return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
	 }
	 
	 
	 @PostMapping("/login")
	 public ResponseEntity<APIResponse<String>> loginCheck(@RequestBody LoginDto loginDto){
		 
		 APIResponse<String> response = new APIResponse<>();
		 
		 UsernamePasswordAuthenticationToken token = 
				 new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
		 
		try {
			 Authentication authenticate = authManager.authenticate(token);
			 
			 if(authenticate.isAuthenticated()) {
				 String jwtToken = jwtService.generateToken(loginDto.getUsername(),
			                authenticate.getAuthorities().iterator().next().getAuthority());

			            response.setMessage("Login Successful");
			            response.setStatus(200);
			            response.setData(jwtToken);  // return JWT
			            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 response.setMessage("Failed");
		 response.setStatus(401);
		 response.setData("Un-Authorized Access");
		 return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
	 }
	 
	 @GetMapping("/get-user")
	 public User getUser(@RequestParam String username) {
		 return userRepository.findByUsername(username);
	 }

}








