import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Empleado extends  Persona{
    private int legajo;
    private LocalDate fechaIngreso;
    private String tipoPersonal;

    private final List<Reclamo> reclamosAsignados =  new ArrayList<>();

    private final List<OrdenMantenimiento> ordenesMantenimiento = new ArrayList<>();

    private final List<Registros> registrosGuardia = new ArrayList<>();

    public Empleado() {}

    public Empleado(int idPersona, String nombre, String apellido, int dni, String telefono, String correoElectronico, int legajo, LocalDate fechaIngreso, String tipoPersonal) {
        super(idPersona, nombre, apellido, dni, telefono, correoElectronico);
        this.legajo = legajo;
        this.fechaIngreso = fechaIngreso;
        this.tipoPersonal = tipoPersonal;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getTipoPersonal() {
        return tipoPersonal;
    }

    public void setTipoPersonal(String tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    public List<Reclamo> getReclamosAsignados() {
        return List.copyOf(reclamosAsignados);
    }
    public void asignarReclamo(Reclamo reclamo) {
        if (reclamo != null && !reclamosAsignados.contains(reclamo)){
            reclamosAsignados.add(reclamo);
        }
    }

    public List<OrdenMantenimiento> getOrdenesMantenimiento() {
        return List.copyOf(ordenesMantenimiento)
    }
    public void agregarOrden(OrdenMantenimiento orden) {
        if (orden != null && !ordenesMantenimiento.contains(orden)){
            ordenesMantenimiento.add(registrosGuardia);
        }

    }

    public List<Registros> getRegistrosGuardia() {
        return List.copyOf(registrosGuardia);
    }
    public void agregarRegistroGuardia(Registros registro){
        if (registro != null && !registrosGuardia.contains(registro)){
            registrosGuardia.add(registro)
        }
    }
}
