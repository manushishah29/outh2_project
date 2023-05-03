package com.spring.security.contoller;

import java.util.List;

import com.spring.security.enums.CustomEnums;
import com.spring.security.exception.CustomException;
import com.spring.security.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.spring.security.model.UserInfo;
import com.spring.security.service.UserInfoService;

@RestController
@Slf4j
@CrossOrigin(value = "localhost:8080")
public class UserController {
	@Autowired
	private UserInfoService userService;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@GetMapping("/user")
	public Object getAllUser(@RequestHeader HttpHeaders requestHeader) {
		try {
			List<UserInfo> userInfos = userService.getAllActiveUserInfo();
			if (userInfos == null || userInfos.isEmpty()) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			return userInfos;
		}catch (CustomException e) {
			log.error("error ::{}", e);
			throw e;
		} catch (Exception e) {
			throw new CustomException(CustomEnums.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@PostMapping("/user")
	public UserInfo addUser(@RequestBody UserInfo userRecord) {
		return userService.addUser(userRecord);
	}
	@GetMapping("/getUserName")
	public UserInfo getUserByUserName(@RequestParam String userName)
	{
		return userService.getUserByUserName(userName);
	}
	@PutMapping("/user/{id}")
	public UserInfo updateUser(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
		return userService.updateUser(id,userRecord);
	}

	
	@PutMapping("/user/changePassword/{id}")
	public UserInfo updateUserPassword(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
		return userService.updatePassword(id,userRecord);
	}
	
	@PutMapping("/user/changeRole/{id}")
	public UserInfo updateUserRole(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
		return userService.updateRole(id,userRecord);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserInfo> getUserById(@PathVariable Integer id) {
		UserInfo userInfo = userService.getUserInfoById(id);
		if (userInfo == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}
}