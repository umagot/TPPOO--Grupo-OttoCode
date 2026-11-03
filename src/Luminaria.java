
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Luminaria extends Equipo {
    private TipoLampara tipoLampara;
    private String potencia;

    public Luminaria(int idEquipo, String sector, LocalDate fechaInstalacion, TipoLampara tipoLampara, String potencia) {
        super(idEquipo, "LUMINARIA", sector, fechaInstalacion);
        this.tipoLampara = tipoLampara;
        this.potencia = potencia;
    }

    @Override
    public Prioridad calcularPrioridadMantenimiento(int fallasActivasEnSector) {
        // Regla: Prioridad normal, que pasa a alta si fallan más de 3 luminarias del mismo sector
        return (fallasActivasEnSector > 3) ? Prioridad.ALTA : Prioridad.NORMAL;
    }

    @Override
    public boolean requiereAlertaAdministrador(LocalDateTime fechaActual) {
        return false;
    }

    public TipoLampara getTipoLampara() { return tipoLampara; }
    public String getPotencia() { return potencia; }

    public void setTipoLampara(TipoLampara tipoLampara) {
        this.tipoLampara = tipoLampara;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }
}