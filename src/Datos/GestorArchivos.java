package Datos; //Clase Gestora de archivos con Patron Singleton
import java.io.*;

public class GestorArchivos {

    // 1. Instancia única estática
    private static GestorArchivos instancia;

    // 2. Constructor privado (bloquea el 'new' desde afuera)
    private GestorArchivos() {
    }

    // 3.Metodo de acceso Global
    public static GestorArchivos getInstancia() {
        if (instancia == null) {
            instancia = new GestorArchivos();
        }
        return instancia;
    }

    // Escribir en el archivo
    public void guardarDatos(String rutaArchivo, Object datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(datos);
        } catch (IOException e) {
            System.err.println("Error al guardar en " + rutaArchivo + ": " + e.getMessage());
        }
    }

    // Leer del archivo
    public Object cargarDatos(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return null; // Si no existe (primera ejecución), retorna null
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return ois.readObject(); // Lee el objeto. Requiere capturar ClassNotFoundException
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer " + rutaArchivo + ": " + e.getMessage());
            return null;
        }
    }
}