package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.UsuariosDTO;

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
public class UsuariosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuariosView.class);
    private InputText txtLogin;
    private InputText txtPassword;
    private InputText txtArbiId_Arbitro;
    private InputText txtDitoId_Directortorneo;
    private InputText txtUsuaId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuariosDTO> data;
    private UsuariosDTO selectedUsuarios;
    private Usuarios entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuariosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuariosDTO usuariosDTO = (UsuariosDTO) e.getObject();

            if (txtLogin == null) {
                txtLogin = new InputText();
            }

            txtLogin.setValue(usuariosDTO.getLogin());

            if (txtPassword == null) {
                txtPassword = new InputText();
            }

            txtPassword.setValue(usuariosDTO.getPassword());

            if (txtArbiId_Arbitro == null) {
                txtArbiId_Arbitro = new InputText();
            }

            txtArbiId_Arbitro.setValue(usuariosDTO.getArbiId_Arbitro());

            if (txtDitoId_Directortorneo == null) {
                txtDitoId_Directortorneo = new InputText();
            }

            txtDitoId_Directortorneo.setValue(usuariosDTO.getDitoId_Directortorneo());

            if (txtUsuaId == null) {
                txtUsuaId = new InputText();
            }

            txtUsuaId.setValue(usuariosDTO.getUsuaId());

            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = businessDelegatorView.getUsuarios(usuaId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuarios = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuarios = null;

        if (txtLogin != null) {
            txtLogin.setValue(null);
            txtLogin.setDisabled(true);
        }

        if (txtPassword != null) {
            txtPassword.setValue(null);
            txtPassword.setDisabled(true);
        }

        if (txtArbiId_Arbitro != null) {
            txtArbiId_Arbitro.setValue(null);
            txtArbiId_Arbitro.setDisabled(true);
        }

        if (txtDitoId_Directortorneo != null) {
            txtDitoId_Directortorneo.setValue(null);
            txtDitoId_Directortorneo.setDisabled(true);
        }

        if (txtUsuaId != null) {
            txtUsuaId.setValue(null);
            txtUsuaId.setDisabled(false);
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
            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = (usuaId != null)
                ? businessDelegatorView.getUsuarios(usuaId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtLogin.setDisabled(false);
            txtPassword.setDisabled(false);
            txtArbiId_Arbitro.setDisabled(false);
            txtDitoId_Directortorneo.setDisabled(false);
            txtUsuaId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtLogin.setValue(entity.getLogin());
            txtLogin.setDisabled(false);
            txtPassword.setValue(entity.getPassword());
            txtPassword.setDisabled(false);
            txtArbiId_Arbitro.setValue(entity.getArbitro().getArbiId());
            txtArbiId_Arbitro.setDisabled(false);
            txtDitoId_Directortorneo.setValue(entity.getDirectortorneo()
                                                    .getDitoId());
            txtDitoId_Directortorneo.setDisabled(false);
            txtUsuaId.setValue(entity.getUsuaId());
            txtUsuaId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuarios = (UsuariosDTO) (evt.getComponent().getAttributes()
                                             .get("selectedUsuarios"));
        txtLogin.setValue(selectedUsuarios.getLogin());
        txtLogin.setDisabled(false);
        txtPassword.setValue(selectedUsuarios.getPassword());
        txtPassword.setDisabled(false);
        txtArbiId_Arbitro.setValue(selectedUsuarios.getArbiId_Arbitro());
        txtArbiId_Arbitro.setDisabled(false);
        txtDitoId_Directortorneo.setValue(selectedUsuarios.getDitoId_Directortorneo());
        txtDitoId_Directortorneo.setDisabled(false);
        txtUsuaId.setValue(selectedUsuarios.getUsuaId());
        txtUsuaId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuarios == null) && (entity == null)) {
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
            entity = new Usuarios();

            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);

            entity.setLogin(FacesUtils.checkString(txtLogin));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setUsuaId(usuaId);
            entity.setArbitro((FacesUtils.checkInteger(txtArbiId_Arbitro) != null)
                ? businessDelegatorView.getArbitro(FacesUtils.checkInteger(
                        txtArbiId_Arbitro)) : null);
            entity.setDirectortorneo((FacesUtils.checkInteger(
                    txtDitoId_Directortorneo) != null)
                ? businessDelegatorView.getDirectortorneo(
                    FacesUtils.checkInteger(txtDitoId_Directortorneo)) : null);
            businessDelegatorView.saveUsuarios(entity);
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
                Integer usuaId = new Integer(selectedUsuarios.getUsuaId());
                entity = businessDelegatorView.getUsuarios(usuaId);
            }

            entity.setLogin(FacesUtils.checkString(txtLogin));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setArbitro((FacesUtils.checkInteger(txtArbiId_Arbitro) != null)
                ? businessDelegatorView.getArbitro(FacesUtils.checkInteger(
                        txtArbiId_Arbitro)) : null);
            entity.setDirectortorneo((FacesUtils.checkInteger(
                    txtDitoId_Directortorneo) != null)
                ? businessDelegatorView.getDirectortorneo(
                    FacesUtils.checkInteger(txtDitoId_Directortorneo)) : null);
            businessDelegatorView.updateUsuarios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuarios = (UsuariosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedUsuarios"));

            Integer usuaId = new Integer(selectedUsuarios.getUsuaId());
            entity = businessDelegatorView.getUsuarios(usuaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = businessDelegatorView.getUsuarios(usuaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuarios(entity);
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
            selectedUsuarios = (UsuariosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedUsuarios"));

            Integer usuaId = new Integer(selectedUsuarios.getUsuaId());
            entity = businessDelegatorView.getUsuarios(usuaId);
            businessDelegatorView.deleteUsuarios(entity);
            data.remove(selectedUsuarios);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String login, String password,
        Integer usuaId, Integer arbiId_Arbitro, Integer ditoId_Directortorneo)
        throws Exception {
        try {
            entity.setLogin(FacesUtils.checkString(login));
            entity.setPassword(FacesUtils.checkString(password));
            businessDelegatorView.updateUsuarios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuariosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtLogin() {
        return txtLogin;
    }

    public void setTxtLogin(InputText txtLogin) {
        this.txtLogin = txtLogin;
    }

    public InputText getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(InputText txtPassword) {
        this.txtPassword = txtPassword;
    }

    public InputText getTxtArbiId_Arbitro() {
        return txtArbiId_Arbitro;
    }

    public void setTxtArbiId_Arbitro(InputText txtArbiId_Arbitro) {
        this.txtArbiId_Arbitro = txtArbiId_Arbitro;
    }

    public InputText getTxtDitoId_Directortorneo() {
        return txtDitoId_Directortorneo;
    }

    public void setTxtDitoId_Directortorneo(InputText txtDitoId_Directortorneo) {
        this.txtDitoId_Directortorneo = txtDitoId_Directortorneo;
    }

    public InputText getTxtUsuaId() {
        return txtUsuaId;
    }

    public void setTxtUsuaId(InputText txtUsuaId) {
        this.txtUsuaId = txtUsuaId;
    }

    public List<UsuariosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuarios();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuariosDTO> usuariosDTO) {
        this.data = usuariosDTO;
    }

    public UsuariosDTO getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(UsuariosDTO usuarios) {
        this.selectedUsuarios = usuarios;
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
