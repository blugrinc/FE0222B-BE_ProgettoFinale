package it.epicode.be.energy.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.service.FatturaService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class FatturaController {

	@Autowired
	private FatturaService fatturaService;

	@GetMapping(path = "/fattura")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista  fatture")
	public ResponseEntity<Page<Fattura>> findAll(Pageable pageable) {
		Page<Fattura> findAll = fatturaService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/fattura")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisce fattura")
	public ResponseEntity<Fattura> save(@RequestBody Fattura fattura) {
		Fattura save = fatturaService.save(fattura);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina fattura per ID")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		fatturaService.delete(id);
		return new ResponseEntity<>("Fattura cancellata!", HttpStatus.OK);

	}

	@PutMapping(path = "/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna fattura")
	public ResponseEntity<Fattura> update(@PathVariable Long id, @RequestBody Fattura fattura) {
		Fattura update = fatturaService.update(id, fattura);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

	@GetMapping(path = "/fatturaidcliente/{idCliente}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca fattura per id cliente")
	public ResponseEntity<Page<Fattura>> findFatturaByIdCliente(@PathVariable Long idCliente, Pageable pageable) {
		Page<Fattura> findByIdCliente = fatturaService.findFatturaByIdCliente(idCliente, pageable);

		if (findByIdCliente.hasContent()) {
			return new ResponseEntity<>(findByIdCliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/fatturastato/{stato}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca per stato fattura")
	public ResponseEntity<Page<Fattura>> findByStato(@PathVariable String stato, Pageable pageable) {
		Page<Fattura> findByStato = fatturaService.findByStato(stato, pageable);

		if (findByStato.hasContent()) {
			return new ResponseEntity<>(findByStato, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/fatturadata/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca per data fattura")
	public ResponseEntity<Page<Fattura>> findByData(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Pageable pageable) {
		Page<Fattura> findByData = fatturaService.findByData(data, pageable);

		if (findByData.hasContent()) {
			return new ResponseEntity<>(findByData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/fatturarange/{minimo}/{massimo}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca per data fattura")
	public ResponseEntity<Page<Fattura>> findByRangeImporto(@PathVariable BigDecimal minimo,
			@PathVariable BigDecimal massimo, Pageable pageable) {
		Page<Fattura> findByRangeImporto = fatturaService.findByRangeImporto(minimo, massimo, pageable);

		if (findByRangeImporto.hasContent()) {
			return new ResponseEntity<>(findByRangeImporto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
}

