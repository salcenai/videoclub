package org.example.videoclub.models.assist;

public enum ERol {


    Admin("ADMIN"),
    User("USER"),
    Anonimo("ANONIMO"),
    ;

    private String nombre;

    public ERol valueOfByNombre(
            String nombre){

        for(ERol rol: ERol.values()){
            if(rol.getNombre().equals(nombre)){
                return rol;
            }
        }
        return Anonimo;
    }

    ERol(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
