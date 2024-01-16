package Modelo.personas;
public class Cliente extends Persona{
    private String direccion;
    private String rfc;


    public Cliente() {
    }

    public Cliente(String nombre, String correo, String direccion, String rfc) {
        super(nombre,correo);
        this.direccion = direccion;
        this.rfc = rfc;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String toString() {
        return "\n\tCliente: "+nombre+"\n\tCorreo: "+correo+"\n\tDireccion: "+direccion+"\n\tRFC del cliente: "+rfc;
    }
    public boolean equals(Object obj) {
        if (obj instanceof Cliente) {
            Cliente tmpCliente = (Cliente) obj;
            if (super.equals(tmpCliente) && this.rfc.equals(tmpCliente.getRfc())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}