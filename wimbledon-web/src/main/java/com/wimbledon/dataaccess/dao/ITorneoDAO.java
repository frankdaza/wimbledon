package com.wimbledon.dataaccess.dao;

import java.util.List;

import com.wimbledon.dataaccess.api.Dao;

import com.wimbledon.modelo.Torneo;
import com.wimbledon.modelo.dto.TorneoDTO;


/**
* Interface for   TorneoDAO.
*
*/
public interface ITorneoDAO extends Dao<Torneo, Integer> {
	public List<TorneoDTO> consultarResultadosTorneos(Integer tornId) throws Exception;
}
