package com.wimbledon.presentation.backingBeans;

import com.wimbledon.modelo.dto.TorneoDTO;
import com.wimbledon.presentation.businessDelegate.*;
import com.wimbledon.utilities.FacesUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

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
public class ResultadosTorneosView implements Serializable {
    
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ResultadosTorneosView.class);

    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    public List<TorneoDTO> listResultadosTornedoDTOs;
    public List<TorneoDTO> listTornedoDTOs;
    public TorneoDTO selectedTorneoDTO;
    
    
    public ResultadosTorneosView() {
        super();
    }

    
    /*
     * Actions Methods
     */
    
    public String action_consultar_partidos_torneo(TorneoDTO torneoDTO) throws Exception {
    	try {
			listResultadosTornedoDTOs = businessDelegatorView.getResultadosTorneos(torneoDTO.getTornId());			
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

	public List<TorneoDTO> getListResultadosTornedoDTOs() {		
		return listResultadosTornedoDTOs;
	}

	public void setListResultadosTornedoDTOs(List<TorneoDTO> listResultadosTornedoDTOs) {
		this.listResultadosTornedoDTOs = listResultadosTornedoDTOs;
	}

	public TorneoDTO getSelectedTorneoDTO() {
		return selectedTorneoDTO;
	}

	public void setSelectedTorneoDTO(TorneoDTO selectedTorneoDTO) {
		this.selectedTorneoDTO = selectedTorneoDTO;
	}

	public List<TorneoDTO> getListTornedoDTOs() {
		try {
			if (listTornedoDTOs == null)
				listTornedoDTOs = businessDelegatorView.getDataTorneo();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return listTornedoDTOs;
	}

	public void setListTornedoDTOs(List<TorneoDTO> listTornedoDTOs) {
		this.listTornedoDTOs = listTornedoDTOs;
	}
    
}
