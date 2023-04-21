/**
 * 
 */
package com.karosanpu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karosanpu.model.Vacante;
import com.karosanpu.service.IVacantesService;

/**
 * @author ksanchezpu
 *
 */
@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	@Autowired
	private IVacantesService serviceVacantes;

	/**
	 * Método para cargar el formulario de registro de vacantes
	 * 
	 * @return
	 */
	@GetMapping("/create")
	public String crear() {
		return "vacantes/formVacante";
	}

	/**
	 * Método para guardar lo ingresado en el formulario (por el momento solo
	 * retornara a una vista list vacantes)
	 * 
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
			@RequestParam("estatus") String estatus, @RequestParam("fecha") String fecha,
			@RequestParam("destacado") int destacado, @RequestParam("salario") double salario,
			@RequestParam("detalles") String detalles) {
		
		System.out.println("Nombre Vacante: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Estatus: " + estatus);
		System.out.println("Fecha Publicación: " + fecha);
		System.out.println("Destacado: " + destacado);
		System.out.println("Salario Ofrecido: " + salario);
		System.out.println("detalles: " + detalles);
		
		return "vacantes/listVacantes";
	}

	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con id: " + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {

		Vacante vacante = serviceVacantes.buscarPorId(idVacante);

		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);

		// Buscar los detalles de la vacante en la BD.......

		return "detalle";
	}
}
