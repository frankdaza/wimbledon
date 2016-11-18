package com.wimbledon.modelo.control;

import com.wimbledon.modelo.Directortorneo;
import com.wimbledon.modelo.dto.DirectortorneoDTO;

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
public interface IDirectortorneoLogic {
    public List<Directortorneo> getDirectortorneo() throws Exception;

    /**
         * Save an new Directortorneo entity
         */
    public void saveDirectortorneo(Directortorneo entity)
        throws Exception;

    /**
         * Delete an existing Directortorneo entity
         *
         */
    public void deleteDirectortorneo(Directortorneo entity)
        throws Exception;

    /**
        * Update an existing Directortorneo entity
        *
        */
    public void updateDirectortorneo(Directortorneo entity)
        throws Exception;

    /**
         * Load an existing Directortorneo entity
         *
         */
    public Directortorneo getDirectortorneo(Integer ditoId)
        throws Exception;

    public List<Directortorneo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Directortorneo> findPageDirectortorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDirectortorneo() throws Exception;

    public List<DirectortorneoDTO> getDataDirectortorneo()
        throws Exception;
}
