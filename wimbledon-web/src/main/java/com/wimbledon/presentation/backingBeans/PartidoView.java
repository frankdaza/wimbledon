package com.wimbledon.presentation.backingBeans;

import com.wimbledon.exceptions.*;

import com.wimbledon.modelo.*;
import com.wimbledon.modelo.dto.PartidoDTO;

import com.wimbledon.presentation.businessDelegate.*;

import com.wimbledon.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
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
import javax.faces.model.SelectItem;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PartidoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PartidoView.class);
	private InputText txtEstado;
	private InputText txtTiempototal;
	private InputText txtArbiId_Arbitro;
	private InputText txtCancId_Cancha;
	private InputText txtDitoId_Directortorneo;
	private InputText txtJugaId_Jugador;
	private InputText txtTipaId_Tipopartido;
	private InputText txtTornId_Torneo;
	private InputText txtPartId;
	private Calendar txtHora;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<PartidoDTO> data;
	private PartidoDTO selectedPartido;
	private Partido entity;
	private boolean showDialog;
	private boolean showDialogArbitro;
	private boolean showDialogSet;
	
	private SelectOneMenu somArbitros;
	private List<SelectItem> losArbitros;
	
	private InputText jugadorASet1;
	private InputText jugadorASet2;
	private InputText jugadorASet3;
	
	private InputText jugadorBSet1;
	private InputText jugadorBSet2;
	private InputText jugadorBSet3;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PartidoView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			PartidoDTO partidoDTO = (PartidoDTO) e.getObject();

			if (txtEstado == null) {
				txtEstado = new InputText();
			}

			txtEstado.setValue(partidoDTO.getEstado());

			if (txtTiempototal == null) {
				txtTiempototal = new InputText();
			}

			txtTiempototal.setValue(partidoDTO.getTiempototal());

			if (txtArbiId_Arbitro == null) {
				txtArbiId_Arbitro = new InputText();
			}

			txtArbiId_Arbitro.setValue(partidoDTO.getArbiId_Arbitro());

			if (txtCancId_Cancha == null) {
				txtCancId_Cancha = new InputText();
			}

			txtCancId_Cancha.setValue(partidoDTO.getCancId_Cancha());

			if (txtDitoId_Directortorneo == null) {
				txtDitoId_Directortorneo = new InputText();
			}

			txtDitoId_Directortorneo.setValue(partidoDTO.getDitoId_Directortorneo());

			if (txtJugaId_Jugador == null) {
				txtJugaId_Jugador = new InputText();
			}

			if (txtTipaId_Tipopartido == null) {
				txtTipaId_Tipopartido = new InputText();
			}

			txtTipaId_Tipopartido.setValue(partidoDTO.getTipaId_Tipopartido());

			if (txtTornId_Torneo == null) {
				txtTornId_Torneo = new InputText();
			}

			txtTornId_Torneo.setValue(partidoDTO.getTornId_Torneo());

			if (txtPartId == null) {
				txtPartId = new InputText();
			}

			txtPartId.setValue(partidoDTO.getPartId());

			if (txtHora == null) {
				txtHora = new Calendar();
			}

			txtHora.setValue(partidoDTO.getHora());

			Integer partId = FacesUtils.checkInteger(txtPartId);
			entity = businessDelegatorView.getPartido(partId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedPartido = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedPartido = null;

		if (txtEstado != null) {
			txtEstado.setValue(null);
			txtEstado.setDisabled(true);
		}

		if (txtTiempototal != null) {
			txtTiempototal.setValue(null);
			txtTiempototal.setDisabled(true);
		}

		if (txtArbiId_Arbitro != null) {
			txtArbiId_Arbitro.setValue(null);
			txtArbiId_Arbitro.setDisabled(true);
		}

		if (txtCancId_Cancha != null) {
			txtCancId_Cancha.setValue(null);
			txtCancId_Cancha.setDisabled(true);
		}

		if (txtDitoId_Directortorneo != null) {
			txtDitoId_Directortorneo.setValue(null);
			txtDitoId_Directortorneo.setDisabled(true);
		}

		if (txtJugaId_Jugador != null) {
			txtJugaId_Jugador.setValue(null);
			txtJugaId_Jugador.setDisabled(true);
		}

		if (txtTipaId_Tipopartido != null) {
			txtTipaId_Tipopartido.setValue(null);
			txtTipaId_Tipopartido.setDisabled(true);
		}

		if (txtTornId_Torneo != null) {
			txtTornId_Torneo.setValue(null);
			txtTornId_Torneo.setDisabled(true);
		}

		if (txtHora != null) {
			txtHora.setValue(null);
			txtHora.setDisabled(true);
		}

		if (txtPartId != null) {
			txtPartId.setValue(null);
			txtPartId.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(true);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		return "";
	}

	public void listener_txtHora() {
		Date inputDate = (Date) txtHora.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			Integer partId = FacesUtils.checkInteger(txtPartId);
			entity = (partId != null) ? businessDelegatorView.getPartido(partId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtEstado.setDisabled(false);
			txtTiempototal.setDisabled(false);
			txtArbiId_Arbitro.setDisabled(false);
			txtCancId_Cancha.setDisabled(false);
			txtDitoId_Directortorneo.setDisabled(false);
			txtJugaId_Jugador.setDisabled(false);
			txtTipaId_Tipopartido.setDisabled(false);
			txtTornId_Torneo.setDisabled(false);
			txtHora.setDisabled(false);
			txtPartId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtEstado.setValue(entity.getEstado());
			txtEstado.setDisabled(false);
			txtHora.setValue(entity.getHora());
			txtHora.setDisabled(false);
			txtTiempototal.setValue(entity.getTiempototal());
			txtTiempototal.setDisabled(false);
			txtArbiId_Arbitro.setValue(entity.getArbitro().getArbiId());
			txtArbiId_Arbitro.setDisabled(false);
			txtCancId_Cancha.setValue(entity.getCancha().getCancId());
			txtCancId_Cancha.setDisabled(false);
			txtDitoId_Directortorneo.setValue(entity.getDirectortorneo().getDitoId());
			txtDitoId_Directortorneo.setDisabled(false);
			txtJugaId_Jugador.setDisabled(false);
			txtTipaId_Tipopartido.setValue(entity.getTipopartido().getTipaId());
			txtTipaId_Tipopartido.setDisabled(false);
			txtTornId_Torneo.setValue(entity.getTorneo().getTornId());
			txtTornId_Torneo.setDisabled(false);
			txtPartId.setValue(entity.getPartId());
			txtPartId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedPartido = (PartidoDTO) (evt.getComponent().getAttributes().get("selectedPartido"));
		txtEstado.setValue(selectedPartido.getEstado());
		txtEstado.setDisabled(false);
		txtHora.setValue(selectedPartido.getHora());
		txtHora.setDisabled(false);
		txtTiempototal.setValue(selectedPartido.getTiempototal());
		txtTiempototal.setDisabled(false);
		txtArbiId_Arbitro.setValue(selectedPartido.getArbiId_Arbitro());
		txtArbiId_Arbitro.setDisabled(false);
		txtCancId_Cancha.setValue(selectedPartido.getCancId_Cancha());
		txtCancId_Cancha.setDisabled(false);
		txtDitoId_Directortorneo.setValue(selectedPartido.getDitoId_Directortorneo());
		txtDitoId_Directortorneo.setDisabled(false);
		txtJugaId_Jugador.setDisabled(false);
		txtTipaId_Tipopartido.setValue(selectedPartido.getTipaId_Tipopartido());
		txtTipaId_Tipopartido.setDisabled(false);
		txtTornId_Torneo.setValue(selectedPartido.getTornId_Torneo());
		txtTornId_Torneo.setDisabled(false);
		txtPartId.setValue(selectedPartido.getPartId());
		txtPartId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_asignar(ActionEvent evt) {
		selectedPartido = (PartidoDTO) (evt.getComponent().getAttributes().get("selectedPartido"));
		setShowDialogArbitro(true);

		return "";
	}
	
	public String action_asignarPuntos(ActionEvent evt) {
		selectedPartido = (PartidoDTO) (evt.getComponent().getAttributes().get("selectedPartido"));
		setShowDialogSet(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedPartido == null) && (entity == null)) {
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
			entity = new Partido();

			Integer partId = FacesUtils.checkInteger(txtPartId);

			entity.setEstado(FacesUtils.checkString(txtEstado));
			entity.setHora(FacesUtils.checkDate(txtHora));
			entity.setPartId(partId);
			entity.setTiempototal(FacesUtils.checkInteger(txtTiempototal));
			entity.setArbitro((FacesUtils.checkInteger(txtArbiId_Arbitro) != null)
					? businessDelegatorView.getArbitro(FacesUtils.checkInteger(txtArbiId_Arbitro)) : null);
			entity.setCancha((FacesUtils.checkInteger(txtCancId_Cancha) != null)
					? businessDelegatorView.getCancha(FacesUtils.checkInteger(txtCancId_Cancha)) : null);
			entity.setDirectortorneo((FacesUtils.checkInteger(txtDitoId_Directortorneo) != null)
					? businessDelegatorView.getDirectortorneo(FacesUtils.checkInteger(txtDitoId_Directortorneo))
					: null);
			entity.setTipopartido((FacesUtils.checkInteger(txtTipaId_Tipopartido) != null)
					? businessDelegatorView.getTipopartido(FacesUtils.checkInteger(txtTipaId_Tipopartido)) : null);
			entity.setTorneo((FacesUtils.checkInteger(txtTornId_Torneo) != null)
					? businessDelegatorView.getTorneo(FacesUtils.checkInteger(txtTornId_Torneo)) : null);
			businessDelegatorView.savePartido(entity);
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
				Integer partId = new Integer(selectedPartido.getPartId());
				entity = businessDelegatorView.getPartido(partId);
			}

			entity.setEstado(FacesUtils.checkString(txtEstado));
			entity.setHora(FacesUtils.checkDate(txtHora));
			entity.setTiempototal(FacesUtils.checkInteger(txtTiempototal));
			entity.setArbitro((FacesUtils.checkInteger(txtArbiId_Arbitro) != null)
					? businessDelegatorView.getArbitro(FacesUtils.checkInteger(txtArbiId_Arbitro)) : null);
			entity.setCancha((FacesUtils.checkInteger(txtCancId_Cancha) != null)
					? businessDelegatorView.getCancha(FacesUtils.checkInteger(txtCancId_Cancha)) : null);
			entity.setDirectortorneo((FacesUtils.checkInteger(txtDitoId_Directortorneo) != null)
					? businessDelegatorView.getDirectortorneo(FacesUtils.checkInteger(txtDitoId_Directortorneo))
					: null);

			entity.setTipopartido((FacesUtils.checkInteger(txtTipaId_Tipopartido) != null)
					? businessDelegatorView.getTipopartido(FacesUtils.checkInteger(txtTipaId_Tipopartido)) : null);
			entity.setTorneo((FacesUtils.checkInteger(txtTornId_Torneo) != null)
					? businessDelegatorView.getTorneo(FacesUtils.checkInteger(txtTornId_Torneo)) : null);
			businessDelegatorView.updatePartido(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}
	
	
	public String action_guardarArbitro() throws Exception{
		
		try {
			
			if (somArbitros.getValue().toString().trim().equals("-1")) {
				throw new Exception("Por favor escoja un arbitro");
			}
			
			
			Partido partido = businessDelegatorView.getPartido(selectedPartido.getPartId());
			
			Arbitro arbitro = businessDelegatorView.getArbitro(new Integer(somArbitros.getValue().toString().trim()));
			
			if (arbitro != null) {
				partido.setArbitro(arbitro);
			}else {
				throw new Exception("No se encontro al arbitro");
			}
			
			businessDelegatorView.updatePartido(partido);
			data = businessDelegatorView.getDataPartido();
			setShowDialogArbitro(false);
			FacesUtils.addInfoMessage("El arbitro se asigno con éxito");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
	
	public String action_guardarSets() throws Exception {
		
		try {
			
			if (jugadorASet1.getValue() != null && jugadorASet1.getValue().toString().trim().equals("") == true) {
				throw new Exception("Ingrese el valor para el set 1 para el jugador A");
			}
			
			if (jugadorASet2.getValue() != null && jugadorASet2.getValue().toString().trim().equals("") == true) {
				throw new Exception("Ingrese el valor para el set 2 para el jugador A");
			}
			
			if (jugadorASet3.getValue() != null && jugadorASet3.getValue().toString().trim().equals("") == true) {
				throw new Exception("Ingrese el valor para el set 3 para el jugador A");
			}
			
			
			if (jugadorBSet1.getValue() != null && jugadorBSet1.getValue().toString().trim().equals("") == true) {
				throw new Exception("Ingrese el valor para el set 1 para el jugador B");
			}
			
			if (jugadorBSet2.getValue() != null && jugadorBSet2.getValue().toString().trim().equals("") == true) {
				throw new Exception("Ingrese el valor para el set 2 para el jugador B");
			}
			
			if (jugadorBSet3.getValue() != null && jugadorBSet3.getValue().toString().trim().equals("") == true) {
				throw new Exception("Ingrese el valor para el set 3 para el jugador B");
			}
			
			List<Sett> setts = selectedPartido.getSetts();
			
			int i = 1;
			
			for (Sett sett : setts) {
				if (i == 1) {
					sett.setGamejugador1(new Integer(jugadorASet1.getValue().toString().trim()));
					sett.setGamejugador2(new Integer(jugadorBSet1.getValue().toString().trim()));
					businessDelegatorView.updateSett(sett);
				}
				
				if (i == 2) {
					sett.setGamejugador1(new Integer(jugadorASet2.getValue().toString().trim()));
					sett.setGamejugador2(new Integer(jugadorBSet2.getValue().toString().trim()));
					businessDelegatorView.updateSett(sett);
				}
				
				if (i == 3) {
					sett.setGamejugador1(new Integer(jugadorASet3.getValue().toString().trim()));
					sett.setGamejugador2(new Integer(jugadorBSet3.getValue().toString().trim()));
					businessDelegatorView.updateSett(sett);
				}
				
				i++;
			}
			
			data = businessDelegatorView.getDataPartido();
			setShowDialogSet(false);
			FacesUtils.addInfoMessage("El partido ha sido actualizado");
			
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedPartido = (PartidoDTO) (evt.getComponent().getAttributes().get("selectedPartido"));

			Integer partId = new Integer(selectedPartido.getPartId());
			entity = businessDelegatorView.getPartido(partId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Integer partId = FacesUtils.checkInteger(txtPartId);
			entity = businessDelegatorView.getPartido(partId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deletePartido(entity);
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

	public String action_closeDialogArbitro() {
		setShowDialogArbitro(false);

		return "";
	}
	
	public String action_closeDialogSet() {
		setShowDialogSet(false);

		return "";
	}
	

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedPartido = (PartidoDTO) (evt.getComponent().getAttributes().get("selectedPartido"));

			Integer partId = new Integer(selectedPartido.getPartId());
			entity = businessDelegatorView.getPartido(partId);
			businessDelegatorView.deletePartido(entity);
			data.remove(selectedPartido);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Integer equiaId, String estado, Date hora, Integer partId, Integer tiempototal,
			Integer arbiId_Arbitro, Integer cancId_Cancha, Integer ditoId_Directortorneo, Integer jugaId_Jugador,
			Integer jugaId_Jugador0, Integer tipaId_Tipopartido, Integer tornId_Torneo) throws Exception {
		try {
			entity.setEstado(FacesUtils.checkString(estado));
			entity.setHora(FacesUtils.checkDate(hora));
			entity.setTiempototal(FacesUtils.checkInteger(tiempototal));
			businessDelegatorView.updatePartido(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("PartidoView").requestRender();
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

	public InputText getTxtTiempototal() {
		return txtTiempototal;
	}

	public void setTxtTiempototal(InputText txtTiempototal) {
		this.txtTiempototal = txtTiempototal;
	}

	public InputText getTxtArbiId_Arbitro() {
		return txtArbiId_Arbitro;
	}

	public void setTxtArbiId_Arbitro(InputText txtArbiId_Arbitro) {
		this.txtArbiId_Arbitro = txtArbiId_Arbitro;
	}

	public InputText getTxtCancId_Cancha() {
		return txtCancId_Cancha;
	}

	public void setTxtCancId_Cancha(InputText txtCancId_Cancha) {
		this.txtCancId_Cancha = txtCancId_Cancha;
	}

	public InputText getTxtDitoId_Directortorneo() {
		return txtDitoId_Directortorneo;
	}

	public void setTxtDitoId_Directortorneo(InputText txtDitoId_Directortorneo) {
		this.txtDitoId_Directortorneo = txtDitoId_Directortorneo;
	}

	public InputText getTxtJugaId_Jugador() {
		return txtJugaId_Jugador;
	}

	public void setTxtJugaId_Jugador(InputText txtJugaId_Jugador) {
		this.txtJugaId_Jugador = txtJugaId_Jugador;
	}

	public InputText getTxtTipaId_Tipopartido() {
		return txtTipaId_Tipopartido;
	}

	public void setTxtTipaId_Tipopartido(InputText txtTipaId_Tipopartido) {
		this.txtTipaId_Tipopartido = txtTipaId_Tipopartido;
	}

	public InputText getTxtTornId_Torneo() {
		return txtTornId_Torneo;
	}

	public void setTxtTornId_Torneo(InputText txtTornId_Torneo) {
		this.txtTornId_Torneo = txtTornId_Torneo;
	}

	public Calendar getTxtHora() {
		return txtHora;
	}

	public void setTxtHora(Calendar txtHora) {
		this.txtHora = txtHora;
	}

	public InputText getTxtPartId() {
		return txtPartId;
	}

	public void setTxtPartId(InputText txtPartId) {
		this.txtPartId = txtPartId;
	}

	public List<PartidoDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataPartido();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PartidoDTO> partidoDTO) {
		this.data = partidoDTO;
	}

	public PartidoDTO getSelectedPartido() {
		return selectedPartido;
	}

	public void setSelectedPartido(PartidoDTO partido) {
		this.selectedPartido = partido;
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

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public boolean isShowDialogArbitro() {
		return showDialogArbitro;
	}

	public void setShowDialogArbitro(boolean showDialogArbitro) {
		this.showDialogArbitro = showDialogArbitro;
	}

	public SelectOneMenu getSomArbitros() {
		return somArbitros;
	}

	public void setSomArbitros(SelectOneMenu somArbitros) {
		this.somArbitros = somArbitros;
	}

	public List<SelectItem> getLosArbitros() {
		try {
			if (losArbitros == null) {
				List<Arbitro> listArbitro = businessDelegatorView.getArbitro();
				losArbitros = new ArrayList<SelectItem>();
				for (Arbitro arbitro : listArbitro) {
					if (arbitro.getEstado().equals("A")) {
						losArbitros.add(new SelectItem(arbitro.getArbiId(), arbitro.getNombre()));
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losArbitros;
	}

	public void setLosArbitros(List<SelectItem> losArbitros) {
		this.losArbitros = losArbitros;
	}

	public boolean isShowDialogSet() {
		return showDialogSet;
	}

	public void setShowDialogSet(boolean showDialogSet) {
		this.showDialogSet = showDialogSet;
	}

	public InputText getJugadorASet1() {
		return jugadorASet1;
	}

	public void setJugadorASet1(InputText jugadorASet1) {
		this.jugadorASet1 = jugadorASet1;
	}

	public InputText getJugadorASet2() {
		return jugadorASet2;
	}

	public void setJugadorASet2(InputText jugadorASet2) {
		this.jugadorASet2 = jugadorASet2;
	}

	public InputText getJugadorASet3() {
		return jugadorASet3;
	}

	public void setJugadorASet3(InputText jugadorASet3) {
		this.jugadorASet3 = jugadorASet3;
	}

	public InputText getJugadorBSet1() {
		return jugadorBSet1;
	}

	public void setJugadorBSet1(InputText jugadorBSet1) {
		this.jugadorBSet1 = jugadorBSet1;
	}

	public InputText getJugadorBSet2() {
		return jugadorBSet2;
	}

	public void setJugadorBSet2(InputText jugadorBSet2) {
		this.jugadorBSet2 = jugadorBSet2;
	}

	public InputText getJugadorBSet3() {
		return jugadorBSet3;
	}

	public void setJugadorBSet3(InputText jugadorBSet3) {
		this.jugadorBSet3 = jugadorBSet3;
	}
}
