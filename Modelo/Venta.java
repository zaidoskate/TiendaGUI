package Modelo;
import Modelo.compras.Factura;
import Modelo.compras.Pedido;
import java.io.Serializable;
public class Venta implements Serializable{
    private Factura factura;
	private Pedido pedido;
	
	public Venta(Factura fac){
		factura=fac;
	}
	public Venta (Pedido ped){
		pedido=ped;
	}
	public Factura getFactura(){
		return factura;
	}
	public Pedido getPedido(){
		return pedido;
	}
	public void setFactura(Factura fac){
		factura= fac;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Venta){
			Venta tmpVenta=(Venta)obj;
			if(this.factura.equals(tmpVenta.factura) ){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
