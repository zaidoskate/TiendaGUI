package Modelo.personas;
public class Proveedor extends Persona{
    private String telefono;


    public Proveedor() {
    }

    public Proveedor(String nombre, String correo, String telefono) {
        super(nombre, correo);
        this.telefono = telefono;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String toString() {
        return "\n\tProveedor: "+nombre+"\n\tcorreo: "+correo+"\n\ttelefono: "+telefono;
    }
    public boolean equals(Object obj){
		if(obj instanceof Proveedor){
			Proveedor tmppProveedor = (Proveedor) obj;
			if(super.equals(tmppProveedor) && this.nombre.equals(tmppProveedor.nombre)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

}