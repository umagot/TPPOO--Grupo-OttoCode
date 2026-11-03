import java.time.LocalDate;
import java.time.LocalDateTime;
import Enum.TipoL;
import Enum.Prioridad;

public class Luminaria extends Equipo {
    private TipoL tipoLampara;
    private String potencia;

    public Luminaria(int idEquipo, String sector, LocalDate fechaInstalacion, TipoL tipoLampara, String potencia) {
        super(idEquipo, "LUMINARIA", sector, fechaInstalacion);
        this.tipoLampara = tipoLampara;
        this.potencia = potencia;
    }

    @Override
    public Prioridad calcularPrioridadMantenimiento(int fallasActivasEnSector) {
        // Regla: Prioridad normal, que pasa a alta si fallan más de 3 luminarias del mismo sector
        return (fallasActivasEnSector > 3) ? Prioridad.ALTA : Prioridad.MEDIA;
    }

    @Override
    public boolean requiereAlertaAdministrador(LocalDateTime fechaActual) {
        return false;
    }

    public TipoL getTipoLampara() { return tipoLampara; }
    public String getPotencia() { return potencia; }

    public void setTipoLampara(TipoL tipoLampara) {
        this.tipoLampara = tipoLampara;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }
}