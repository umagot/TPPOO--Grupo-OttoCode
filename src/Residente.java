import java.util.List;
import Enum.Tipo;
import Enum.estadoR;
public class Residente extends Persona {
    private int IdResidente;
    private int idLote;
    private Tipo tipoResidente;
    private estadoR estadoReclamo;
    private Lote lote;
    private List<AutorizacionVisita> autorizacionesVisita;


}