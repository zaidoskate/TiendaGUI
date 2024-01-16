package Modelo.productos;
import Modelo.personas.Proveedor;
public class Electronico extends Producto{
    private String numeroSerie;


    public Electronico() {
    }

    public Electronico(String codigo, float precioVenta, float precioCompra, float descu, String des, Proveedor prov, int cant, String numeroSerie) {
        super(codigo, precioVenta, precioCompra, descu, des, prov, cant);
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
    public String toString() {
        return super.toString()+
            "\nNumero de serie del producto: " + getNumeroSerie();
    }
    public boolean equals(Object obj){
		if(obj instanceof Electronico){
			Electronico tmpElectronico = (Electronico) obj;
			if(super.equals(tmpElectronico) && this.numeroSerie.equals(tmpElectronico.numeroSerie)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
   

   
