package com.wimbledon.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wimbledon.modelo.Sett;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;
import java.util.List;


/**
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class PartidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoDTO.class);
    private String estado;
    private Date hora;
    private Integer partId;
    private Integer tiempototal;
    private Integer arbiId_Arbitro;
    private Integer cancId_Cancha;
    private Integer ditoId_Directortorneo;
    private Integer jugaId_JugadorA;
    private Integer jugaId_JugadorB;
    private Integer tipaId_Tipopartido;
    private Integer tornId_Torneo;
    private String nombreJugadorA;
    private String nombreJugadorB;
    
    private List<Sett> setts;
    private String nombreArbitro;
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getTiempototal() {
        return tiempototal;
    }

    public void setTiempototal(Integer tiempototal) {
        this.tiempototal = tiempototal;
    }

    public Integer getArbiId_Arbitro() {
        return arbiId_Arbitro;
    }

    public void setArbiId_Arbitro(Integer arbiId_Arbitro) {
        this.arbiId_Arbitro = arbiId_Arbitro;
    }

    public Integer getCancId_Cancha() {
        return cancId_Cancha;
    }

    public void setCancId_Cancha(Integer cancId_Cancha) {
        this.cancId_Cancha = cancId_Cancha;
    }

    public Integer getDitoId_Directortorneo() {
        return ditoId_Directortorneo;
    }

    public void setDitoId_Directortorneo(Integer ditoId_Directortorneo) {
        this.ditoId_Directortorneo = ditoId_Directortorneo;
    }   

    public Integer getTipaId_Tipopartido() {
        return tipaId_Tipopartido;
    }

    public void setTipaId_Tipopartido(Integer tipaId_Tipopartido) {
        this.tipaId_Tipopartido = tipaId_Tipopartido;
    }

    public Integer getTornId_Torneo() {
        return tornId_Torneo;
    }

    public void setTornId_Torneo(Integer tornId_Torneo) {
        this.tornId_Torneo = tornId_Torneo;
    }

	public Integer getJugaId_JugadorA() {
		return jugaId_JugadorA;
	}

	public void setJugaId_JugadorA(Integer jugaId_JugadorA) {
		this.jugaId_JugadorA = jugaId_JugadorA;
	}

	public Integer getJugaId_JugadorB() {
		return jugaId_JugadorB;
	}

	public void setJugaId_JugadorB(Integer jugaId_JugadorB) {
		this.jugaId_JugadorB = jugaId_JugadorB;
	}

	public String getNombreJugadorA() {
		return nombreJugadorA;
	}

	public void setNombreJugadorA(String nombreJugadorA) {
		this.nombreJugadorA = nombreJugadorA;
	}

	public String getNombreJugadorB() {
		return nombreJugadorB;
	}

	public void setNombreJugadorB(String nombreJugadorB) {
		this.nombreJugadorB = nombreJugadorB;
	}

	public List<Sett> getSetts() {
		return setts;
	}

	public void setSetts(List<Sett> setts) {
		this.setts = setts;
	}

	public String getNombreArbitro() {
		return nombreArbitro;
	}

	public void setNombreArbitro(String nombreArbitro) {
		this.nombreArbitro = nombreArbitro;
	}
}
