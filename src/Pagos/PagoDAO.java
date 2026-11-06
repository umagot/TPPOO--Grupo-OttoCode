package Pagos;

import Datos.GestorArchivos;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    private final String RUTA_ARCHIVO = "pagos.dat";
    private GestorArchivos conexion;
    private List<Pago> tablaPagos;

    public PagoDAO() {
        this.conexion = GestorArchivos.getInstancia();

        Object datosRecuperados = conexion.cargarDatos(RUTA_ARCHIVO);

        if (datosRecuperados != null) {
            this.tablaPagos = (List<Pago>) datosRecuperados;
        } else {
            this.tablaPagos = new ArrayList<>();
        }
    }

    public boolean guardar(Pago pago) {
        this.tablaPagos.add(pago);
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaPagos);
        return true;
    }

    public List<Pago> obtenerTodos() {
        return this.tablaPagos;
    }

    public void actualizarArchivo() {
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaPagos);
    }
}