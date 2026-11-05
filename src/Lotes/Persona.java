package Lotes;

import Reclamos.ComentarioReclamo;
import Reclamos.Reclamo;

import java.util.ArrayList;
import java.util.List;


public  abstract class Persona {
    private int idPersona;
    private String Nombre;
    private String apellido;
    private int dni;
    private String telefono;
    private String CorreoElectronico;

    private final List<Reclamo> reclamos = new ArrayList<>();
    private final List<ComentarioReclamo> Comentarios = new ArrayList<>();

    public Persona () {}

    public Persona(int idPersona, String nombre, String apellido, int dni, String telefono, String correoElectronico) {
        this.idPersona = idPersona;
        Nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        CorreoElectronico = correoElectronico;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public List<Reclamo> getReclamos() {
        return List.copyOf(reclamos);
    }
    public void agregarReclamo(Reclamo reclamo) {
        if ((reclamo != null) && !reclamos.contains(reclamo)) {
            reclamos.add(reclamo);
        }
    }

    public List<ComentarioReclamo> getComentarios() {
        return List.copyOf(Comentarios);
    }
    public void agregarComentario(ComentarioReclamo comentario){
        if (comentario != null && !Comentarios.contains(comentario)) {
            Comentarios.add(comentario);
        }
    }
}
