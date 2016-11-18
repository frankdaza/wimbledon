package com.wimbledon.modelo.control;

import com.wimbledon.dataaccess.dao.*;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.ArbitroDTO;

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
@Service("ArbitroLogic")
public class ArbitroLogic implements IArbitroLogic {
    private static final Logger log = LoggerFactory.getLogger(ArbitroLogic.class);

    /**
     * DAO injected by Spring that manages Arbitro entities
     *
     */
    @Autowired
    private IArbitroDAO arbitroDAO;

    /**
    * DAO injected by Spring that manages Partido entities
    *
    */
    @Autowired
    private IPartidoDAO partidoDAO;

    /**
    * DAO injected by Spring that manages Usuarios entities
    *
    */
    @Autowired
    private IUsuariosDAO usuariosDAO;

    @Transactional(readOnly = true)
    public List<Arbitro> getArbitro() throws Exception {
        log.debug("finding all Arbitro instances");

        List<Arbitro> list = new ArrayList<Arbitro>();

        try {
            list = arbitroDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Arbitro failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Arbitro");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveArbitro(Arbitro entity) throws Exception {
        log.debug("saving Arbitro instance");

        try {
//            if (entity.getArbiId() == null) {
//                throw new ZMessManager().new EmptyFieldException("arbiId");
//            }


            if (entity.getEstado() == null) {
                throw new ZMessManager().new EmptyFieldException("estado");
            }

            if ((entity.getEstado() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEstado(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("estado");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getNumeroIdentificacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "numeroIdentificacion");
            }

            if ((entity.getNumeroIdentificacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNumeroIdentificacion(), 10) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "numeroIdentificacion");
            }

//            if (getArbitro(entity.getArbiId()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            arbitroDAO.save(entity);

            log.debug("save Arbitro successful");
        } catch (Exception e) {
            log.error("save Arbitro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteArbitro(Arbitro entity) throws Exception {
        log.debug("deleting Arbitro instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Arbitro");
        }

        if (entity.getArbiId() == null) {
            throw new ZMessManager().new EmptyFieldException("arbiId");
        }

        List<Partido> partidos = null;
        List<Usuarios> usuarioses = null;

        try {
            partidos = partidoDAO.findByProperty("arbitro.arbiId",
                    entity.getArbiId());

            if (Utilities.validationsList(partidos) == true) {
                throw new ZMessManager().new DeletingException("partidos");
            }

            usuarioses = usuariosDAO.findByProperty("arbitro.arbiId",
                    entity.getArbiId());

            if (Utilities.validationsList(usuarioses) == true) {
                throw new ZMessManager().new DeletingException("usuarioses");
            }

            arbitroDAO.delete(entity);

            log.debug("delete Arbitro successful");
        } catch (Exception e) {
            log.error("delete Arbitro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateArbitro(Arbitro entity) throws Exception {
        log.debug("updating Arbitro instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Arbitro");
            }

            if (entity.getArbiId() == null) {
                throw new ZMessManager().new EmptyFieldException("arbiId");
            }


            if (entity.getEstado() == null) {
                throw new ZMessManager().new EmptyFieldException("estado");
            }

            if ((entity.getEstado() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEstado(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("estado");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getNumeroIdentificacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "numeroIdentificacion");
            }

            if ((entity.getNumeroIdentificacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNumeroIdentificacion(), 10) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "numeroIdentificacion");
            }

            arbitroDAO.update(entity);

            log.debug("update Arbitro successful");
        } catch (Exception e) {
            log.error("update Arbitro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<ArbitroDTO> getDataArbitro() throws Exception {
        try {
            List<Arbitro> arbitro = arbitroDAO.findAll();

            List<ArbitroDTO> arbitroDTO = new ArrayList<ArbitroDTO>();

            for (Arbitro arbitroTmp : arbitro) {
                ArbitroDTO arbitroDTO2 = new ArbitroDTO();

                arbitroDTO2.setArbiId(arbitroTmp.getArbiId());
                arbitroDTO2.setEstado((arbitroTmp.getEstado() != null)
                    ? arbitroTmp.getEstado() : null);
                arbitroDTO2.setNombre((arbitroTmp.getNombre() != null)
                    ? arbitroTmp.getNombre() : null);
                arbitroDTO2.setNumeroIdentificacion((arbitroTmp.getNumeroIdentificacion() != null)
                    ? arbitroTmp.getNumeroIdentificacion() : null);
                arbitroDTO.add(arbitroDTO2);
            }

            return arbitroDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Arbitro getArbitro(Integer arbiId) throws Exception {
        log.debug("getting Arbitro instance");

        Arbitro entity = null;

        try {
            entity = arbitroDAO.findById(arbiId);
        } catch (Exception e) {
            log.error("get Arbitro failed", e);
            throw new ZMessManager().new FindingException("Arbitro");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Arbitro> findPageArbitro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Arbitro> entity = null;

        try {
            entity = arbitroDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Arbitro Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberArbitro() throws Exception {
        Long entity = null;

        try {
            entity = arbitroDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Arbitro Count");
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
    public List<Arbitro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Arbitro> list = new ArrayList<Arbitro>();
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
            list = arbitroDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
