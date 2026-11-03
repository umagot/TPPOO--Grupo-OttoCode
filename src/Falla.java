

import java.time.LocalDateTime;

public class Falla {
    private int idFalla;
    private int idEquipo;
    private LocalDateTime fechaRegistro;
    private String descripcion;
    private Equipo equipo;
    private OrdenMantenimiento ordenMantenimiento;

    public Falla(int idFalla, Equipo equipo, LocalDateTime fechaRegistro, String descripcion) {
        this.idFalla = idFalla;
        this.idEquipo = equipo.getIdEquipo();
        this.fechaRegistro = fechaRegistro;
        this.descripcion = descripcion;
        this.equipo = equipo;
        this.ordenMantenimiento = null;
    }


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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
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

    public OrdenMantenimiento getOrdenMantenimiento() {
        return ordenMantenimiento;
    }

    public void setOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) {
        this.ordenMantenimiento = ordenMantenimiento;
    }
}

