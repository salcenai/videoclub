package org.example.videoclub.models;

import javax.persistence.*;

@Entity
@Table(name = "equipo_pelicula", schema = "videoclub")
public class EquipoPelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JoinColumn(name = "id_pelicula", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Pelicula pelicula;

    @JoinColumn(name = "id_seccion", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Seccion seccion;

    @JoinColumn(name = "id_equipo", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo equipo;

    @Column(name = "notas")
    private String notas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

}
