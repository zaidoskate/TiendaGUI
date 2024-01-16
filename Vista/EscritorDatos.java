package Vista;

public class EscritorDatos {//Clase para mostrar informacion en consola

    public EscritorDatos() {
    }
    public void consultarProducto(String producto){//Muestra la informacion de un solo producto
        System.out.println(producto);
    }
    public void consultarProveedor(String proveedor){//Muestra la informacion de un solo proveedor
		System.out.println(proveedor);
	}
    public void consultarCliente(String cliente){//Muestra la informacion de un solo cliente
        System.out.println(cliente);
    }
    public void imprimirVenta(String venta){//Muestra la informacion de una sola factura o un solo pedido
        System.out.println(venta);
    }
    public void mostrarVector(String informacion,String parametro){//Muestra la informacion de un vector de cualquier tipo
        System.out.println(parametro+":");
        System.out.println("-----------------");
        System.out.println(informacion);
    }
    public void consultarGanancia(double ganancia,String parametro){//Muestra la informacion de las ganancias
        System.out.println(parametro+":");
        System.out.println("$"+ganancia);
    }

    
}
