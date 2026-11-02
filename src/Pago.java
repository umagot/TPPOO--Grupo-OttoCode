import java.util.Date;
import Enum.MedioP;
public class Pago {
    private int idPago;
    private int idExpensa;
    private Date fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public Pago(int idPago, int idExpensa, Date fecha, int importe, MedioP medioPago, int interes, Expensa expensa) {
        this.idPago = idPago;
        this.idExpensa = idExpensa;
        this.fecha = fecha;
        this.importe = importe;
        MedioPago = medioPago;
        this.interes = interes;
        this.expensa = expensa;
    }
}