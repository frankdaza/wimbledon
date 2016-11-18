package com.wimbledon.modelo.control;

import com.wimbledon.dataaccess.dao.*;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.CanchaDTO;

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
@Service("CanchaLogic")
public class CanchaLogic implements ICanchaLogic {
    private static final Logger log = LoggerFactory.getLogger(CanchaLogic.class);

    /**
     * DAO injected by Spring that manages Cancha entities
     *
     */
    @Autowired
    private ICanchaDAO canchaDAO;

    /**
    * DAO injected by Spring that manages Partido entities
    *
    */
    @Autowired
    private IPartidoDAO partidoDAO;

    @Transactional(readOnly = true)
    public List<Cancha> getCancha() throws Exception {
        log.debug("finding all Cancha instances");

        List<Cancha> list = new ArrayList<Cancha>();

        try {
            list = canchaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Cancha failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Cancha");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveCancha(Cancha entity) throws Exception {
        log.debug("saving Cancha instance");

        try {
            if (entity.getCancId() == null) {
                throw new ZMessManager().new EmptyFieldException("cancId");
            }

            if (entity.getEstado() == null) {
                throw new ZMessManager().new EmptyFieldException("estado");
            }

            if (entity.getMantenimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "mantenimiento");
            }

            if ((entity.getMantenimiento() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getMantenimiento(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "mantenimiento");
            }

            if (entity.getNumero() == null) {
                throw new ZMessManager().new EmptyFieldException("numero");
            }

            if (getCancha(entity.getCancId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            canchaDAO.save(entity);

            log.debug("save Cancha successful");
        } catch (Exception e) {
            log.error("save Cancha failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteCancha(Cancha entity) throws Exception {
        log.debug("deleting Cancha instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Cancha");
        }

        if (entity.getCancId() == null) {
            throw new ZMessManager().new EmptyFieldException("cancId");
        }

        List<Partido> partidos = null;

        try {
            partidos = partidoDAO.findByProperty("cancha.cancId",
                    entity.getCancId());

            if (Utilities.validationsList(partidos) == true) {
                throw new ZMessManager().new DeletingException("partidos");
            }

            canchaDAO.delete(entity);

            log.debug("delete Cancha successful");
        } catch (Exception e) {
            log.error("delete Cancha failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateCancha(Cancha entity) throws Exception {
        log.debug("updating Cancha instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Cancha");
            }

            if (entity.getCancId() == null) {
                throw new ZMessManager().new EmptyFieldException("cancId");
            }

            if (entity.getEstado() == null) {
                throw new ZMessManager().new EmptyFieldException("estado");
            }

            if (entity.getMantenimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "mantenimiento");
            }

            if ((entity.getMantenimiento() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getMantenimiento(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "mantenimiento");
            }

            if (entity.getNumero() == null) {
                throw new ZMessManager().new EmptyFieldException("numero");
            }

            canchaDAO.update(entity);

            log.debug("update Cancha successful");
        } catch (Exception e) {
            log.error("update Cancha failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<CanchaDTO> getDataCancha() throws Exception {
        try {
            List<Cancha> cancha = canchaDAO.findAll();

            List<CanchaDTO> canchaDTO = new ArrayList<CanchaDTO>();

            for (Cancha canchaTmp : cancha) {
                CanchaDTO canchaDTO2 = new CanchaDTO();

                canchaDTO2.setCancId(canchaTmp.getCancId());
                canchaDTO2.setEstado((canchaTmp.getEstado() != null)
                    ? canchaTmp.getEstado() : null);
                canchaDTO2.setMantenimiento((canchaTmp.getMantenimiento() != null)
                    ? canchaTmp.getMantenimiento() : null);
                canchaDTO2.setNumero((canchaTmp.getNumero() != null)
                    ? canchaTmp.getNumero() : null);
                canchaDTO.add(canchaDTO2);
            }

            return canchaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Cancha getCancha(Integer cancId) throws Exception {
        log.debug("getting Cancha instance");

        Cancha entity = null;

        try {
            entity = canchaDAO.findById(cancId);
        } catch (Exception e) {
            log.error("get Cancha failed", e);
            throw new ZMessManager().new FindingException("Cancha");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Cancha> findPageCancha(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Cancha> entity = null;

        try {
            entity = canchaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Cancha Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberCancha() throws Exception {
        Long entity = null;

        try {
            entity = canchaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Cancha Count");
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
    public List<Cancha> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Cancha> list = new ArrayList<Cancha>();
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
            list = canchaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
