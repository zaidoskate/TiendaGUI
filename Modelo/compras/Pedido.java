package Modelo.compras;
import Modelo.productos.*;

import java.io.Serializable;

import Modelo.personas.Proveedor;
public class Pedido implements Impuesto, Serializable {
    private Producto producto;
    private Proveedor proveedor;

    public Pedido() {
    }

    public Pedido(Producto producto, Proveedor proveedor) {
        this.producto = producto;
        this.proveedor = proveedor;
    }

    public double calcularImpuestos() {
        return producto.getPrecioVenta() * iva;
    }

    public double calcularTotal() {
        int cantidad=(10-producto.getCantidad());
        return (producto.getPrecioCompra() + calcularImpuestos())*cantidad;
    }

    public boolean consultarStock(){
        if(this.producto.getCantidad()<=5){
            return true;
        }else
            return false;
    }
    public void actualizarStock(){
        if(consultarStock()){
            producto.setCantidad(10);
        }
    }

    public String imprimirPedido() {
        String salida;
        int cantidad=(10-producto.getCantidad());
        salida="\nPedido\n"
        +this.proveedor.toString()
        +"\n\tProducto\t\tPrecio Venta\t\tPrecio Compra\t\tDescuento";
        salida+="\n\t--------\t\t--------\t---------";
        salida+="\n\t"+producto.getDescripcion() + "\t\t" + producto.getPrecioVenta() + "\t\t" + producto.getPrecioCompra() + producto.getDescuento()+
        "\n\nCantidad del pedido: "+cantidad+" unidades"+
        "\nTotal del pedido:"+calcularTotal()+"\n\n";
        return salida;
    }

    public Proveedor getProveedor(){
        return this.proveedor;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
