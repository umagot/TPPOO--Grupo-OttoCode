import java.util.List;
import java.util.ArrayList;

public class Lote {
    private int id_Lote;
    private int Superficie;
    private int idTitular;

    private List<Residente> residentes= new ArrayList<>();
    private List<Expensa> expensas= new ArrayList<>();
    private List<Reclamo> reclamos= new ArrayList<>();
    private List<AutorizacionVisita> autorizaciones = new ArrayList<>();

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
        return List.copyOf(residentes);
    }

    public void setResidentes(List<Residente> residentes) {
        this.residentes = residentes;
    }

    public List<Expensa> getExpensas() {
        return List.copyOf(expensas);
    }

    public void setExpensas(List<Expensa> expensas) {
        this.expensas = expensas;
    }

    public List<Reclamo> getReclamos() {
        return List.copyOf(reclamos);
    }

    public void setReclamos(List<Reclamo> reclamos) {
        this.reclamos = reclamos;
    }

    public List<AutorizacionVisita> getAutorizaciones() {
        return List.copyOf(autorizaciones);
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
    }

   public Lote(int id_Lote, int superficie, int idTitular,
            List<Residente> residentes,
            List<Expensa> expensas,
            List<Reclamo> reclamos,
            List<AutorizacionVisita> autorizaciones) {

    this.id_Lote = id_Lote;
    this.Superficie = superficie;
    this.idTitular = idTitular;

    if (residentes != null) {
        this.residentes = new ArrayList<>(residentes);
    } else {
        this.residentes = new ArrayList<>();
    }

    if (expensas != null) {
        this.expensas = new ArrayList<>(expensas);
    } else {
        this.expensas = new ArrayList<>();
    }

    if (reclamos != null) {
        this.reclamos = new ArrayList<>(reclamos);
    } else {
        this.reclamos = new ArrayList<>();
    }

    if (autorizaciones != null) {
        this.autorizaciones = new ArrayList<>(autorizaciones);
    } else {
        this.autorizaciones = new ArrayList<>();
    }
}

    public void agregarResidente(Residente residente) {
    if (residente != null && !residentes.contains(residente)) {
        residentes.add(residente);
        residente.setLote(this);
        residente.setIdLote(this.id_Lote);
    }
}

public void agregarExpensa(Expensa expensa) {
    if (expensa != null && !expensas.contains(expensa)) {
        expensas.add(expensa);
    }
}

    public void agregarReclamo(Reclamo reclamo) {
        if (reclamo != null && !reclamos.contains(reclamo)) {
            reclamos.add(reclamo);

            if (reclamo.getLote() != this) {
                reclamo.setLote(this);
            }
        }
    }
}
