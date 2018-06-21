package com.dona.Logica;

public class Persona {

    private int id, edad;
    private String nombres, apellidos, profesion,numAfiliacion;
    private boolean estado;

    public Persona(int id, int edad, String nombres, String apellidos, String profesion, String numAfiliacion, boolean estado) {
        this.id = id;
        this.edad = edad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.profesion = profesion;
        this.numAfiliacion = numAfiliacion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getProfesion() {
        return profesion;
    }

    public String getNumAfiliacion() {
        return numAfiliacion;
    }

    public boolean getEstado() {
        return estado;
    }
}
