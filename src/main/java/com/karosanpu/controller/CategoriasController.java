/**
 * 
 */
package com.karosanpu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.karosanpu.model.Categoria;
import com.karosanpu.model.Vacante;
import com.karosanpu.service.ICategoriasService;

/**
 * @author ksanchezpu
 *
 */
@Controller
@RequestMapping(value = "/categorias")
public class CategoriasController {

	// Consumo de servicios
	@Autowired
	private ICategoriasService serviceCategorias;


	/**
	 * Método para listar vategorias en nueva pagina index
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = serviceCategorias.buscarTodas();
		model.addAttribute("listadoCategorias", lista);
		return "categorias/listCategorias";
	}

	// @GetMapping("/create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear() {
		return "categorias/formCategoria";
	}

	// @PostMapping("/save")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion) {
		System.out.println("Categoria:" + nombre);
		System.out.println("Descripcion: " + descripcion);

		return "categorias/listCategorias";
	}

}
