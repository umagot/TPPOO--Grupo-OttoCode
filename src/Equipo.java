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

    public Equipo(int idEquipo, String tipo, String sector, Date fecha, Estado estado, List<Falla> fallas, List<OrdenDeMantenimiento> ordenesMantenimiento) {
        this.idEquipo = idEquipo;
        this.tipo = tipo;
        this.sector = sector;
        this.fecha = fecha;
        this.estado = estado;
        this.fallas = fallas;
        this.ordenesMantenimiento = ordenesMantenimiento;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Falla> getFallas() {
        return fallas;
    }

    public void setFallas(List<Falla> fallas) {
        this.fallas = fallas;
    }

    public List<OrdenDeMantenimiento> getOrdenesMantenimiento() {
        return ordenesMantenimiento;
    }

    public void setOrdenesMantenimiento(List<OrdenDeMantenimiento> ordenesMantenimiento) {
        this.ordenesMantenimiento = ordenesMantenimiento;
    }
}