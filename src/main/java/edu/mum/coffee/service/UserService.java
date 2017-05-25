package edu.mum.coffee.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Authorities;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Users;
import edu.mum.coffee.repository.PersonRepository;
import edu.mum.coffee.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	private static final String DEFAULT_PHONE = "(123) 789-1234";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public Users save(Users users) {
		users.setEnabled(true);
		users.setPassword(encodePassword(users.getPassword()));

		Set<Authorities> authorities = new HashSet<>();
		Authorities authority = new Authorities();
		authority.setAuthority("ROLE_USER");
		authority.setUsers(users);
		authorities.add(authority);
		users.setAuthorities(authorities);
		
		Person person = createPersonByUser(users);
		personRepository.save(person);
		return userRepository.save(users);
	}
	
	public Users findUser(String username) {
		return userRepository.findOne(username);
	}

	public String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	private Person createPersonByUser(Users users) {
		Person person = new Person();
		person.setEmail(users.getUserName());
		person.setEnable(true);
		person.setFirstName(users.getFirstName());
		person.setLastName(users.getLastName());
		person.setPhone(DEFAULT_PHONE);
		person.setAddress(new Address("FairField", "IA", "US", "52766"));
		return person;
	}
}
