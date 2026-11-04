import java.util.Date;
import java.util.List;
import Enum.Estado;
import Enum.Prioridad;

public class ControladorMantenimiento {

    public OrdenMantenimiento crearOrden(int idOrden, Date fechaApertura, String descripcion, Equipo equipo, Empleado empleado, Falla falla, List<Equipo> equipos) {

        // Validaciones
        if (equipo == null || empleado == null || falla == null || equipos == null) {
            throw new IllegalArgumentException(
                    "El equipo, el empleado, la falla y la lista de equipos son obligatorios."
            );
        }

        if (fechaApertura == null || descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException(
                    "La fecha y la descripción de la orden son obligatorias."
            );
        }

        if (falla.getEquipo() != equipo) {
            throw new IllegalArgumentException(
                    "La falla no pertenece al equipo indicado."
            );
        }

        if (falla.getOrdenMantenimiento() != null) {
            throw new IllegalArgumentException(
                    "Esta falla ya tiene una orden de mantenimiento."
            );
        }

        // Contar luminarias fuera de servicio en el mismo sector

        int fallasActivasEnSector = 0;

        if (equipo instanceof Luminaria) {
            for (Equipo e : equipos) {
                if (e instanceof Luminaria && e.getSector().equals(equipo.getSector())  && e.getEstado() != Estado.ACTIVO) {

                    fallasActivasEnSector++;
                }
            }
        }

        // Calcular prioridad automáticamente
        Prioridad prioridad = equipo.calcularPrioridadMantenimiento(fallasActivasEnSector);

        // Crear la orden
        OrdenMantenimiento orden = new OrdenMantenimiento(idOrden, prioridad, fechaApertura, descripcion, equipo, empleado, falla
        );

        // Establecer las relaciones
        equipo.agregarOrden(orden);
        empleado.agregarOrden(orden);
        falla.setOrdenMantenimiento(orden);


        // El equipo pasa a estar en reparación
        equipo.marcarEnReparacion();

        return orden;
    }

    public void cerrarOrden(OrdenMantenimiento orden, Date fechaCierre, String descripcionReparacion) {

        if (orden == null) {
            throw new IllegalArgumentException("La orden es obligatoria.");
        }

        if (fechaCierre == null || descripcionReparacion == null
                || descripcionReparacion.isBlank()) {
            throw new IllegalArgumentException(
                    "La fecha de cierre y la descripción de la reparación son obligatorias."
            );
        }

        if (orden.getFechaCierre() != null) {
            throw new IllegalArgumentException("La orden ya está cerrada.");
        }

        if (fechaCierre.before(orden.getFechaApertura())) {
            throw new IllegalArgumentException(
                    "La fecha de cierre no puede ser anterior a la apertura."
            );
        }

        // Registrar el cierre
        orden.setFechaCierre(fechaCierre);
        orden.setDescripcionReparacion(descripcionReparacion);

        // El equipo vuelve a estar operativo
        orden.getEquipo().marcarOperativo();
    }
}