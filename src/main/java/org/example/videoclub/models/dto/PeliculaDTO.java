package org.example.videoclub.models.dto;

public class PeliculaDTO {

    private Long id;
    private String codigoPais;
    private String titulo;
    private Integer anio;
    private Integer duracion;

    public PeliculaDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
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

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "PeliculaDTO{" +
                "id=" + id +
                ", codigoPais='" + codigoPais + '\'' +
                ", titulo='" + titulo + '\'' +
                ", anio=" + anio +
                ", duracion=" + duracion +
                '}';
    }

}
