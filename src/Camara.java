import java.util.Date;
import java.util.List;
import Enum.Estado;

public class Camara extends Equipo {
    private String resolucion;
    private int diasGrabacion;

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public int getDiasGrabacion() {
        return diasGrabacion;
    }

    public void setDiasGrabacion(int diasGrabacion) {
        this.diasGrabacion = diasGrabacion;
    }

    public Camara(int idEquipo, String tipo, String sector, Date fecha, Estado estado, List<Falla> fallas, List<OrdenDeMantenimiento> ordenesMantenimiento, String resolucion, int diasGrabacion) {
        super(idEquipo, tipo, sector, fecha, estado, fallas, ordenesMantenimiento);
        this.resolucion = resolucion;
        this.diasGrabacion = diasGrabacion;
    }
}