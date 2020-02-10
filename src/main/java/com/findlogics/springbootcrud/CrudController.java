package com.findlogics.springbootcrud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.findlogics.springbootcrud.exceptionhandling.ResourceNotFoundException;
import com.findlogics.springbootcrud.model.User;
import com.findlogics.springbootcrud.repository.UserRepository;

@RestController
public class CrudController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/signup")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@PostMapping("/signup")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/signup/{id}")
	public User getUserById(@PathVariable(value = "id") Integer userId) throws ResourceNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			throw new ResourceNotFoundException("User", "id", userId);
		return user.get();
	}

	@PutMapping("/signup/{id}")
	public User updateUser(@PathVariable(value = "id") Integer userId, @RequestBody User userDetails)
			throws ResourceNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			throw new ResourceNotFoundException("User", "id", userId);
		if (userDetails.getName() == null || userDetails.getName().isEmpty())
			userDetails.setName(user.get().getName());
		if (userDetails.getEmail() == null || userDetails.getEmail().isEmpty())
			userDetails.setEmail(user.get().getEmail());
		if (userDetails.getPassword() == null || userDetails.getPassword().isEmpty())
			userDetails.setPassword(user.get().getPassword());
		userDetails.setId(userId);
		return userRepository.save(userDetails);
	}

	@DeleteMapping("/signup/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}

}
