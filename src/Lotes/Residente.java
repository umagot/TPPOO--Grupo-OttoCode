package Lotes;

import java.io.Serializable;
import java.util.List;
import Enum.Tipo;
import Enum.estadoR;
import Visitas_Y_Accesos.AutorizacionVisita;

import java.util.ArrayList;

public class Residente extends Persona implements Serializable {
    private int IdResidente;
    private int idLote;
    private Tipo tipoResidente;
    private Lote lote;
    private List<AutorizacionVisita> autorizacionesVisita = new ArrayList<>();

    public int getIdResidente() {
        return IdResidente;
    }

    public void setIdResidente(int idResidente) {
        IdResidente = idResidente;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public Tipo getTipoResidente() {
        return tipoResidente;
    }

    public void setTipoResidente(Tipo tipoResidente) {
        this.tipoResidente = tipoResidente;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;

        if (lote != null) {
            this.idLote = lote.getId_Lote();
        }
    }

    public List<AutorizacionVisita> getAutorizacionesVisita() {
        return List.copyOf(autorizacionesVisita);
    }

    public void agregarAutorizacion(AutorizacionVisita autorizacion) {
        if (autorizacion != null && !autorizacionesVisita.contains(autorizacion)) {
            autorizacionesVisita.add(autorizacion);
        }
    }


    public Residente(int idPersona, String nombre, String apellido, int dni, String telefono, String correoElectronico, int idResidente, Tipo tipoResidente, Lote lote) {

        super(idPersona, nombre, apellido, dni, telefono, correoElectronico);
        this.IdResidente = idResidente;
        this.tipoResidente = tipoResidente;
        this.lote = lote;
        this.idLote = lote.getId_Lote();
        this.autorizacionesVisita = new ArrayList<>();
    }
}