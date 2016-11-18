package com.wimbledon.modelo.control;

import com.wimbledon.dataaccess.dao.*;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.SettDTO;

import com.wimbledon.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SettLogic")
public class SettLogic implements ISettLogic {
    private static final Logger log = LoggerFactory.getLogger(SettLogic.class);

    /**
     * DAO injected by Spring that manages Sett entities
     *
     */
    @Autowired
    private ISettDAO settDAO;

    /**
    * Logic injected by Spring that manages Partido entities
    *
    */
    @Autowired
    IPartidoLogic logicPartido1;

    @Transactional(readOnly = true)
    public List<Sett> getSett() throws Exception {
        log.debug("finding all Sett instances");

        List<Sett> list = new ArrayList<Sett>();

        try {
            list = settDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Sett failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Sett");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSett(Sett entity) throws Exception {
        log.debug("saving Sett instance");

        try {
//            if (entity.getPartido() == null) {
//                throw new ZMessManager().new ForeignException("partido");
//            }
//
//            if (entity.getEstado() == null) {
//                throw new ZMessManager().new EmptyFieldException("estado");
//            }
//
//            if ((entity.getEstado() != null) &&
//                    (Utilities.checkWordAndCheckWithlength(entity.getEstado(), 1) == false)) {
//                throw new ZMessManager().new NotValidFormatException("estado");
//            }
//
//            if (entity.getGamejugador1() == null) {
//                throw new ZMessManager().new EmptyFieldException("gamejugador1");
//            }
//
//            if (entity.getGamejugador2() == null) {
//                throw new ZMessManager().new EmptyFieldException("gamejugador2");
//            }
//
//            if (entity.getSetId() == null) {
//                throw new ZMessManager().new EmptyFieldException("setId");
//            }
//
//            if (entity.getTiempo() == null) {
//                throw new ZMessManager().new EmptyFieldException("tiempo");
//            }
//
//            if (entity.getPartido().getPartId() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "partId_Partido");
//            }
//
//            if (getSett(entity.getSetId()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            settDAO.save(entity);

            log.debug("save Sett successful");
        } catch (Exception e) {
            log.error("save Sett failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSett(Sett entity) throws Exception {
        log.debug("deleting Sett instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Sett");
        }

        if (entity.getSetId() == null) {
            throw new ZMessManager().new EmptyFieldException("setId");
        }

        try {
            settDAO.delete(entity);

            log.debug("delete Sett successful");
        } catch (Exception e) {
            log.error("delete Sett failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSett(Sett entity) throws Exception {
        log.debug("updating Sett instance");

        try {
//            if (entity == null) {
//                throw new ZMessManager().new NullEntityExcepcion("Sett");
//            }
//
//            if (entity.getPartido() == null) {
//                throw new ZMessManager().new ForeignException("partido");
//            }
//
//            if (entity.getEstado() == null) {
//                throw new ZMessManager().new EmptyFieldException("estado");
//            }
//
//            if ((entity.getEstado() != null) &&
//                    (Utilities.checkWordAndCheckWithlength(entity.getEstado(), 1) == false)) {
//                throw new ZMessManager().new NotValidFormatException("estado");
//            }
//
//            if (entity.getGamejugador1() == null) {
//                throw new ZMessManager().new EmptyFieldException("gamejugador1");
//            }
//
//            if (entity.getGamejugador2() == null) {
//                throw new ZMessManager().new EmptyFieldException("gamejugador2");
//            }
//
//            if (entity.getSetId() == null) {
//                throw new ZMessManager().new EmptyFieldException("setId");
//            }
//
//            if (entity.getTiempo() == null) {
//                throw new ZMessManager().new EmptyFieldException("tiempo");
//            }
//
//            if (entity.getPartido().getPartId() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "partId_Partido");
//            }

            settDAO.update(entity);

            log.debug("update Sett successful");
        } catch (Exception e) {
            log.error("update Sett failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SettDTO> getDataSett() throws Exception {
        try {
            List<Sett> sett = settDAO.findAll();

            List<SettDTO> settDTO = new ArrayList<SettDTO>();

            for (Sett settTmp : sett) {
                SettDTO settDTO2 = new SettDTO();

                settDTO2.setSetId(settTmp.getSetId());
                settDTO2.setEstado((settTmp.getEstado() != null)
                    ? settTmp.getEstado() : null);
                settDTO2.setGamejugador1((settTmp.getGamejugador1() != null)
                    ? settTmp.getGamejugador1() : null);
                settDTO2.setGamejugador2((settTmp.getGamejugador2() != null)
                    ? settTmp.getGamejugador2() : null);
                settDTO2.setTiempo((settTmp.getTiempo() != null)
                    ? settTmp.getTiempo() : null);
                settDTO2.setPartId_Partido((settTmp.getPartido().getPartId() != null)
                    ? settTmp.getPartido().getPartId() : null);
                settDTO.add(settDTO2);
            }

            return settDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Sett getSett(Integer setId) throws Exception {
        log.debug("getting Sett instance");

        Sett entity = null;

        try {
            entity = settDAO.findById(setId);
        } catch (Exception e) {
            log.error("get Sett failed", e);
            throw new ZMessManager().new FindingException("Sett");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Sett> findPageSett(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Sett> entity = null;

        try {
            entity = settDAO.findPage(sortColumnName, sortAscending, startRow,
                    maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Sett Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSett() throws Exception {
        Long entity = null;

        try {
            entity = settDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Sett Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Sett> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Sett> list = new ArrayList<Sett>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = settDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
