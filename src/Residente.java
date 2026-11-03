import java.util.List;
import Enum.Tipo;
import Enum.estadoR;

public class Residente extends Persona {
    private int IdResidente;
    private int idLote;
    private Tipo tipoResidente;
    private estadoR estadoReclamo;
    private Lote lote;
    private List<AutorizacionVisita> autorizacionesVisita;

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

    public estadoR getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(estadoR estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public List<AutorizacionVisita> getAutorizacionesVisita() {
        return autorizacionesVisita;
    }

    public void setAutorizacionesVisita(List<AutorizacionVisita> autorizacionesVisita) {
        this.autorizacionesVisita = autorizacionesVisita;
    }

    public Residente(int idResidente, int idLote, Tipo tipoResidente, estadoR estadoReclamo, Lote lote, List<AutorizacionVisita> autorizacionesVisita) {
        IdResidente = idResidente;
        this.idLote = idLote;
        this.tipoResidente = tipoResidente;
        this.estadoReclamo = estadoReclamo;
        this.lote = lote;
        this.autorizacionesVisita = autorizacionesVisita;
    }

    public Residente(int idPersona, String nombre, String apellido, int dni, String telefono, String correoElectronico, int idResidente, int idLote, Tipo tipoResidente, estadoR estadoReclamo, Lote lote, List<AutorizacionVisita> autorizacionesVisita) {
        super(idPersona, nombre, apellido, dni, telefono, correoElectronico);
        IdResidente = idResidente;
        this.idLote = idLote;
        this.tipoResidente = tipoResidente;
        this.estadoReclamo = estadoReclamo;
        this.lote = lote;
        this.autorizacionesVisita = autorizacionesVisita;
    }
}