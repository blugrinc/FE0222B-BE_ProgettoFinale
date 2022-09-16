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

import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/province")
public class ProvinceControllerWeb {

	@Autowired
	ProvinciaService provinciaService;

	

	@GetMapping("/mostraelenco")
	public ModelAndView mostraElencoProvince() {
		log.info("Test elenco province su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencoprovince");
		view.addObject("listaProvince", provinciaService.findAll());
	
		return view;
	}

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Provincia provincia, Model model) {
		log.info("Form aggiungi provincia");
		return "formProvincia";
	}

	@PostMapping("/addProvincia")
	public String aggiungiProvincia(@Valid Provincia provincia, BindingResult result, Model model) {
		log.info("Aggiungi provincia");
		if (result.hasErrors()) {
			return "formProvincia";
		}
		provinciaService.save(provincia);

		return "redirect:/provincia/mostraelenco";
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
		log.info("Test mostra form aggiorna provincia");
		Optional<Provincia> provinciaAggiorna = provinciaService.findById(id);
		if (provinciaAggiorna.isPresent()) {
			ModelAndView view = new ModelAndView("editProvincia");
			view.addObject("provincia", provinciaAggiorna.get());
			
			return view;
		}

		return new ModelAndView("error").addObject("message", "id non trovato");
	}

	@PostMapping("/aggiornaprovincia/{id}")
	public String aggiornaProvincia(@PathVariable Long id, Provincia provincia, BindingResult result, Model model) {
		provinciaService.update(id, provincia);

		log.info("Provincia aggiorna");
		return "redirect:/province/mostraelenco";

	}

	@GetMapping("/eliminaprovince/{id}")
	public ModelAndView eliminaProvincia(@PathVariable Long id, Model model,Pageable pageable) {
		Optional<Provincia> provinciaDelete = provinciaService.findById(id);
		if (provinciaDelete.isPresent()) {
			provinciaService.delete(provinciaDelete.get().getId());
			ModelAndView view = new ModelAndView("elencoprovince");
			view.addObject("listaProvince", provinciaService.findAll(pageable));
			return view;

		} else {
			return new ModelAndView("error").addObject("message", "id non trovato");
		}

	}
}

