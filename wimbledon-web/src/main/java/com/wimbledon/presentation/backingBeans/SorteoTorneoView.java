package com.wimbledon.presentation.backingBeans;

import com.wimbledon.presentation.businessDelegate.*;
import com.wimbledon.utilities.FacesUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class SorteoTorneoView implements Serializable {
    
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SorteoTorneoView.class);
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    private Long numeroJugadores;
    
    public SorteoTorneoView() {
        super();
    }

    /*
     * Actions Methods
     */
    public String action_comprobar_numero_jugadores() throws Exception {
    	try {
    		numeroJugadores = businessDelegatorView.findTotalNumberJugador();
    		
    		if (numeroJugadores != null) {
    			
    			if (numeroJugadores == 0)
    				throw new Exception("No hay jugadores para realizar el sorteo");
    			
    			if (numeroJugadores % 2 != 0)
    				throw new Exception("El número de jugadores para realizar el torneo debe ser un número par, por favor agregue otro jugador");
    			
    		}
    		
    		businessDelegatorView.realizar_sorteo_torneo();
    		
    		FacesUtils.addInfoMessage("El sorteo del partido ha sido realizado exitosamente");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	return "";
    }
    
    
    
    /*
     * Getters and Setters
     */
    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

	public Long getNumeroJugadores() {
		return numeroJugadores;
	}

	public void setNumeroJugadores(Long numeroJugadores) {
		this.numeroJugadores = numeroJugadores;
	}
	

}
