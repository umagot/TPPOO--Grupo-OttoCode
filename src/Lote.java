import java.util.List;

public class Lote {
    private int id_Lote;
    private int Superficie;
    private int idTitular;

    private List<Residente> residentes;
    private List<Expensa> expensas;
    private List<Reclamo> reclamos;
    private List<AutorizacionVisita> autorizaciones;

    public int getId_Lote() {
        return id_Lote;
    }

    public void setId_Lote(int id_Lote) {
        this.id_Lote = id_Lote;
    }

    public int getSuperficie() {
        return Superficie;
    }

    public void setSuperficie(int superficie) {
        Superficie = superficie;
    }

    public int getIdTitular() {
        return idTitular;
    }

    public void setIdTitular(int idTitular) {
        this.idTitular = idTitular;
    }

    public List<Residente> getResidentes() {
        return residentes;
    }

    public void setResidentes(List<Residente> residentes) {
        this.residentes = residentes;
    }

    public List<Expensa> getExpensas() {
        return expensas;
    }

    public void setExpensas(List<Expensa> expensas) {
        this.expensas = expensas;
    }

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<Reclamo> reclamos) {
        this.reclamos = reclamos;
    }

    public List<AutorizacionVisita> getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(List<AutorizacionVisita> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    public void agregarAutorizacion(AutorizacionVisita autorizacion) {
        if (autorizacion != null
                && !autorizaciones.contains(autorizacion)) {
            autorizaciones.add(autorizacion);

            if (autorizacion.getLote() != this) {
                autorizacion.setLote(this);
            }
        }

    public Lote(int id_Lote, int superficie, int idTitular, List<Residente> residentes, List<Expensa> expensas, List<Reclamo> reclamos, List<AutorizacionVisita> autorizaciones) {
        this.id_Lote = id_Lote;
        Superficie = superficie;
        this.idTitular = idTitular;
        this.residentes = residentes;
        this.expensas = expensas;
        this.reclamos = reclamos;
        this.autorizaciones = autorizaciones;
    }
}
