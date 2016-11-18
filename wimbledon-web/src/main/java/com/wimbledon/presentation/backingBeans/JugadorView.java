package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.JugadorDTO;

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
public class JugadorView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(JugadorView.class);
    private InputText txtEstado;
    private InputText txtHombre;
    private InputText txtNombre;
    private InputText txtNumeroIdentificacion;
    private InputText txtRanking;
    private InputText txtJugaId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<JugadorDTO> data;
    private JugadorDTO selectedJugador;
    private Jugador entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public JugadorView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            JugadorDTO jugadorDTO = (JugadorDTO) e.getObject();

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(jugadorDTO.getEstado());

            if (txtHombre == null) {
                txtHombre = new InputText();
            }

            txtHombre.setValue(jugadorDTO.getHombre());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(jugadorDTO.getNombre());

            if (txtNumeroIdentificacion == null) {
                txtNumeroIdentificacion = new InputText();
            }

            txtNumeroIdentificacion.setValue(jugadorDTO.getNumeroIdentificacion());

            if (txtRanking == null) {
                txtRanking = new InputText();
            }

            txtRanking.setValue(jugadorDTO.getRanking());

            if (txtJugaId == null) {
                txtJugaId = new InputText();
            }

            txtJugaId.setValue(jugadorDTO.getJugaId());

            Integer jugaId = FacesUtils.checkInteger(txtJugaId);
            entity = businessDelegatorView.getJugador(jugaId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedJugador = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedJugador = null;

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtHombre != null) {
            txtHombre.setValue(null);
            txtHombre.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtNumeroIdentificacion != null) {
            txtNumeroIdentificacion.setValue(null);
            txtNumeroIdentificacion.setDisabled(true);
        }

        if (txtRanking != null) {
            txtRanking.setValue(null);
            txtRanking.setDisabled(true);
        }

        if (txtJugaId != null) {
            txtJugaId.setValue(null);
            txtJugaId.setDisabled(false);
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
            Integer jugaId = FacesUtils.checkInteger(txtJugaId);
            entity = (jugaId != null)
                ? businessDelegatorView.getJugador(jugaId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstado.setDisabled(false);
            txtHombre.setDisabled(false);
            txtNombre.setDisabled(false);
            txtNumeroIdentificacion.setDisabled(false);
            txtRanking.setDisabled(false);
            txtJugaId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtHombre.setValue(entity.getHombre());
            txtHombre.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtNumeroIdentificacion.setValue(entity.getNumeroIdentificacion());
            txtNumeroIdentificacion.setDisabled(false);
            txtRanking.setValue(entity.getRanking());
            txtRanking.setDisabled(false);
            txtJugaId.setValue(entity.getJugaId());
            txtJugaId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedJugador = (JugadorDTO) (evt.getComponent().getAttributes()
                                           .get("selectedJugador"));
        txtEstado.setValue(selectedJugador.getEstado());
        txtEstado.setDisabled(false);
        txtHombre.setValue(selectedJugador.getHombre());
        txtHombre.setDisabled(false);
        txtNombre.setValue(selectedJugador.getNombre());
        txtNombre.setDisabled(false);
        txtNumeroIdentificacion.setValue(selectedJugador.getNumeroIdentificacion());
        txtNumeroIdentificacion.setDisabled(false);
        txtRanking.setValue(selectedJugador.getRanking());
        txtRanking.setDisabled(false);
        txtJugaId.setValue(selectedJugador.getJugaId());
        txtJugaId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedJugador == null) && (entity == null)) {
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
            entity = new Jugador();

            Integer jugaId = FacesUtils.checkInteger(txtJugaId);

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setHombre(FacesUtils.checkInteger(txtHombre));
            entity.setJugaId(jugaId);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    txtNumeroIdentificacion));
            entity.setRanking(FacesUtils.checkInteger(txtRanking));
            businessDelegatorView.saveJugador(entity);
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
                Integer jugaId = new Integer(selectedJugador.getJugaId());
                entity = businessDelegatorView.getJugador(jugaId);
            }

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setHombre(FacesUtils.checkInteger(txtHombre));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    txtNumeroIdentificacion));
            entity.setRanking(FacesUtils.checkInteger(txtRanking));
            businessDelegatorView.updateJugador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedJugador = (JugadorDTO) (evt.getComponent().getAttributes()
                                               .get("selectedJugador"));

            Integer jugaId = new Integer(selectedJugador.getJugaId());
            entity = businessDelegatorView.getJugador(jugaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer jugaId = FacesUtils.checkInteger(txtJugaId);
            entity = businessDelegatorView.getJugador(jugaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteJugador(entity);
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
            selectedJugador = (JugadorDTO) (evt.getComponent().getAttributes()
                                               .get("selectedJugador"));

            Integer jugaId = new Integer(selectedJugador.getJugaId());
            entity = businessDelegatorView.getJugador(jugaId);
            businessDelegatorView.deleteJugador(entity);
            data.remove(selectedJugador);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estado, Integer hombre,
        Integer jugaId, String nombre, String numeroIdentificacion,
        Integer ranking) throws Exception {
        try {
            entity.setEstado(FacesUtils.checkString(estado));
            entity.setHombre(FacesUtils.checkInteger(hombre));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    numeroIdentificacion));
            entity.setRanking(FacesUtils.checkInteger(ranking));
            businessDelegatorView.updateJugador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("JugadorView").requestRender();
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

    public InputText getTxtHombre() {
        return txtHombre;
    }

    public void setTxtHombre(InputText txtHombre) {
        this.txtHombre = txtHombre;
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

    public InputText getTxtRanking() {
        return txtRanking;
    }

    public void setTxtRanking(InputText txtRanking) {
        this.txtRanking = txtRanking;
    }

    public InputText getTxtJugaId() {
        return txtJugaId;
    }

    public void setTxtJugaId(InputText txtJugaId) {
        this.txtJugaId = txtJugaId;
    }

    public List<JugadorDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataJugador();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<JugadorDTO> jugadorDTO) {
        this.data = jugadorDTO;
    }

    public JugadorDTO getSelectedJugador() {
        return selectedJugador;
    }

    public void setSelectedJugador(JugadorDTO jugador) {
        this.selectedJugador = jugador;
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
