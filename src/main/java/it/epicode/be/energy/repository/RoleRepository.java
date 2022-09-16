package it.epicode.be.energy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.energy.model.security.Role;
import it.epicode.be.energy.model.security.Roles;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(Roles role);
}
