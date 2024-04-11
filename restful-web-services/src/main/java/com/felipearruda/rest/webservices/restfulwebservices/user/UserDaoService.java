package com.felipearruda.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	private static long usersCount = 0;
	
	static {
		users.add(new User(++usersCount, "Felipe", LocalDate.now().minusYears(22)));
		users.add(new User(++usersCount, "Aline", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Daniela", LocalDate.now().minusYears(20)));
		users.add(new User(++usersCount, "Dayane", LocalDate.now().minusYears(37)));
	}

	public List<User> findAll() {
		return users;
	}

	public User findById(Long id) {
		return this.users.stream()
				.filter(user -> id.equals(user.getId()))
				.findFirst()
				.orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		this.users.add(user);
		return user;
	}
	
	public void deleteById(Long id) {
		this.users.removeIf(user -> id.equals(user.getId()));
	}
	
}
