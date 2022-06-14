package org.example.videoclub.models.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PeliculaCompletaDTO {

    private Long id;
    private String tituloCompacto;
    private String titulo;
    private String codigoPais;
    private String pais;
    private Integer anio;
    private Integer duracion;
    private String sinopsis;
    private Integer puntuacionMedia;
    private Long numVotos;

    private Map<String, String> mapGeneros;

    private CriticaDTO criticaPersonal;

    private List<CriticaDTO> lstCriticas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloCompacto() {
        return tituloCompacto;
    }

    public void setTituloCompacto(String tituloCompacto) {
        this.tituloCompacto = tituloCompacto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public Integer getPuntuacionMedia() {
        return puntuacionMedia;
    }

    public void setPuntuacionMedia(Integer puntuacionMedia) {
        this.puntuacionMedia = puntuacionMedia;
    }

    public Long getNumVotos() {
        return numVotos;
    }

    public void setNumVotos(Long numVotos) {
        this.numVotos = numVotos;
    }

    public Map<String, String> getMapGeneros() {
        return mapGeneros;
    }

    public void setMapGeneros(Map<String, String> mapGeneros) {
        this.mapGeneros = mapGeneros;
    }

    public CriticaDTO getCriticaPersonal() {
        return criticaPersonal;
    }

    public void setCriticaPersonal(CriticaDTO criticaPersonal) {
        this.criticaPersonal = criticaPersonal;
    }

    public List<CriticaDTO> getLstCriticas() {
        return lstCriticas;
    }

    public void setLstCriticas(List<CriticaDTO> lstCriticas) {
        this.lstCriticas = lstCriticas;
    }

    @Override
    public String toString() {
        return "PeliculaCompletaDTO{" +
                "id=" + id +
                ", tituloCompacto='" + tituloCompacto + '\'' +
                ", titulo='" + titulo + '\'' +
                ", codigoPais='" + codigoPais + '\'' +
                ", pais='" + pais + '\'' +
                ", anio=" + anio +
                ", duracion=" + duracion +
                ", sinopsis='" + sinopsis + '\'' +
                ", puntuacionMedia=" + puntuacionMedia +
                ", numVotos=" + numVotos +
                ", mapGeneros=" + mapGeneros +
                ", criticaPersonal=" + criticaPersonal +
                ", lstCriticas=" + lstCriticas +
                '}';
    }

}
