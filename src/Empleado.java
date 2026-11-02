import java.util.Date;
import java.util.List;
import Enum.TipoP;


public class Empleado extends Persona {
    private int Legajo;
    private Date fechaIngreso;
    private TipoP tipo_personal;

    private List<OrdenDeMantenimiento> ordenesMantenimiento;
    private List<Reclamo> reclamosAsignados;
}