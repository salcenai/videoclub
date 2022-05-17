package org.example.videoclub.models.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginaPeliculasMiniaturaDTO {

    private List<PeliculaMiniaturaDTO> peliculas = new ArrayList<>();
    private Integer paginaActual;
    private Integer totalPaginas;
    private Long totalPeliculas;

    public List<PeliculaMiniaturaDTO> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<PeliculaMiniaturaDTO> peliculas) {
        this.peliculas = peliculas;
    }

    public Integer getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(Integer paginaActual) {
        this.paginaActual = paginaActual;
    }

    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public Long getTotalPeliculas() {
        return totalPeliculas;
    }

    public void setTotalPeliculas(Long totalPeliculas) {
        this.totalPeliculas = totalPeliculas;
    }

}
