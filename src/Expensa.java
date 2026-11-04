import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import Enum.Estado;

public class Expensa {
    private int idExpensa;
    private int idLote;
    private int importe;
    private Date fecha_vencimiento;
    private Estado estado;
    private String periodo;

    private Lote lote;
    private List<Pago> pagos = new ArrayList<>();

    public double calcularTotalPagado() {
        return 0.0;
    }

    public double calcularSaldoPendiente() {
        return 0.0;
    }

    public Pago crearPago(String datos) {
        return null;
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

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public Expensa(int idExpensa, int idLote, int importe, Date fecha_vencimiento, Estado estado, String periodo, Lote lote, List<Pago> pagos) {
        this.idExpensa = idExpensa;
        this.idLote = idLote;
        this.importe = importe;
        this.fecha_vencimiento = fecha_vencimiento;
        this.estado = estado;
        this.periodo = periodo;
        this.lote = lote;
        this.pagos = pagos;
    }
    public void agregarPago(Pago pago) {
        if (pago != null && !pagos.contains(pago)) {
            pagos.add(pago);

            if (pago.getExpensa() != this) {
                pago.setExpensa(this);
            }
        }
    }
        
     public Pago crearPago(int idPago, Date fecha, int importe, String medioPago, double tasaInteresDiaria) {
         if (importe <= 0) {
             throw new IllegalArgumentException("El importe debe ser positivo.");
        }
         if (importe > calcularSaldoPendiente()) {
             throw new IllegalArgumentException("El pago supera el saldo pendiente.");
        }
     }           
}
