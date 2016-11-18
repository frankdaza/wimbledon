package com.wimbledon.modelo.control;

import com.wimbledon.modelo.Arbitro;
import com.wimbledon.modelo.dto.ArbitroDTO;

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
public interface IArbitroLogic {
    public List<Arbitro> getArbitro() throws Exception;

    /**
         * Save an new Arbitro entity
         */
    public void saveArbitro(Arbitro entity) throws Exception;

    /**
         * Delete an existing Arbitro entity
         *
         */
    public void deleteArbitro(Arbitro entity) throws Exception;

    /**
        * Update an existing Arbitro entity
        *
        */
    public void updateArbitro(Arbitro entity) throws Exception;

    /**
         * Load an existing Arbitro entity
         *
         */
    public Arbitro getArbitro(Integer arbiId) throws Exception;

    public List<Arbitro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Arbitro> findPageArbitro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberArbitro() throws Exception;

    public List<ArbitroDTO> getDataArbitro() throws Exception;
}
