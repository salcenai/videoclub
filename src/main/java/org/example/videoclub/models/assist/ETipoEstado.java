package org.example.videoclub.models.assist;

public enum ETipoEstado {

    Ninguno("NINGUNO", "No la he visto"),
    Pendiente("PENDIENTE", "¡Quiero verla!"),
    Vista("VISTA", "¡Ya la he visto!"),
    ;


    private final String codigo;
    private final String nombre;

    ETipoEstado(String codigo, String nombre) {
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
