package org.example.videoclub.models.assist;

public enum EGenero {

    Accion("Accion", "Acción"),
    Animacion("Animacion", "Animación"),
    Aventuras("Aventuras", "Aventuras"),
    Belico("Belico", "Bélico"),
    CienciaFiccion("CienciaFiccion", "Ciencia ficción"),
    CineNegro("CineNegro", "Cine negro"),
    Comedia("Comedia", "Comedia"),
    Documental("Documental", "Documental"),
    Drama("Drama", "Drama"),
    Fantastico("Fantastico", "Fantástico"),
    Infantil("Infantil", "Infantil"),
    Intriga("Intriga", "Intriga"),
    Musical("Musical", "Musical"),
    Romance("Romance", "Romance"),
    Terror("Terror", "Terror"),
    Thriller("Thriller", "Thriller"),
    Western("Western", "Western"),
    ;

    private final String codigo;
    private final String nombre;

    EGenero(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

}
