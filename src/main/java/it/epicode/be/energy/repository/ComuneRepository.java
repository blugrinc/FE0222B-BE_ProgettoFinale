package it.epicode.be.energy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.energy.model.Comune;

public interface ComuneRepository extends JpaRepository<Comune, Long>  {

	public Page<Comune> findAll(Pageable pageable);

	public Optional<Comune> findByNome(String nome);
	


}
