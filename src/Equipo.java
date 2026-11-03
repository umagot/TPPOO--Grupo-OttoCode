import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Enum.Estado;
import Enum.Prioridad;

public abstract class Equipo {
    private int idEquipo;
    private String tipo;
    private String sector; // Calles internas, accesos, espacios comunes
    private LocalDate fecha;
    private Estado estado;
    private LocalDateTime fechaInicioInactividad;
    private List<Falla> fallas;
    private List<OrdenMantenimiento> ordenes;

    public Equipo(int idEquipo, String tipo, String sector, LocalDate fechaInstalacion) {
        this.idEquipo = idEquipo;
        this.tipo = tipo;
        this.sector = sector;
        this.fecha = fechaInstalacion;
        this.estado = Estado.ACTIVO;
        this.fallas = new ArrayList<>();
        this.ordenes = new ArrayList<>();
    }

    public abstract Prioridad calcularPrioridadMantenimiento(int fallasActivasEnSector);

    public abstract boolean requiereAlertaAdministrador(LocalDateTime fechaActual);

    public Falla registrarFalla(int idFalla, String descripcion, LocalDateTime fechaRegistro) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción de la falla no puede estar vacía.");
        }

        Falla nuevaFalla = new Falla(idFalla, this, fechaRegistro, descripcion);
        this.fallas.add(nuevaFalla);

        if (this.estado == Estado.ACTIVO) {
            this.fechaInicioInactividad = fechaRegistro;
        }
        this.estado = Estado.FUERA_DE_SERVICIO;
        return nuevaFalla;
    }

    public void marcarEnReparacion() {
        this.estado = Estado.EN_REPARACION;
    }

    public void marcarOperativo() {
        this.estado = Estado.ACTIVO;
        this.fechaInicioInactividad = null;
    }

    public void marcarFueraDeServicio() {
        if (this.fechaInicioInactividad == null) {
            this.fechaInicioInactividad = LocalDateTime.now();
        }
        this.estado = Estado.FUERA_DE_SERVICIO;
    }

    public void agregarOrden(OrdenMantenimiento orden) {
        if (orden != null && !ordenes.contains(orden)) {
            this.ordenes.add(orden);
        }
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

    public Estado getEstado() {
        return estado;
    }

    public LocalDateTime getFechaInicioInactividad() {
        return fechaInicioInactividad;
    }

    public List<Falla> getFallas() {
        return List.copyOf(fallas);
    }

    public List<OrdenMantenimiento> getOrdenes() {
        return List.copyOf(ordenes);
    }
}