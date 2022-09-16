package it.epicode.be.energy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exception.EnergyException;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.repository.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepository;

	public Page<Fattura> findAll(Pageable pageable) {
		return fatturaRepository.findAll(pageable);
	}

	public Fattura save(Fattura fattura) {
		return fatturaRepository.save(fattura);
	}

	public void delete(Long id) {
		fatturaRepository.deleteById(id);

	}

	public Fattura update(Long id, Fattura fattura) {
		Optional<Fattura> fatturaResult = fatturaRepository.findById(id);
		if (fatturaResult.isPresent()) {
			Fattura update = fatturaResult.get();
			update.setAnno(fattura.getAnno());
			update.setData(fattura.getData());
			update.setImporto(fattura.getImporto());
			update.setNumeroFattura(fattura.getNumeroFattura());
			update.setStato(fattura.getStato());
			update.setCliente(fattura.getCliente());

			return fatturaRepository.save(update);
		} else
			throw new EnergyException("Errore nell'aggiornamento della Fattura");
	}

	// FIND

	public Page<Fattura> findFatturaByIdCliente(Long idCliente, Pageable pageable) {
		try {
			return fatturaRepository.findFatturaByIdCliente(idCliente, pageable);
		} catch (Exception e) {
			throw new EnergyException("Id cliente non trovato");
		}

	}

	public Page<Fattura> findByStato(String stato, Pageable pageable) {
		try {
			return fatturaRepository.findByStato(stato, pageable);
		} catch (Exception e) {
			throw new EnergyException("Stato fattura con stato "+stato+" non trovata");
		}
	}

	public Page<Fattura> findByData(LocalDate data, Pageable pageable) {
		try {
			return fatturaRepository.findByData(data, pageable);
		} catch (Exception e) {
			throw new EnergyException("Fattura con data " + data + " non trovata");
		}
	}

	public Page<Fattura> findByAnno(int anno, Pageable pageable) {
		try {
			return fatturaRepository.findByAnno(anno, pageable);
		} catch (Exception e) {
			throw new EnergyException("Fattura con anno " + anno + " non trovata");
		}
	}
	
	
	public Page<Fattura> findByRangeImporto(BigDecimal minimo, BigDecimal massimo, Pageable pageable) {
		try {
			return fatturaRepository.findByRangeImporto(minimo,massimo, pageable);
		} catch (Exception e) {
			throw new EnergyException("Range non valido");
		}
	}

}
