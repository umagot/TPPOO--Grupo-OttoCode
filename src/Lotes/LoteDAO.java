package Lotes;

import Datos.GestorArchivos;
import java.util.ArrayList;
import java.util.List;

public class LoteDAO {

    private final String RUTA_ARCHIVO = "lotes.dat";
    private GestorArchivos conexion;
    private List<Lote> tablaLotes;

    public LoteDAO() {
        this.conexion = GestorArchivos.getInstancia();

        Object datosRecuperados = conexion.cargarDatos(RUTA_ARCHIVO);

        if (datosRecuperados != null) {
            this.tablaLotes = (List<Lote>) datosRecuperados;
        } else {
            this.tablaLotes = new ArrayList<>();
        }
    }

    public boolean guardar(Lote lote) {
        this.tablaLotes.add(lote);
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaLotes);
        return true;
    }

    public List<Lote> obtenerTodos() {
        return this.tablaLotes;
    }

    public void actualizarArchivo() {
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaLotes);
    }
}