package it.epicode.be.energy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.energy.model.Indirizzo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {

	public Page<Indirizzo> findAll(Pageable pageable);

	

}
