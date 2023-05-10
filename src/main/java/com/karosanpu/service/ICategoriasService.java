/**
 * 
 */
package com.karosanpu.service;

import java.util.List;

import com.karosanpu.model.Categoria;

/**
 * @author ksanchezpu
 *
 */
public interface ICategoriasService {

	/**
	 * Método para guardar categorias
	 * 
	 * @param categoria
	 */
	void guardar(Categoria categoria);

	/**
	 * Método para obtener todas la categorias
	 * 
	 * @return
	 */
	List<Categoria> buscarTodas();

	/**
	 * Método para buscar id una categoria
	 * 
	 * @param idCategoria
	 * @return
	 */
	Categoria buscarPorId(Integer idCategoria);

}
