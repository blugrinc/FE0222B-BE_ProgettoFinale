package it.epicode.be.energy.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.energy.model.Fattura;


public interface FatturaRepository extends JpaRepository<Fattura, Long> {

	@Query("SELECT f FROM Fattura f WHERE f.cliente.id = :id")
	public Page<Fattura> findFatturaByIdCliente(Long id, Pageable pageable);

	@Query("SELECT f FROM Fattura f WHERE f.stato = :stato")
	public Page<Fattura> findByStato(String stato, Pageable pageable);

	public Page<Fattura> findByData(LocalDate data, Pageable pageable);

	public Page<Fattura> findByAnno(int anno, Pageable pageable);

	@Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :minimo AND :massimo")
	public Page<Fattura> findByRangeImporto(BigDecimal minimo, BigDecimal massimo, Pageable pageable);

	public Page<Fattura> findAll(Pageable pageable);

}
