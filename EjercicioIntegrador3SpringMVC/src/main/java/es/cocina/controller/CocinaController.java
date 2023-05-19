package es.cocina.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CocinaController {
	
	@GetMapping("/")
	public String cocinaPrincipal() {
		return "cocina";
		
	}
	@GetMapping("/recetas")
	public String recetas() {
		return "recetas";
		
	}
	@GetMapping("/sobreNosotros")
	public String sobreNosotros() {
		return "sobreNosotros";
		
	}
	@GetMapping("/contacto")
	public String contacto() {
		return "contacto";
		
	}

}
