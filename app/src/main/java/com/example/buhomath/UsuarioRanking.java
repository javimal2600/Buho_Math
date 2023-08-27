package com.example.buhomath;

public class UsuarioRanking {
    private String nombre;
    private Integer puntaje;

    public UsuarioRanking(String nombre, Integer puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPuntaje() {
        return puntaje;
    }
}
