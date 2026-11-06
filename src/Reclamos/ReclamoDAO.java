package Reclamos;

import Datos.GestorArchivos;
import java.util.ArrayList;
import java.util.List;

public class ReclamoDAO {

    private final String RUTA_ARCHIVO = "reclamos.dat"; // Usamos .dat para archivos serializados
    private GestorArchivos conexion;
    private List<Reclamo> tablaReclamos; // Esta es la "tabla" en memoria RAM


    public ReclamoDAO() {

        this.conexion = GestorArchivos.getInstancia();


        Object datosRecuperados = conexion.cargarDatos(RUTA_ARCHIVO);

        if (datosRecuperados != null) {

            this.tablaReclamos = (List<Reclamo>) datosRecuperados;
        } else {

            this.tablaReclamos = new ArrayList<>();
        }
    }

    public boolean guardar(Reclamo reclamo) {

        this.tablaReclamos.add(reclamo);


        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaReclamos);

        return true;
    }


    public List<Reclamo> obtenerTodos() {
        return this.tablaReclamos;
    }

    public void actualizarArchivo() {
        conexion.guardarDatos(RUTA_ARCHIVO, this.tablaReclamos);
    }
}