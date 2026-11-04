import java.util.Date;
import Enum.EstadoReclamo;

    public class HistorialEstadoReclamo {
        private int idHistorial;
        private int idReclamo;
        private EstadoReclamo nuevoEstado;
        private Date fechaCambio;
        private String Observacion;
        private Reclamo reclamo;

        public int getIdHistorial() {
            return idHistorial;
        }

        public void setIdHistorial(int idHistorial) {
            this.idHistorial = idHistorial;
        }

        public int getIdReclamo() {
            return idReclamo;
        }

        public void setIdReclamo(int idReclamo) {
            this.idReclamo = idReclamo;
        }

        public EstadoReclamo getNuevoEstado() {
            return nuevoEstado;
        }

        public void setNuevoEstado(EstadoReclamo nuevoEstado) {
            this.nuevoEstado = nuevoEstado;
        }

        public Date getFechaCambio() {
            return fechaCambio;
        }

        public void setFechaCambio(Date fechaCambio) {
            this.fechaCambio = fechaCambio;
        }

        public String getObservacion() {
            return Observacion;
        }

        public void setObservacion(String observacion) {
            Observacion = observacion;
        }

        public Reclamo getReclamo() {
            return reclamo;
        }

        public void setReclamo(Reclamo reclamo) {
            this.reclamo = reclamo;
        }

        public HistorialEstadoReclamo(int idHistorial, int idReclamo, EstadoReclamo nuevoEstado, Date fechaCambio, String observacion, Reclamo reclamo) {
            this.idHistorial = idHistorial;
            this.idReclamo = idReclamo;
            this.nuevoEstado = nuevoEstado;
            this.fechaCambio = fechaCambio;
            Observacion = observacion;
            this.reclamo = reclamo;
        }
    }
