import Enum.Prioridad;

import java.util.Date;

public OrdenMantenimiento crearOrden(
        int idOrden,
        Prioridad prioridad,
        Date fechaApertura,
        String descripcion,
        Equipo equipo,
        Empleado empleado,
        Falla falla) {

    // Validar los datos necesarios
    if (equipo == null || empleado == null || falla == null) {
        throw new IllegalArgumentException(
                "El equipo, el empleado y la falla son obligatorios."
        );
    }

    if (falla.getEquipo() != equipo) {
        throw new IllegalArgumentException(
                "La falla no pertenece al equipo indicado."
        );
    }
    // Crear la orden
    OrdenMantenimiento orden = new OrdenMantenimiento(idOrden, prioridad, fechaApertura, descripcion, equipo, empleado, falla);

    // Establecer las relaciones
    equipo.agregarOrden(orden);
    empleado.agregarOrden(orden);
    falla.setOrdenMantenimiento(orden);

    return orden;
}