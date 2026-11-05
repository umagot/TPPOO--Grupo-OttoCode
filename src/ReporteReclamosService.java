
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Enum.Estado;
import Enum.EstadoReclamo;

public class ReporteReclamosService {

    private List<Reclamo> reclamos;
    private List<Expensa> expensas;
    private List<Equipo> equipos;

    public ReporteReclamosService(
            List<Reclamo> reclamos,
            List<Expensa> expensas,
            List<Equipo> equipos) {

        this.reclamos = new ArrayList<>(reclamos);
        this.expensas = new ArrayList<>(expensas);
        this.equipos = new ArrayList<>(equipos);
    }

    public List<Reclamo> generarReporteReclamosPendientes() {

        List<Reclamo> pendientes = new ArrayList<>();

        for (Reclamo reclamo : reclamos) {

            List<HistorialEstadoReclamo> historial =
                    reclamo.getHistorial();

            if (historial == null || historial.isEmpty()) {
                pendientes.add(reclamo);
            } else {

                EstadoReclamo ultimoEstado =
                        historial.get(historial.size() - 1).getNuevoEstado();

                if (ultimoEstado != EstadoReclamo.RESUELTO
                        && ultimoEstado != EstadoReclamo.CERRADO) {

                    pendientes.add(reclamo);
                }
            }
        }

        return pendientes;
    }
    public double generarReporteMorosidad() {

        double totalMorosidad = 0;
        Date fechaActual = new Date();

        for (Expensa expensa : expensas) {

            if (expensa.getFecha_vencimiento() != null
                    && expensa.getFecha_vencimiento().before(fechaActual)
                    && expensa.calcularSaldoPendiente() > 0) {

                totalMorosidad += expensa.calcularSaldoPendiente();
            }
        }

        return totalMorosidad;
    }

    public List<Equipo> generarReporteEquiposFueraServicio() {

        List<Equipo> equiposFueraServicio = new ArrayList<>();

        for (Equipo equipo : equipos) {

            if (equipo.getEstado() == Estado.FUERA_DE_SERVICIO) {
                equiposFueraServicio.add(equipo);
            }
        }

        return equiposFueraServicio;
    }
}

}
