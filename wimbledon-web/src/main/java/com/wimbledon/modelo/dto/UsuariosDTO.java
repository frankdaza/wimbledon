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
public class UsuariosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuariosDTO.class);
    private String login;
    private String password;
    private Integer usuaId;
    private Integer arbiId_Arbitro;
    private Integer ditoId_Directortorneo;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public Integer getArbiId_Arbitro() {
        return arbiId_Arbitro;
    }

    public void setArbiId_Arbitro(Integer arbiId_Arbitro) {
        this.arbiId_Arbitro = arbiId_Arbitro;
    }

    public Integer getDitoId_Directortorneo() {
        return ditoId_Directortorneo;
    }

    public void setDitoId_Directortorneo(Integer ditoId_Directortorneo) {
        this.ditoId_Directortorneo = ditoId_Directortorneo;
    }
}
