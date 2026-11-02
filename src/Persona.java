import java.util.Date;

public abstract class Persona {
    protected int idPersona;
    protected String Nombre;
    protected String Apellido;
    protected int DNI;
    protected int Telefono;
    protected String correo_electronico;

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public Persona(int idPersona, String nombre, String apellido, int DNI, int telefono, String correo_electronico) {
        this.idPersona = idPersona;
        Nombre = nombre;
        Apellido = apellido;
        this.DNI = DNI;
        Telefono = telefono;
        this.correo_electronico = correo_electronico;
    }
}