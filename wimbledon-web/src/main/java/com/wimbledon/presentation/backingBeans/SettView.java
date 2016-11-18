package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.SettDTO;

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
public class SettView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SettView.class);
    private InputText txtEstado;
    private InputText txtGamejugador1;
    private InputText txtGamejugador2;
    private InputText txtTiempo;
    private InputText txtPartId_Partido;
    private InputText txtSetId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SettDTO> data;
    private SettDTO selectedSett;
    private Sett entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SettView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SettDTO settDTO = (SettDTO) e.getObject();

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(settDTO.getEstado());

            if (txtGamejugador1 == null) {
                txtGamejugador1 = new InputText();
            }

            txtGamejugador1.setValue(settDTO.getGamejugador1());

            if (txtGamejugador2 == null) {
                txtGamejugador2 = new InputText();
            }

            txtGamejugador2.setValue(settDTO.getGamejugador2());

            if (txtTiempo == null) {
                txtTiempo = new InputText();
            }

            txtTiempo.setValue(settDTO.getTiempo());

            if (txtPartId_Partido == null) {
                txtPartId_Partido = new InputText();
            }

            txtPartId_Partido.setValue(settDTO.getPartId_Partido());

            if (txtSetId == null) {
                txtSetId = new InputText();
            }

            txtSetId.setValue(settDTO.getSetId());

            Integer setId = FacesUtils.checkInteger(txtSetId);
            entity = businessDelegatorView.getSett(setId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSett = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSett = null;

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtGamejugador1 != null) {
            txtGamejugador1.setValue(null);
            txtGamejugador1.setDisabled(true);
        }

        if (txtGamejugador2 != null) {
            txtGamejugador2.setValue(null);
            txtGamejugador2.setDisabled(true);
        }

        if (txtTiempo != null) {
            txtTiempo.setValue(null);
            txtTiempo.setDisabled(true);
        }

        if (txtPartId_Partido != null) {
            txtPartId_Partido.setValue(null);
            txtPartId_Partido.setDisabled(true);
        }

        if (txtSetId != null) {
            txtSetId.setValue(null);
            txtSetId.setDisabled(false);
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
            Integer setId = FacesUtils.checkInteger(txtSetId);
            entity = (setId != null) ? businessDelegatorView.getSett(setId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstado.setDisabled(false);
            txtGamejugador1.setDisabled(false);
            txtGamejugador2.setDisabled(false);
            txtTiempo.setDisabled(false);
            txtPartId_Partido.setDisabled(false);
            txtSetId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtGamejugador1.setValue(entity.getGamejugador1());
            txtGamejugador1.setDisabled(false);
            txtGamejugador2.setValue(entity.getGamejugador2());
            txtGamejugador2.setDisabled(false);
            txtTiempo.setValue(entity.getTiempo());
            txtTiempo.setDisabled(false);
            txtPartId_Partido.setValue(entity.getPartido().getPartId());
            txtPartId_Partido.setDisabled(false);
            txtSetId.setValue(entity.getSetId());
            txtSetId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSett = (SettDTO) (evt.getComponent().getAttributes()
                                     .get("selectedSett"));
        txtEstado.setValue(selectedSett.getEstado());
        txtEstado.setDisabled(false);
        txtGamejugador1.setValue(selectedSett.getGamejugador1());
        txtGamejugador1.setDisabled(false);
        txtGamejugador2.setValue(selectedSett.getGamejugador2());
        txtGamejugador2.setDisabled(false);
        txtTiempo.setValue(selectedSett.getTiempo());
        txtTiempo.setDisabled(false);
        txtPartId_Partido.setValue(selectedSett.getPartId_Partido());
        txtPartId_Partido.setDisabled(false);
        txtSetId.setValue(selectedSett.getSetId());
        txtSetId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSett == null) && (entity == null)) {
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
            entity = new Sett();

            Integer setId = FacesUtils.checkInteger(txtSetId);

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setGamejugador1(FacesUtils.checkInteger(txtGamejugador1));
            entity.setGamejugador2(FacesUtils.checkInteger(txtGamejugador2));
            entity.setSetId(setId);
            entity.setTiempo(FacesUtils.checkDouble(txtTiempo));
            entity.setPartido((FacesUtils.checkInteger(txtPartId_Partido) != null)
                ? businessDelegatorView.getPartido(FacesUtils.checkInteger(
                        txtPartId_Partido)) : null);
            businessDelegatorView.saveSett(entity);
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
                Integer setId = new Integer(selectedSett.getSetId());
                entity = businessDelegatorView.getSett(setId);
            }

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setGamejugador1(FacesUtils.checkInteger(txtGamejugador1));
            entity.setGamejugador2(FacesUtils.checkInteger(txtGamejugador2));
            entity.setTiempo(FacesUtils.checkDouble(txtTiempo));
            entity.setPartido((FacesUtils.checkInteger(txtPartId_Partido) != null)
                ? businessDelegatorView.getPartido(FacesUtils.checkInteger(
                        txtPartId_Partido)) : null);
            businessDelegatorView.updateSett(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSett = (SettDTO) (evt.getComponent().getAttributes()
                                         .get("selectedSett"));

            Integer setId = new Integer(selectedSett.getSetId());
            entity = businessDelegatorView.getSett(setId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer setId = FacesUtils.checkInteger(txtSetId);
            entity = businessDelegatorView.getSett(setId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSett(entity);
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
            selectedSett = (SettDTO) (evt.getComponent().getAttributes()
                                         .get("selectedSett"));

            Integer setId = new Integer(selectedSett.getSetId());
            entity = businessDelegatorView.getSett(setId);
            businessDelegatorView.deleteSett(entity);
            data.remove(selectedSett);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estado, Integer gamejugador1,
        Integer gamejugador2, Integer setId, Double tiempo,
        Integer partId_Partido) throws Exception {
        try {
            entity.setEstado(FacesUtils.checkString(estado));
            entity.setGamejugador1(FacesUtils.checkInteger(gamejugador1));
            entity.setGamejugador2(FacesUtils.checkInteger(gamejugador2));
            entity.setTiempo(FacesUtils.checkDouble(tiempo));
            businessDelegatorView.updateSett(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SettView").requestRender();
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

    public InputText getTxtGamejugador1() {
        return txtGamejugador1;
    }

    public void setTxtGamejugador1(InputText txtGamejugador1) {
        this.txtGamejugador1 = txtGamejugador1;
    }

    public InputText getTxtGamejugador2() {
        return txtGamejugador2;
    }

    public void setTxtGamejugador2(InputText txtGamejugador2) {
        this.txtGamejugador2 = txtGamejugador2;
    }

    public InputText getTxtTiempo() {
        return txtTiempo;
    }

    public void setTxtTiempo(InputText txtTiempo) {
        this.txtTiempo = txtTiempo;
    }

    public InputText getTxtPartId_Partido() {
        return txtPartId_Partido;
    }

    public void setTxtPartId_Partido(InputText txtPartId_Partido) {
        this.txtPartId_Partido = txtPartId_Partido;
    }

    public InputText getTxtSetId() {
        return txtSetId;
    }

    public void setTxtSetId(InputText txtSetId) {
        this.txtSetId = txtSetId;
    }

    public List<SettDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSett();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SettDTO> settDTO) {
        this.data = settDTO;
    }

    public SettDTO getSelectedSett() {
        return selectedSett;
    }

    public void setSelectedSett(SettDTO sett) {
        this.selectedSett = sett;
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
