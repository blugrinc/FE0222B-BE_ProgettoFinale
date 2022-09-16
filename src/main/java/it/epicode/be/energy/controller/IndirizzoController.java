package it.epicode.be.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.service.IndirizzoService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class IndirizzoController {

	@Autowired
	private IndirizzoService indirizzoService;

	
	@GetMapping(path = "/indirizzo")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista  indirizzi")
	public ResponseEntity<Page<Indirizzo>> findAll(Pageable pageable) {
		Page<Indirizzo> findAll = indirizzoService.findAll(pageable);
		
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	
	}
	
	@PostMapping(path = "/indirizzo")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisce indirizzo")
	public ResponseEntity<Indirizzo> save(@RequestBody Indirizzo indirizzo) {
		Indirizzo save = indirizzoService.save(indirizzo);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/indirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina indirizzo per ID")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		indirizzoService.delete(id);
		return new ResponseEntity<>("Indirizzo cancellato", HttpStatus.OK);

	}

	@PutMapping(path = "/indirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna indirizzo")
	public ResponseEntity<Indirizzo> update(@PathVariable Long id, @RequestBody Indirizzo indirizzo) {
		Indirizzo update = indirizzoService.update(id, indirizzo);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}
}
