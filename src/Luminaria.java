import Enum.TipoL;
import Enum.Estado;
import java.util.Date;
import java.util.List;

public class Luminaria extends Equipo {
    private TipoL tipoLampara;
    private String potencia;

    public TipoL getTipoLampara() {
        return tipoLampara;
    }

    public void setTipoLampara(TipoL tipoLampara) {
        this.tipoLampara = tipoLampara;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public Luminaria(int idEquipo, String tipo, String sector, Date fecha, Estado estado, List<Falla> fallas, List<OrdenDeMantenimiento> ordenesMantenimiento, TipoL tipoLampara, String potencia) {
        super(idEquipo, tipo, sector, fecha, estado, fallas, ordenesMantenimiento);
        this.tipoLampara = tipoLampara;
        this.potencia = potencia;
    }
}