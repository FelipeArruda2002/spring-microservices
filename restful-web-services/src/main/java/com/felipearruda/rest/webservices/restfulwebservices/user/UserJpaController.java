package com.felipearruda.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipearruda.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.felipearruda.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jpa/users")
public class UserJpaController {

	private final UserRepository repository;
	private final PostRepository postRepository;
	
	public UserJpaController(UserRepository repository, PostRepository postRepository) {
		this.repository = repository;
		this.postRepository = postRepository;
	}

	@GetMapping
	public List<User> findAll() {
		return this.repository.findAll();
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> findById(@PathVariable Long id) throws Exception {
		Optional<User> optional = this.repository.findById(id);
		
		if (optional.isEmpty()) {
			throw new UserNotFoundException(String.format("User with id %d not found", id));
		}
		
		EntityModel<User> entityModel = EntityModel.of(optional.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).findAll());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		User savedUser = this.repository.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
	
	@GetMapping("/{id}/posts")
	public List<Post> getPostsForUser(@PathVariable Long id) {
		User user = this.repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
		
		return user.getPosts();
	}
	
	@PostMapping("/{id}/posts")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Post> createPostForUser(@PathVariable Long id, @Valid  @RequestBody Post post) {
		User user = this.repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
		
		post.setUser(user);
		
		post = this.postRepository.save(post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
