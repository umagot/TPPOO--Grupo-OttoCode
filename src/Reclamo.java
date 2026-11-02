import java.util.Date;
import java.util.List;
import Enum.TipoP;

public class Reclamo {
    private int idReclamo;
    private int idPersona;
    private Date fecha;
    private String descripcion;
    private TipoP tipo;
    private int idLote;
    private int idEmpleado;
    private Persona persona;
    private Lote lote;
    private Empleado empleado;
    private List<HistorialEstadoReclamo> historial;
    private List<ComentarioReclamo> comentarios;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoP getTipo() {
        return tipo;
    }

    public void setTipo(TipoP tipo) {
        this.tipo = tipo;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<HistorialEstadoReclamo> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialEstadoReclamo> historial) {
        this.historial = historial;
    }

    public List<ComentarioReclamo> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioReclamo> comentarios) {
        this.comentarios = comentarios;
    }

    public Reclamo(int idReclamo, int idPersona, Date fecha, String descripcion, TipoP tipo, int idLote, int idEmpleado, Persona persona, Lote lote, Empleado empleado, List<HistorialEstadoReclamo> historial, List<ComentarioReclamo> comentarios) {
        this.idReclamo = idReclamo;
        this.idPersona = idPersona;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.idLote = idLote;
        this.idEmpleado = idEmpleado;
        this.persona = persona;
        this.lote = lote;
        this.empleado = empleado;
        this.historial = historial;
        this.comentarios = comentarios;
    }
}