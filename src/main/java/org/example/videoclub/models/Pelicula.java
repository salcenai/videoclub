package org.example.videoclub.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JoinColumn(name = "id_pais", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Pais pais;

    @Column(name = "titulo_compacto", nullable = false, unique = true)
    private String tituloCompacto;

    @Column(nullable = false)
    private String titulo;

    @Column
    private Integer anio;

    @Column
    private Integer duracion;

    @Column
    private String sinopsis;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "puntuacion_media")
    private Integer puntuacionMedia;

    @Column(name = "num_votos")
    private Long numVotos;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estado> estados;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GeneroPelicula> generoPeliculas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
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

}
