package Lotes;

import Pagos.Expensa;
import Reclamos.Reclamo;
import Visitas_Y_Accesos.AutorizacionVisita;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import Enum.EstadoP;
import java.io.Serializable
    
public class Lote {
    private int id_Lote;
    private int Superficie;
    private int idTitular;
    private boolean esMoroso;

    private List<Residente> residentes = new ArrayList<>();
    private List<Expensa> expensas = new ArrayList<>();
    private List<Reclamo> reclamos = new ArrayList<>();
    private List<AutorizacionVisita> autorizaciones = new ArrayList<>();

    public int getId_Lote() { return id_Lote; }
    public void setId_Lote(int id_Lote) { this.id_Lote = id_Lote; }
    public int getSuperficie() { return Superficie; }
    public void setSuperficie(int superficie) { Superficie = superficie; }
    public int getIdTitular() { return idTitular; }
    public void setIdTitular(int idTitular) { this.idTitular = idTitular; }
    public boolean isEsMoroso() { return esMoroso; }

    public List<Residente> getResidentes() { return List.copyOf(residentes); }
    public void setResidentes(List<Residente> residentes) { this.residentes = residentes; }
    public List<Expensa> getExpensas() { return expensas; }
    public void setExpensas(List<Expensa> expensas) { this.expensas = expensas; }
    public List<Reclamo> getReclamos() { return List.copyOf(reclamos); }
    public void setReclamos(List<Reclamo> reclamos) { this.reclamos = reclamos; }
    public List<AutorizacionVisita> getAutorizaciones() { return List.copyOf(autorizaciones); }
    public void setAutorizaciones(List<AutorizacionVisita> autorizaciones) { this.autorizaciones = autorizaciones; }

    public Lote(int id_Lote, int superficie, int idTitular,
                List<Residente> residentes,
                List<Expensa> expensas,
                List<Reclamo> reclamos,
                List<AutorizacionVisita> autorizaciones) {

        this.id_Lote = id_Lote;
        this.Superficie = superficie;
        this.idTitular = idTitular;
        this.esMoroso = false;

        if (residentes != null) this.residentes = new ArrayList<>(residentes);
        if (expensas != null) this.expensas = new ArrayList<>(expensas);
        if (reclamos != null) this.reclamos = new ArrayList<>(reclamos);
        if (autorizaciones != null) this.autorizaciones = new ArrayList<>(autorizaciones);
    }


    public Expensa emitirExpensaMensual(int idExpensa, String periodo, int presupuestoTotalBarrio, int superficieTotalBarrio) {
        double coeficiente = (double) this.Superficie / superficieTotalBarrio;
        int importeExpensa = (int) (presupuestoTotalBarrio * coeficiente);

        String[] partes = periodo.split("-");
        int anio = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        LocalDate fechaVencimiento = LocalDate.of(anio, mes, 10);

        Expensa nuevaExpensa = new Expensa(idExpensa, this.id_Lote, importeExpensa, fechaVencimiento, EstadoP.PENDIENTE, periodo, this, new ArrayList<>());
        agregarExpensa(nuevaExpensa);

        verificarEstadoMorosidad();
        return nuevaExpensa;
    }

    public void verificarEstadoMorosidad() {
        int periodosImpagos = 0;
        for (Expensa e : expensas) {
            if (e.calcularSaldoPendiente() > 0) {
                periodosImpagos++;
            }
        }

        if (periodosImpagos > 3) {
            if (!this.esMoroso) {
                this.esMoroso = true;
                notificarTitular("NOTIFICACIÓN FORMAL: El Lote " + id_Lote + " registra " + periodosImpagos + " períodos impagos. Estado: MOROSO.");
            }
        } else {
            if (this.esMoroso) {
                this.esMoroso = false;
                notificarTitular("AVISO: Deuda regularizada. Se retira la condición de MOROSO.");
            }
        }
    }

    private void notificarTitular(String mensaje) {
        System.out.println("[TITULAR ID " + idTitular + "]: " + mensaje);
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
            if (expensa.getLote() != this) {
                expensa.setLote(this);
            }
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

    public void agregarAutorizacion(AutorizacionVisita autorizacion) {
        if (autorizacion != null && !autorizaciones.contains(autorizacion)) {
            autorizaciones.add(autorizacion);
            if (autorizacion.getLote() != this) {
                autorizacion.setLote(this);
            }
        }
    }

    public List<Expensa> getExpensasOrdenadasPorImporte() {
        List<Expensa> listaOrdenada = new ArrayList<>(expensas);
        listaOrdenada.sort(Expensa.porImporteDescendente);
        return listaOrdenada;
    }
}
