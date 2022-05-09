package org.example.videoclub.models.assist;

public enum EGenero {

    Accion("ACCIO", "Acción"),
    Animacion("ANIMA", "Animación"),
    Aventuras("AVENT", "Aventuras"),
    Belico("BELIC", "Bélico"),
    CienciaFiccion("CIFI", "Ciencia ficción"),
    CineNegro("CINE", "Cine negro"),
    Comedia("COMED", "Comedia"),
    Documental("DOCUM", "Documental"),
    Drama("DRAMA", "Drama"),
    Fantastico("FANTA", "Fantástico"),
    Infantil("INFAN", "Infantil"),
    Intriga("INTRI", "Intriga"),
    Musical("MUSIC", "Musical"),
    Romance("ROMAN", "Romance"),
    Terror("TERRO", "Terror"),
    Thriller("THRIL", "Thriller"),
    Western("WESTE", "Western"),
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
