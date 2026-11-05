package Reclamos;

import Lotes.Persona;
import java.util.Date;

public class ComentarioReclamo {
    private int idComentario;
    private int idReclamo;
    private int idPersona;
    private Date fecha;
    private String Comentario;
    private Reclamo reclamo;
    private Persona persona;

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ComentarioReclamo(int idComentario, int idReclamo, int idPersona, Date fecha, String comentario, Reclamo reclamo, Persona persona) {
        this.idComentario = idComentario;
        this.idReclamo = idReclamo;
        this.idPersona = idPersona;
        this.fecha = fecha;
        Comentario = comentario;
        this.reclamo = reclamo;
        this.persona = persona;
    }
}