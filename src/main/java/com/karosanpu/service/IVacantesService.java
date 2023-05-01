/**
 * 
 */
package com.karosanpu.service;

import java.util.List;

import com.karosanpu.model.Vacante;

/**
 * @author KaRoT
 *
 */
public interface IVacantesService {
	
	List<Vacante> buscarTodas();

	Vacante buscarPorId(Integer idVacante);
	
	/**
	 * Método para guardar datos de la vacantes
	 * 
	 * @param vacante
	 */
	void guardar(Vacante vacante);
}
