package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.ArbitroDTO;

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
public class ArbitroView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ArbitroView.class);

    private InputText txtEstado;
    private InputText txtNombre;
    private InputText txtNumeroIdentificacion;
    private InputText txtArbiId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ArbitroDTO> data;
    private ArbitroDTO selectedArbitro;
    private Arbitro entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ArbitroView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ArbitroDTO arbitroDTO = (ArbitroDTO) e.getObject();

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(arbitroDTO.getEstado());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(arbitroDTO.getNombre());

            if (txtNumeroIdentificacion == null) {
                txtNumeroIdentificacion = new InputText();
            }

            txtNumeroIdentificacion.setValue(arbitroDTO.getNumeroIdentificacion());

            if (txtArbiId == null) {
                txtArbiId = new InputText();
            }

            txtArbiId.setValue(arbitroDTO.getArbiId());

            Integer arbiId = FacesUtils.checkInteger(txtArbiId);
            entity = businessDelegatorView.getArbitro(arbiId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedArbitro = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedArbitro = null;
        txtEstado.resetValue();
        txtNombre.resetValue();
        txtNumeroIdentificacion.resetValue();
       

        return "";
    }

 
    public String action_edit(ActionEvent evt) {
        selectedArbitro = (ArbitroDTO) (evt.getComponent().getAttributes()
                                           .get("selectedArbitro"));

        txtEstado.setValue(selectedArbitro.getEstado());
        txtEstado.setDisabled(false);
        txtNombre.setValue(selectedArbitro.getNombre());
        txtNombre.setDisabled(false);
        txtNumeroIdentificacion.setValue(selectedArbitro.getNumeroIdentificacion());
        txtNumeroIdentificacion.setDisabled(false);
        txtArbiId.setValue(selectedArbitro.getArbiId());
        txtArbiId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedArbitro == null) && (entity == null)) {
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
            entity = new Arbitro();

            Integer arbiId = FacesUtils.checkInteger(txtArbiId);

            entity.setArbiId(arbiId);
            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    txtNumeroIdentificacion));
            businessDelegatorView.saveArbitro(entity);
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
                Integer arbiId = new Integer(selectedArbitro.getArbiId());
                entity = businessDelegatorView.getArbitro(arbiId);
            }

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    txtNumeroIdentificacion));
            businessDelegatorView.updateArbitro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedArbitro = (ArbitroDTO) (evt.getComponent().getAttributes()
                                               .get("selectedArbitro"));

            Integer arbiId = new Integer(selectedArbitro.getArbiId());
            entity = businessDelegatorView.getArbitro(arbiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer arbiId = FacesUtils.checkInteger(txtArbiId);
            entity = businessDelegatorView.getArbitro(arbiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteArbitro(entity);
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
            selectedArbitro = (ArbitroDTO) (evt.getComponent().getAttributes()
                                               .get("selectedArbitro"));

            Integer arbiId = new Integer(selectedArbitro.getArbiId());
            entity = businessDelegatorView.getArbitro(arbiId);
            businessDelegatorView.deleteArbitro(entity);
            data.remove(selectedArbitro);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer arbiId, Integer docuId,
        String estado, String nombre, String numeroIdentificacion)
        throws Exception {
        try {
            entity.setEstado(FacesUtils.checkString(estado));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    numeroIdentificacion));
            businessDelegatorView.updateArbitro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ArbitroView").requestRender();
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

    public InputText getTxtNumeroIdentificacion() {
        return txtNumeroIdentificacion;
    }

    public void setTxtNumeroIdentificacion(InputText txtNumeroIdentificacion) {
        this.txtNumeroIdentificacion = txtNumeroIdentificacion;
    }

    public InputText getTxtArbiId() {
        return txtArbiId;
    }

    public void setTxtArbiId(InputText txtArbiId) {
        this.txtArbiId = txtArbiId;
    }

    public List<ArbitroDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataArbitro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ArbitroDTO> arbitroDTO) {
        this.data = arbitroDTO;
    }

    public ArbitroDTO getSelectedArbitro() {
        return selectedArbitro;
    }

    public void setSelectedArbitro(ArbitroDTO arbitro) {
        this.selectedArbitro = arbitro;
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
