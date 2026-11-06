package Pagos;

public class PagoInvalidoExcepcion extends RuntimeException {
    public PagoInvalidoExcepcion(String message) {
        super(message);
    }
}

