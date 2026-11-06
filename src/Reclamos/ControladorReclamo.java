package Reclamos;
import Datos.GestorArchivos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Enum.EstadoReclamo;
import Enum.TipoP;
import Lotes.Empleado;
import Lotes.Lote;
import Lotes.Persona;
import Lotes.Residente;


public class ControladorReclamo {

    private final String RUTA_ARCHIVO = "reclamos.txt";
    private List<Reclamo> reclamos;
    private List<Empleado> empleados;
    private int siguienteIdHistorial = 1;


    // Constructor principal
    public ControladorReclamo(List<Empleado> empleados) {

        if (empleados != null) {
            this.empleados = new ArrayList<>(empleados);
        } else {
            this.empleados = new ArrayList<>();
        }

        // Cargamos los datos usando el Singleton
        Object datosRecuperados = GestorArchivos.getInstancia().cargarDatos(RUTA_ARCHIVO);

        if (datosRecuperados != null) {
            // Hacemos el cast obligatorio de Object a List<Reclamo>
            this.reclamos = (List<Reclamo>) datosRecuperados;
        } else {
            // Si el archivo no existe, arrancamos de cero
            this.reclamos = new ArrayList<>();
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

        guardarCambios();

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

        // Validacion de los 5 reclamos

        int reclamosActivos = 0;
        for (Reclamo r : empleado.getReclamosAsignados()) {
            if (r.getEstadoActual() == EstadoReclamo.ABIERTO ||
                    r.getEstadoActual() == EstadoReclamo.ASIGNADO ||
                    r.getEstadoActual() == EstadoReclamo.EN_CURSO) {
                reclamosActivos++;
            }
        }

        if (reclamosActivos >= 5) {
            throw new IllegalStateException("El empleado ya tiene 5 reclamos en curso. No se le pueden asignar más.");
        }

        reclamo.setEmpleado(empleado);
        reclamo.setIdEmpleado(empleado.getIdPersona());

        empleado.asignarReclamo(reclamo);

        actualizarEstado(
                idReclamo,
                EstadoReclamo.ASIGNADO,
                "Reclamos.Reclamo asignado a un empleado."
        );
        guardarCambios();
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

        guardarCambios();
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

        guardarCambios();

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

    private void guardarCambios() {
        GestorArchivos.getInstancia().guardarDatos(RUTA_ARCHIVO, this.reclamos);
    }
}

