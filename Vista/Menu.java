package Vista;
import Controlador.*;
import Modelo.personas.*;
import Modelo.productos.*;
import Modelo.compras.*;
import Modelo.Venta;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {
	private FileOutputStream fo;
	private ObjectOutputStream oos;
	private ControladorProducto controladorProducto;
	private ControladorElectronico controladorElectronico;
	private ControladorCliente controladorCliente;
	private ControladorProveedor controladorProveedor;
	private ControladorVenta controladorVenta;
	private VentanaElectronico lectorElectronico;
	private VentanaNoElectronico lectorNoElectronico;
	private VentanaCliente lectorCliente;
	private VentanaProveedor lectorProveedor;
	private VentanaPedidos lectorPedidos;
	private Scanner sc = new Scanner(System.in);
	private int opc, opc2, opc3, opc4;
	private Cliente[] vectorE;
	private Proveedor[] vectorP;
	private Electronico[] vectorL;
	private Producto[] vectorT;
	private Venta[] vectorV;
	private LectorDatos lectorD = new LectorDatos();
	public void addControladorCliente(ControladorCliente cc){
		controladorCliente = cc;
	}

	public void addControladorProveedor(ControladorProveedor cpr){
		controladorProveedor = cpr;
	}

	public void addControladorProducto(ControladorProducto cp){
		controladorProducto = cp;
	}

	public void addControladorElectronico(ControladorElectronico ce){
		controladorElectronico = ce;
	}
	public void addControladorVenta(ControladorVenta cv){
		controladorVenta = cv;
	}
	public void iniciarMenu(){//Menu
		System.out.println("\n---Menu Principal---");
			solicitaOpcion();
			opc = leerOpcion();
			switch(opc){
				case 1: 
					solicitaOpcion2();
					opc2 = leerOpcion();
					switch(opc2){
						case 1://Proveedor
						System.out.println("\n---Menu Proveedor---");
							solicitaOpcion4();
							opc4 = leerOpcion();
							switch(opc4){
								case 1://Agregar
									lectorProveedor = new VentanaProveedor();
								lectorProveedor.addControlador(controladorProveedor);
								controladorProveedor.addVentana(lectorProveedor);
								lectorProveedor.mostrarVentana();
									break;
								case 2://Modificar
									System.out.println("\nOpcion modificar proveedor");
									controladorProveedor.modificarProveedor();
									break;
								case 3://Eliminar
									System.out.println("\nOpcion eliminar proveedor");
									controladorProveedor.eliminarProveedor();
									break;
								case 4://Consultar especifico
									System.out.println("\nOpcion Consultar un Proveedor");
									controladorProveedor.consultarProveedor();
									break;
								case 5://Consultar todos
									System.out.println("\nOpcion consultar todos los Proveedores\n");
									controladorProveedor.mostrarProveedores();
									iniciarMenu();
									break;
								case 6://Menu anterior
									System.out.println("Regresando al menu principal");
									iniciarMenu();
									break;
								default://default
									System.out.println("Opcion no valida");
									iniciarMenu();
									break;
							}
							break;
						case 2://Cliente
						System.out.println("\n---Menu Cliente---");
							solicitaOpcion4();
							opc4 = leerOpcion();
							switch(opc4){
								case 1://Agregar
								lectorCliente = new VentanaCliente();
								lectorCliente.addControlador(controladorCliente);
								controladorCliente.addVentana(lectorCliente);
								lectorCliente.mostrarVentana();
									break;
								case 2://Modificar
									System.out.println("\nOpcion modificar cliente");
									controladorCliente.modificarCliente();
									break;
								case 3://Eliminar
									System.out.println("\nOpcion eliminar cliente");
								    controladorCliente.eliminarCliente();
									
									break;
								case 4://Consultar especifico
									System.out.println("\nOpcion consultar un cliente");
									controladorCliente.consultarCliente();
									break;
								case 5://Consultar todos
									System.out.println("\nOpcion consultar todos\n");
									controladorCliente.mostrarClientes();
									iniciarMenu();
									break;
								case 6://Menu anterior
									System.out.println("Regresando al menu principal");
									iniciarMenu();
									break;
								default://Default
									System.out.println("Opcion no valida");
									iniciarMenu();
									break;
							}
							break;
						case 3://Menu anterior
							System.out.println("Regresando al menu principal");
							iniciarMenu();
							break;
					}
					break;
				case 2://Productos
					solicitaOpcion3();
					opc3 = leerOpcion();
					switch(opc3){
						case 1://No Electronico 
						System.out.println("\n---Menu No Electronicos---");
							solicitaOpcion4();
							opc4 = leerOpcion();
							switch(opc4){
								case 1://Agregar producto no electronico
								lectorNoElectronico = new VentanaNoElectronico();
								lectorNoElectronico.addControlador(controladorProducto);
								controladorProducto.addVentana(lectorNoElectronico);
								lectorNoElectronico.mostrarVentana();
									break;
								case 2://Modificar producto no electronico
									System.out.println("\nOpcion eliminar producto");
									controladorProducto.modificarProducto();
									break;
								case 3://Eliminar producto no electronico
									System.out.println("\nOpcion modificar producto");
									controladorProducto.eliminarProducto();
									break;
								case 4://Consultar producto no electronico especifico
									System.out.println("\nOpcion consultar producto electronico especifico");
									controladorProducto.consultarProducto();
									break;
								case 5://Consultar todos los productos no electronicos
									System.out.println("\nOpcion consultar todos\n");
									controladorProducto.mostrarProductos();
									iniciarMenu();
									break;
								case 6://Menu anterior
									System.out.println("Regresando al menu principal");
									iniciarMenu();
									break;
								default://Default
									System.out.println("Opcion no valida");
									iniciarMenu();
									break;
							}	
							break;
						case 2://Electronicos
						System.out.println("\n---Menu Electronicos---");
							solicitaOpcion4();
							opc4 = leerOpcion();
							switch(opc4){
								case 1://Agregar
								lectorElectronico = new VentanaElectronico();
								lectorElectronico.addControlador(controladorElectronico);
								controladorElectronico.addVentana(lectorElectronico);
								lectorElectronico.mostrarVentana();
									break;
								case 2://Modificar
							    	System.out.println("\nOpcion modificar producto");
									controladorElectronico.modificarProducto();
									break;
								case 3://Eliminar
									System.out.println("\nOpcion eliminar Producto");
								    controladorElectronico.eliminarProducto();
									break;
								case 4://Consultar especifico
									System.out.println("\nOpcion Consultar uno");
									controladorElectronico.consultarElectronico();
									break;
								case 5://Consultar todos
									System.out.println("\nOpcion consultar todos\n");
									controladorElectronico.mostrarElectronicos();
									iniciarMenu();
									break;
								case 6://Menu anterior
									System.out.println("Regresando al menu principal");
									iniciarMenu();
									break;
								default://Default
									System.out.println("Opcion no valida");
									iniciarMenu();
									break;
							}	
							break;
						case 3://regresar
							System.out.println("Regresando al menu principal");
							iniciarMenu();
					}
					break;
				case 3://Ventas
					solicitaOpcionVentas();
							opc4 = leerOpcion();
							switch(opc4){
								case 1://Generar pedido
								lectorPedidos = new VentanaPedidos();
								lectorPedidos.addControlador(controladorVenta);
								controladorVenta.addVentana(lectorPedidos);
								lectorPedidos.mostrarVentana();
									break;
								case 2://Generar factura
									System.out.println("\nGenerar Factura ");
									controladorVenta.generarFactura();

									break;
								case 3://Consultar las facturas de un cliente
									System.out.println("\nConsultar facturas de cliente");
									controladorVenta.consultarFactura();
									break;
								case 4://Consultar todos los pedidos
									System.out.println("\nOpcion consultar todos los pedidos:");
									controladorVenta.mostrarPedidos();
									iniciarMenu();
									break;
								case 5://Consultar todas las facturas
									System.out.println("\nOpcion consultar todas las facturas:");
									controladorVenta.mostrarFacturas();
									iniciarMenu();
									break;
								case 6://Consultar ganancia neta de la tienda
									System.out.println("\nOpcion consultar ganancia neta");
									controladorVenta.consultarGananciaNeta();
									iniciarMenu();
									break;
								case 7://Consultar ganancia actual (incluyendo pedidos(gasto))
									System.out.println("\nOpcion consultar ganancia actual");
									controladorVenta.consultarGanancia();
									iniciarMenu();
									break;
								default:
									System.out.println("Opcion no valida");
									iniciarMenu();
									break;
							}
					
					break;
				case 4://Salir
				System.out.println("Saliendo...");
					System.out.println("Guardando datos de Clientes...");
					guardarDatosClientes();
					System.out.println("Guardando datos de proveedores...");
					guardarDatosProveedores();
					System.out.println("Guardando datos de productos electronicos...");
					guardarDatosProductosElectronicos();
					System.out.println("Guardando datos de producto no electronicos...");
					guardarDatosProductosNoElectronicos();
					System.out.println("Guardando datos de Ventas...");
					guardarDatosVentas();
					break;
				default:
					System.out.println("Opcion no valida");
					iniciarMenu();
					break;
			}
		
	}

	public void solicitaOpcion(){//Menu principal
		System.out.println(" 1: Gestion usuarios");
		System.out.println(" 2: Gestion Productos");
		System.out.println(" 3: Gestion Ventas");
		System.out.println(" 4: Salir");
	}
	public void solicitaOpcion2(){//Menu usuarios
		System.out.println("\n---Menu Usuarios---");
		System.out.println(" 1: Proveedores");
		System.out.println(" 2: Cliente");
		System.out.println(" 3: Regresar");	
	}

	public void solicitaOpcion3(){//Menu productos
		System.out.println("\n---Menu Productos---");
		System.out.println(" 1: No Electronicos");
		System.out.println(" 2: Electronicos");
		System.out.println(" 3: Regresar");	
	}

	public void solicitaOpcion4(){//Menu principal 2
		System.out.println(" 1: Registrar nuevo");
		System.out.println(" 2: Modificar");
		System.out.println(" 3: Eliminar");
		System.out.println(" 4: Consultar uno");
		System.out.println(" 5: Consultar todos");
		System.out.println(" 6: Regresar");			
	}
	public void solicitaOpcionVentas(){//Menu ventas
		System.out.println("\n---Menu Ventas---");
		System.out.println(" 1: Generar Pedido");
		System.out.println(" 2: Generar Factura");
		System.out.println(" 3: Consultar facturas de cliente especifico");
		System.out.println(" 4: Consultar todos los pedidos");
		System.out.println(" 5: Consultar todas las facturas");
		System.out.println(" 6: Consultar ganancia neta");
		System.out.println(" 7: Consultar ganancia actual");
		System.out.println(" 8: Regresar");			
	}	

	public int leerOpcion(){//Entrada de datos en los menus
		int opcion=0;
		System.out.println("Escribe una opcion:");
		try {
			opcion=sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error en la entrada");
		}
		sc.nextLine();
		return opcion;
	}
	public int menuModificacionProveedor(){//Menu modificacion de proveedores
		int i=0;
		System.out.println("Que dato desea modificar: ");
		System.out.println("1)Nombre\n2)Telefono\n3)Correo");
		try {
			i=sc.nextInt();
			
		} catch (InputMismatchException e) {
			System.out.println("Error en la entrada");
		}
		sc.nextLine();
		return i;
	}
	public int menuModificacionCliente(){//Menu modificacion de clientes
		int i=0;
		System.out.println("Que dato desea modificar: ");
		System.out.println("1) Nombre\n2) Direccion\n3) correo\n4) RFC");
		try {
			i=sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error en la entrada");
		}
		sc.nextLine();
		return i;
	}
	public int menuModificacionProducto(){//Menu modificacion de productos no electronicos
		int opModificarL=0;
		System.out.println("Que desea modificar: ");
		System.out.println("1)Precio de Venta\n2)Descuento\n3)Descripcion\n4)Numero de existencias\n5)Codigo");
		try {
			opModificarL=sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error en la entrada");
		}
		sc.nextLine();
		return opModificarL;
	}
	public int menuModificacionElectronico(){//Menu modificacion de productos electronicos
		int opModificarL=0;
		System.out.println("Que desea modificar: ");
		System.out.println("1)Precio de Venta\n2)Descuento\n3)Descripcion\n4)Numero de existencias\n5)Codigo\n6)Numero de serie");
		try {
			opModificarL=sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error en la entrada");
		}
		sc.nextLine();
		return opModificarL;
	}
	public void guardarDatosClientes(){
		try{
			fo = new FileOutputStream("Clientes.datos");
			oos = new ObjectOutputStream(fo);
			for(int i = 0; i<controladorCliente.getNumClientes(); i++){
				Cliente c = controladorCliente.getCliente(i);
				oos.writeObject(c);
			}
			if(oos!=null){
				oos.close();
				fo.close();
			}
			System.out.println("Datos de clientes guardados");
		}catch(IOException ex){
			ex.getMessage();
		}
	}

	public void guardarDatosProveedores(){
		try{
			fo = new FileOutputStream("Proveedores.datos");
			oos = new ObjectOutputStream(fo);
			for(int i = 0; i<controladorProveedor.getNumProveedores(); i++){
				Proveedor p = controladorProveedor.getProveedor(i);
				oos.writeObject(p);
			}
			if(oos!=null){
				oos.close();
				fo.close();
			}
			System.out.println("Datos de proveedores guardados");
		}catch(IOException ex){
			ex.getMessage();
		}
	}

	public void guardarDatosProductosNoElectronicos(){
		try{
			fo = new FileOutputStream("NoElectronicos.datos");
			oos = new ObjectOutputStream(fo);
			for(int i = 0; i<controladorProducto.getNumProducto(); i++){
				Producto pt = controladorProducto.getProducto(i);
				oos.writeObject(pt);
			}
			if(oos!=null){
				oos.close();
				fo.close();
			}
			System.out.println("Datos de Productos no electronicos guardados");
		}catch(IOException ex){
			ex.getMessage();
		}
	}

	public void guardarDatosProductosElectronicos(){
		try{
			fo = new FileOutputStream("Electronicos.datos");
			oos = new ObjectOutputStream(fo);
			for(int i = 0; i<controladorElectronico.getNumElectronico(); i++){
				Electronico e = controladorElectronico.getElectronico(i);
				oos.writeObject(e);
			}
			if(oos!=null){
				oos.close();
				fo.close();
			}
			System.out.println("Datos de Productos Electronicos guardados");
		}catch(IOException ex){
			ex.getMessage();
		}
	}
	public void guardarDatosVentas(){
		try{
			fo = new FileOutputStream("Ventas.datos");
			oos = new ObjectOutputStream(fo);
			for(int i = 0; i<controladorVenta.getNumVentas(); i++){
				Venta v = controladorVenta.getVenta(i);
				oos.writeObject(v);
			}
			if(oos!=null){
				oos.close();
				fo.close();
			}
			System.out.println("Datos de Ventas guardados");
		}catch(IOException ex){
			ex.getMessage();
		}
	}	
}