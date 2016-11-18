package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.CanchaDTO;

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
public class CanchaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CanchaView.class);
    private InputText txtEstado;
    private InputText txtMantenimiento;
    private InputText txtNumero;
    private InputText txtCancId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CanchaDTO> data;
    private CanchaDTO selectedCancha;
    private Cancha entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CanchaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CanchaDTO canchaDTO = (CanchaDTO) e.getObject();

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(canchaDTO.getEstado());

            if (txtMantenimiento == null) {
                txtMantenimiento = new InputText();
            }

            txtMantenimiento.setValue(canchaDTO.getMantenimiento());

            if (txtNumero == null) {
                txtNumero = new InputText();
            }

            txtNumero.setValue(canchaDTO.getNumero());

            if (txtCancId == null) {
                txtCancId = new InputText();
            }

            txtCancId.setValue(canchaDTO.getCancId());

            Integer cancId = FacesUtils.checkInteger(txtCancId);
            entity = businessDelegatorView.getCancha(cancId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCancha = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCancha = null;

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtMantenimiento != null) {
            txtMantenimiento.setValue(null);
            txtMantenimiento.setDisabled(true);
        }

        if (txtNumero != null) {
            txtNumero.setValue(null);
            txtNumero.setDisabled(true);
        }

        if (txtCancId != null) {
            txtCancId.setValue(null);
            txtCancId.setDisabled(false);
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
            Integer cancId = FacesUtils.checkInteger(txtCancId);
            entity = (cancId != null) ? businessDelegatorView.getCancha(cancId)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstado.setDisabled(false);
            txtMantenimiento.setDisabled(false);
            txtNumero.setDisabled(false);
            txtCancId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtMantenimiento.setValue(entity.getMantenimiento());
            txtMantenimiento.setDisabled(false);
            txtNumero.setValue(entity.getNumero());
            txtNumero.setDisabled(false);
            txtCancId.setValue(entity.getCancId());
            txtCancId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCancha = (CanchaDTO) (evt.getComponent().getAttributes()
                                         .get("selectedCancha"));
        txtEstado.setValue(selectedCancha.getEstado());
        txtEstado.setDisabled(false);
        txtMantenimiento.setValue(selectedCancha.getMantenimiento());
        txtMantenimiento.setDisabled(false);
        txtNumero.setValue(selectedCancha.getNumero());
        txtNumero.setDisabled(false);
        txtCancId.setValue(selectedCancha.getCancId());
        txtCancId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCancha == null) && (entity == null)) {
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
            entity = new Cancha();

            Integer cancId = FacesUtils.checkInteger(txtCancId);

            entity.setCancId(cancId);
            entity.setEstado(FacesUtils.checkInteger(txtEstado));
            entity.setMantenimiento(FacesUtils.checkString(txtMantenimiento));
            entity.setNumero(FacesUtils.checkInteger(txtNumero));
            businessDelegatorView.saveCancha(entity);
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
                Integer cancId = new Integer(selectedCancha.getCancId());
                entity = businessDelegatorView.getCancha(cancId);
            }

            entity.setEstado(FacesUtils.checkInteger(txtEstado));
            entity.setMantenimiento(FacesUtils.checkString(txtMantenimiento));
            entity.setNumero(FacesUtils.checkInteger(txtNumero));
            businessDelegatorView.updateCancha(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCancha = (CanchaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCancha"));

            Integer cancId = new Integer(selectedCancha.getCancId());
            entity = businessDelegatorView.getCancha(cancId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer cancId = FacesUtils.checkInteger(txtCancId);
            entity = businessDelegatorView.getCancha(cancId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCancha(entity);
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
            selectedCancha = (CanchaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCancha"));

            Integer cancId = new Integer(selectedCancha.getCancId());
            entity = businessDelegatorView.getCancha(cancId);
            businessDelegatorView.deleteCancha(entity);
            data.remove(selectedCancha);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer cancId, Integer estado,
        String mantenimiento, Integer numero) throws Exception {
        try {
            entity.setEstado(FacesUtils.checkInteger(estado));
            entity.setMantenimiento(FacesUtils.checkString(mantenimiento));
            entity.setNumero(FacesUtils.checkInteger(numero));
            businessDelegatorView.updateCancha(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CanchaView").requestRender();
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

    public InputText getTxtMantenimiento() {
        return txtMantenimiento;
    }

    public void setTxtMantenimiento(InputText txtMantenimiento) {
        this.txtMantenimiento = txtMantenimiento;
    }

    public InputText getTxtNumero() {
        return txtNumero;
    }

    public void setTxtNumero(InputText txtNumero) {
        this.txtNumero = txtNumero;
    }

    public InputText getTxtCancId() {
        return txtCancId;
    }

    public void setTxtCancId(InputText txtCancId) {
        this.txtCancId = txtCancId;
    }

    public List<CanchaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCancha();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CanchaDTO> canchaDTO) {
        this.data = canchaDTO;
    }

    public CanchaDTO getSelectedCancha() {
        return selectedCancha;
    }

    public void setSelectedCancha(CanchaDTO cancha) {
        this.selectedCancha = cancha;
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
