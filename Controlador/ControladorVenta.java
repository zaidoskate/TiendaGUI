package Controlador;
import Modelo.Venta;
import Modelo.compras.*;
import Modelo.personas.*;
import Modelo.productos.*;
import Vista.*;
public class ControladorVenta {
    private Venta[] ventas;
	private ControladorCliente controladorCliente;
	private ControladorProducto controladorProducto;
	private ControladorElectronico controladorElectronico;
	private int numVentas;
	private LectorDatos lectorD = new LectorDatos();
	private Menu menu;
	private VentanaPedidos lectorpPedidos;
	private EscritorDatos escritorD=new EscritorDatos();
	private double ganancia = 0;
	private double gananciaNeta = 0;
	private boolean existenciaFactura = false;
	private boolean existenciaPedido = false;
	private boolean ventanaAbierta = false;
	public ControladorVenta(Venta[] ventas, Menu m,ControladorCliente cc,ControladorProducto cp,ControladorElectronico ce, boolean exist){
		this.ventas = ventas;
		menu=m;
		controladorCliente=cc;
		controladorProducto=cp;
		controladorElectronico=ce;
		int n = 0;	
		for(int i = 0; i<ventas.length; i++){
			if(ventas[i]!=null){
				n++;
			}
		}
		numVentas=n;
		existenciaFactura = exist;
		existenciaPedido = exist;
	}

	public void addControladorCliente(ControladorCliente cc){
		controladorCliente = cc;
	}

	public void addControladorProducto(ControladorProducto cp){
		controladorProducto = cp;
	}

	public void addControladorElectronico(ControladorElectronico ce){
		controladorElectronico = ce;
	}

	public void addVista(Menu m){
		menu = m;
	}
	public void addVentana(VentanaPedidos lector){
		lectorpPedidos=lector;
	}

	public void cerrarLector(){
		lectorpPedidos.setVisible(false);
		lectorpPedidos.dispose();
		menu.iniciarMenu();
	}
	public void generarFactura(){//Creacion de una factura
		if(controladorProducto.getNumProducto()>0||controladorElectronico.getNumElectronico()>0){//Valida que haya productos
			int pos=validarCliente();
			if(pos>=0){//Si existe un cliente
				Factura factura=new Factura(controladorCliente.getCliente(pos));
				generarCarrito(factura);
				if(factura.getNumProductos()>=1){//Si se agregaron productos al carrito
					escritorD.imprimirVenta(factura.imprimirFactura());//Imprimir factura
					ventas[numVentas]=new Venta(factura);
					ganancia+=(factura.calcularTotal());
					gananciaNeta+=factura.calcularTotal();//Suma a las ganancias de la tienda
					numVentas++;
					existenciaFactura=true;//Ahora hay facturas
				}else{
					System.out.println("Tu carrito esta vacio");
				}
			}
		}else{
			System.out.println("La tienda esta vacia");
		}
		menu.iniciarMenu();
	}

	public void agregarProductos(Factura factura){
		System.out.println("\nIngrese productos en el carrito por su codigo: ");
		String codigo=lectorD.leerDatoString("Codigo de producto");//Se lee el codigo del producto que se quiere comprar
		int prod=controladorProducto.buscarProducto(codigo);
		int ele=controladorElectronico.buscarProducto(codigo);
		if(ele>=0){//Si el codigo coincidio con un electronico
			int cantidad=Integer.parseInt(lectorD.leerDatoString("Cantidad de este producto a comprar"));//cantidad de este producto a comprar
			for (int i = 0; i < cantidad; i++) {
				factura.addProducto(controladorElectronico.getElectronico(ele));//Se agrega
			}
			controladorElectronico.getElectronico(ele).setCantidad(controladorElectronico.getElectronico(ele).getCantidad()-cantidad);//Se disminuye la cantidad del producto
			System.out.println("Producto agregado al carrito con exito");
			if(controladorElectronico.getElectronico(ele).getCantidad()<=5){//Si tras la compra se bajo el stock por debajo del minimo
				System.out.println("Producto bajo en stock\n\n\t");
				String aux=lectorD.leerDatoStringMayus("Realizar pedido?\tSi\tNo");
				if(aux.equals("Si")){//Si se quiere se realiza el pedido de el producto
					generarPedido(ele,1);
				}
			}
		}else if(prod>=0){//Chequeo con productos no electronicos
			int cantidad=Integer.parseInt(lectorD.leerDatoString("Cantidad de este producto a comprar"));
			for (int i = 0; i < cantidad; i++) {
				factura.addProducto(controladorProducto.getProducto(prod));
			}
			controladorProducto.getProducto(prod).setCantidad(controladorProducto.getProducto(prod).getCantidad()-cantidad);
			System.out.println("Producto agregado al carrito con exito");
			if(controladorProducto.getProducto(prod).getCantidad()<=5){
				System.out.println("Producto bajo en stock\n\n\t");
				String aux=lectorD.leerDatoStringMayus("Realizar pedido?\tSi\tNo");
				if(aux.equals("Si")){
					generarPedido(prod,2);
				}
			}
		}else{
			System.out.println("Producto no encontrado");
		}
	}


	public void generarCarrito(Factura factura){//Agregar los productos que se van a comprar
		boolean satisfecho=true;
		
		while((satisfecho)&&factura.getNumProductos()<=10){//Mientras se quiera seguir comprando y el carrito tenga espacio
			if(factura.getNumProductos()<=10){
				if(controladorProducto.getNumProducto()>=1||controladorElectronico.getNumElectronico()>=1){//Si hay productos
					if(controladorElectronico.mostrarElectronicos() || controladorProducto.mostrarProductos()){//SI los productos tienen stock (>0);
						if(controladorProducto.mostrarProductos());
						agregarProductos(factura);
					}else{//Si no hay stock de los productos existentes
						System.out.println("No hay stock de productos");
						String aux=lectorD.leerDatoStringMayus("Realizar pedido?\tSi\tNo");
						if(aux.equals("Si")){
							String codigo=lectorD.leerDatoString("Ingrese Codigo de producto");
							generarPedido(codigo);//Se genera un pedido si se desea
						}
					}
				}
			}else{
				System.out.println("Tu carrito esta lleno");
			}
			String continuar=lectorD.leerDatoStringMayus("Desea seguir comprando?\tSi\tNo");//Cambio de boolean para seguir comprando
			if(continuar.equals("No")){
				satisfecho=false;
			}
		}
	}

	public int validarCliente(){//Metodo para validar si un cliente existe
		String rfc=lectorD.leerStringMayus("Ingrese el RFC");
		int pos=controladorCliente.buscarCliente(rfc);
		if(pos==-1){//Si no existe
			String resp=lectorD.leerDatoStringMayus("Cliente no encontrado\n\nCrear nuevo cliente?\tSi\tNo");
			if(resp.equals("Si")){//Se crea un nuevo cliente si se desea
				String[] datos=lectorD.leerDatosCliente();
				Cliente cliente=new Cliente(datos[0], datos[1], datos[2], datos[3]);
				controladorCliente.addCliente(cliente);
				pos=controladorCliente.getNumClientes()-1;//Se agrega al vector de clientes
			}
		}else{
			System.out.println("Bienvenido "+controladorCliente.getCliente(pos).getNombre());//Si existe bienvenido + cliente
		}
		
		return pos;//Retorna la posicion del vector del vector donde se encuentra el cliente
		
	}

	public void mostrarFacturas(){//Mostrar todas las facturas de la tienda
		if(existenciaFactura){//Si ya se realizaron facturas
			String facturas="";
			for(int i=0; i<numVentas; i++){
				if(ventas[i].getFactura()!=null){//Del vector de ventas, las que sean facturas
					facturas+=(ventas[i].getFactura().imprimirFactura())+"\n----------------\n";//Se concatenan a un String
				}
			}
			escritorD.mostrarVector(facturas,"Lista de facturas");//Se manda a EscritorDatos
		}else{
			System.out.println("La tienda no tiene ventas");
		}
		
	}
	public void consultarFactura(){//Consultar facturas de un cliente
		if(existenciaFactura){//Si ya se genero al menos una factura
			String rfc=lectorD.leerStringMayus("RFC del cliente");
			int pos=controladorCliente.buscarCliente(rfc);//Buscar cliente
			if(pos==-1){//Si no existe
				System.out.println("Cliente no encontrado");
			}else{//Si existe
				String facturas="";
				boolean compras=false;
				for (int i = 0; i < numVentas; i++) {
					if(ventas[i].getFactura()!=null){
						if(ventas[i].getFactura().getCliente().equals(controladorCliente.getCliente(pos))){//Si alguna de las facturas del vector coincide en cliente con el ingresado
							facturas+=ventas[i].getFactura().imprimirFactura()+"\n";
							compras=true;
						}
					}
				}
				if(compras){//Si tiene facturas el cliente
					escritorD.mostrarVector(facturas,"Facturas del cliente "+controladorCliente.getCliente(pos).getNombre());
				}else{
					System.out.println("Cliente no tiene facturas");
				}
			}
		}else{
			System.out.println("La tienda no tiene facturas emitidas");
		}
		menu.iniciarMenu();
	}
	public void mostrarPedidos(){//Mostrar todos los pedidos de la tienda
		if(existenciaPedido){//Si la tienda ya genero al menos un pedido
			String pedidos="";
			for(int i=0; i<numVentas; i++){
				if(ventas[i].getPedido()!=null){//Si es un pedido dentro del vector ventas
					pedidos+=(ventas[i].getPedido().imprimirPedido())+"\n";//Concatena el pedido a un String
				}
			}
			escritorD.mostrarVector(pedidos,"Lista de pedidos");
		}else{
			System.out.println("La tienda no tiene pedidos");
		}
		menu.iniciarMenu();
	}
	public void generarPedido(String codigo){//Generar un pedido
		ventanaAbierta = true;
		if(controladorProducto.getNumProducto()>=1||controladorElectronico.getNumElectronico()>=1){//Si hay productos
			if(numVentas<=ventas.length){
				int pos=controladorProducto.buscarProducto(codigo);
				int ele=controladorElectronico.buscarProducto(codigo);
				//Se busca el producto por el codigo que se ingreso
				if(pos>=0){//Productos no electronicos
					Pedido pedido=new Pedido(controladorProducto.getProducto(pos),controladorProducto.getProducto(pos).getProveedor());
					if(pedido.consultarStock()){//Si el stock del producto necesita actualizarse
						ganancia-=pedido.calcularTotal();
						escritorD.imprimirVenta(pedido.imprimirPedido());
						pedido.actualizarStock();
						ventas[numVentas]=new Venta(pedido);
						numVentas++;
						existenciaPedido=true;
						//Se imprime el pedido, se le resta la ganancia y se actualiza el stock de dicho producto
					}else{
						System.out.println("Hay stock suficiente del producto: "+pedido.getProducto().getCodigo());
					}
				}else if(ele>=0){//Productos electronicos
					Pedido pedido=new Pedido(controladorElectronico.getElectronico(ele),controladorElectronico.getElectronico(ele).getProveedor());
					if(pedido.consultarStock()){
						ganancia-=pedido.calcularTotal();
						escritorD.imprimirVenta(pedido.imprimirPedido());
						pedido.actualizarStock();
						ventas[numVentas]=new Venta(pedido);
						numVentas++;
						existenciaPedido=true;
					}else{
						System.out.println("Hay stock suficiente del producto: "+pedido.getProducto().getCodigo());
					}
				}else{
					System.out.println("Codigo no encontrado");
				}
			}else{
				System.out.println("No hay espacio para mas ventas");
			}
		}else{
			System.out.println("No hay productos registrados para pedir");
		}
		if(ventanaAbierta == true){
			lectorpPedidos.setVisible(false);
			lectorpPedidos.dispose();
		}
		menu.iniciarMenu();
	}
	public void generarPedido(int pos,int tipo){//Generar un pedido sabiendo el producto
		if(numVentas<=ventas.length){
			switch(tipo){
				case 1:
					Pedido pedido=new Pedido(controladorElectronico.getElectronico(pos), controladorElectronico.getElectronico(pos).getProveedor());
					if(pedido.consultarStock()){
						ganancia-=pedido.calcularTotal();
						escritorD.imprimirVenta(pedido.imprimirPedido());
						pedido.actualizarStock();
						ventas[numVentas]=new Venta(pedido);
						numVentas++;
					}else{
						System.out.println("Hay stock suficiente del producto: "+pedido.getProducto().getCodigo());
					}
					break;
				case 2:
					Pedido pedido1 = new Pedido (controladorProducto.getProducto(pos), controladorProducto.getProducto(pos).getProveedor());
					if(pedido1.consultarStock()){
						ganancia-=pedido1.calcularTotal();
						escritorD.imprimirVenta(pedido1.imprimirPedido());
						pedido1.actualizarStock();
						ventas[numVentas]=new Venta(pedido1);
						numVentas++;
					}else{
						System.out.println("Hay stock suficiente del producto: "+pedido1.getProducto().getCodigo());
					}
					break;
			}
		}else{
			System.out.println("No hay espacio para mas ventas");
		}
	}
	public void consultarGanancia(){//Manda a EscritorDatos la ganancia para imprimirla
		escritorD.consultarGanancia(ganancia, "Ganancia actual de la tienda");
	}
	public void consultarGananciaNeta(){//Manda a EscritorDatos la ganancia neta para imprimirla
		escritorD.consultarGanancia(gananciaNeta, "Ganancia neta de la tienda");
	}
	public Venta getVenta(int i){
		return ventas[i];
	}
	
	public int getNumVentas(){
		return numVentas;
	}
}