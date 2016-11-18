package com.wimbledon.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class TorneoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TorneoDTO.class);
    private String estado;
    private String nombre;
    private String premio;
    private Integer tornId;
    private String nombreJugadorA;
    private String nombreJugadorB;
    private Integer jugaIdA;
    private Integer jugaIdB;
    private Integer gameJugador1;
    private Integer gameJugador2;
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public Integer getTornId() {
        return tornId;
    }

    public void setTornId(Integer tornId) {
        this.tornId = tornId;
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

	public Integer getJugaIdA() {
		return jugaIdA;
	}

	public void setJugaIdA(Integer jugaIdA) {
		this.jugaIdA = jugaIdA;
	}

	public Integer getJugaIdB() {
		return jugaIdB;
	}

	public void setJugaIdB(Integer jugaIdB) {
		this.jugaIdB = jugaIdB;
	}

	public Integer getGameJugador1() {
		return gameJugador1;
	}

	public void setGameJugador1(Integer gameJugador1) {
		this.gameJugador1 = gameJugador1;
	}

	public Integer getGameJugador2() {
		return gameJugador2;
	}

	public void setGameJugador2(Integer gameJugador2) {
		this.gameJugador2 = gameJugador2;
	}

	
}
