package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.TorneoDTO;

import com.wimbledon.presentation.businessDelegate.*;

import com.wimbledon.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class TorneoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TorneoView.class);
    private InputText txtEstado;
    private InputText txtNombre;
    private InputText txtPremio;
    private InputText txtTornId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TorneoDTO> data;
    private TorneoDTO selectedTorneo;
    private Torneo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TorneoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TorneoDTO torneoDTO = (TorneoDTO) e.getObject();

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(torneoDTO.getEstado());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(torneoDTO.getNombre());

            if (txtPremio == null) {
                txtPremio = new InputText();
            }

            txtPremio.setValue(torneoDTO.getPremio());

            if (txtTornId == null) {
                txtTornId = new InputText();
            }

            txtTornId.setValue(torneoDTO.getTornId());

            Integer tornId = FacesUtils.checkInteger(txtTornId);
            entity = businessDelegatorView.getTorneo(tornId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTorneo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTorneo = null;

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtPremio != null) {
            txtPremio.setValue(null);
            txtPremio.setDisabled(true);
        }

        if (txtTornId != null) {
            txtTornId.setValue(null);
            txtTornId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer tornId = FacesUtils.checkInteger(txtTornId);
            entity = (tornId != null) ? businessDelegatorView.getTorneo(tornId)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstado.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPremio.setDisabled(false);
            txtTornId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPremio.setValue(entity.getPremio());
            txtPremio.setDisabled(false);
            txtTornId.setValue(entity.getTornId());
            txtTornId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTorneo = (TorneoDTO) (evt.getComponent().getAttributes()
                                         .get("selectedTorneo"));
        txtEstado.setValue(selectedTorneo.getEstado());
        txtEstado.setDisabled(false);
        txtNombre.setValue(selectedTorneo.getNombre());
        txtNombre.setDisabled(false);
        txtPremio.setValue(selectedTorneo.getPremio());
        txtPremio.setDisabled(false);
        txtTornId.setValue(selectedTorneo.getTornId());
        txtTornId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTorneo == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Torneo();

            Integer tornId = FacesUtils.checkInteger(txtTornId);

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPremio(FacesUtils.checkString(txtPremio));
            entity.setTornId(tornId);
            businessDelegatorView.saveTorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Integer tornId = new Integer(selectedTorneo.getTornId());
                entity = businessDelegatorView.getTorneo(tornId);
            }

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPremio(FacesUtils.checkString(txtPremio));
            businessDelegatorView.updateTorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTorneo = (TorneoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedTorneo"));

            Integer tornId = new Integer(selectedTorneo.getTornId());
            entity = businessDelegatorView.getTorneo(tornId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer tornId = FacesUtils.checkInteger(txtTornId);
            entity = businessDelegatorView.getTorneo(tornId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedTorneo = (TorneoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedTorneo"));

            Integer tornId = new Integer(selectedTorneo.getTornId());
            entity = businessDelegatorView.getTorneo(tornId);
            businessDelegatorView.deleteTorneo(entity);
            data.remove(selectedTorneo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estado, String nombre,
        String premio, Integer tornId) throws Exception {
        try {
            entity.setEstado(FacesUtils.checkString(estado));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPremio(FacesUtils.checkString(premio));
            businessDelegatorView.updateTorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TorneoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(InputText txtEstado) {
        this.txtEstado = txtEstado;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtPremio() {
        return txtPremio;
    }

    public void setTxtPremio(InputText txtPremio) {
        this.txtPremio = txtPremio;
    }

    public InputText getTxtTornId() {
        return txtTornId;
    }

    public void setTxtTornId(InputText txtTornId) {
        this.txtTornId = txtTornId;
    }

    public List<TorneoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTorneo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TorneoDTO> torneoDTO) {
        this.data = torneoDTO;
    }

    public TorneoDTO getSelectedTorneo() {
        return selectedTorneo;
    }

    public void setSelectedTorneo(TorneoDTO torneo) {
        this.selectedTorneo = torneo;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
