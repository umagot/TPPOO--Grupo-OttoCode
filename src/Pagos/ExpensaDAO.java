package Pagos; // Acordate de poner tu paquete real

import Datos.GestorArchivos;
import java.util.ArrayList;
import java.util.List;

public class ExpensaDAO {

    private final String RUTA_ARCHIVO = "expensas.dat";
    private GestorArchivos conexion;
    private List<Expensa> tablaExpensas;

    public ExpensaDAO() {
        this.conexion = GestorArchivos.getInstancia();

        Object datosRecuperados = conexion.cargarDatos(RUTA_ARCHIVO);

        if (datosRecuperados != null) {
            this.tablaExpensas = (List<Expensa>) datosRecuperados;
        } else {
            this.tablaExpensas = new ArrayList<>();
        }
    }

    public boolean guardar(Expensa expensa) {
        this.tablaExpensas.add(expensa);
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaExpensas);
        return true;
    }

    public List<Expensa> obtenerTodas() {
        return this.tablaExpensas;
    }

    public void actualizarArchivo() {
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaExpensas);
    }
}