import java.util.Date;

public class OrdenDeMantenimiento {
    private int idOrden;
    private int idEquipo;
    private int idEmpleado;
    private int prioridad;
    private Date fechaApertura;
    private String Descripcion;
    private Date fechaCierre;
    private Equipo equipo;
    private Empleado empleado;
    private Falla falla;

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

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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

    public OrdenDeMantenimiento(int idOrden, int idEquipo, int idEmpleado, int prioridad, Date fechaApertura, String descripcion, Date fechaCierre, Equipo equipo, Empleado empleado, Falla falla) {
        this.idOrden = idOrden;
        this.idEquipo = idEquipo;
        this.idEmpleado = idEmpleado;
        this.prioridad = prioridad;
        this.fechaApertura = fechaApertura;
        Descripcion = descripcion;
        this.fechaCierre = fechaCierre;
        this.equipo = equipo;
        this.empleado = empleado;
        this.falla = falla;
    }
}