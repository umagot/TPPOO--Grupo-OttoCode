import java.util.Date;
import java.util.List;
import Enum.TipoP;

public class Reclamo {
    private int idReclamo;
    private int idPersona;
    private Date fecha;
    private String descripcion;
    private TipoP tipo;
    private int idLote;
    private int idEmpleado;
    private Persona persona;
    private Lote lote;
    private Empleado empleado;
    private List<HistorialEstadoReclamo> historial;
    private List<ComentarioReclamo> comentarios;


}