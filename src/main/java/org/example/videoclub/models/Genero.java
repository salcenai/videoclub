package org.example.videoclub.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "generos", schema = "videoclub")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(unique = false, nullable = false)
    private Integer prioridad;

    @Column(unique = true, nullable = false, length = 45)
    private String codigo;

    @Column(unique = true, nullable = false, length = 45)
    private String nombre;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
    private Set<GeneroPelicula> generosPelicula = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
