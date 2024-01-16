package Modelo.compras;
import Modelo.productos.*;
import java.io.Serializable;
import Modelo.personas.Cliente;
public class Factura implements Impuesto,Serializable {
    private Producto[] productoVendido;
    private Cliente cliente;
    private int numProductos=0;

    public Factura() {
        productoVendido=new Producto[10];
    }

    public Factura(Cliente c) {;
        productoVendido=new Producto[10];
        cliente=c;
    }
    public float calcularImpuestos(float sub){
        return calcularSubtotal()*iva;
    }
    public float calcularDescuento() {
        float descuento = 0;
        for (int i=0;i<numProductos;i++) {
            descuento += productoVendido[i].getDescuento();
        }
        return descuento;
    }
    public float calcularImpuestos() {
        return calcularSubtotal() * iva;
    }
    public float calcularSubtotal(){
        float subtotal=0.0F;
        for(int i=0;i<numProductos;i++){
            subtotal+=productoVendido[i].getPrecioVenta();
        }
        return subtotal;
    }
    public float calcularTotal() {
        return calcularSubtotal() - calcularDescuento() + calcularImpuestos();
    }
    public void addProducto(Producto p){
        productoVendido[numProductos]=p;
        numProductos++;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public int getNumProductos(){
        return this.numProductos;
    }
    public String imprimirFactura() {
        String resultado; 
        resultado="Factura\n" 
        +this.cliente.toString();
        for (int i = 0; i < numProductos; i++) {
            boolean encontrado = false;
            for (int j = 0; j < i; j++) {
                if (productoVendido[i].equals(productoVendido[j])) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                resultado += productoVendido[i].getInfoVenta() + "\n";
            }
        }
        resultado += "\nSubtotal: $" + calcularSubtotal() + "\n";
        resultado += "Descuento: $" + calcularDescuento() + "\n";
        resultado += "Impuestos: $" + calcularImpuestos() + "\n";
        resultado += "Total: $" + calcularTotal() + "\n";
    
        return resultado;
    }
    
    
    public Producto[] getProductos(){
        return productoVendido;
    }
}
