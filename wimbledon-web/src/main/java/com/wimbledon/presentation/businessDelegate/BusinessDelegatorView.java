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

import com.wimbledon.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IArbitroLogic arbitroLogic;
    @Autowired
    private ICanchaLogic canchaLogic;
    @Autowired
    private IDirectortorneoLogic directortorneoLogic;
    @Autowired
    private IJugadorLogic jugadorLogic;
    @Autowired
    private IPartidoLogic partidoLogic;
    @Autowired
    private ISettLogic settLogic;
    @Autowired
    private ITipopartidoLogic tipopartidoLogic;
    @Autowired
    private ITorneoLogic torneoLogic;
    @Autowired
    private IUsuariosLogic usuariosLogic;

    public List<Arbitro> getArbitro() throws Exception {
        return arbitroLogic.getArbitro();
    }

    public void saveArbitro(Arbitro entity) throws Exception {
        arbitroLogic.saveArbitro(entity);
    }

    public void deleteArbitro(Arbitro entity) throws Exception {
        arbitroLogic.deleteArbitro(entity);
    }

    public void updateArbitro(Arbitro entity) throws Exception {
        arbitroLogic.updateArbitro(entity);
    }

    public Arbitro getArbitro(Integer arbiId) throws Exception {
        Arbitro arbitro = null;

        try {
            arbitro = arbitroLogic.getArbitro(arbiId);
        } catch (Exception e) {
            throw e;
        }

        return arbitro;
    }

    public List<Arbitro> findByCriteriaInArbitro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return arbitroLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Arbitro> findPageArbitro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return arbitroLogic.findPageArbitro(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberArbitro() throws Exception {
        return arbitroLogic.findTotalNumberArbitro();
    }

    public List<ArbitroDTO> getDataArbitro() throws Exception {
        return arbitroLogic.getDataArbitro();
    }

    public List<Cancha> getCancha() throws Exception {
        return canchaLogic.getCancha();
    }

    public void saveCancha(Cancha entity) throws Exception {
        canchaLogic.saveCancha(entity);
    }

    public void deleteCancha(Cancha entity) throws Exception {
        canchaLogic.deleteCancha(entity);
    }

    public void updateCancha(Cancha entity) throws Exception {
        canchaLogic.updateCancha(entity);
    }

    public Cancha getCancha(Integer cancId) throws Exception {
        Cancha cancha = null;

        try {
            cancha = canchaLogic.getCancha(cancId);
        } catch (Exception e) {
            throw e;
        }

        return cancha;
    }

    public List<Cancha> findByCriteriaInCancha(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return canchaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Cancha> findPageCancha(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return canchaLogic.findPageCancha(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberCancha() throws Exception {
        return canchaLogic.findTotalNumberCancha();
    }

    public List<CanchaDTO> getDataCancha() throws Exception {
        return canchaLogic.getDataCancha();
    }

    public List<Directortorneo> getDirectortorneo() throws Exception {
        return directortorneoLogic.getDirectortorneo();
    }

    public void saveDirectortorneo(Directortorneo entity)
        throws Exception {
        directortorneoLogic.saveDirectortorneo(entity);
    }

    public void deleteDirectortorneo(Directortorneo entity)
        throws Exception {
        directortorneoLogic.deleteDirectortorneo(entity);
    }

    public void updateDirectortorneo(Directortorneo entity)
        throws Exception {
        directortorneoLogic.updateDirectortorneo(entity);
    }

    public Directortorneo getDirectortorneo(Integer ditoId)
        throws Exception {
        Directortorneo directortorneo = null;

        try {
            directortorneo = directortorneoLogic.getDirectortorneo(ditoId);
        } catch (Exception e) {
            throw e;
        }

        return directortorneo;
    }

    public List<Directortorneo> findByCriteriaInDirectortorneo(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return directortorneoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Directortorneo> findPageDirectortorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return directortorneoLogic.findPageDirectortorneo(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberDirectortorneo() throws Exception {
        return directortorneoLogic.findTotalNumberDirectortorneo();
    }

    public List<DirectortorneoDTO> getDataDirectortorneo()
        throws Exception {
        return directortorneoLogic.getDataDirectortorneo();
    }

    public List<Jugador> getJugador() throws Exception {
        return jugadorLogic.getJugador();
    }

    public void saveJugador(Jugador entity) throws Exception {
        jugadorLogic.saveJugador(entity);
    }

    public void deleteJugador(Jugador entity) throws Exception {
        jugadorLogic.deleteJugador(entity);
    }

    public void updateJugador(Jugador entity) throws Exception {
        jugadorLogic.updateJugador(entity);
    }

    public Jugador getJugador(Integer jugaId) throws Exception {
        Jugador jugador = null;

        try {
            jugador = jugadorLogic.getJugador(jugaId);
        } catch (Exception e) {
            throw e;
        }

        return jugador;
    }

    public List<Jugador> findByCriteriaInJugador(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return jugadorLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Jugador> findPageJugador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return jugadorLogic.findPageJugador(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberJugador() throws Exception {
        return jugadorLogic.findTotalNumberJugador();
    }

    public List<JugadorDTO> getDataJugador() throws Exception {
        return jugadorLogic.getDataJugador();
    }

    public List<Partido> getPartido() throws Exception {
        return partidoLogic.getPartido();
    }

    public void savePartido(Partido entity) throws Exception {
        partidoLogic.savePartido(entity);
    }

    public void deletePartido(Partido entity) throws Exception {
        partidoLogic.deletePartido(entity);
    }

    public void updatePartido(Partido entity) throws Exception {
        partidoLogic.updatePartido(entity);
    }

    public Partido getPartido(Integer partId) throws Exception {
        Partido partido = null;

        try {
            partido = partidoLogic.getPartido(partId);
        } catch (Exception e) {
            throw e;
        }

        return partido;
    }

    public List<Partido> findByCriteriaInPartido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return partidoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Partido> findPagePartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return partidoLogic.findPagePartido(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPartido() throws Exception {
        return partidoLogic.findTotalNumberPartido();
    }

    public List<PartidoDTO> getDataPartido() throws Exception {
        return partidoLogic.getDataPartido();
    }

    public List<Sett> getSett() throws Exception {
        return settLogic.getSett();
    }

    public void saveSett(Sett entity) throws Exception {
        settLogic.saveSett(entity);
    }

    public void deleteSett(Sett entity) throws Exception {
        settLogic.deleteSett(entity);
    }

    public void updateSett(Sett entity) throws Exception {
        settLogic.updateSett(entity);
    }

    public Sett getSett(Integer setId) throws Exception {
        Sett sett = null;

        try {
            sett = settLogic.getSett(setId);
        } catch (Exception e) {
            throw e;
        }

        return sett;
    }

    public List<Sett> findByCriteriaInSett(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return settLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Sett> findPageSett(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return settLogic.findPageSett(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberSett() throws Exception {
        return settLogic.findTotalNumberSett();
    }

    public List<SettDTO> getDataSett() throws Exception {
        return settLogic.getDataSett();
    }

    public List<Tipopartido> getTipopartido() throws Exception {
        return tipopartidoLogic.getTipopartido();
    }

    public void saveTipopartido(Tipopartido entity) throws Exception {
        tipopartidoLogic.saveTipopartido(entity);
    }

    public void deleteTipopartido(Tipopartido entity) throws Exception {
        tipopartidoLogic.deleteTipopartido(entity);
    }

    public void updateTipopartido(Tipopartido entity) throws Exception {
        tipopartidoLogic.updateTipopartido(entity);
    }

    public Tipopartido getTipopartido(Integer tipaId) throws Exception {
        Tipopartido tipopartido = null;

        try {
            tipopartido = tipopartidoLogic.getTipopartido(tipaId);
        } catch (Exception e) {
            throw e;
        }

        return tipopartido;
    }

    public List<Tipopartido> findByCriteriaInTipopartido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return tipopartidoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Tipopartido> findPageTipopartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipopartidoLogic.findPageTipopartido(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipopartido() throws Exception {
        return tipopartidoLogic.findTotalNumberTipopartido();
    }

    public List<TipopartidoDTO> getDataTipopartido() throws Exception {
        return tipopartidoLogic.getDataTipopartido();
    }

    public List<Torneo> getTorneo() throws Exception {
        return torneoLogic.getTorneo();
    }

    public void saveTorneo(Torneo entity) throws Exception {
        torneoLogic.saveTorneo(entity);
    }

    public void deleteTorneo(Torneo entity) throws Exception {
        torneoLogic.deleteTorneo(entity);
    }

    public void updateTorneo(Torneo entity) throws Exception {
        torneoLogic.updateTorneo(entity);
    }

    public Torneo getTorneo(Integer tornId) throws Exception {
        Torneo torneo = null;

        try {
            torneo = torneoLogic.getTorneo(tornId);
        } catch (Exception e) {
            throw e;
        }

        return torneo;
    }

    public List<Torneo> findByCriteriaInTorneo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return torneoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Torneo> findPageTorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return torneoLogic.findPageTorneo(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberTorneo() throws Exception {
        return torneoLogic.findTotalNumberTorneo();
    }

    public List<TorneoDTO> getDataTorneo() throws Exception {
        return torneoLogic.getDataTorneo();
    }

    public List<Usuarios> getUsuarios() throws Exception {
        return usuariosLogic.getUsuarios();
    }

    public void saveUsuarios(Usuarios entity) throws Exception {
        usuariosLogic.saveUsuarios(entity);
    }

    public void deleteUsuarios(Usuarios entity) throws Exception {
        usuariosLogic.deleteUsuarios(entity);
    }

    public void updateUsuarios(Usuarios entity) throws Exception {
        usuariosLogic.updateUsuarios(entity);
    }

    public Usuarios getUsuarios(Integer usuaId) throws Exception {
        Usuarios usuarios = null;

        try {
            usuarios = usuariosLogic.getUsuarios(usuaId);
        } catch (Exception e) {
            throw e;
        }

        return usuarios;
    }

    public List<Usuarios> findByCriteriaInUsuarios(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuariosLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuarios> findPageUsuarios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuariosLogic.findPageUsuarios(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuarios() throws Exception {
        return usuariosLogic.findTotalNumberUsuarios();
    }

    public List<UsuariosDTO> getDataUsuarios() throws Exception {
        return usuariosLogic.getDataUsuarios();
    }
    
    @Override
    public void realizar_sorteo_torneo() throws Exception {
    	torneoLogic.realizar_sorteo_torneo();
    }
}
