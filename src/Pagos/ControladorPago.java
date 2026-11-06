package Pagos;

import java.util.ArrayList;
import java.util.List;

public class ControladorPago {

    private List<Pago> pagos;
    private int siguienteIdPago;

    public ControladorPago() {
        this.pagos = new ArrayList<>();
        this.siguienteIdPago = 1;
    }

    public Pago buscarPago(int idPago) {
        for (Pago pago : pagos) {
            if (pago.getIdPago() == idPago) {
                return pago;
            }
        }

        return null;
    }

    public void agregarPago(Pago pago) {
        if (pago == null) {
            throw new IllegalArgumentException("El pago no puede ser null.");
        }

        if (buscarPago(pago.getIdPago()) != null) {
            throw new IllegalArgumentException("El pago ya está registrado.");
        }

        pagos.add(pago);
    }

    public List<Pago> getPagos() {
        return List.copyOf(pagos);
    }
}