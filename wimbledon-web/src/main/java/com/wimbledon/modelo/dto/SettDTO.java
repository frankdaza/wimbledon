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
public class SettDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SettDTO.class);
    private String estado;
    private Integer gamejugador1;
    private Integer gamejugador2;
    private Integer setId;
    private Double tiempo;
    private Integer partId_Partido;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getGamejugador1() {
        return gamejugador1;
    }

    public void setGamejugador1(Integer gamejugador1) {
        this.gamejugador1 = gamejugador1;
    }

    public Integer getGamejugador2() {
        return gamejugador2;
    }

    public void setGamejugador2(Integer gamejugador2) {
        this.gamejugador2 = gamejugador2;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Double getTiempo() {
        return tiempo;
    }

    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getPartId_Partido() {
        return partId_Partido;
    }

    public void setPartId_Partido(Integer partId_Partido) {
        this.partId_Partido = partId_Partido;
    }
}
