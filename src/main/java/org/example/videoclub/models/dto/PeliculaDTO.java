package org.example.videoclub.models.dto;

import java.util.List;

public class PeliculaDTO {

    private Long id;
    private String titulo;
    private String tituloCompacto;
    private String codigoPais;
    private Integer anio;
    private Integer duracion;
    private String sinopsis;
    private List<String> lstGeneros;
    private List<String> lstDireccion;
    private List<String> lstGuion;
    private List<String> lstBandaSonora;
    private List<String> lstFotografia;
    private List<String> lstProduccion;
    private List<String> lstReparto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloCompacto() {
        return tituloCompacto;
    }

    public void setTituloCompacto(String tituloCompacto) {
        this.tituloCompacto = tituloCompacto;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<String> getLstGeneros() {
        return lstGeneros;
    }

    public void setLstGeneros(List<String> lstGeneros) {
        this.lstGeneros = lstGeneros;
    }

    public List<String> getLstDireccion() {
        return lstDireccion;
    }

    public void setLstDireccion(List<String> lstDireccion) {
        this.lstDireccion = lstDireccion;
    }

    public List<String> getLstGuion() {
        return lstGuion;
    }

    public void setLstGuion(List<String> lstGuion) {
        this.lstGuion = lstGuion;
    }

    public List<String> getLstBandaSonora() {
        return lstBandaSonora;
    }

    public void setLstBandaSonora(List<String> lstBandaSonora) {
        this.lstBandaSonora = lstBandaSonora;
    }

    public List<String> getLstFotografia() {
        return lstFotografia;
    }

    public void setLstFotografia(List<String> lstFotografia) {
        this.lstFotografia = lstFotografia;
    }

    public List<String> getLstProduccion() {
        return lstProduccion;
    }

    public void setLstProduccion(List<String> lstProduccion) {
        this.lstProduccion = lstProduccion;
    }

    public List<String> getLstReparto() {
        return lstReparto;
    }

    public void setLstReparto(List<String> lstReparto) {
        this.lstReparto = lstReparto;
    }

    @Override
    public String toString() {
        return "PeliculaDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", tituloCompacto='" + tituloCompacto + '\'' +
                ", codigoPais='" + codigoPais + '\'' +
                ", anio=" + anio +
                ", duracion=" + duracion +
                ", sinopsis='" + sinopsis + '\'' +
                ", lstGeneros=" + lstGeneros +
                ", lstDireccion=" + lstDireccion +
                ", lstGuion=" + lstGuion +
                ", lstBandaSonora=" + lstBandaSonora +
                ", lstFotografia=" + lstFotografia +
                ", lstProduccion=" + lstProduccion +
                ", lstReparto=" + lstReparto +
                '}';
    }

}
