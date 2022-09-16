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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.service.ClienteService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(path = "/cliente")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista  clienti")
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/cliente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisci cliente")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente save = clienteService.save(cliente);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/cliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina cliente")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return new ResponseEntity<>("Cliente cancellato!", HttpStatus.OK);

	}

	@PutMapping(path = "/cliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna cliente")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente save = clienteService.update(id, cliente);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@GetMapping(path = "/clientepartenome/{nome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca per parte del nome")
	public ResponseEntity<Page<Cliente>> findByParteNome(@PathVariable String nome, Pageable pageable) {
		Page<Cliente> find = clienteService.findByParteNome(nome, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/ordernomecontatto")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca clienti in ordine alfabetico")
	public ResponseEntity<Page<Cliente>> OrderByNomeContattoAsc(Pageable pageable) {
		Page<Cliente> find = clienteService.OrderByNomeContattoAsc(pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/orderfatturatoannuale")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca cliente per fatturato(minore) ")
	public ResponseEntity<Page<Cliente>> OrderByFatturatoAnnuale(Pageable pageable) {
		Page<Cliente> find = clienteService.OrderByFatturatoAnnuale(pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/orderdatainserimento")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca cliente per ordine di data inserimento ")
	public ResponseEntity<Page<Cliente>> OrderByDataInserimento(Pageable pageable) {
		Page<Cliente> find = clienteService.OrderByDataInserimento(pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/findclientefatturatoannuale/{fatturato}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Trova clienti in base al fatturato annuale")
	public ResponseEntity<Page<Cliente>> findByFatturatoAnnuale(@PathVariable BigDecimal fatturato, Pageable pageable) {
		Page<Cliente> find = clienteService.findByFatturatoAnnuale(fatturato, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/findclientedatainserimento/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Trova clienti in base alla data di inserimento")
	public ResponseEntity<Page<Cliente>> findByDataInserimento(@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate data,Pageable pageable){
		Page<Cliente> find = clienteService.findByDataInserimento(data, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(path = "/findclientedataultimocontatto/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Trova clienti in base alla data dell'ultimo contatto")
	public ResponseEntity<Page<Cliente>> findByDataUltimoContatto(@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate data,Pageable pageable){
		Page<Cliente> find = clienteService.findByDataUltimoContatto(data, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
}
