package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exception.EnergyException;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;

	public Indirizzo save(Indirizzo indirizzo) {
		return indirizzoRepository.save(indirizzo);
	}


	public Page<Indirizzo> findAll(Pageable pageable) {
		return indirizzoRepository.findAll(pageable);
	}

	public List<Indirizzo> findAll() {
		return indirizzoRepository.findAll();
	}

	public Optional<Indirizzo> findById(Long id) {
		return indirizzoRepository.findById(id);
	}

	public void delete(Long id) {
		if (indirizzoRepository.findById(id).isPresent()) {
			Indirizzo i = indirizzoRepository.findById(id).get();

			indirizzoRepository.delete(i);
		}

	}

	public Indirizzo update(Long id, Indirizzo indirizzo) {
		Optional<Indirizzo> Indirizzoresult = indirizzoRepository.findById(id);
		if (Indirizzoresult.isPresent()) {
			Indirizzo update = Indirizzoresult.get();
			update.setVia(indirizzo.getVia());
			update.setCivico(indirizzo.getCivico());
			update.setLocalita(indirizzo.getLocalita());
			update.setCap(indirizzo.getCap());
			update.setComune(indirizzo.getComune());

			return indirizzoRepository.save(update);
		} else {
			throw new EnergyException("Errore nell'aggiornamento di indirizzo");
		}
	}
}

