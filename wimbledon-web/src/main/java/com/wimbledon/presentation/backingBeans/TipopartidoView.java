package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.TipopartidoDTO;

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
public class TipopartidoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipopartidoView.class);
    private InputText txtEstado;
    private InputText txtNombre;
    private InputText txtTipaId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipopartidoDTO> data;
    private TipopartidoDTO selectedTipopartido;
    private Tipopartido entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipopartidoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipopartidoDTO tipopartidoDTO = (TipopartidoDTO) e.getObject();

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(tipopartidoDTO.getEstado());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(tipopartidoDTO.getNombre());

            if (txtTipaId == null) {
                txtTipaId = new InputText();
            }

            txtTipaId.setValue(tipopartidoDTO.getTipaId());

            Integer tipaId = FacesUtils.checkInteger(txtTipaId);
            entity = businessDelegatorView.getTipopartido(tipaId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipopartido = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipopartido = null;

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtTipaId != null) {
            txtTipaId.setValue(null);
            txtTipaId.setDisabled(false);
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
            Integer tipaId = FacesUtils.checkInteger(txtTipaId);
            entity = (tipaId != null)
                ? businessDelegatorView.getTipopartido(tipaId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstado.setDisabled(false);
            txtNombre.setDisabled(false);
            txtTipaId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtTipaId.setValue(entity.getTipaId());
            txtTipaId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipopartido = (TipopartidoDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedTipopartido"));
        txtEstado.setValue(selectedTipopartido.getEstado());
        txtEstado.setDisabled(false);
        txtNombre.setValue(selectedTipopartido.getNombre());
        txtNombre.setDisabled(false);
        txtTipaId.setValue(selectedTipopartido.getTipaId());
        txtTipaId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipopartido == null) && (entity == null)) {
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
            entity = new Tipopartido();

            Integer tipaId = FacesUtils.checkInteger(txtTipaId);

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTipaId(tipaId);
            businessDelegatorView.saveTipopartido(entity);
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
                Integer tipaId = new Integer(selectedTipopartido.getTipaId());
                entity = businessDelegatorView.getTipopartido(tipaId);
            }

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updateTipopartido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipopartido = (TipopartidoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTipopartido"));

            Integer tipaId = new Integer(selectedTipopartido.getTipaId());
            entity = businessDelegatorView.getTipopartido(tipaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer tipaId = FacesUtils.checkInteger(txtTipaId);
            entity = businessDelegatorView.getTipopartido(tipaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipopartido(entity);
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
            selectedTipopartido = (TipopartidoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTipopartido"));

            Integer tipaId = new Integer(selectedTipopartido.getTipaId());
            entity = businessDelegatorView.getTipopartido(tipaId);
            businessDelegatorView.deleteTipopartido(entity);
            data.remove(selectedTipopartido);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estado, String nombre,
        Integer tipaId) throws Exception {
        try {
            entity.setEstado(FacesUtils.checkString(estado));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updateTipopartido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipopartidoView").requestRender();
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

    public InputText getTxtTipaId() {
        return txtTipaId;
    }

    public void setTxtTipaId(InputText txtTipaId) {
        this.txtTipaId = txtTipaId;
    }

    public List<TipopartidoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipopartido();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipopartidoDTO> tipopartidoDTO) {
        this.data = tipopartidoDTO;
    }

    public TipopartidoDTO getSelectedTipopartido() {
        return selectedTipopartido;
    }

    public void setSelectedTipopartido(TipopartidoDTO tipopartido) {
        this.selectedTipopartido = tipopartido;
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
