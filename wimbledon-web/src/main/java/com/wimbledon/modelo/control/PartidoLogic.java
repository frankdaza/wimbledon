package com.wimbledon.modelo.control;

import com.wimbledon.dataaccess.dao.*;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.PartidoDTO;

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
@Service("PartidoLogic")
public class PartidoLogic implements IPartidoLogic {
    private static final Logger log = LoggerFactory.getLogger(PartidoLogic.class);

    /**
     * DAO injected by Spring that manages Partido entities
     *
     */
    @Autowired
    private IPartidoDAO partidoDAO;

    /**
    * DAO injected by Spring that manages Sett entities
    *
    */
    @Autowired
    private ISettDAO settDAO;

    /**
    * Logic injected by Spring that manages Arbitro entities
    *
    */
    @Autowired
    IArbitroLogic logicArbitro1;

    /**
    * Logic injected by Spring that manages Cancha entities
    *
    */
    @Autowired
    ICanchaLogic logicCancha2;

    /**
    * Logic injected by Spring that manages Directortorneo entities
    *
    */
    @Autowired
    IDirectortorneoLogic logicDirectortorneo3;

    /**
    * Logic injected by Spring that manages Jugador entities
    *
    */
    @Autowired
    IJugadorLogic logicJugador4;

    /**
    * Logic injected by Spring that manages Jugador entities
    *
    */
    @Autowired
    IJugadorLogic logicJugador5;

    /**
    * Logic injected by Spring that manages Tipopartido entities
    *
    */
    @Autowired
    ITipopartidoLogic logicTipopartido6;

    /**
    * Logic injected by Spring that manages Torneo entities
    *
    */
    @Autowired
    ITorneoLogic logicTorneo7;

    @Transactional(readOnly = true)
    public List<Partido> getPartido() throws Exception {
        log.debug("finding all Partido instances");

        List<Partido> list = new ArrayList<Partido>();

        try {
            list = partidoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Partido failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Partido");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePartido(Partido entity) throws Exception {
        log.debug("saving Partido instance");

        try {
//            if (entity.getArbitro() == null) {
//                throw new ZMessManager().new ForeignException("arbitro");
//            }
//
//            if (entity.getCancha() == null) {
//                throw new ZMessManager().new ForeignException("cancha");
//            }
//
//            if (entity.getDirectortorneo() == null) {
//                throw new ZMessManager().new ForeignException("directortorneo");
//            }
//
//            if (entity.getJugadorByJugaaId() == null) {
//                throw new ZMessManager().new ForeignException(
//                    "jugadorByJugaaId");
//            }
//
//            if (entity.getJugadorByJugabId() == null) {
//                throw new ZMessManager().new ForeignException(
//                    "jugadorByJugabId");
//            }
//
//            if (entity.getTipopartido() == null) {
//                throw new ZMessManager().new ForeignException("tipopartido");
//            }
//
//            if (entity.getTorneo() == null) {
//                throw new ZMessManager().new ForeignException("torneo");
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
//            if (entity.getHora() == null) {
//                throw new ZMessManager().new EmptyFieldException("hora");
//            }                     

            partidoDAO.save(entity);

            log.debug("save Partido successful");
        } catch (Exception e) {
            log.error("save Partido failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePartido(Partido entity) throws Exception {
        log.debug("deleting Partido instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Partido");
        }

        if (entity.getPartId() == null) {
            throw new ZMessManager().new EmptyFieldException("partId");
        }

        List<Sett> setts = null;

        try {
            setts = settDAO.findByProperty("partido.partId", entity.getPartId());

            if (Utilities.validationsList(setts) == true) {
                throw new ZMessManager().new DeletingException("setts");
            }

            partidoDAO.delete(entity);

            log.debug("delete Partido successful");
        } catch (Exception e) {
            log.error("delete Partido failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePartido(Partido entity) throws Exception {
        log.debug("updating Partido instance");

        try {
//            if (entity == null) {
//                throw new ZMessManager().new NullEntityExcepcion("Partido");
//            }
//
//            if (entity.getArbitro() == null) {
//                throw new ZMessManager().new ForeignException("arbitro");
//            }
//
//            if (entity.getCancha() == null) {
//                throw new ZMessManager().new ForeignException("cancha");
//            }
//
//            if (entity.getDirectortorneo() == null) {
//                throw new ZMessManager().new ForeignException("directortorneo");
//            }
//
//            if (entity.getJugadorByJugaaId() == null) {
//                throw new ZMessManager().new ForeignException(
//                    "jugadorByJugaaId");
//            }
//
//            if (entity.getJugadorByJugabId() == null) {
//                throw new ZMessManager().new ForeignException(
//                    "jugadorByJugabId");
//            }
//
//            if (entity.getTipopartido() == null) {
//                throw new ZMessManager().new ForeignException("tipopartido");
//            }
//
//            if (entity.getTorneo() == null) {
//                throw new ZMessManager().new ForeignException("torneo");
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
//            if (entity.getHora() == null) {
//                throw new ZMessManager().new EmptyFieldException("hora");
//            }            

           
            partidoDAO.update(entity);

            log.debug("update Partido successful");
        } catch (Exception e) {
            log.error("update Partido failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PartidoDTO> getDataPartido() throws Exception {
        try {
            List<Partido> partido = partidoDAO.findAll();

            List<PartidoDTO> partidoDTO = new ArrayList<PartidoDTO>();

            for (Partido partidoTmp : partido) {
                PartidoDTO partidoDTO2 = new PartidoDTO();

                partidoDTO2.setPartId(partidoTmp.getPartId());                
                partidoDTO2.setEstado((partidoTmp.getEstado() != null)
                    ? partidoTmp.getEstado() : null);
                partidoDTO2.setHora(partidoTmp.getHora());
                partidoDTO2.setTiempototal((partidoTmp.getTiempototal() != null)
                    ? partidoTmp.getTiempototal() : null);
                partidoDTO2.setArbiId_Arbitro((partidoTmp.getArbitro() != null)
                    ? partidoTmp.getArbitro().getArbiId() : null);
                partidoDTO2.setCancId_Cancha((partidoTmp.getCancha() != null)
                    ? partidoTmp.getCancha().getCancId() : null);
                partidoDTO2.setDitoId_Directortorneo((partidoTmp.getDirectortorneo() != null)
                    ? partidoTmp.getDirectortorneo().getDitoId() : null);                
                partidoDTO2.setTipaId_Tipopartido((partidoTmp.getTipopartido() != null)
                    ? partidoTmp.getTipopartido().getTipaId() : null);
                partidoDTO2.setTornId_Torneo((partidoTmp.getTorneo() != null)
                    ? partidoTmp.getTorneo().getTornId() : null);
                partidoDTO2.setJugaId_JugadorA((partidoTmp.getJugadorByJugaaId() != null)
                		? partidoTmp.getJugadorByJugaaId().getJugaId() : null);
                partidoDTO2.setJugaId_JugadorB((partidoTmp.getJugadorByJugabId() != null)
                		? partidoTmp.getJugadorByJugabId().getJugaId() : null);
                partidoDTO2.setNombreJugadorA((partidoTmp.getJugadorByJugaaId() != null)
                		? partidoTmp.getJugadorByJugaaId().getNombre() : null);                
                partidoDTO2.setNombreJugadorB((partidoTmp.getJugadorByJugabId() != null)
                		? partidoTmp.getJugadorByJugabId().getNombre() : null);
                partidoDTO.add(partidoDTO2);
            }

            return partidoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Partido getPartido(Integer partId) throws Exception {
        log.debug("getting Partido instance");

        Partido entity = null;

        try {
            entity = partidoDAO.findById(partId);
        } catch (Exception e) {
            log.error("get Partido failed", e);
            throw new ZMessManager().new FindingException("Partido");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Partido> findPagePartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Partido> entity = null;

        try {
            entity = partidoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Partido Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPartido() throws Exception {
        Long entity = null;

        try {
            entity = partidoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Partido Count");
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
    public List<Partido> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Partido> list = new ArrayList<Partido>();
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
            list = partidoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
