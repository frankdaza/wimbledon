package com.wimbledon.modelo;
// Generated Nov 17, 2016 8:50:16 PM by Hibernate Tools 4.3.1.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Cancha generated by hbm2java
 */
public class Cancha  implements java.io.Serializable {


     private Integer cancId;
     private Integer numero;
     private String mantenimiento;
     private String estado;
     private Set<Partido> partidos = new HashSet<Partido>(0);

    public Cancha() {
    }

	
    public Cancha(Integer cancId, Integer numero, String mantenimiento, String estado) {
        this.cancId = cancId;
        this.numero = numero;
        this.mantenimiento = mantenimiento;
        this.estado = estado;
    }
    public Cancha(Integer cancId, Integer numero, String mantenimiento, String estado, Set<Partido> partidos) {
       this.cancId = cancId;
       this.numero = numero;
       this.mantenimiento = mantenimiento;
       this.estado = estado;
       this.partidos = partidos;
    }
   
    public Integer getCancId() {
        return this.cancId;
    }
    
    public void setCancId(Integer cancId) {
        this.cancId = cancId;
    }
    public Integer getNumero() {
        return this.numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getMantenimiento() {
        return this.mantenimiento;
    }
    
    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set<Partido> getPartidos() {
        return this.partidos;
    }
    
    public void setPartidos(Set<Partido> partidos) {
        this.partidos = partidos;
    }




}


