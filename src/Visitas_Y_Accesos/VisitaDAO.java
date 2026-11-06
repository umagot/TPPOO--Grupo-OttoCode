package Visitas_Y_Accesos;

import Datos.GestorArchivos;
import java.util.ArrayList;
import java.util.List;

public class VisitaDAO {

    private final String RUTA_ARCHIVO = "visitas.dat";
    private GestorArchivos conexion;
    private List<AutorizacionVisita> tablaVisitas;

    public VisitaDAO() {
        this.conexion = GestorArchivos.getInstancia();

        Object datosRecuperados = conexion.cargarDatos(RUTA_ARCHIVO);

        if (datosRecuperados != null) {
            this.tablaVisitas = (List<AutorizacionVisita>) datosRecuperados;
        } else {
            this.tablaVisitas = new ArrayList<>();
        }
    }

    public boolean guardar(AutorizacionVisita visita) {
        this.tablaVisitas.add(visita);
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaVisitas);
        return true;
    }

    public List<AutorizacionVisita> obtenerTodas() {
        return this.tablaVisitas;
    }

    public void actualizarArchivo() {
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaVisitas);
    }
}