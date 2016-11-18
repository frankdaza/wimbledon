package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.DirectortorneoDTO;

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
public class DirectortorneoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DirectortorneoView.class);
    private InputText txtDocuId;
    private InputText txtEstado;
    private InputText txtNombre;
    private InputText txtNumeroIdentificacion;
    private InputText txtDitoId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DirectortorneoDTO> data;
    private DirectortorneoDTO selectedDirectortorneo;
    private Directortorneo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DirectortorneoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            DirectortorneoDTO directortorneoDTO = (DirectortorneoDTO) e.getObject();

            if (txtDocuId == null) {
                txtDocuId = new InputText();
            }

            txtDocuId.setValue(directortorneoDTO.getDocuId());

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(directortorneoDTO.getEstado());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(directortorneoDTO.getNombre());

            if (txtNumeroIdentificacion == null) {
                txtNumeroIdentificacion = new InputText();
            }

            txtNumeroIdentificacion.setValue(directortorneoDTO.getNumeroIdentificacion());

            if (txtDitoId == null) {
                txtDitoId = new InputText();
            }

            txtDitoId.setValue(directortorneoDTO.getDitoId());

            Integer ditoId = FacesUtils.checkInteger(txtDitoId);
            entity = businessDelegatorView.getDirectortorneo(ditoId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedDirectortorneo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDirectortorneo = null;

        if (txtDocuId != null) {
            txtDocuId.setValue(null);
            txtDocuId.setDisabled(true);
        }

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtNumeroIdentificacion != null) {
            txtNumeroIdentificacion.setValue(null);
            txtNumeroIdentificacion.setDisabled(true);
        }

        if (txtDitoId != null) {
            txtDitoId.setValue(null);
            txtDitoId.setDisabled(false);
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
            Integer ditoId = FacesUtils.checkInteger(txtDitoId);
            entity = (ditoId != null)
                ? businessDelegatorView.getDirectortorneo(ditoId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDocuId.setDisabled(false);
            txtEstado.setDisabled(false);
            txtNombre.setDisabled(false);
            txtNumeroIdentificacion.setDisabled(false);
            txtDitoId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDocuId.setValue(entity.getDocuId());
            txtDocuId.setDisabled(false);
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtNumeroIdentificacion.setValue(entity.getNumeroIdentificacion());
            txtNumeroIdentificacion.setDisabled(false);
            txtDitoId.setValue(entity.getDitoId());
            txtDitoId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedDirectortorneo = (DirectortorneoDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedDirectortorneo"));
        txtDocuId.setValue(selectedDirectortorneo.getDocuId());
        txtDocuId.setDisabled(false);
        txtEstado.setValue(selectedDirectortorneo.getEstado());
        txtEstado.setDisabled(false);
        txtNombre.setValue(selectedDirectortorneo.getNombre());
        txtNombre.setDisabled(false);
        txtNumeroIdentificacion.setValue(selectedDirectortorneo.getNumeroIdentificacion());
        txtNumeroIdentificacion.setDisabled(false);
        txtDitoId.setValue(selectedDirectortorneo.getDitoId());
        txtDitoId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDirectortorneo == null) && (entity == null)) {
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
            entity = new Directortorneo();

            Integer ditoId = FacesUtils.checkInteger(txtDitoId);

            entity.setDitoId(ditoId);
            entity.setDocuId(FacesUtils.checkInteger(txtDocuId));
            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    txtNumeroIdentificacion));
            businessDelegatorView.saveDirectortorneo(entity);
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
                Integer ditoId = new Integer(selectedDirectortorneo.getDitoId());
                entity = businessDelegatorView.getDirectortorneo(ditoId);
            }

            entity.setDocuId(FacesUtils.checkInteger(txtDocuId));
            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    txtNumeroIdentificacion));
            businessDelegatorView.updateDirectortorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDirectortorneo = (DirectortorneoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedDirectortorneo"));

            Integer ditoId = new Integer(selectedDirectortorneo.getDitoId());
            entity = businessDelegatorView.getDirectortorneo(ditoId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer ditoId = FacesUtils.checkInteger(txtDitoId);
            entity = businessDelegatorView.getDirectortorneo(ditoId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDirectortorneo(entity);
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
            selectedDirectortorneo = (DirectortorneoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedDirectortorneo"));

            Integer ditoId = new Integer(selectedDirectortorneo.getDitoId());
            entity = businessDelegatorView.getDirectortorneo(ditoId);
            businessDelegatorView.deleteDirectortorneo(entity);
            data.remove(selectedDirectortorneo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer ditoId, Integer docuId,
        String estado, String nombre, String numeroIdentificacion)
        throws Exception {
        try {
            entity.setDocuId(FacesUtils.checkInteger(docuId));
            entity.setEstado(FacesUtils.checkString(estado));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    numeroIdentificacion));
            businessDelegatorView.updateDirectortorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DirectortorneoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDocuId() {
        return txtDocuId;
    }

    public void setTxtDocuId(InputText txtDocuId) {
        this.txtDocuId = txtDocuId;
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

    public InputText getTxtNumeroIdentificacion() {
        return txtNumeroIdentificacion;
    }

    public void setTxtNumeroIdentificacion(InputText txtNumeroIdentificacion) {
        this.txtNumeroIdentificacion = txtNumeroIdentificacion;
    }

    public InputText getTxtDitoId() {
        return txtDitoId;
    }

    public void setTxtDitoId(InputText txtDitoId) {
        this.txtDitoId = txtDitoId;
    }

    public List<DirectortorneoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDirectortorneo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DirectortorneoDTO> directortorneoDTO) {
        this.data = directortorneoDTO;
    }

    public DirectortorneoDTO getSelectedDirectortorneo() {
        return selectedDirectortorneo;
    }

    public void setSelectedDirectortorneo(DirectortorneoDTO directortorneo) {
        this.selectedDirectortorneo = directortorneo;
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
