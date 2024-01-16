package Modelo.personas;
import java.io.Serializable;
public abstract class Persona implements Serializable{
    protected String nombre;
    protected String correo;

    public Persona() {
    }

    public Persona(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
    
