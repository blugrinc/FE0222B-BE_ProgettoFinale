package it.epicode.be.energy.service.security;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.security.User;
import it.epicode.be.energy.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

}
