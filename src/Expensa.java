import java.util.Date;
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
    private List<Pago> pagos;

    public double calcularTotalPagado() {
        return 0.0;
    }

    public double calcularSaldoPendiente() {
        return 0.0;
    }

    public Pago crearPago(String datos) {
        return null;
    }

}