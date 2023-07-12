/**
 * 
 */
package com.karosanpu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.karosanpu.model.Vacante;
import com.karosanpu.service.ICategoriasService;
import com.karosanpu.service.IVacantesService;
import com.karosanpu.util.Utileria;

/**
 * @author ksanchezpu
 *
 */
@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	//inyectamos el valor de la propieda de la ruta donde guardar
	@Value("${empleosSpringBoot.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	/**
	 * Método para listar vacantes en nueva pagina index
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
    	model.addAttribute("listadoVacantes", lista);
		return "vacantes/listVacantes";
	}

	/**
	 * Método para cargar el formulario de registro de vacantes
	 * 
	 * @return
	 */
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "vacantes/formVacante";
	}

	/**
	 * UTILIZANDO Data Binding 
	 * Método para guardar lo ingresado en el formulario
	 * (por el momento solo retornara a una vista list vacantes)
	 * 
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, 
			@RequestParam("archivoImagen") MultipartFile multiPart) {

		if (result.hasErrors()) {
			
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}

			
			return "vacantes/formVacante";
			
		}
		
		//Manejo de archivos
		if (!multiPart.isEmpty()) {
			// String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "d:/tools/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}
		
		serviceVacantes.guardar(vacante);
		//para mensaje
		attributes.addFlashAttribute("msg", "Registro Guardado");
		System.out.println("Vacante: " + vacante);
		
		return "redirect:/vacantes/index";
	}

	/**
	 * UTILIZANDO RequestParam Método para guardar lo ingresado en el formulario
	 * (por el momento solo retornara a una vista list vacantes)
	 * 
	 * @return
	 */
	/**
	 * @PostMapping("/save") public String guardar(@RequestParam("nombre") String
	 * nombre, @RequestParam("descripcion") String
	 * descripcion, @RequestParam("estatus") String estatus, @RequestParam("fecha")
	 * String fecha, @RequestParam("destacado") int
	 * destacado, @RequestParam("salario") double salario, @RequestParam("detalles")
	 * String detalles) {
	 * 
	 * System.out.println("Nombre Vacante: " + nombre);
	 * System.out.println("Descripcion: " + descripcion);
	 * System.out.println("Estatus: " + estatus); System.out.println("Fecha
	 * Publicación: " + fecha); System.out.println("Destacado: " + destacado);
	 * System.out.println("Salario Ofrecido: " + salario);
	 * System.out.println("detalles: " + detalles);
	 * 
	 * return "vacantes/listVacantes"; }
	 */

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

	/**
	 * Método par dar el formato de la fecha para todos los tipos Date
	 * 
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
