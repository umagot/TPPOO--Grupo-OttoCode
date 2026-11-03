
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Equipo {
    private int idEquipo;
    private String tipo;
    private String sector; // Calles internas, accesos, espacios comunes
    private LocalDate fecha;
    private EstadoEquipo estado;
    private LocalDateTime fechaInicioInactividad;
    private List<Falla> fallas;
    private List<OrdenMantenimiento> ordenes;

    public Equipo(int idEquipo, String tipo, String sector, LocalDate fechaInstalacion) {
        this.idEquipo = idEquipo;
        this.tipo = tipo;
        this.sector = sector;
        this.fecha = fechaInstalacion;
        this.estado = EstadoEquipo.OPERATIVO;
        this.fallas = new ArrayList<>();
        this.ordenes = new ArrayList<>();
    }

    public abstract Prioridad calcularPrioridadMantenimiento(int fallasActivasEnSector);

    public abstract boolean requiereAlertaAdministrador(LocalDateTime fechaActual);

    public Falla registrarFalla(int idFalla, String descripcion, LocalDateTime fechaRegistro) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción de la falla no puede estar vacía.");
        }
        Falla nuevaFalla = new Falla(idFalla, this.idEquipo, fechaRegistro, descripcion);
        this.fallas.add(nuevaFalla);
        if (this.estado == EstadoEquipo.OPERATIVO) {
            this.fechaInicioInactividad = fechaRegistro;
        }
        this.estado = EstadoEquipo.CON_FALLA;
        return nuevaFalla;
    }

    public void marcarEnReparacion() {
        this.estado = EstadoEquipo.EN_REPARACION;
    }

    public void marcarOperativo() {
        this.estado = EstadoEquipo.OPERATIVO;
        this.fechaInicioInactividad = null;
    }

    public void marcarFueraDeServicio() {
        if (this.fechaInicioInactividad == null) {
            this.fechaInicioInactividad = LocalDateTime.now();
        }
        this.estado = EstadoEquipo.FUERA_DE_SERVICIO;
    }

    public void agregarOrden(OrdenMantenimiento orden) {
        this.ordenes.add(orden);
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSector() {
        return sector;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoEquipo getEstado() {
        return estado;
    }

    public LocalDateTime getFechaInicioInactividad() {
        return fechaInicioInactividad;
    }

    public List<Falla> getFallas() {
        return fallas;
    }

    public List<OrdenMantenimiento> getOrdenes() {
        return ordenes;
    }
}