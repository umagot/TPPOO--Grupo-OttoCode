import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Lotes.Lote;
import Lotes.Residente;
import Lotes.Empleado;
import Mantenimiento.Equipo;
import Mantenimiento.Camara;
import Mantenimiento.Luminaria;
import Pagos.Expensa;
import Reclamos.Reclamo;
import Reclamos.HistorialEstadoReclamo;
import Visitas_Y_Accesos.AutorizacionVisita;
import Enum.Tipo;
import Enum.TipoP;
import Enum.TipoL;
import Enum.EstadoP;
import Enum.EstadoReclamo;

public class CrearDatosPrueba {

    public static void main(String[] args) {
        try {
            // ==========================================
            // 1. CREAR LOTES Y RESIDENTES
            // ==========================================
            List<Lote> lotes = new ArrayList<>();
            Lote lote1 = new Lote(101, 450, 1001, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            Residente res1 = new Residente(1001, "Juan", "Pérez", 30111222, "11-5555-1111", "juan@example.com", 1, Tipo.GENERAL, lote1);
            lote1.agregarResidente(res1);
            lotes.add(lote1);

            // ==========================================
            // 2. CREAR Y GUARDAR EQUIPOS (MANTENIMIENTO)
            // ==========================================
            List<Equipo> equipos = new ArrayList<>();
            equipos.add(new Camara(501, "Entrada Principal", LocalDate.now(), "1080p", 15));
            equipos.add(new Luminaria(502, "Calle Los Patos", LocalDate.now(), TipoL.LED, "100W"));

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("equipos.dat"))) {
                out.writeObject(equipos);
                System.out.println(" equipos.dat creado correctamente.");
            }

            // ==========================================
            // 3. CREAR Y GUARDAR EMPLEADOS (MANTENIMIENTO Y GUARDIA)
            // ==========================================
            List<Empleado> empleados = new ArrayList<>();
            empleados.add(new Empleado(1, "Carlos", "Mantenimiento", 20000000, "11223344", "carlos@barrio.com", 12345, LocalDate.parse("2026-10-06"), TipoP.MANTENIMIENTO));
            empleados.add(new Empleado(2, "Roberto", "Guardia", 25000000, "11998877", "roberto@barrio.com", 12346, LocalDate.parse("2026-01-15"), TipoP.SEGURIDAD));

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("empleados.dat"))) {
                out.writeObject(empleados);
                System.out.println(" empleados.dat creado correctamente.");
            }

            // ==========================================
            // 4. CREAR Y GUARDAR EXPENSAS BASE
            // ==========================================
            List<Expensa> expensas = new ArrayList<>();
            Expensa exp1 = new Expensa(1, lote1.getId_Lote(), 50000, LocalDate.of(2026, 9, 10), EstadoP.IMPAGA, "2026-09", lote1, new ArrayList<>());
            Expensa exp2 = new Expensa(2, lote1.getId_Lote(), 55000, LocalDate.of(2026, 11, 20), EstadoP.IMPAGA, "2026-11", lote1, new ArrayList<>());

            expensas.add(exp1);
            expensas.add(exp2);
            lote1.agregarExpensa(exp1);
            lote1.agregarExpensa(exp2);

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("expensas.dat"))) {
                out.writeObject(expensas);
                System.out.println(" expensas.dat creado correctamente.");
            }

            // ==========================================
            // 5. CREAR Y GUARDAR AUTORIZACIONES DE VISITA
            // ==========================================
            List<AutorizacionVisita> autorizaciones = new ArrayList<>();

            AutorizacionVisita aut1 = new AutorizacionVisita(
                    1,
                    lote1.getId_Lote(),
                    res1.getIdResidente(),
                    "Pepe Gómez",
                    30123456,
                    LocalDateTime.now().minusDays(1),
                    false,
                    lote1,
                    res1,
                    new ArrayList<>()
            );
            aut1.setFechaHoraFin(LocalDateTime.now().plusDays(30));

            AutorizacionVisita aut2 = new AutorizacionVisita(
                    2,
                    lote1.getId_Lote(),
                    res1.getIdResidente(),
                    "María Técnica (Internet)",
                    28999888,
                    LocalDateTime.now().minusHours(2),
                    true,
                    lote1,
                    res1,
                    new ArrayList<>()
            );
            aut2.setFechaHoraFin(LocalDateTime.now().plusDays(7));

            autorizaciones.add(aut1);
            autorizaciones.add(aut2);
            lote1.agregarAutorizacion(aut1);
            lote1.agregarAutorizacion(aut2);
            res1.agregarAutorizacion(aut1);
            res1.agregarAutorizacion(aut2);

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("visitas.dat"))) {
                out.writeObject(autorizaciones);
                System.out.println(" visitas.dat creado correctamente.");
            }

            // ==========================================
            // 6. CREAR Y GUARDAR RECLAMO BASE
            // ==========================================
            List<Reclamo> reclamos = new ArrayList<>();
            Reclamo rec1 = new Reclamo(
                    1,
                    res1.getIdPersona(),
                    new Date(),
                    "Portón de acceso norte no cierra correctamente",
                    TipoP.SEGURIDAD,
                    lote1.getId_Lote(),
                    0,
                    res1,
                    lote1,
                    null,
                    new ArrayList<>(),
                    new ArrayList<>()
            );
            rec1.setEstadoActual(EstadoReclamo.ABIERTO);
            rec1.agregarHistorial(new HistorialEstadoReclamo(
                    1,
                    rec1.getIdReclamo(),
                    EstadoReclamo.ABIERTO,
                    new Date(),
                    "Reclamo registrado.",
                    rec1
            ));

            reclamos.add(rec1);
            res1.agregarReclamo(rec1);
            lote1.agregarReclamo(rec1);

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("reclamos.dat"))) {
                out.writeObject(reclamos);
                System.out.println(" reclamos.dat creado correctamente.");
            }

            // ==========================================
            // 7. GUARDAR LOTES (CON TODAS SUS RELACIONES)
            // ==========================================
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lotes.dat"))) {
                out.writeObject(lotes);
                System.out.println(" lotes.dat creado correctamente.");
            }

            System.out.println(" ¡Todos los datos de prueba fueron generados con éxito!");

        } catch (Exception e) {
            System.out.println(" Error al crear los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}