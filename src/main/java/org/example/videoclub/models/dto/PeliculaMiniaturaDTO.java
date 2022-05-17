package org.example.videoclub.models.dto;

public class PeliculaMiniaturaDTO {

    private Long id;
    private String tituloCompacto;
    private String titulo;
    private Integer anio;
    private Integer puntuacionMedia;
    private Integer puntuacionPersonal;
    private String fecha;

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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getPuntuacionMedia() {
        return puntuacionMedia;
    }

    public void setPuntuacionMedia(Integer puntuacionMedia) {
        this.puntuacionMedia = puntuacionMedia;
    }

    public Integer getPuntuacionPersonal() {
        return puntuacionPersonal;
    }

    public void setPuntuacionPersonal(Integer puntuacionPersonal) {
        this.puntuacionPersonal = puntuacionPersonal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
