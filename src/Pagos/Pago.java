package Pagos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Enum.MedioP;

public class Pago {
    private int idPago;
    private int idExpensa;
    private LocalDate fecha;
    private int importe;
    private MedioP MedioPago;
    private int interes;
    private Expensa expensa;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdExpensa() {
        return idExpensa;
    }

    public void setIdExpensa(int idExpensa) {
        this.idExpensa = idExpensa;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public MedioP getMedioPago() {
        return MedioPago;
    }

    public void setMedioPago(MedioP medioPago) {
        MedioPago = medioPago;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public Expensa getExpensa() {
        return expensa;
    }

    public void setExpensa(Expensa expensa) {
        this.expensa = expensa;
    }

    public Pago(int idPago, int idExpensa, LocalDate fecha, int importe, MedioP medioPago, int interes, Expensa expensa) {
        this.idPago = idPago;
        this.idExpensa = idExpensa;
        this.fecha = fecha;
        this.importe = importe;
        this.MedioPago = medioPago;
        this.interes = interes;
        this.expensa = expensa;
    }

    public int compareTo(Pago otroPago) {
        if (this.fecha == null || otroPago.getFecha() == null) {
            return 0;
        }
        return this.fecha.compareTo(otroPago.getFecha());
    }

    public static Pago registrarPagoEnExpensaMasVencida(
            int idPago,
            List<Expensa> listaExpensas,
            LocalDate fechaPago,
            int montoDisponible,
            MedioP medioPago,
            double tasaInteresDiaria
    ) {

        List<Expensa> pendientes = new ArrayList<>();
        for (Expensa e : listaExpensas) {
            if (e.calcularSaldoPendiente() > 0) {
                pendientes.add(e);
            }
        }

        if (pendientes.isEmpty()) {
            throw new PagoInvalidoExcepcion("No hay expensas pendientes de pago en la lista proporcionada.");
        }

        Collections.sort(pendientes);

        Expensa masVencida = pendientes.get(0);
        int montoAAplicar = montoDisponible;
        if (montoAAplicar > masVencida.calcularSaldoPendiente()) {
            montoAAplicar = (int) masVencida.calcularSaldoPendiente();
        }

        return masVencida.crearPago(idPago, fechaPago, montoAAplicar, medioPago, tasaInteresDiaria);
    }
}