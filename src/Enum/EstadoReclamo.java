package Enum;

public enum EstadoReclamo {
    ABIERTO, ASIGNADO, EN_CURSO, RESUELTO, CERRADO;

    // Regla de negocio: solo se puede avanzar al estado siguiente
    public boolean puedeAvanzarA(EstadoReclamo siguiente) {
        return switch (this) {
            case ABIERTO -> siguiente == ASIGNADO;
            case ASIGNADO -> siguiente == EN_CURSO;
            case EN_CURSO -> siguiente == RESUELTO;
            case RESUELTO -> siguiente == CERRADO || siguiente == EN_CURSO; // Reapertura dentro de 5 días
            case CERRADO -> false;
        };
    }
}