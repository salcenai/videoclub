package org.example.videoclub.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "estados", schema = "videoclub")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JoinColumn(name = "id_pelicula", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Pelicula pelicula;

    @JoinColumn(name = "id_usuario", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JoinColumn(name = "id_tipo_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoEstado tipoEstado;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "critica")
    private String critica;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoEstado getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(TipoEstado tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }

}
