package Visitas_Y_Accesos;

import java.time.LocalDateTime;
import java.util.List;
import Lotes.Lote;
import Lotes.Residente;

public class AutorizacionVisita {
    private int idAutorizacion;
    private int idLote;
    private int idResidente;
    private String nombre;
    private int DNI;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private boolean esProveedor;
    private Lote lote;
    private Residente residente;
    private List<Registros> registros;

    public int getIdAutorizacion() {
        return idAutorizacion;
    }

    public void setIdAutorizacion(int idAutorizacion) {
        this.idAutorizacion = idAutorizacion;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(int idResidente) {
        this.idResidente = idResidente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaingreso) {
        this.fechaHoraInicio = fechaingreso;
    }
    public LocalDateTime getFechaHoraFin() {
    return fechaHoraFin;
    }

public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
    this.fechaHoraFin = fechaHoraFin;
    }
    
    public boolean isEsProveedor() {
        return esProveedor;
    }

    public void setEsProveedor(boolean esProveedor) {
        this.esProveedor = esProveedor;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public List<Registros> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registros> registros) {
        this.registros = registros;
    }

    public AutorizacionVisita(int idAutorizacion, int idLote, int idResidente, String nombre, int DNI, LocalDateTime fechaingreso, boolean esProveedor, Lote lote, Residente residente, List<Registros> registros) {
        this.idAutorizacion = idAutorizacion;
        this.idLote = idLote;
        this.idResidente = idResidente;
        this.nombre = nombre;
        this.DNI = DNI;
        this.fechaHoraInicio = fechaingreso;
        this.esProveedor = esProveedor;
        this.lote = lote;
        this.residente = residente;
        this.registros = registros;
    }
}
