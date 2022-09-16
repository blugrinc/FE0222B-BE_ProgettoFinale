package it.epicode.be.energy.controller.web;

import java.util.Optional;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.service.ClienteService;
import it.epicode.be.energy.service.ComuneService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/clienti")
public class ClienteControllerWeb {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ComuneService comuneService;

	

	@GetMapping("/mostraelenco")
	public ModelAndView mostraElencoClienti(Pageable pageable) {
		log.info("Test elenco clienti su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencoclienti");
		view.addObject("listaClienti", clienteService.findAll(pageable));
	
		return view;
	}

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Cliente cliente, Model model) {
		log.info("Form aggiungi cliente");
		model.addAttribute("listaComuni", comuneService.findAll());
		return "formCliente";
	}

	@PostMapping("/addCliente")
	public String aggiungiCliente(@Valid Cliente cliente, BindingResult result, Model model) {
		log.info("Aggiungi cliente");
		if (result.hasErrors()) {
			model.addAttribute("listaComuni", comuneService.findAll());
			return "formCliente";
		}
		clienteService.save(cliente);

		return "redirect:/clienti/mostraelenco";
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
		log.info("Test mostra form aggiorna cliente");
		Optional<Cliente> clienteAggiorna = clienteService.findById(id);
		if (clienteAggiorna.isPresent()) {
			ModelAndView view = new ModelAndView("editCliente");
			view.addObject("cliente", clienteAggiorna.get());
			
			return view;
		}

		return new ModelAndView("error").addObject("message", "id non trovato");
	}

	@PostMapping("/aggiornacliente/{id}")
	public String aggiornaCliente(@PathVariable Long id, Cliente cliente, BindingResult result, Model model) {
		clienteService.update(id, cliente);

		log.info("Cliente aggiorna");
		return "redirect:/clienti/mostraelenco";

	}

	@GetMapping("/eliminacliente/{id}")
	public ModelAndView eliminaCliente(@PathVariable Long id, Model model,Pageable pageable) {
		Optional<Cliente> clienteDelete = clienteService.findById(id);
		if (clienteDelete.isPresent()) {
			clienteService.delete(clienteDelete.get().getId());
			ModelAndView view = new ModelAndView("elencoclienti");
			view.addObject("listaClienti", clienteService.findAll(pageable));
			return view;

		} else {
			return new ModelAndView("error").addObject("message", "id non trovato");
		}

	}

}

