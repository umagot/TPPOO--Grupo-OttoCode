
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Lotes.Lote;
import Lotes.Residente;
import Lotes.Empleado;
import Mantenimiento.Equipo;
import Mantenimiento.Camara;
import Mantenimiento.Luminaria;
import Enum.Tipo;
import Enum.TipoP;
import Enum.TipoL;

public class CrearDatosPrueba {

    public static void main(String[] args) {
        try {
            // ==========================================
            // 1. CREAR Y GUARDAR LOTES Y RESIDENTES
            // ==========================================
            List<Lote> lotes = new ArrayList<>();
            Lote lote1 = new Lote(101, 450, 1001, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            Residente res1 = new Residente(1001, "Juan", "Pérez", 30111222, "11-5555-1111", "juan@example.com", 1, Tipo.GENERAL, lote1);
            lote1.agregarResidente(res1);
            lotes.add(lote1);

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lotes.dat"))) {
                out.writeObject(lotes);
                System.out.println(" lotes.dat creado correctamente.");
            }

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
            // 3. CREAR Y GUARDAR EMPLEADOS
            // ==========================================
            List<Empleado> empleados = new ArrayList<>();
            empleados.add(new Empleado(1, "Carlos", "Mantenimiento", 20000000, "11223344", "carlos@barrio.com", 12345, LocalDate.parse("2026-10-06"), TipoP.MANTENIMIENTO));

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("empleados.dat"))) {
                out.writeObject(empleados);
                System.out.println(" empleados.dat creado correctamente.");
            }

            System.out.println(" ¡Todos los datos de prueba fueron generados con éxito!.");

        } catch (Exception e) {
            System.out.println(" Error al crear los datos: " + e.getMessage());
        }
    }
}