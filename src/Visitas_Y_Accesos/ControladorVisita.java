package Visitas_Y_Accesos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorVisita {

    private List<AutorizacionVisita> autorizaciones;

    public ControladorVisita(List<AutorizacionVisita> autorizaciones) {

        if (autorizaciones == null) {
            this.autorizaciones = new ArrayList<>();
        } else {
            this.autorizaciones = new ArrayList<>(autorizaciones);
        }
    }

    
    public ControladorVisita() {
        this.autorizaciones = new ArrayList<>();
    }

    // 1. REGISTRAR UNA AUTORIZACION DE VISITA
    public AutorizacionVisita registrarAutorizacion(
            int idAutorizacion,
            Residente residente,
            String nombre,
            int dni,
            Date fechaHoraInicio,
            Date fechaHoraFin,
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

        if (fechaHoraFin.before(fechaHoraInicio)) {
            throw new IllegalArgumentException(
                    "La fecha de fin no puede ser anterior al inicio."
            );
        }

        if (buscarAutorizacion(idAutorizacion) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una autorización con ese ID."
            );
        }

        // Obtener el lote del residente
        Lote lote = residente.getLote();

        // Crear la autorización
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

        // Agregar a la lista del controlador
        autorizaciones.add(autorizacion);

        // Agregar a la lista del lote
        if (lote.getAutorizaciones() == null) {
            lote.setAutorizaciones(new ArrayList<>());
        }

        lote.agregarAutorizacion(autorizacion);

        // Agregar a la lista del residente
        List<AutorizacionVisita> listaResidente =
                residente.getAutorizacionesVisita();

        if (listaResidente == null) {
            listaResidente = new ArrayList<>();
        } else {
            listaResidente = new ArrayList<>(listaResidente);
        }

        // 4. REGISTRAR EL INGRESO DE UNA VISITA
public Registros registrarIngreso(
        int idAcceso,
        int idAutorizacion,
        int idGuardia) {

    // Buscar la autorización
    AutorizacionVisita autorizacion =
            buscarAutorizacion(idAutorizacion);

    if (autorizacion == null) {
        throw new IllegalArgumentException(
                "No existe esa autorización."
        );
    }

    // Comprobar que esté vigente
    if (!estaAutorizada(idAutorizacion)) {
        throw new IllegalStateException(
                "La visita no está autorizada en este momento."
        );
    }

    // Crear el registro sin hora de ingreso ni salida
    Registros registro = new Registros(
            idAcceso,
            idAutorizacion,
            idGuardia,
            null,
            null,
            autorizacion
    );

    // Registrar la hora actual
    registro.registrarIngreso(new Date());

    // Asociar el registro a la autorización
    autorizacion.getRegistros().add(registro);

    return registro;
}
        // 5. REGISTRAR LA SALIDA DE UNA VISITA
public void registrarSalida(int idAutorizacion) {

    AutorizacionVisita autorizacion =
            buscarAutorizacion(idAutorizacion);

    if (autorizacion == null) {
        throw new IllegalArgumentException(
                "No existe esa autorización."
        );
    }

    List<Registros> registros = autorizacion.getRegistros();

    // Buscar un ingreso que todavía no tenga salida
    for (Registros registro : registros) {

        if (registro.getHoraingreso() != null
                && registro.getHoraSalida() == null) {

            registro.registrarSalida(new Date());
            return;
        }
    }

    throw new IllegalStateException(
            "No hay ningún ingreso pendiente de salida."
    );
}



        

        listaResidente.add(autorizacion);
        residente.setAutorizacionesVisita(listaResidente);

        return autorizacion;
    }

    // 2. BUSCAR UNA AUTORIZACION POR ID
    public AutorizacionVisita buscarAutorizacion(int idAutorizacion) {

        for (AutorizacionVisita autorizacion : autorizaciones) {

            if (autorizacion.getIdAutorizacion() == idAutorizacion) {
                return autorizacion;
            }
        }

        return null;
    }

    // 3. VALIDAR SI UNA VISITA ESTA AUTORIZADA
    public boolean estaAutorizada(int idAutorizacion) {

        AutorizacionVisita autorizacion =
                buscarAutorizacion(idAutorizacion);

        if (autorizacion == null) {
            return false;
        }

        Date ahora = new Date();

        return !ahora.before(autorizacion.getFechaHoraInicio())
                && !ahora.after(autorizacion.getFechaHoraFin());
    }

    // 4. OBTENER TODAS LAS AUTORIZACIONES
    public List<AutorizacionVisita> getAutorizaciones() {
        return List.copyOf(autorizaciones);
    }

    // 5. BUSCAR AUTORIZACIONES POR DNI
    public List<AutorizacionVisita> buscarPorDNI(int dni) {

        List<AutorizacionVisita> encontradas = new ArrayList<>();

        for (AutorizacionVisita autorizacion : autorizaciones) {

            if (autorizacion.getDNI() == dni) {
                encontradas.add(autorizacion);
            }
        }

        return encontradas;
    }
}
