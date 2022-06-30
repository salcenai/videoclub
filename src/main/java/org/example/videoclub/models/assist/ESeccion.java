package org.example.videoclub.models.assist;

public enum ESeccion {

    Direccion("Dirección"),
    Guion("Guion"),
    BandaSonora("Banda sonora"),
    Fotografia("Fotografía"),
    Produccion("Producción"),
    Reparto("Reparto"),
    ;

    private final String nombre;

    ESeccion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }



}
