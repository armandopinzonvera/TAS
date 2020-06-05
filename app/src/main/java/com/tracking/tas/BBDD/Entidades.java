package com.tracking.tas.BBDD;

public class Entidades {

    public String id;
    public String nombre_muestreo;
    public String Id_transecto;
    public String grupo_organismos;

    public Entidades(String id, String nombre_muestreo, String id_transecto, String grupo_organismos) {
        this.id = id;
        this.nombre_muestreo = nombre_muestreo;
        Id_transecto = id_transecto;
        this.grupo_organismos = grupo_organismos;
    }

    public Entidades() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_muestreo() {
        return nombre_muestreo;
    }

    public void setNombre_muestreo(String nombre_muestreo) {
        this.nombre_muestreo = nombre_muestreo;
    }

    public String getId_transecto() {
        return Id_transecto;
    }

    public void setId_transecto(String id_transecto) {
        Id_transecto = id_transecto;
    }

    public String getGrupo_organismos() {
        return grupo_organismos;
    }

    public void setGrupo_organismos(String grupo_organismos) {
        this.grupo_organismos = grupo_organismos;
    }
}
