package Mantenimiento;

import java.time.LocalDateTime;
import java.util.List;
import Enum.Estado;
import Enum.Prioridad;
import Lotes.Empleado;

public class ControladorMantenimiento {

    public OrdenMantenimiento crearOrden(int idOrden, LocalDateTime fechaApertura, String descripcion, Equipo equipo, Empleado empleado, Falla falla, List<Equipo> equipos) {

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

                if (e instanceof Luminaria
                        && e.getSector().equals(equipo.getSector())
                        && e.getEstado() == Estado.FUERA_DE_SERVICIO) {
                    fallasActivasEnSector++;
                }
            }
        }

        // Calcular prioridad automáticamente
        Prioridad prioridad = equipo.calcularPrioridadMantenimiento(fallasActivasEnSector);


        //Si la prioridad dio ALTA y es una luminaria, actualizar las anteriores
        if (equipo instanceof Luminaria && prioridad == Prioridad.ALTA) {
            for (Equipo e : equipos) {
                if (e instanceof Luminaria && e.getSector().equals(equipo.getSector()) && e != equipo) {
                    // Buscar órdenes activas (sin cerrar) de esta luminaria y subirles la prioridad
                    for (OrdenMantenimiento ordenAnterior : e.getOrdenes()) {
                        if (ordenAnterior.getFechaCierre() == null) {
                            ordenAnterior.setPrioridad(Prioridad.ALTA);
                        }
                    }
                }
            }
        }

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

    public void cerrarOrden(OrdenMantenimiento orden, LocalDateTime fechaCierre, String descripcionReparacion) {

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

        if (fechaCierre.isBefore(orden.getFechaApertura())) {
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