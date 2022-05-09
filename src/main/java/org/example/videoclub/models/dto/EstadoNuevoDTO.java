package org.example.videoclub.models.dto;

public class EstadoNuevoDTO {

    private Long idPelicula;
    private String codigoTipoEstado;
    private Integer puntuacion;
    private String critica;

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getCodigoTipoEstado() {
        return codigoTipoEstado;
    }

    public void setCodigoTipoEstado(String codigoTipoEstado) {
        this.codigoTipoEstado = codigoTipoEstado;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }

    @Override
    public String toString() {
        return "EstadoNuevoDTO{" +
                "idPelicula=" + idPelicula +
                ", codigoTipoEstado='" + codigoTipoEstado + '\'' +
                ", puntuacion=" + puntuacion +
                ", critica='" + critica + '\'' +
                '}';
    }

}
