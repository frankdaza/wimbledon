package com.wimbledon.modelo.control;

import com.wimbledon.modelo.Sett;
import com.wimbledon.modelo.dto.SettDTO;

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
public interface ISettLogic {
    public List<Sett> getSett() throws Exception;

    /**
         * Save an new Sett entity
         */
    public void saveSett(Sett entity) throws Exception;

    /**
         * Delete an existing Sett entity
         *
         */
    public void deleteSett(Sett entity) throws Exception;

    /**
        * Update an existing Sett entity
        *
        */
    public void updateSett(Sett entity) throws Exception;

    /**
         * Load an existing Sett entity
         *
         */
    public Sett getSett(Integer setId) throws Exception;

    public List<Sett> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Sett> findPageSett(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSett() throws Exception;

    public List<SettDTO> getDataSett() throws Exception;
}
