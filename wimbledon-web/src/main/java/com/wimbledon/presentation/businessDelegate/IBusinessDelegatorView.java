package com.wimbledon.presentation.businessDelegate;

import com.wimbledon.modelo.Arbitro;
import com.wimbledon.modelo.Cancha;
import com.wimbledon.modelo.Directortorneo;
import com.wimbledon.modelo.Jugador;
import com.wimbledon.modelo.Partido;
import com.wimbledon.modelo.Sett;
import com.wimbledon.modelo.Tipopartido;
import com.wimbledon.modelo.Torneo;
import com.wimbledon.modelo.Usuarios;
import com.wimbledon.modelo.control.ArbitroLogic;
import com.wimbledon.modelo.control.CanchaLogic;
import com.wimbledon.modelo.control.DirectortorneoLogic;
import com.wimbledon.modelo.control.IArbitroLogic;
import com.wimbledon.modelo.control.ICanchaLogic;
import com.wimbledon.modelo.control.IDirectortorneoLogic;
import com.wimbledon.modelo.control.IJugadorLogic;
import com.wimbledon.modelo.control.IPartidoLogic;
import com.wimbledon.modelo.control.ISettLogic;
import com.wimbledon.modelo.control.ITipopartidoLogic;
import com.wimbledon.modelo.control.ITorneoLogic;
import com.wimbledon.modelo.control.IUsuariosLogic;
import com.wimbledon.modelo.control.JugadorLogic;
import com.wimbledon.modelo.control.PartidoLogic;
import com.wimbledon.modelo.control.SettLogic;
import com.wimbledon.modelo.control.TipopartidoLogic;
import com.wimbledon.modelo.control.TorneoLogic;
import com.wimbledon.modelo.control.UsuariosLogic;
import com.wimbledon.modelo.dto.ArbitroDTO;
import com.wimbledon.modelo.dto.CanchaDTO;
import com.wimbledon.modelo.dto.DirectortorneoDTO;
import com.wimbledon.modelo.dto.JugadorDTO;
import com.wimbledon.modelo.dto.PartidoDTO;
import com.wimbledon.modelo.dto.SettDTO;
import com.wimbledon.modelo.dto.TipopartidoDTO;
import com.wimbledon.modelo.dto.TorneoDTO;
import com.wimbledon.modelo.dto.UsuariosDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Arbitro> getArbitro() throws Exception;

    public void saveArbitro(Arbitro entity) throws Exception;

    public void deleteArbitro(Arbitro entity) throws Exception;

    public void updateArbitro(Arbitro entity) throws Exception;

    public Arbitro getArbitro(Integer arbiId) throws Exception;

    public List<Arbitro> findByCriteriaInArbitro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Arbitro> findPageArbitro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberArbitro() throws Exception;

    public List<ArbitroDTO> getDataArbitro() throws Exception;

    public List<Cancha> getCancha() throws Exception;

    public void saveCancha(Cancha entity) throws Exception;

    public void deleteCancha(Cancha entity) throws Exception;

    public void updateCancha(Cancha entity) throws Exception;

    public Cancha getCancha(Integer cancId) throws Exception;

    public List<Cancha> findByCriteriaInCancha(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cancha> findPageCancha(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCancha() throws Exception;

    public List<CanchaDTO> getDataCancha() throws Exception;

    public List<Directortorneo> getDirectortorneo() throws Exception;

    public void saveDirectortorneo(Directortorneo entity)
        throws Exception;

    public void deleteDirectortorneo(Directortorneo entity)
        throws Exception;

    public void updateDirectortorneo(Directortorneo entity)
        throws Exception;

    public Directortorneo getDirectortorneo(Integer ditoId)
        throws Exception;

    public List<Directortorneo> findByCriteriaInDirectortorneo(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<Directortorneo> findPageDirectortorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDirectortorneo() throws Exception;

    public List<DirectortorneoDTO> getDataDirectortorneo()
        throws Exception;

    public List<Jugador> getJugador() throws Exception;

    public void saveJugador(Jugador entity) throws Exception;

    public void deleteJugador(Jugador entity) throws Exception;

    public void updateJugador(Jugador entity) throws Exception;

    public Jugador getJugador(Integer jugaId) throws Exception;

    public List<Jugador> findByCriteriaInJugador(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Jugador> findPageJugador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberJugador() throws Exception;

    public List<JugadorDTO> getDataJugador() throws Exception;

    public List<Partido> getPartido() throws Exception;

    public void savePartido(Partido entity) throws Exception;

    public void deletePartido(Partido entity) throws Exception;

    public void updatePartido(Partido entity) throws Exception;

    public Partido getPartido(Integer partId) throws Exception;

    public List<Partido> findByCriteriaInPartido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Partido> findPagePartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPartido() throws Exception;

    public List<PartidoDTO> getDataPartido() throws Exception;

    public List<Sett> getSett() throws Exception;

    public void saveSett(Sett entity) throws Exception;

    public void deleteSett(Sett entity) throws Exception;

    public void updateSett(Sett entity) throws Exception;

    public Sett getSett(Integer setId) throws Exception;

    public List<Sett> findByCriteriaInSett(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Sett> findPageSett(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSett() throws Exception;

    public List<SettDTO> getDataSett() throws Exception;

    public List<Tipopartido> getTipopartido() throws Exception;

    public void saveTipopartido(Tipopartido entity) throws Exception;

    public void deleteTipopartido(Tipopartido entity) throws Exception;

    public void updateTipopartido(Tipopartido entity) throws Exception;

    public Tipopartido getTipopartido(Integer tipaId) throws Exception;

    public List<Tipopartido> findByCriteriaInTipopartido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tipopartido> findPageTipopartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipopartido() throws Exception;

    public List<TipopartidoDTO> getDataTipopartido() throws Exception;

    public List<Torneo> getTorneo() throws Exception;

    public void saveTorneo(Torneo entity) throws Exception;

    public void deleteTorneo(Torneo entity) throws Exception;

    public void updateTorneo(Torneo entity) throws Exception;

    public Torneo getTorneo(Integer tornId) throws Exception;

    public List<Torneo> findByCriteriaInTorneo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Torneo> findPageTorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTorneo() throws Exception;

    public List<TorneoDTO> getDataTorneo() throws Exception;

    public List<Usuarios> getUsuarios() throws Exception;

    public void saveUsuarios(Usuarios entity) throws Exception;

    public void deleteUsuarios(Usuarios entity) throws Exception;

    public void updateUsuarios(Usuarios entity) throws Exception;

    public Usuarios getUsuarios(Integer usuaId) throws Exception;

    public List<Usuarios> findByCriteriaInUsuarios(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuarios> findPageUsuarios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuarios() throws Exception;

    public List<UsuariosDTO> getDataUsuarios() throws Exception;
    public void realizar_sorteo_torneo() throws Exception;
}
