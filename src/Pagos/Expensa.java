package Pagos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Lotes.Lote;
import Enum.EstadoP;
public class Expensa {
    private int idExpensa;
    private int idLote;
    private int importe;
    private LocalDate fecha_vencimiento;
    private EstadoP estado;
    private String periodo;

    private Lote lote;
    private List<Pago> pagos = new ArrayList<>();

    public double calcularTotalCapitalPagado() {
        double total = 0;
        for (Pago pago : pagos) {
            total += pago.getImporte() - pago.getInteres();
        }
        return total;
    }

    public double calcularSaldoPendiente() {
        return importe - calcularTotalCapitalPagado();
    }

    public int getIdExpensa() {
        return idExpensa;
    }

    public void setIdExpensa(int idExpensa) {
        this.idExpensa = idExpensa;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public EstadoP getEstado() {
        return estado;
    }

    public void setEstado(EstadoP estado) {
        this.estado = estado;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public List<Pago> getPagos() {
        return List.copyOf(pagos);
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public Expensa(int idExpensa, int idLote, int importe, LocalDate fecha_vencimiento, EstadoP estado, String periodo, Lote lote, List<Pago> pagos) {
        this.idExpensa = idExpensa;
        this.idLote = idLote;
        this.importe = importe;
        this.fecha_vencimiento = fecha_vencimiento;
        this.estado = estado;
        this.periodo = periodo;
        this.lote = lote;
        if (pagos != null) {
            this.pagos = pagos;
        }
    }

    public void agregarPago(Pago pago) {
        if (pago != null && !pagos.contains(pago)) {
            pagos.add(pago);

            if (pago.getExpensa() != this) {
                pago.setExpensa(this);
            }
        }
    }

    public double calcularTotalInteresesPagados() {
        double total = 0;

        for (Pago pago : pagos) {
            total += pago.getInteres();
        }

        return total;
    }

}