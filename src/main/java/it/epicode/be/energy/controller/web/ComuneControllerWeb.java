package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.energy.service.ComuneService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/comuni")
public class ComuneControllerWeb {
	
	@Autowired
	ComuneService comuneService;

	@GetMapping("/mostraelenco")
	public ModelAndView mostraElencoProvince() {
		log.info("Test elenco comune su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencocomuni");
		view.addObject("listaComuni", comuneService.findAll());
	
		return view;
	}
}
