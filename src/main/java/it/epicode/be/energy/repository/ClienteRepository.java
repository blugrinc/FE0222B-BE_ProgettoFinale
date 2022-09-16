package it.epicode.be.energy.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.energy.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	// OrderBy:
	public Page<Cliente> OrderByNomeContattoAsc(Pageable pageable);
	
	public Page<Cliente> OrderByFatturatoAnnuale(Pageable pageable);
	
	public Page<Cliente> OrderByDataInserimento(Pageable pageable);
	
	public Page<Cliente> OrderByDataUltimoContattoAsc(Pageable pageable);

	
	public Page<Cliente> OrderBySedeLegale(Pageable pageable);

	// Filter:
	public Page<Cliente> findByFatturatoAnnuale(BigDecimal fatturato, Pageable pageable);

	public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);

	public Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoInserimento, Pageable pagable);
	
	
	
	@Query("SELECT c FROM Cliente c WHERE c.nomeContatto LIKE %:nomeContatto% ")
	public Page<Cliente> findByParteNome(String nomeContatto, Pageable pageable);
	
	public Page<Cliente> findAll(Pageable pageable);
	


	

}

