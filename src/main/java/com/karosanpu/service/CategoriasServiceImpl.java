/**
 * 
 */
package com.karosanpu.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.karosanpu.model.Categoria;

/**
 * @author ksanchezpu
 *
 */
@Service
public class CategoriasServiceImpl implements ICategoriasService {

	/**
	 * Variable global para listado de categorias
	 */
	private List<Categoria> listaCategorias = null;

	/**
	 * Llenado de datos para lista de categorias
	 */
	public CategoriasServiceImpl() {

		listaCategorias = new LinkedList<Categoria>();

		// Creamos la categoria 1.
		Categoria categoria1 = new Categoria();
		categoria1.setId(1);
		categoria1.setNombre("Ejemplo categoria 1");
		categoria1.setDescripcion("Solicitamos Ing. Civil para diseñar categoria1.");

		// Creamos la categoria 2.
		Categoria categoria2 = new Categoria();
		categoria2.setId(2);
		categoria2.setNombre("Ejemplo categoria 2");
		categoria2.setDescripcion("Solicitamos Ing. Civil para diseña.");

		// Creamos la categoria 3.
		Categoria categoria3 = new Categoria();
		categoria3.setId(3);
		categoria3.setNombre("Ejemplo categoria 3");
		categoria3.setDescripcion("Solicitamos Ing. puente peatonal.");

		// Creamos la categoria 4.
		Categoria categoria4 = new Categoria();
		categoria4.setId(4);
		categoria4.setNombre("Ejemplo categoria 4");
		categoria4.setDescripcion("Civil para diseñar puente peatonal.");

		// Creamos la categoria 5.
		Categoria categoria5 = new Categoria();
		categoria4.setId(5);
		categoria4.setNombre("Educacion");
		categoria4.setDescripcion("Maestros, tutores, etc.");

		/**
		 * Agregamos los 4 objetos de tipo Vacante a la lista ...
		 */
		listaCategorias.add(categoria1);
		listaCategorias.add(categoria2);
		listaCategorias.add(categoria3);
		listaCategorias.add(categoria4);
		listaCategorias.add(categoria5);

	}

	@Override
	public void guardar(Categoria categoria) {
		listaCategorias.add(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		return listaCategorias;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria v : listaCategorias) {

			if (v.getId() == idCategoria) {
				return v;
			}

		}

		return null;
	}

}
