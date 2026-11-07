
package Pagos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Lotes.Lote;
import Enum.EstadoP;
import Enum.MedioP;

public class ControladorExpensas {

    private ExpensaDAO dao;
    private List<Expensa> expensas;
  
    public ControladorExpensas() {
        this.dao = new ExpensaDAO();
        this.expensas = dao.obtenerTodas();
    }

    public ControladorExpensas(List<Expensa> expensas) {
        this.dao = new ExpensaDAO();
        this.expensas = dao.obtenerTodas();
    }

    public Expensa registrarExpensa(
            int idExpensa,
            Lote lote,
            int importe,
            LocalDate fechaVencimiento,
            String periodo) {

        if (lote == null) {
            throw new IllegalArgumentException(
                    "El lote no puede ser nulo.");
        }

        if (importe <= 0) {
            throw new IllegalArgumentException(
                    "El importe debe ser positivo.");
        }

        if (fechaVencimiento == null) {
            throw new IllegalArgumentException(
                    "La fecha de vencimiento es obligatoria.");
        }

        if (periodo == null || periodo.isBlank()) {
            throw new IllegalArgumentException(
                    "El período es obligatorio.");
        }

        if (buscarExpensa(idExpensa) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una expensa con ese ID.");
        }

        Expensa nuevaExpensa = new Expensa(
                idExpensa,
                lote.getId_Lote(),
                importe,
                fechaVencimiento,
                EstadoP.IMPAGA,
                periodo,
                lote,
                new ArrayList<>()
        );

        expensas.add(nuevaExpensa);
        lote.agregarExpensa(nuevaExpensa);

        guardarCambios();

        return nuevaExpensa;
    }

    public Pago registrarPago(
            int idExpensa,
            int idPago,
            LocalDate fecha,
            int importe,
            MedioP medioPago) {

        Expensa expensa = buscarExpensa(idExpensa);

        if (expensa == null) {
            throw new IllegalArgumentException(
                    "No existe una expensa con ese ID.");
        }

        if (fecha == null || medioPago == null) {
            throw new IllegalArgumentException(
                    "La fecha y el medio de pago son obligatorios.");
        }

       Pago pago = expensa.crearPago(idPago, fecha, importe, medioPago);

        guardarCambios();

        return pago;
    }

    public Expensa buscarExpensa(int idExpensa) {

        for (Expensa expensa : expensas) {

            if (expensa.getIdExpensa() == idExpensa) {
                return expensa;
            }
        }

        return null;
    }

    public List<Expensa> obtenerExpensasPorLote(int idLote) {

        List<Expensa> resultado = new ArrayList<>();

        for (Expensa expensa : expensas) {

            if (expensa.getIdLote() == idLote) {
                resultado.add(expensa);
            }
        }

        return resultado;
    }

    public List<Expensa> obtenerExpensasPendientes() {

        List<Expensa> pendientes = new ArrayList<>();

        for (Expensa expensa : expensas) {

            if (expensa.calcularSaldoPendiente() > 0) {
                pendientes.add(expensa);
            }
        }

        return pendientes;
    }

    public double calcularDeudaLote(int idLote) {

        double deuda = 0;

        for (Expensa expensa : expensas) {

            if (expensa.getIdLote() == idLote) {
                deuda += expensa.calcularSaldoPendiente();
            }
        }

        return deuda;
    }

    public List<Expensa> getExpensas() {
        return List.copyOf(expensas);
    }
    private void guardarCambios() {
        dao.actualizarArchivo();
    }
}
