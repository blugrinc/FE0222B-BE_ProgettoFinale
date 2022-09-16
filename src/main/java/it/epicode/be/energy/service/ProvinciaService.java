package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exception.EnergyException;
import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;

	public Optional<Provincia> findById(Long id) {
		return provinciaRepository.findById(id);
	}

	public Page<Provincia> findAll(Pageable pageable) {
		return provinciaRepository.findAll(pageable);
	}

	public List<Provincia> findAll() {
		return provinciaRepository.findAll();
	}

	public Provincia save(Provincia provincia) {
		return provinciaRepository.save(provincia);
	}

	public void delete(Long id) {
		provinciaRepository.deleteById(id);
	}

	public Provincia update(Long id, Provincia cliente) {
		Optional<Provincia> clienteResult = provinciaRepository.findById(id);
		if (clienteResult.isPresent()) {
			Provincia update = clienteResult.get();
			update.setSigla(cliente.getSigla());
			update.setNome(cliente.getNome());
			update.setRegione(cliente.getRegione());
			return provinciaRepository.save(update);
		} else {
			throw new EnergyException("Errore nell'aggiornamento della Provincia");
		}
	}

}

