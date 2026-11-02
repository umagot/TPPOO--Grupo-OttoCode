import java.util.Date;
import java.util.List;
import Enum.Estado;

public abstract class Equipo {
    protected int idEquipo;
    protected String tipo;
    protected String sector;
    protected Date fecha;
    protected Estado estado;
    protected List<Falla> fallas;
    protected List<OrdenDeMantenimiento> ordenesMantenimiento;

}