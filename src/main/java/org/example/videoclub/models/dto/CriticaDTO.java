package org.example.videoclub.models.dto;

import java.util.Map;

public class CriticaDTO {

    private Long id;
    private String nombreUsuario;
    private Integer puntuacion;

    private String codigoTipoEstado;
    private String fecha;
    private String critica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCodigoTipoEstado() {
        return codigoTipoEstado;
    }

    public void setCodigoTipoEstado(String codigoTipoEstado) {
        this.codigoTipoEstado = codigoTipoEstado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }

}
