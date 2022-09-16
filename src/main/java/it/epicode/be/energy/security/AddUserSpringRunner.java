package it.epicode.be.energy.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.be.energy.model.security.Role;
import it.epicode.be.energy.model.security.Roles;
import it.epicode.be.energy.model.security.User;
import it.epicode.be.energy.repository.RoleRepository;
import it.epicode.be.energy.repository.UserRepository;


@Component
public class AddUserSpringRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		Role roleU = new Role();
		roleU.setRoleName(Roles.ROLE_USER);
		Role roleA = new Role();
		roleA.setRoleName(Roles.ROLE_ADMIN);
		
	
		User admin = new User();
		Set<Role> rolesA = new HashSet<>(); 
		rolesA.add(roleA);
		rolesA.add(roleU);
		admin.setUserName("admin");
		admin.setPassword(bCrypt.encode("admin"));
		admin.setEmail("admin@admin.it");
		admin.setRoles(rolesA);
		admin.setActive(true);
		
		
		User user = new User();
		Set<Role> rolesU = new HashSet<>(); 
		rolesU.add(roleU);
		user.setUserName("user");
		user.setPassword(bCrypt.encode("user"));
		user.setEmail("user@user.it");
		user.setRoles(rolesU);
		user.setActive(true);
		
		
		roleRepository.save(roleU);
		roleRepository.save(roleA);
		userRepository.save(admin);
		userRepository.save(user);


	}

}
