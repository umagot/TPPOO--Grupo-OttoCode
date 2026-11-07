
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Importes Lotes
import Lotes.Lote;
import Lotes.LoteDAO;
import Lotes.Residente;
import Lotes.Empleado;

// Importes Pagos
import Pagos.ControladorExpensas;
import Pagos.Expensa;
import Pagos.Pago;

// Importes Visitas
import Visitas_Y_Accesos.ControladorVisita;
import Visitas_Y_Accesos.AutorizacionVisita;

// Importes Reclamos
import Reclamos.ControladorReclamo;
import Reclamos.Reclamo;

// Importes Mantenimiento
import Mantenimiento.ControladorMantenimiento;
import Mantenimiento.Equipo;
import Mantenimiento.Camara;
import Mantenimiento.Luminaria;
import Mantenimiento.Falla;
import Mantenimiento.OrdenMantenimiento;

// Importes Enums
import Enum.MedioP;
import Enum.TipoP;
import Enum.Estado;
import Enum.EstadoP;
import Enum.TipoL;
import Enum.EstadoReclamo;


public class Main {

    private static Scanner scanner = new Scanner(System.in);

    // ==========================================
    // CONTROLADORES Y DAOs
    // ==========================================
    private static LoteDAO loteDAO = new LoteDAO();
    private static ControladorExpensas controladorExpensas = new ControladorExpensas();
    private static ControladorVisita controladorVisita = new ControladorVisita();
    private static ControladorReclamo controladorReclamo;

    // Controladores y Listas de Mantenimiento
    private static ControladorMantenimiento controladorMantenimiento = new ControladorMantenimiento();
    private static List<Equipo> equipos = new ArrayList<>();
    private static List<Empleado> empleados = new ArrayList<>();
    private static List<Falla> fallasRegistradas = new ArrayList<>();

    public static void main(String[] args) {

        // Carga inicial leyendo los archivos .dat
        inicializarDatosDePrueba();

        // El controlador se crea después de cargar los empleados,
        // para que pueda aplicar correctamente las reglas de asignación.
        controladorReclamo = new ControladorReclamo(empleados);

        // Cierra los reclamos que superaron el plazo de 5 días resueltos.
        controladorReclamo.cerrarReclamosVencidos();

        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE GESTION DEL BARRIO =====");
            System.out.println("1. Lotes y Residentes");
            System.out.println("2. Expensas y Pagos");
            System.out.println("3. Visitas y Accesos");
            System.out.println("4. Reclamos");
            System.out.println("5. Mantenimiento y Seguridad");
            System.out.println("6. Reportes (General)");
            System.out.println("0. Salir y Guardar");

            opcion = leerEntero("Seleccione un módulo: ");

            try {
                switch (opcion) {
                    case 1:
                        submenuLotes();
                        break;
                    case 2:
                        submenuExpensas();
                        break;
                    case 3:
                        submenuVisitas();
                        break;
                    case 4:
                        submenuReclamos();
                        break;
                    case 5:
                        submenuMantenimiento();
                        break;
                    case 6:
                        generarReportes();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema y guardando datos...");
                        guardarDatosMantenimiento();
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error de validación: " + e.getMessage());
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto.");
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado: " + e.getMessage());
            }

        } while (opcion != 0);

        scanner.close();
    }
    // ==========================================
    // SUBMENUS
    // ==========================================

    // ==========================================
    // SUBMENUS
    // ==========================================

    private static void submenuLotes() {
        int opcion;
        do {
            System.out.println("\n--- MÓDULO: LOTES Y RESIDENTES ---");
            System.out.println("1. Listar lotes");
            System.out.println("2. Listar residentes");
            System.out.println("0. Volver al Menú Principal");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1: listarLotes(); break;
                    case 2: listarResidentes(); break;
                    case 0: break;
                    default: System.out.println("Opción incorrecta.");
                }
            } catch (IllegalArgumentException | IllegalStateException | java.time.DateTimeException | Pagos.PagoInvalidoExcepcion e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void submenuExpensas() {
        int opcion;
        do {
            System.out.println("\n--- MÓDULO: EXPENSAS Y PAGOS ---");
            System.out.println("1. Listar expensas");
            System.out.println("2. Registrar nueva expensa");
            System.out.println("3. Registrar un pago");
            System.out.println("0. Volver al Menú Principal");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1: listarExpensas(); break;
                    case 2: registrarExpensa(); break;
                    case 3: registrarPago(); break;
                    case 0: break;
                    default: System.out.println("Opción incorrecta.");
                }
            } catch (IllegalArgumentException | IllegalStateException | java.time.DateTimeException | Pagos.PagoInvalidoExcepcion e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void submenuVisitas() {
        int opcion;
        do {
            System.out.println("\n--- MÓDULO: VISITAS Y ACCESOS ---");
            System.out.println("1. Listar autorizaciones");
            System.out.println("2. Registrar autorizacion de visita");
            System.out.println("3. Registrar ingreso de visitante");
            System.out.println("4. Registrar salida de visitante");
            System.out.println("0. Volver al Menú Principal");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1: listarAutorizaciones(); break;
                    case 2: registrarVisita(); break;
                    case 3: registrarIngresoVisita(); break;
                    case 4: registrarSalidaVisita(); break;
                    case 0: break;
                    default: System.out.println("Opción incorrecta.");
                }
            } catch (IllegalArgumentException | IllegalStateException | java.time.DateTimeException | Pagos.PagoInvalidoExcepcion e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void submenuReclamos() {
        int opcion;
        do {
            System.out.println("\n--- MÓDULO: RECLAMOS ---");
            System.out.println("1. Listar reclamos");
            System.out.println("2. Registrar reclamo");
            System.out.println("3. Actualizar estado de reclamo");
            System.out.println("4. Asignar reclamo a un empleado");
            System.out.println("0. Volver al Menú Principal");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1: listarReclamos(); break;
                    case 2: registrarReclamo(); break;
                    case 3: actualizarEstadoReclamo(); break;
                    case 4: asignarReclamoAEmpleado(); break;
                    case 0: break;
                    default: System.out.println("Opción incorrecta.");
                }
            } catch (IllegalArgumentException | IllegalStateException | java.time.DateTimeException | Pagos.PagoInvalidoExcepcion e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void submenuMantenimiento() {
        int opcion;
        do {
            System.out.println("\n--- MÓDULO: MANTENIMIENTO ---");
            System.out.println("1. Consultar estado de los equipos");
            System.out.println("2. Registrar falla en equipo");
            System.out.println("3. Generar orden de mantenimiento");
            System.out.println("4. Cerrar orden de mantenimiento (Terminar reparación)");
            System.out.println("0. Volver al Menú Principal");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1: consultarEstadoEquipos(); break;
                    case 2: registrarFallaEquipo(); break;
                    case 3: generarOrdenMantenimiento(); break;
                    case 4: cerrarOrdenMantenimiento(); break;
                    case 0: break;
                    default: System.out.println("Opción incorrecta.");
                }
            } catch (IllegalArgumentException | IllegalStateException | java.time.DateTimeException | Pagos.PagoInvalidoExcepcion e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void inicializarDatosDePrueba() {
        System.out.println("Cargando datos del sistema...");

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("equipos.dat"))) {
            equipos = (List<Equipo>) in.readObject();
        } catch (Exception e) {
            System.out.println(" -> No se encontró equipos.dat o está vacío.");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("empleados.dat"))) {
            empleados = (List<Empleado>) in.readObject();
        } catch (Exception e) {
            System.out.println(" -> No se encontró empleados.dat o está vacío.");
        }

        // Las fallas se reconstruyen desde los equipos para que apunten al MISMO objeto
        fallasRegistradas = new ArrayList<>();
        for (Equipo eq : equipos) {
            fallasRegistradas.addAll(eq.getFallas());
        }
    }

    private static void guardarDatosMantenimiento() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("equipos.dat"))) {
            out.writeObject(equipos);
        } catch (Exception e) {
            System.out.println("Error al guardar equipos: " + e.getMessage());
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("empleados.dat"))) {
            out.writeObject(empleados);
        } catch (Exception e) {
            System.out.println("Error al guardar empleados: " + e.getMessage());
        }
    }


    // ==========================================
    // MÉTODOS DE LOTES Y RESIDENTES
    // ==========================================

    private static void listarLotes() {
        List<Lote> lotes = loteDAO.obtenerTodos();
        if (lotes.isEmpty()) {
            System.out.println("No hay lotes registrados.");
            return;
        }
        for (Lote lote : lotes) {
            System.out.println("ID: " + lote.getId_Lote() + " | Superficie: " + lote.getSuperficie() + " | Titular: " + lote.getIdTitular());
        }
    }

    private static void listarResidentes() {
        List<Lote> lotes = loteDAO.obtenerTodos();
        for (Lote lote : lotes) {
            for (Residente residente : lote.getResidentes()) {
                System.out.println("ID persona: " + residente.getIdPersona() + " | Nombre: " + residente.getNombre() + " " + residente.getApellido() + " | DNI: " + residente.getDni() + " | Lote: " + lote.getId_Lote());
            }
        }
    }

    private static Lote buscarLote(int idLote) {
        for (Lote lote : loteDAO.obtenerTodos()) {
            if (lote.getId_Lote() == idLote) return lote;
        }
        return null;
    }

    private static Residente buscarResidente(int idPersona) {
        for (Lote lote : loteDAO.obtenerTodos()) {
            for (Residente residente : lote.getResidentes()) {
                if (residente.getIdPersona() == idPersona) return residente;
            }
        }
        return null;
    }

    // ==========================================
    // MÉTODOS DE EXPENSAS Y PAGOS
    // ==========================================

    private static void listarExpensas() {
        List<Expensa> expensas = controladorExpensas.getExpensas();
        for (Expensa expensa : expensas) {
            System.out.println("ID: " + expensa.getIdExpensa() + " | Lote: " + expensa.getIdLote() + " | Periodo: " + expensa.getPeriodo() + " | Importe: $" + expensa.getImporte() + " | Saldo: $" + expensa.calcularSaldoPendiente() + " | Estado: " + expensa.getEstado());
        }
    }

    private static void registrarExpensa() {
        int idExpensa = leerEntero("ID de la expensa: ");
        int idLote = leerEntero("ID del lote: ");
        Lote lote = buscarLote(idLote);

        if (lote == null) {
            System.out.println("No existe ese lote.");
            return;
        }

        int importe = leerEntero("Importe: ");
        System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());
        System.out.print("Periodo (AAAA-MM): ");
        String periodo = scanner.nextLine();

        Expensa expensa = controladorExpensas.registrarExpensa(idExpensa, lote, importe, fecha, periodo);
        System.out.println("Expensa registrada: " + expensa.getIdExpensa());
    }

    private static void registrarPago() {
        int idExpensa = leerEntero("ID de la expensa: ");
        int idPago = leerEntero("ID del pago: ");
        int importe = leerEntero("Importe del pago: ");
        System.out.print("Fecha del pago (AAAA-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        System.out.println("Medios de pago:");
        for (MedioP medio : MedioP.values()) {
            System.out.println("- " + medio);
        }
        System.out.print("Ingrese el medio de pago: ");
        MedioP medioPago = MedioP.valueOf(scanner.nextLine().trim().toUpperCase());

        Pago pago = controladorExpensas.registrarPago(idExpensa, idPago, fecha, importe, medioPago);
        System.out.println("Pago registrado: " + pago.getIdPago());
    }

    // ==========================================
    // MÉTODOS DE VISITAS Y RECLAMOS
    // ==========================================

    private static void registrarVisita() {
        int idAutorizacion = leerEntero("ID de autorizacion: ");
        int idPersona = leerEntero("ID de persona del residente: ");
        Residente residente = buscarResidente(idPersona);

        if (residente == null) {
            System.out.println("No se encontro el residente.");
            return;
        }

        System.out.print("Nombre del visitante: ");
        String nombre = scanner.nextLine();
        int dni = leerEntero("DNI del visitante: ");
        System.out.print("Inicio (AAAA-MM-DDTHH:MM): ");
        LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Fin (AAAA-MM-DDTHH:MM): ");
        LocalDateTime fin = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Es proveedor? (true/false): ");
        boolean esProveedor = Boolean.parseBoolean(scanner.nextLine());

        AutorizacionVisita autorizacion = controladorVisita.registrarAutorizacion(idAutorizacion, residente, nombre, dni, inicio, fin, esProveedor);
        System.out.println("Autorizacion registrada: " + autorizacion.getIdAutorizacion());
    }

    private static void listarAutorizaciones() {
        List<AutorizacionVisita> autorizaciones = controladorVisita.getAutorizaciones();
        for (AutorizacionVisita autorizacion : autorizaciones) {
            System.out.println("ID: " + autorizacion.getIdAutorizacion() + " | Visitante: " + autorizacion.getNombre() + " | DNI: " + autorizacion.getDNI() + " | Lote: " + autorizacion.getIdLote() + " | Desde: " + autorizacion.getFechaHoraInicio() + " | Hasta: " + autorizacion.getFechaHoraFin());
        }
    }

    private static void registrarIngresoVisita() {
        int idAcceso = leerEntero("ID del registro de acceso: ");
        int idAutorizacion = leerEntero("ID de la autorización: ");
        int idGuardia = leerEntero("ID del guardia: ");

        controladorVisita.registrarIngreso(idAcceso, idAutorizacion, idGuardia);
        System.out.println("Ingreso registrado correctamente.");
    }

    private static void registrarSalidaVisita() {
        int idAutorizacion = leerEntero("ID de la autorización: ");
        controladorVisita.registrarSalida(idAutorizacion);
        System.out.println("Salida registrada correctamente.");
    }

    private static void registrarReclamo() {
        int idReclamo = leerEntero("ID del reclamo: ");
        int idPersona = leerEntero("ID de persona del residente: ");
        Residente residente = buscarResidente(idPersona);

        if (residente == null) {
            System.out.println("No se encontro el residente.");
            return;
        }

        System.out.println("Tipos de reclamo:");
        for (TipoP tipo : TipoP.values()) {
            System.out.println("- " + tipo);
        }
        System.out.print("Ingrese el tipo: ");
        TipoP tipo = TipoP.valueOf(scanner.nextLine().trim().toUpperCase());

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        Reclamo reclamo = controladorReclamo.registrarReclamo(idReclamo, residente, tipo, descripcion, new Date());
        System.out.println("Reclamo registrado: " + reclamo.getIdReclamo());
    }

    private static void listarReclamos() {
        List<Reclamo> reclamos = controladorReclamo.getReclamos();
        for (Reclamo reclamo : reclamos) {
            System.out.println("ID: " + reclamo.getIdReclamo() + " | Descripcion: " + reclamo.getDescripcion() + " | Estado: " + reclamo.getEstadoActual() + " | Lote: " + reclamo.getIdLote());
        }
    }

    private static void actualizarEstadoReclamo() {
        int idReclamo = leerEntero("ID del reclamo a actualizar: ");

        Reclamo reclamoEncontrado = null;
        for (Reclamo r : controladorReclamo.getReclamos()) {
            if (r.getIdReclamo() == idReclamo) {
                reclamoEncontrado = r;
                break;
            }
        }

        if (reclamoEncontrado == null) {
            System.out.println("Error: No se encontró un reclamo con ese ID.");
            return;
        }

        System.out.println("Estado actual: " + reclamoEncontrado.getEstadoActual());

        // Mostramos las opciones válidas para que el usuario no se equivoque
        System.out.println("Estados disponibles:");
        for (EstadoReclamo est : EstadoReclamo.values()) {
            System.out.println("- " + est);
        }

        System.out.print("Ingrese el nuevo estado: ");

        try {
            EstadoReclamo nuevoEstado = EstadoReclamo.valueOf(
                    scanner.nextLine().trim().toUpperCase()
            );

            // La asignación debe pasar por el controlador para validar
            // el límite de cinco reclamos en curso por empleado.
            if (nuevoEstado == EstadoReclamo.ASIGNADO
                    && reclamoEncontrado.getEstadoActual() == EstadoReclamo.ABIERTO) {
                int idEmpleado = leerEntero("ID del empleado responsable: ");
                controladorReclamo.asignarReclamo(idReclamo, idEmpleado);
                // La reapertura debe pasar por su método específico para
                // validar el plazo de 5 días desde la resolución.
            } else if (reclamoEncontrado.getEstadoActual() == EstadoReclamo.RESUELTO
                    && nuevoEstado == EstadoReclamo.EN_CURSO) {
                controladorReclamo.reabrirReclamo(idReclamo);
            } else {
                System.out.print("Observación del cambio: ");
                String observacion = scanner.nextLine();
                controladorReclamo.actualizarEstado(
                        idReclamo,
                        nuevoEstado,
                        observacion
                );
            }

            System.out.println("Estado del reclamo actualizado correctamente a " + nuevoEstado + ".");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("No se pudo actualizar el reclamo: " + e.getMessage());
        }
    }

    private static void asignarReclamoAEmpleado() {
        int idReclamo = leerEntero("ID del reclamo a asignar: ");
        int idEmpleado = leerEntero("ID del empleado responsable: ");

        controladorReclamo.asignarReclamo(idReclamo, idEmpleado);
        System.out.println("Reclamo asignado correctamente.");
    }

    // ==========================================
    // MÉTODOS DE MANTENIMIENTO
    // ==========================================
    private static void consultarEstadoEquipos() {
        System.out.println("\n--- ESTADO DE EQUIPOS ---");
        for (Equipo eq : equipos) {
            System.out.println("Equipo " + eq.getIdEquipo() + " (" + eq.getTipo() + ") en " + eq.getSector());
            System.out.println(" -> Estado: " + eq.getEstado());
            if (eq.getEstado() != Estado.ACTIVO) {
                System.out.println(" -> Inactivo desde: " + eq.getFechaInicioInactividad());
            }
        }
    }

    private static void registrarFallaEquipo() {
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos cargados.");
            return;
        }
        consultarEstadoEquipos();

        int idEquipo = leerEntero("ID del equipo que falla: ");
        Equipo equipoFalla = null;
        for (Equipo eq : equipos) {
            if (eq.getIdEquipo() == idEquipo) {
                equipoFalla = eq; break;
            }
        }

        if (equipoFalla == null) {
            System.out.println("Equipo no encontrado.");
            return;
        }

        int idFalla = leerEntero("Ingrese un ID para esta falla: ");
        System.out.print("Descripción del problema: ");
        String descripcion = scanner.nextLine();

        Falla falla = equipoFalla.registrarFalla(idFalla, descripcion, LocalDateTime.now());
        fallasRegistradas.add(falla);

        System.out.println("¡Falla registrada! El equipo ahora está FUERA DE SERVICIO.");
        guardarDatosMantenimiento();
    }

    private static void generarOrdenMantenimiento() {
        if (fallasRegistradas.isEmpty()) {
            System.out.println("No hay fallas pendientes de orden.");
            return;
        }

        System.out.println("--- Fallas Pendientes ---");
        for (Falla f : fallasRegistradas) {
            if (f.getOrdenMantenimiento() == null) {
                System.out.println("ID Falla: " + f.getIdFalla() + " | Equipo ID: " + f.getIdEquipo() + " | Desc: " + f.getDescripcion());
            }
        }

        int idFalla = leerEntero("ID de la falla a reparar: ");
        Falla fallaElegida = null;
        for (Falla f : fallasRegistradas) {
            if (f.getIdFalla() == idFalla && f.getOrdenMantenimiento() == null) {
                fallaElegida = f; break;
            }
        }

        if (fallaElegida == null) {
            System.out.println("Falla no encontrada o ya tiene orden.");
            return;
        }

        if (empleados.isEmpty()) {
            System.out.println("Error: No hay empleados cargados para asignar la orden.");
            return;
        }

        System.out.println("--- Empleados disponibles ---");
        for (Empleado emp : empleados) {
            System.out.println("ID: " + emp.getIdPersona() + " | " + emp.getNombre() + " " + emp.getApellido() + " | " + emp.getTipoPersonal());
        }

        int idEmpleado = leerEntero("ID del empleado responsable: ");
        Empleado empleadoElegido = null;
        for (Empleado emp : empleados) {
            if (emp.getIdPersona() == idEmpleado) {
                empleadoElegido = emp;
                break;
            }
        }

        if (empleadoElegido == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        int idOrden = leerEntero("ID para la nueva Orden: ");
        System.out.print("Descripción del trabajo a realizar: ");
        String trabajo = scanner.nextLine();

        try {
            OrdenMantenimiento orden = controladorMantenimiento.crearOrden(
                    idOrden, LocalDateTime.now(), trabajo, fallaElegida.getEquipo(),
                    empleadoElegido, fallaElegida, equipos
            );
            System.out.println("¡Orden " + orden.getIdOrden() + " creada con éxito! Prioridad: " + orden.getPrioridad());
            guardarDatosMantenimiento();
        } catch (Exception e) {
            System.out.println("Error al crear la orden: " + e.getMessage());
        }
    }

    private static void cerrarOrdenMantenimiento() {
        System.out.println("\n--- EQUIPOS EN REPARACIÓN ---");
        boolean hayOrdenes = false;

        for (Equipo eq : equipos) {
            for (OrdenMantenimiento orden : eq.getOrdenes()) {
                if (orden.getFechaCierre() == null) {
                    System.out.println("ID Orden: " + orden.getIdOrden() + " | Equipo: " + eq.getTipo() + " (Sector: " + eq.getSector() + ")");
                    hayOrdenes = true;
                }
            }
        }

        if (!hayOrdenes) {
            System.out.println("No hay órdenes de mantenimiento abiertas.");
            return;
        }

        int idOrden = leerEntero("Ingrese el ID de la orden que desea cerrar: ");
        OrdenMantenimiento ordenACerrar = null;

        for (Equipo eq : equipos) {
            for (OrdenMantenimiento orden : eq.getOrdenes()) {
                if (orden.getIdOrden() == idOrden && orden.getFechaCierre() == null) {
                    ordenACerrar = orden;
                    break;
                }
            }
        }

        if (ordenACerrar == null) {
            System.out.println("Error: Orden no encontrada o ya está cerrada.");
            return;
        }

        System.out.print("Descripción del trabajo realizado para solucionarlo: ");
        String detalle = scanner.nextLine();

        try {
            controladorMantenimiento.cerrarOrden(ordenACerrar, LocalDateTime.now(), detalle);
            System.out.println("¡Orden cerrada con éxito! El equipo vuelve a estar ACTIVO.");
            guardarDatosMantenimiento();
        } catch (Exception e) {
            System.out.println("Error al cerrar la orden: " + e.getMessage());
        }
    }

    // ==========================================
    // MÉTODOS DE REPORTES
    // ==========================================
    private static void generarReportes() {
        System.out.println("\n========= REPORTES DEL BARRIO =========");

        System.out.println("\n1. MOROSIDAD (Expensas Impagas o Parciales):");
        boolean hayDeuda = false;
        for (Expensa ex : controladorExpensas.getExpensas()) {
            if (ex.getEstado() == EstadoP.IMPAGA || ex.getEstado() == EstadoP.PARCIAL) {
                System.out.println(" - Lote " + ex.getIdLote() + " adeuda $" + ex.calcularSaldoPendiente() + " (Periodo: " + ex.getPeriodo() + ")");
                hayDeuda = true;
            }
        }
        if (!hayDeuda) System.out.println(" - No hay expensas en mora.");

        System.out.println("\n2. RECLAMOS PENDIENTES:");
        boolean hayReclamos = false;
        for (Reclamo rec : controladorReclamo.getReclamos()) {
            if (rec.getEstadoActual() != EstadoReclamo.CERRADO && rec.getEstadoActual() != EstadoReclamo.RESUELTO) {
                System.out.println(" - Reclamo #" + rec.getIdReclamo() + ": " + rec.getDescripcion() + " (Estado: " + rec.getEstadoActual() + ")");
                hayReclamos = true;
            }
        }
        if (!hayReclamos) System.out.println(" - No hay reclamos pendientes.");

        System.out.println("\n3. EQUIPOS FUERA DE SERVICIO:");
        boolean hayEquipos = false;
        for (Equipo eq : equipos) {
            if (eq.getEstado() == Estado.FUERA_DE_SERVICIO || eq.getEstado() == Estado.EN_REPARACION) {
                System.out.println(" - " + eq.getTipo() + " en " + eq.getSector() + " [" + eq.getEstado() + "] (Inactivo desde: " + eq.getFechaInicioInactividad() + ")");
                hayEquipos = true;
            }
        }
        if (!hayEquipos) System.out.println(" - Todos los equipos funcionan correctamente.");

        System.out.println("=======================================\n");
    }

    // ==========================================
    // LEER ENTEROS (BLINDADO)
    // ==========================================
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("--> [ERROR] Debes ingresar un número, no texto. Intentalo de nuevo.");
            }
        }
    }
}