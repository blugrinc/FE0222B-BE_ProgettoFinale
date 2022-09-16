package it.epicode.be.energy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exception.EnergyException;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.repository.ClienteRepository;
import it.epicode.be.energy.repository.FatturaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	FatturaRepository fatturaRepository;

	public Page<Cliente> findByParteNome(String nome, Pageable pageable) {
		try {
			Page<Cliente> clienti = clienteRepository.findByParteNome(nome, pageable);
			if (clienti.hasContent()) {
				return clienti;
			}
			log.error("Cliente non trovato");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}

	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente update(Long id, Cliente cliente) {
		Optional<Cliente> clienteResult = clienteRepository.findById(id);
		if (clienteResult.isPresent()) {
			Cliente update = clienteResult.get();
			update.setRagioneSociale(cliente.getRagioneSociale());
			update.setDataInserimento(cliente.getDataInserimento());
			update.setDataUltimoContatto(cliente.getDataUltimoContatto());
			update.setPartitaIva(cliente.getPartitaIva());
			update.setNomeContatto(cliente.getNomeContatto());
			update.setCognomeContatto(cliente.getCognomeContatto());
			update.setEmailContatto(cliente.getEmailContatto());
			update.setTelefonoContatto(cliente.getTelefonoContatto());
			update.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			update.setPec(cliente.getPec());
			update.setEmail(cliente.getEmail());
			update.setSedeLegale(cliente.getSedeLegale());
			update.setSedeOperativa(cliente.getSedeOperativa());
			update.setTelefono(cliente.getTelefono());
			update.setTipoCliente(cliente.getTipoCliente());
			return clienteRepository.save(update);
		} else {
			throw new EnergyException("Errore nell'aggiornamento di Cliente");
		}
	}
	
	//ORDER

	public Page<Cliente> OrderByNomeContattoAsc(Pageable pageable) {
		try {
			Page<Cliente> clientiNomeAsc = clienteRepository.OrderByNomeContattoAsc(pageable);
			if (clientiNomeAsc.hasContent()) {
				return clientiNomeAsc;
			}
			log.error("Cliente non trovato");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}

	public Page<Cliente> OrderByFatturatoAnnuale(Pageable pageable) {
		try {
			Page<Cliente> fatturatoAnnualeAsc = clienteRepository.OrderByFatturatoAnnuale(pageable);
			if (fatturatoAnnualeAsc.hasContent()) {
				return fatturatoAnnualeAsc;
			}
			log.error("Fatturato non trovato");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}

	public Page<Cliente> OrderByDataInserimento(Pageable pageable) {
		try {
			Page<Cliente> dataInserimento = clienteRepository.OrderByDataInserimento(pageable);
			if (dataInserimento.hasContent()) {
				return dataInserimento;
			}
			log.error("Data Inserimento non trovata");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}
	}

	// FIND

	public Page<Cliente> findByFatturatoAnnuale(BigDecimal fatturato, Pageable pageable) {
		try {
			return clienteRepository.findByFatturatoAnnuale(fatturato, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}
	
	public Page<Cliente> findByDataInserimento(LocalDate data, Pageable pageable) {
		try {

			return clienteRepository.findByDataInserimento(data, pageable);
		} catch (Exception e) {

			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}

	public Page<Cliente> findByDataUltimoContatto(LocalDate data, Pageable pageable) {
		try {

			return clienteRepository.findByDataUltimoContatto(data, pageable);
		} catch (Exception e) {

			e.printStackTrace();
			throw new Error(e.getMessage());
		}
	}
}
