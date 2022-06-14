package org.example.videoclub.models.dto;

import java.util.List;

public class PeliculaNuevaDTO {

    private String titulo;
    private String tituloCompacto;
    private String codigoPais;
    private Integer anio;
    private Integer duracion;
    private String sinopsis;
    private List<String> lstGeneros;

    public String getTitulo() {
        return titulo;
    }

    public String getTituloCompacto() {
        return tituloCompacto;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public Integer getAnio() {
        return anio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public List<String> getLstGeneros() {
        return lstGeneros;
    }

    @Override
    public String toString() {
        return "PeliculaNuevaDTO{" +
                "titulo='" + titulo + '\'' +
                ", tituloCompacto='" + tituloCompacto + '\'' +
                ", codigoPais='" + codigoPais + '\'' +
                ", anio=" + anio +
                ", duracion=" + duracion +
                ", sinopsis='" + sinopsis + '\'' +
                ", lstGeneros=" + lstGeneros +
                '}';
    }

}
