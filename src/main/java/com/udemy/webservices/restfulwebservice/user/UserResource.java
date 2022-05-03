package com.udemy.webservices.restfulwebservice.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping()
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("Id= " + id);

		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("All-users"));

		return model;
	}
	
	@PostMapping()
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable int id){
		User user = service.deleteById(id);
		if(user == null){
			throw new UserNotFoundException("Id: "+id);
		}
		return user;
	}
}
