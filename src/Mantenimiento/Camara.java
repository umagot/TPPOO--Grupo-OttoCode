package Mantenimiento;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import Enum.Estado ;
import Enum.Prioridad;

public class Camara extends Equipo {
    private String resolucion;
    private int diasGrabacion;

    public Camara(int idEquipo, String sector, LocalDate fechaInstalacion, String resolucion, int diasGrabacion) {
        super(idEquipo, "CAMARA", sector, fechaInstalacion);
        this.resolucion = resolucion;
        this.diasGrabacion = diasGrabacion;
    }

    @Override
    public Prioridad calcularPrioridadMantenimiento(int fallasActivasEnSector) {
        // Regla: Una falla en una cámara genera una orden de mantenimiento de prioridad alta
        return Prioridad.ALTA;
    }

    @Override
    public boolean requiereAlertaAdministrador(LocalDateTime fechaActual) {
        // Regla: Si una cámara está sin funcionar más de 48 horas, se informa al administrador
        if (getEstado() != Estado.ACTIVO && getFechaInicioInactividad() != null) {
            return Duration.between(getFechaInicioInactividad(), fechaActual).toHours() > 48;
        }
        return false;
    }

    public String getResolucion() { return resolucion; }
    public int getDiasGrabacion() { return diasGrabacion; }
}