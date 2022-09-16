package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exception.EnergyException;
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;

	public Comune save(Comune comune) {
		return comuneRepository.save(comune);
	}

	public Optional<Comune> findByNome(String nome) {
		return comuneRepository.findByNome(nome);
	}

	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepository.findAll(pageable);
	}

	public List<Comune> findAll() {
		return comuneRepository.findAll();
	}

	public Optional<Comune> findById(Long id) {
		return comuneRepository.findById(id);
	}

	public void delete(Long id) {
		if (comuneRepository.findById(id).isPresent()) {
			Comune c = comuneRepository.findById(id).get();
			c.setProvincia(null);
			comuneRepository.delete(c);
		}

	}

	public Comune update(Long id, Comune comune) {
		Optional<Comune> comuneResult = comuneRepository.findById(id);
		if (comuneResult.isPresent()) {
			Comune update = comuneResult.get();
			update.setNome(comune.getNome());
			update.setProvincia(comune.getProvincia());

			return comuneRepository.save(update);
		} else {
			throw new EnergyException("Errore nell'aggiornamento di comune");
		}
	}

}
