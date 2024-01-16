package Controlador;
import Modelo.productos.Producto;
import Vista.*;
import java.util.InputMismatchException;

import Modelo.personas.Proveedor;
public class ControladorProducto {
    private Producto[] productos;
	private int numproducto;
	private Menu menu;
	private ControladorElectronico controladorElectronico;
	private VentanaNoElectronico lectorNoElectronico;
	private LectorDatos lectorD = new LectorDatos();
	private EscritorDatos escritorD=new EscritorDatos();
	private ControladorProveedor controlProveedor;

	public ControladorProducto(Producto[] l, Menu m, ControladorProveedor cp,ControladorElectronico ce){
		this.productos=l;
		menu=m;
		controlProveedor=cp;
		controladorElectronico=ce;
		int n = 0;
		for(int i = 0; i<l.length; i++){
			if(l[i]!=null){
				n++;
			}
		}
		numproducto=n;
	}
	
	public void addVista(Menu m){
		menu=m;
	}
	public void addVentana(VentanaNoElectronico lector){
		lectorNoElectronico = lector;
	}
	public void agregarProducto(String[] datos) {//Crear un producto
		boolean coincideProducto = true, coincideProveedor = true;
		int j = 0;
		Proveedor proveedor = new Proveedor(datos[5], datos[6], datos[7]);
		
		for (int k = 0; k < controlProveedor.getNumProveedores(); k++) {
			if (this.controlProveedor.getProveedor(k).getNombre().equals(proveedor.getNombre())) {//Si el proveedor ingresado coincide con uno existente
				System.out.println("El proveedor ingresado coincide con uno existente");
				proveedor = this.controlProveedor.getProveedor(k);
				coincideProveedor = false;
			}
		}
		
		if (numproducto <= productos.length) {
			try {
				if (Integer.parseInt(datos[8]) <= 10 && Integer.parseInt(datos[8]) >= 6) {
					Producto proAux = new Producto(datos[0], Float.parseFloat(datos[1]),Float.parseFloat(datos[2]), Float.parseFloat(datos[3]),
						datos[4], proveedor, Integer.parseInt(datos[8]));
					
					if (coincideProveedor) {
						controlProveedor.addProveedor(proveedor);//Agregar al vector si el proveedor no coincidia
					}
					
					while (j < numproducto && coincideProducto) {
						if (productos[j].getCodigo().equals(proAux.getCodigo())) {
							coincideProducto = false;
						}
						j++;
					}
					
					j = 0;
					
					while (j < controladorElectronico.getNumElectronico() && coincideProducto) {
						if (controladorElectronico.getElectronico(j).getCodigo().equals(proAux.getCodigo())) {
							coincideProducto = false;
						}
						j++;
					}
					
					//Compara si ya existe un no electronico con el codigo proporcionado
					
					if (coincideProducto) {//Si no coincide agrega el producto al vector
						productos[numproducto] = proAux;
						System.out.println("Se ha registrado el producto");
						numproducto++;
					} else {
						System.out.println("El codigo ya se encuentra registrado en el sistema");
					}
				} else {
					System.out.println("El producto no puede tener unidades superiores o inferiores a su stock maximo/minimo\nFallo al crear el producto");
				}
			} catch (NumberFormatException e) {
				System.err.println("Asegurate de ingresar datos numericos validos\nFallo al crear el producto");
			}
		} else {
			System.out.println("No hay espacio para más productos");
		}
		
		lectorNoElectronico.setVisible(true);
		lectorNoElectronico.dispose();
		menu.iniciarMenu();
	}
	
	public void cerrarLector(){
		lectorNoElectronico.setVisible(true);
		lectorNoElectronico.dispose();
		menu.iniciarMenu();
	}

	public int buscarProducto(String codigo){//Buscar un producto por su codigo
		int i=0, b=-1;
		Producto pro;
		String cod;
		while((i<numproducto)&&(b==-1)){
			pro=productos[i];
			cod=pro.getCodigo();
			if(cod.equals(codigo)){
				b=i;
			}else{
				i++;
			}
		}
		return b;//Retorna -1 si no existe
	}

	public void eliminarProducto(){//Elimina un producto no electronico por su codigo
		if(numproducto>=1){
			int i=0;
			mostrarProductos();
			String cod=lectorD.leerDatoString("Ingresa el codigo del producto a eliminar");
			int pos=buscarProducto(cod);
			if((pos<0)||(pos>=productos.length)){
				System.out.println("Codigo del producto no existente en el registro");
			}else{
				cod=productos[pos].getCodigo();
				for(i=pos+1;i<=numproducto-1;i++){
					productos[i-1]=productos[i];
				}
				System.out.println("El producto con codigo "+cod+" ha sido eliminado");
				numproducto--;
			}
		}else{
			System.out.println("No hay productos registrados");
		}
		menu.iniciarMenu();
	}

	public void modificarProducto(){//Modifica algun atributo de un producto no electronico (identificandolo por su codigo)
		if(numproducto>=1){
			mostrarProductos();
			String cod=lectorD.leerDatoString("Ingresa el codigo del producto a modificar");
			int pos=buscarProducto(cod);
			if((pos<0)||(pos>=productos.length)){
				System.out.println("El codigo del producto no existe en el registro");
			}
			else{
				int opModificarL=menu.menuModificacionProducto();
				try{
					switch(opModificarL){
						case 1:
							float precio=lectorD.leerDatoFloat("Ingrese el nuevo precio de venta");
							productos[pos].setPrecioVenta(precio);
							System.out.println("Precio modificado con exito.");
							break;
						case 2:
							float des=lectorD.leerDatoFloat("Ingrese el nuevo descuento del Producto");
							productos[pos].setDescuento(des);
							System.out.println("Descuento modificado con exito.");
							break;
						case 3:
							String descri=lectorD.leerDatoStringMayus("Ingrese la nueva descripcion");
							productos[pos].setDescripcion(descri);
							System.out.println("Descripcion modificada con exito.");
							break;
							
						case 4:
							int numcan=lectorD.leerDatoInt("Ingrese el nuevo numero de existencias");
							productos[pos].setCantidad(numcan);
							System.out.println("Numero de existencias modificado con exito.");
							break;
						case 5:
							boolean c=false;
							String codigo=lectorD.leerDatoString("Ingrese el nuevo codigo");
							for(int i=0;i<numproducto;i++){
								if(codigo.equals(productos[i].getCodigo())){
									System.out.println("Este codigo ya lo tiene otro Producto");
									c=true;
								}
							}
							if(!c){
								productos[pos].setCodigo(codigo);
								System.out.println("Codigo modificado con exito.");
							}
							break;	
						}
				}catch(NumberFormatException e){
					System.out.println("Error en la entrada de datos\nDato no modificado");
				}catch(InputMismatchException e1){
					System.out.println("Error en la entrada de datos\nDato no modificado");
				}
			}
		}else{
			System.out.println("No hay productos registrados en el sistema");
		}
		menu.iniciarMenu();
	}

	public void consultarProducto(){//Consulta de un producto no electronico especifico (por su codigo)
		if(numproducto>=1){
			String cod=lectorD.leerDatoString("Ingrese el codigo del producto a consultar");
			int pos=buscarProducto(cod);
			if((pos == -1)){
				System.out.println("Producto no hallado.");
			}else{
				String producto=productos[pos]+"";
				escritorD.consultarProducto(producto);
			}
		}else{
			System.out.println("No hay productos registrados en el sistema");
		}
		menu.iniciarMenu();
	}

	public boolean mostrarProductos(){//Muestra todos los productos no electronicos (con EscritorDatos)
		boolean existencia=false;
		if(numproducto>=1){
			String producto="";
			for(int i = 0; i<numproducto; i++){
				if(productos[i].getCantidad()>=1){
					producto+=productos[i]+"\n-------------------\n";
					existencia=true;
				}
			}
			if(existencia){
				escritorD.mostrarVector(producto,"Lista de productos no electronicos");
			}
		}else{
			System.out.println("No hay productos registrados en el sistema");
		}
		return existencia;//Retorna true si hay stock de los productos
	}
	public Producto getProducto(int i){
		return productos[i];
	}
	public int getNumProducto(){
		return numproducto;
	}
}