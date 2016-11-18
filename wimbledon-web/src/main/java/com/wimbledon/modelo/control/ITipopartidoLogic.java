package com.wimbledon.modelo.control;

import com.wimbledon.modelo.Tipopartido;
import com.wimbledon.modelo.dto.TipopartidoDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ITipopartidoLogic {
    public List<Tipopartido> getTipopartido() throws Exception;

    /**
         * Save an new Tipopartido entity
         */
    public void saveTipopartido(Tipopartido entity) throws Exception;

    /**
         * Delete an existing Tipopartido entity
         *
         */
    public void deleteTipopartido(Tipopartido entity) throws Exception;

    /**
        * Update an existing Tipopartido entity
        *
        */
    public void updateTipopartido(Tipopartido entity) throws Exception;

    /**
         * Load an existing Tipopartido entity
         *
         */
    public Tipopartido getTipopartido(Integer tipaId) throws Exception;

    public List<Tipopartido> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tipopartido> findPageTipopartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipopartido() throws Exception;

    public List<TipopartidoDTO> getDataTipopartido() throws Exception;
}
