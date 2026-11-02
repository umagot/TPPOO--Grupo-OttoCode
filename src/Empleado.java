import java.util.Date;
import java.util.List;
import Enum.TipoP;

public class Empleado extends Persona {
    private int Legajo;
    private Date fechaIngreso;
    private TipoP tipo_personal;

    private List<OrdenDeMantenimiento> ordenesMantenimiento;
    private List<Reclamo> reclamosAsignados;

    public int getLegajo() {
        return Legajo;
    }

    public void setLegajo(int legajo) {
        Legajo = legajo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public TipoP getTipo_personal() {
        return tipo_personal;
    }

    public void setTipo_personal(TipoP tipo_personal) {
        this.tipo_personal = tipo_personal;
    }

    public List<OrdenDeMantenimiento> getOrdenesMantenimiento() {
        return ordenesMantenimiento;
    }

    public void setOrdenesMantenimiento(List<OrdenDeMantenimiento> ordenesMantenimiento) {
        this.ordenesMantenimiento = ordenesMantenimiento;
    }

    public List<Reclamo> getReclamosAsignados() {
        return reclamosAsignados;
    }

    public void setReclamosAsignados(List<Reclamo> reclamosAsignados) {
        this.reclamosAsignados = reclamosAsignados;
    }

    public Empleado(int idPersona, String nombre, String apellido, int DNI, int telefono, String correo_electronico, int legajo, Date fechaIngreso, TipoP tipo_personal, List<OrdenDeMantenimiento> ordenesMantenimiento, List<Reclamo> reclamosAsignados) {
        super(idPersona, nombre, apellido, DNI, telefono, correo_electronico);
        Legajo = legajo;
        this.fechaIngreso = fechaIngreso;
        this.tipo_personal = tipo_personal;
        this.ordenesMantenimiento = ordenesMantenimiento;
        this.reclamosAsignados = reclamosAsignados;
    }
}