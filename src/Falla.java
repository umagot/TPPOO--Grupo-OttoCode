import java.util.Date;

public class Falla {
    private int idFalla;
    private int idEquipo;
    private Date fechaRegistro;
    private String descripcion;
    private Equipo equipo;
    private OrdenDeMantenimiento ordenMantenimiento;

    public int getIdFalla() {
        return idFalla;
    }

    public void setIdFalla(int idFalla) {
        this.idFalla = idFalla;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public OrdenDeMantenimiento getOrdenMantenimiento() {
        return ordenMantenimiento;
    }

    public void setOrdenMantenimiento(OrdenDeMantenimiento ordenMantenimiento) {
        this.ordenMantenimiento = ordenMantenimiento;
    }

    public Falla(int idFalla, int idEquipo, Date fechaRegistro, String descripcion, Equipo equipo, OrdenDeMantenimiento ordenMantenimiento) {
        this.idFalla = idFalla;
        this.idEquipo = idEquipo;
        this.fechaRegistro = fechaRegistro;
        this.descripcion = descripcion;
        this.equipo = equipo;
        this.ordenMantenimiento = ordenMantenimiento;
    }
}