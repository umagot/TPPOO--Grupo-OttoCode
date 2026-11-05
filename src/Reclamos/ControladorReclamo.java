package Reclamos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Enum.EstadoReclamo;
import Enum.TipoP;
import Lotes.Empleado;
import Lotes.Lote;
import Lotes.Residente;

public class ControladorReclamo {

    private List<Reclamo> reclamos;
    private List<Empleado> empleados;
    private int siguienteIdHistorial = 1;


    // Constructor principal
    public ControladorReclamo(List<Reclamo> reclamos, List<Empleado> empleados) {

        if (reclamos == null) {
            this.reclamos = new ArrayList<>();
        } else {
            this.reclamos = new ArrayList<>(reclamos);
        }

        if (empleados == null) {
            this.empleados = new ArrayList<>();
        } else {
            this.empleados = new ArrayList<>(empleados);
        }

    }

    // Constructor sin parámetros
    public ControladorReclamo() {
        this.reclamos = new ArrayList<>();
        this.empleados = new ArrayList<>();
    }

    // 1. REGISTRAR UN RECLAMO
    public Reclamo registrarReclamo(
            int idReclamo,
            Residente residente,
            TipoP tipo,
            String descripcion,
            Date fecha) {

        if (residente == null) {
            throw new IllegalArgumentException("El residente no puede ser nulo.");
        }

        if (residente.getLote() == null) {
            throw new IllegalStateException("El residente no tiene un lote asociado.");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("Debe indicar el tipo de reclamo.");
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }

        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }

        if (buscarReclamo(idReclamo) != null) {
            throw new IllegalArgumentException("Ya existe un reclamo con ese ID.");
        }

        Lote lote = residente.getLote();

        Reclamo reclamo = new Reclamo(
                idReclamo,
                residente.getIdPersona(),
                fecha,
                descripcion,
                tipo,
                lote.getId_Lote(),
                0,
                residente,
                lote,
                null,
                new ArrayList<>(),
                new ArrayList<>()
        );

        reclamo.setEstadoActual(EstadoReclamo.ABIERTO);

        reclamos.add(reclamo);
        residente.agregarReclamo(reclamo);
        lote.agregarReclamo(reclamo);

        registrarHistorial(
                reclamo,
                EstadoReclamo.ABIERTO,
                "Reclamos.Reclamo registrado."
        );

        return reclamo;
    }

    // 2. ASIGNAR UN RECLAMO A UN EMPLEADO
    public void asignarReclamo(int idReclamo, int idEmpleado) {

        Reclamo reclamo = buscarReclamo(idReclamo);

        if (reclamo == null) {
            throw new IllegalArgumentException("No existe el reclamo indicado.");
        }

        Empleado empleado = buscarEmpleado(idEmpleado);

        if (empleado == null) {
            throw new IllegalArgumentException("No existe el empleado indicado.");
        }

        if (reclamo.getEstadoActual() != EstadoReclamo.ABIERTO) {
            throw new IllegalStateException("Solo se pueden asignar reclamos abiertos.");
        }

        reclamo.setEmpleado(empleado);
        reclamo.setIdEmpleado(empleado.getIdPersona());

        empleado.asignarReclamo(reclamo);

        actualizarEstado(
                idReclamo,
                EstadoReclamo.ASIGNADO,
                "Reclamos.Reclamo asignado a un empleado."
        );
    }

    // 3. ACTUALIZAR EL ESTADO DE UN RECLAMO
    public void actualizarEstado(
            int idReclamo,
            EstadoReclamo nuevoEstado,
            String observacion) {

        Reclamo reclamo = buscarReclamo(idReclamo);

        if (reclamo == null) {
            throw new IllegalArgumentException("No existe el reclamo indicado.");
        }

        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }

        if (observacion == null || observacion.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar una observación.");
        }

        EstadoReclamo estadoActual = reclamo.getEstadoActual();

        if (estadoActual == null) {
            throw new IllegalStateException("El reclamo no tiene un estado actual.");
        }

        if (!estadoActual.puedeAvanzarA(nuevoEstado)) {
            throw new IllegalStateException(
                    "No se puede pasar de " + estadoActual + " a " + nuevoEstado + "."
            );
        }

        reclamo.setEstadoActual(nuevoEstado);

        registrarHistorial(
                reclamo,
                nuevoEstado,
                observacion
        );
    }

    // 4. AGREGAR UN COMENTARIO A UN RECLAMO
    public ComentarioReclamo agregarComentario(
            int idComentario,
            int idReclamo,
            Persona persona,
            String comentario) {

        Reclamo reclamo = buscarReclamo(idReclamo);

        if (reclamo == null) {
            throw new IllegalArgumentException("No existe el reclamo indicado.");
        }

        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula.");
        }

        if (comentario == null || comentario.isBlank()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío.");
        }

        if (reclamo.getEstadoActual() == EstadoReclamo.CERRADO) {
            throw new IllegalStateException("No se pueden agregar comentarios a un reclamo cerrado.");
        }

        ComentarioReclamo nuevoComentario = new ComentarioReclamo(
                idComentario,
                idReclamo,
                persona.getIdPersona(),
                new Date(),
                comentario,
                reclamo,
                persona
        );

        reclamo.agregarComentario(nuevoComentario);
        persona.agregarComentario(nuevoComentario);

        return nuevoComentario;
    }

    // 5. REGISTRAR UN CAMBIO EN EL HISTORIAL
    private void registrarHistorial(
            Reclamo reclamo,
            EstadoReclamo nuevoEstado,
            String observacion) {

        HistorialEstadoReclamo historial = new HistorialEstadoReclamo(
                siguienteIdHistorial,
                reclamo.getIdReclamo(),
                nuevoEstado,
                new Date(),
                observacion,
                reclamo
        );

        reclamo.agregarHistorial(historial);

        siguienteIdHistorial++;
    }

    // 6. BUSCAR UN RECLAMO POR SU ID
    public Reclamo buscarReclamo(int idReclamo) {

        for (Reclamo reclamo : reclamos) {
            if (reclamo.getIdReclamo() == idReclamo) {
                return reclamo;
            }
        }

        return null;
    }

    // 7. BUSCAR UN EMPLEADO POR SU ID DE PERSONA
    public Empleado buscarEmpleado(int idEmpleado) {

        for (Empleado empleado : empleados) {
            if (empleado.getIdPersona() == idEmpleado) {
                return empleado;
            }
        }

        return null;
    }

    // 8. OBTENER LA LISTA DE RECLAMOS
    public List<Reclamo> getReclamos() {
        return List.copyOf(reclamos);
    }
}
