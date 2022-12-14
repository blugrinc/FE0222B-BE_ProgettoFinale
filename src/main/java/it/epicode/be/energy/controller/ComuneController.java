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
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.service.ComuneService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name="bearerAuth")
public class ComuneController {
	
	@Autowired
	private ComuneService comuneService;

	@GetMapping(path = "/comune")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista  comuni")
	public ResponseEntity<Page<Comune>> findAll(Pageable pageable) {
		Page<Comune> findAll = comuneService.findAll(pageable);
		
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	
	}

	
	@PostMapping(path = "/comune")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisci comune")
	public ResponseEntity<Comune> save(@RequestBody Comune comune) {
		Comune save = comuneService.save(comune);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/comune/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina comune")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		comuneService.delete(id);
		return new ResponseEntity<>("Comune cancellato!", HttpStatus.OK);

	}
	
	@PutMapping(path = "/comune/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna comune")
	public ResponseEntity<Comune> update(@PathVariable Long id, @RequestBody Comune comune) {
		Comune save = comuneService.update(id, comune);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

}
