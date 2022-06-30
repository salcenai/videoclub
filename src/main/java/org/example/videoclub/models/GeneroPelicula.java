package org.example.videoclub.models;

import javax.persistence.*;

@Entity
@Table(name = "generos_pelicula", schema = "videoclub")
public class GeneroPelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer orden;

    @JoinColumn(name = "id_pelicula", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Pelicula pelicula;

    @JoinColumn(name = "id_genero", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Genero genero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

}
