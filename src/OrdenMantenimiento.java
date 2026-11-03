import java.util.Date;
import Enum.Prioridad;

public class OrdenMantenimiento {
    private int idOrden;
    private int idEquipo;
    private int idEmpleado;
    private Prioridad prioridad;
    private Date fechaApertura;
    private String descripcion;
    private Date fechaCierre;
    private Equipo equipo;
    private Empleado empleado;
    private Falla falla;

    public OrdenMantenimiento(int idOrden, Prioridad prioridad, Date fechaApertura, String descripcion, Equipo equipo, Empleado empleado, Falla falla) {
        this.idOrden = idOrden;
        this.idEquipo = equipo.getIdEquipo();
        this.idEmpleado = empleado.getLegajo();
        this.prioridad = prioridad;
        this.fechaApertura = fechaApertura;
        this.descripcion = descripcion;
        this.fechaCierre = fechaCierre;

        this.equipo =equipo;
        this.empleado = empleado;
        this.falla = falla;

    }



    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Falla getFalla() {
        return falla;
    }

    public void setFalla(Falla falla) {
        this.falla = falla;
    }
}