package Visitas_Y_Accesos;

import Lotes.Lote;
import Lotes.Residente;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class ControladorVisita {

    private VisitaDAO dao;
    private List<AutorizacionVisita> autorizaciones;

    public ControladorVisita() {
        this.dao = new VisitaDAO();
        this.autorizaciones = dao.obtenerTodas();
    }

    public AutorizacionVisita registrarAutorizacion(
            int idAutorizacion,
            Residente residente,
            String nombre,
            int dni,
            LocalDateTime fechaHoraInicio,
            LocalDateTime fechaHoraFin,
            boolean esProveedor) {

        if (residente == null) {
            throw new IllegalArgumentException(
                    "El residente no puede ser nulo."
            );
        }

        if (residente.getLote() == null) {
            throw new IllegalStateException(
                    "El residente no tiene un lote asociado."
            );
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del visitante es obligatorio."
            );
        }

        if (dni <= 0) {
            throw new IllegalArgumentException(
                    "El DNI debe ser válido."
            );
        }

        if (fechaHoraInicio == null || fechaHoraFin == null) {
            throw new IllegalArgumentException(
                    "Las fechas de inicio y fin son obligatorias."
            );
        }

        if (fechaHoraFin.isBefore(fechaHoraInicio)) {
            throw new IllegalArgumentException(
                    "La fecha de fin no puede ser anterior al inicio."
            );
        }

        if (buscarAutorizacion(idAutorizacion) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una autorización con ese ID."
            );
        }

        Lote lote = residente.getLote();

        AutorizacionVisita autorizacion = new AutorizacionVisita(
                idAutorizacion,
                lote.getId_Lote(),
                residente.getIdResidente(),
                nombre,
                dni,
                fechaHoraInicio,
                esProveedor,
                lote,
                residente,
                new ArrayList<>()
        );

        autorizacion.setFechaHoraFin(fechaHoraFin);

        autorizaciones.add(autorizacion);

        if (lote.getAutorizaciones() == null) {
            lote.setAutorizaciones(new ArrayList<>());
        }

        lote.agregarAutorizacion(autorizacion);

        residente.agregarAutorizacion(autorizacion);

        guardarCambios();

        return autorizacion;
    }

    public Registros registrarIngreso(
            int idAcceso,
            int idAutorizacion,
            int idGuardia) {

        AutorizacionVisita autorizacion =
                buscarAutorizacion(idAutorizacion);

        if (autorizacion == null) {
            throw new IllegalArgumentException(
                    "No existe esa autorización."
            );
        }

        if (!estaAutorizada(idAutorizacion)) {
            throw new IllegalStateException(
                    "La visita no está autorizada en este momento."
            );
        }

        for (Registros registroExistente : autorizacion.getRegistros()) {

            if (registroExistente.getHoraingreso() != null
                    && registroExistente.getHoraSalida() == null) {

                throw new IllegalStateException(
                        "La visita ya tiene un ingreso pendiente de salida."
                );
            }
        }

        Registros registro = new Registros(
                idAcceso,
                idAutorizacion,
                idGuardia,
                null,
                null,
                autorizacion
        );

        registro.registrarIngreso(LocalDateTime.now());

        autorizacion.getRegistros().add(registro);

        guardarCambios();

        return registro;
    }

    public void registrarSalida(int idAutorizacion) {

        AutorizacionVisita autorizacion =
                buscarAutorizacion(idAutorizacion);

        if (autorizacion == null) {
            throw new IllegalArgumentException(
                    "No existe esa autorización."
            );
        }

        List<Registros> registros = autorizacion.getRegistros();

        for (Registros registro : registros) {

            if (registro.getHoraingreso() != null
                    && registro.getHoraSalida() == null) {

                registro.registrarSalida(LocalDateTime.now());
                guardarCambios();
                return;
            }
        }

        throw new IllegalStateException(
                "No hay ningún ingreso pendiente de salida."
        );
    }

    public AutorizacionVisita buscarAutorizacion(int idAutorizacion) {

        for (AutorizacionVisita autorizacion : autorizaciones) {

            if (autorizacion.getIdAutorizacion() == idAutorizacion) {
                return autorizacion;
            }
        }

        return null;
    }

    public boolean estaAutorizada(int idAutorizacion) {

        AutorizacionVisita autorizacion =
                buscarAutorizacion(idAutorizacion);

        if (autorizacion == null) {
            return false;
        }

        LocalDateTime ahora = LocalDateTime.now();

        return !ahora.isBefore(autorizacion.getFechaHoraInicio())
                && !ahora.isAfter(autorizacion.getFechaHoraFin());
    }

    public List<AutorizacionVisita> getAutorizaciones() {
        return List.copyOf(autorizaciones);
    }

    public List<AutorizacionVisita> buscarPorDNI(int dni) {

        List<AutorizacionVisita> encontradas = new ArrayList<>();

        for (AutorizacionVisita autorizacion : autorizaciones) {

            if (autorizacion.getDNI() == dni) {
                encontradas.add(autorizacion);
            }
        }

        return encontradas;
    }

    private void guardarCambios() {
        dao.actualizarArchivo();
    }
}
