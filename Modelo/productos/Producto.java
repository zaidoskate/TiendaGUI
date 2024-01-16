package Modelo.productos;

import java.io.Serializable;

import Modelo.personas.Proveedor;
public class Producto implements Serializable{
    protected String codigo;
    protected float precioVenta;
    protected float precioCompra;
    protected float descuento;
    protected String descripcion;
    protected Proveedor proveedor;
    protected int cantidad;

    public Producto(){

    }

    public Producto(String codigo, float precioVenta,float precioCompra, float descuento, String descripcion, Proveedor proveedor, int cantidad) {
        this.codigo = codigo;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.descuento = descuento;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
    }

    public float getPrecioCompra(){
        return this.precioCompra;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getDescuento() {
        return this.descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proveedor getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String toString() {
        return "Producto:"+
            "\nCodigo: " + this.codigo +
            "\nDescripcion: " + this.descripcion +
            "\nProveedor: " + this.proveedor.getNombre() +
            "\nPrecio de venta: " + this.precioVenta +
            "\nPrecio de Compra: " + this.precioCompra+
            "\nDescuento: " + this.descuento +
            "\nCantidad: " + this.cantidad;
    }

    public String getInfoVenta(){
        return "\nProducto:"+
        "\n\tCodigo: " + this.codigo +
        "\n\tDescripcion: " + this.descripcion +
        "\n\tProveedor: " + this.proveedor.getNombre() +
        "\n\tPrecio de venta: " + this.precioVenta;
    }
    public boolean equals(Object obj){
        if(obj instanceof Producto){
            Producto tmpProducto = (Producto) obj;
            if(super.equals(tmpProducto) && this.codigo.equals(tmpProducto.codigo)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}