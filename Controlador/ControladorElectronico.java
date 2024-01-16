package Controlador;
import Modelo.productos.Electronico;
import Vista.*;
import java.util.InputMismatchException;

import Modelo.personas.Proveedor;
public class ControladorElectronico {
    private Electronico[] electronicos;
	private int numElectronico;
	private Menu menu;
    private VentanaElectronico lectorElectronico;
	private ControladorProducto controladorProducto;
	private LectorDatos lectorD = new LectorDatos();
	private EscritorDatos escritorD=new EscritorDatos();
	private ControladorProveedor controlProveedor;

	public ControladorElectronico(Electronico[] l, Menu m, ControladorProveedor proveedor){
		this.electronicos=l;
		menu=m;
		controlProveedor=proveedor;
		int n = 0;	
		for(int i = 0; i<l.length; i++){
			if(l[i]!=null){
				n++;
			}
		}
		numElectronico=n;
	}

	public void addControladorProducto(ControladorProducto cp){
		controladorProducto=cp;
	}
	public void addVista(Menu m){
		menu=m;
	}
	public void addVentana(VentanaElectronico lector){
		lectorElectronico = lector;
	}
	public void agregarProducto(String[] datos) {//Crear producto electronico
		int j = 0;
		boolean coincideProducto = true, coincideProveedor = true;
		Proveedor proveedor = new Proveedor(datos[5], datos[6], datos[7]);
	
		for (int k = 0; k < controlProveedor.getNumProveedores(); k++) {
			if (controlProveedor.getProveedor(k).getNombre().equals(proveedor.getNombre())) {//Si el proveedor ingresado coincide con uno existente
				System.out.println("El proveedor ingresado coincide con uno existente");
				proveedor = controlProveedor.getProveedor(k);
				coincideProveedor = false;
			}
		}
		
		if (numElectronico <= electronicos.length) {
			
			try {
				if(Integer.parseInt(datos[8])<=10&&Integer.parseInt(datos[8])>=6){
					Electronico proAux = new Electronico(datos[0], Float.parseFloat(datos[1]),Float.parseFloat(datos[2]), Float.parseFloat(datos[3]),
					datos[4], proveedor, Integer.parseInt(datos[8]), datos[9]);
					if(coincideProveedor){
						controlProveedor.addProveedor(proveedor);
					}
		
					while ((j < numElectronico) && (coincideProducto)) {
						if ((electronicos[j].getCodigo().equals(proAux.getCodigo()))) {//Si hay un producto electronico con el mismo codigo
							coincideProducto = false;
						}
						j++;
					}
					j=0;
					while(j<controladorProducto.getNumProducto() && coincideProducto){
						if(controladorProducto.getProducto(j).getCodigo().equals(proAux.getCodigo())){
							coincideProducto=false;
						}
						j++;
					}
					if (coincideProducto) {
						electronicos[numElectronico] = proAux;
						System.out.println("Se ha registrado el producto electronico");
						numElectronico++;
					}else{
						System.out.println("El producto ya se encuentra registrado en el sistema");
					}
				}else{
					System.out.println("El producto no puede tener unidades superiores o inferiores a su stock maximo/minimo\nFallo al crear el producto");
				}
			} catch (NumberFormatException e) {
				System.err.println("Asegurate de ingresar datos numericos validos\nFallo al crear el producto electronico.");
			}
		} else {
			System.out.println("No hay espacio para más productos electrónicos");
		}
		    lectorElectronico.setVisible(true);
			lectorElectronico.dispose();
			menu.iniciarMenu();
		}

public void cerrarLector(){
			lectorElectronico.setVisible(true);
			lectorElectronico.dispose();
			menu.iniciarMenu();
		}

	public int buscarProducto(String codigo){//Buscar un producto por su codigo
		int i=0, b=-1;
		Electronico elec;
		String cod;
		while((i<numElectronico)&&(b==-1)){
			elec=electronicos[i];
			cod=elec.getCodigo();
			if(cod.equals(codigo)){
				b=i;
			}else{
				i++;
			}
		}
		return b;//Retorna -1 si no existe
	}

	public void eliminarProducto(){//Elimina un electronico por su codigo
		if(numElectronico>=1){
			int i=0;
			mostrarElectronicos();
			String cod=lectorD.leerDatoString("Codigo del producto a eliminar");
			int pos=buscarProducto(cod);
			if((pos<0)||(pos>=electronicos.length)){
				System.out.println("Codigo del producto electronico no existente en el registro");
			}else{
				cod=electronicos[pos].getCodigo();
				for(i=pos+1;i<=numElectronico-1;i++){
					electronicos[i-1]=electronicos[i];
				}
				System.out.println("El producto con codigo "+cod+" ha sido eliminado");
				numElectronico--;
			}
		}else{
			System.out.println("No hay productos electronicos registrados en el sistema");
		}
		menu.iniciarMenu();
	}

	public void modificarProducto(){//Modifica un electronico por su codigo
		if(numElectronico>=1){
			mostrarElectronicos();
			String cod=lectorD.leerDatoString("Codigo");
			int pos=buscarProducto(cod);
			if((pos<0)||(pos>=electronicos.length)){
				System.out.println("El Codigo del producto electronico no existente en el registro");
			}
			else{
				int opModificarL=menu.menuModificacionElectronico();
				try {
					switch(opModificarL){
						case 1:
							float precio=lectorD.leerDatoFloat("Ingresa el nuevo precio de venta");
							electronicos[pos].setPrecioVenta(precio);
							System.out.println("Precio modificado con exito.");
							break;
						case 2:
							float des=lectorD.leerDatoFloat("Ingresa el nuevo descuento");
							electronicos[pos].setDescuento(des);
							System.out.println("Descuento modificado con exito.");
							break;
						case 3:
							String descri=lectorD.leerDatoStringMayus("Ingresa la nueva descripcion:");
							electronicos[pos].setDescripcion(descri);
							System.out.println("Descripcion modificada con exito.");
							break;
						case 4:
							int numcan=lectorD.leerDatoInt("Ingresa el nuevo numero de existencias");
							electronicos[pos].setCantidad(numcan);
							System.out.println("Numero de existencias modificado con exito.");
							break;
						case 5:
							boolean c=false;
							String codigo=lectorD.leerDatoString("Ingresa el nuevo codigo");
							for(int i=0;i<numElectronico;i++){
								if(codigo.equals(electronicos[i].getCodigo())){
									System.out.println("Este codigo ya lo tiene otro Producto");
									c=true;
								}
							}
							if(c==false){
								electronicos[pos].setCodigo(codigo);
								System.out.println("Codigo modificado con exito.");
							}
							break;
						case 6:
							
							String numser=lectorD.leerDatoString("Ingrese el nuevo numero de serie: ");
							electronicos[pos].setNumeroSerie(numser);
							System.out.println("Numero de serie modificado con exito.");
							break;			
							
						}
				} catch (NumberFormatException e) {
					System.err.println("Ingresa datos numericos\nFallo al modificar el producto electronico");
				}catch(InputMismatchException e1){
					System.out.println("Error en la entrada de datos\nDato no modificado");
				}
			}
		}else{
			System.out.println("No hay productos electronicos registrados en el sistema");
		}
		menu.iniciarMenu();
	}

	public void consultarElectronico(){//Consulta un electronico en particular
		if(numElectronico>=1){
			String cod=lectorD.leerDatoString("Ingrese el codigo del producto a consultar");
			int pos=buscarProducto(cod);
			if((pos == -1)){
				System.out.println("Producto no hallado.");
			}else{
				String producto=electronicos[pos]+"";
				escritorD.consultarProducto(producto);
			}
		}else{
			System.out.println("No hay productos electronicos registrados en el sistema");
		}
		menu.iniciarMenu();
	}

	public boolean mostrarElectronicos(){//Muestra todos los electronicos (con EscritorDatos)
		boolean existencia=false;
		if(numElectronico>=1){
			String producto="";
			for(int i = 0; i<numElectronico; i++){
				if(electronicos[i].getCantidad()>=1){
					producto+=electronicos[i]+"\n";
					existencia=true;
				}
			}
			if(existencia){
				escritorD.mostrarVector(producto,"Lista de productos electronicos");
			}
		}else{
			System.out.println("No hay productos electronicos registrados en el sistema");
		}
		return existencia;
	}
	public Electronico getElectronico(int i){
		return electronicos[i];
	}
	public int getNumElectronico(){
		return numElectronico;
	}
}