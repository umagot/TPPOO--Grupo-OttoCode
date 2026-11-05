package Visitas_Y_Accesos;

import java.util.Date;

public class Registros {
    private int idAcceso;
    private int idAutorizacion;
    private int idGuardia;
    private Date horaingreso;
    private Date horaSalida;
    private AutorizacionVisita autorizacionVisita;

    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public int getIdAutorizacion() {
        return idAutorizacion;
    }

    public void setIdAutorizacion(int idAutorizacion) {
        this.idAutorizacion = idAutorizacion;
    }

    public int getIdGuardia() {
        return idGuardia;
    }

    public void setIdGuardia(int idGuardia) {
        this.idGuardia = idGuardia;
    }

    public Date getHoraingreso() {
        return horaingreso;
    }

    public void setHoraingreso(Date horaingreso) {
        this.horaingreso = horaingreso;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public AutorizacionVisita getAutorizacionVisita() {
        return autorizacionVisita;
    }

    public void setAutorizacionVisita(AutorizacionVisita autorizacionVisita) {
        this.autorizacionVisita = autorizacionVisita;
    }


    public void registrarIngreso(Date horaIngreso) {
    if (horaIngreso == null) {
        throw new IllegalArgumentException("La hora de ingreso no puede ser nula");
    }

    if (this.horaingreso != null) {
        throw new IllegalStateException("El ingreso ya fue registrado");
    }

    this.horaingreso = horaIngreso;
}
    public void registrarSalida(Date horaSalida) {
    if (horaSalida == null) {
        throw new IllegalArgumentException("La hora de salida no puede ser nula");
    }

    if (this.horaingreso == null) {
        throw new IllegalStateException("Primero debe registrarse el ingreso");
    }

    if (horaSalida.before(this.horaingreso)) {
        throw new IllegalArgumentException("La salida no puede ser anterior al ingreso");
    }

    if (this.horaSalida != null) {
        throw new IllegalStateException("La salida ya fue registrada");
    }

    this.horaSalida = horaSalida;
}



    

    public Registros(int idAcceso, int idAutorizacion, int idGuardia, Date horaingreso, Date horaSalida, AutorizacionVisita autorizacionVisita) {
        this.idAcceso = idAcceso;
        this.idAutorizacion = idAutorizacion;
        this.idGuardia = idGuardia;
        this.horaingreso = horaingreso;
        this.horaSalida = horaSalida;
        this.autorizacionVisita = autorizacionVisita;
    }
}
