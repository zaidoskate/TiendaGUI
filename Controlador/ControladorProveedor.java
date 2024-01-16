package Controlador;
import java.util.Scanner;
import Modelo.personas.*;
import Vista.VentanaProveedor;
import Vista.*;
public class ControladorProveedor {
    private Proveedor[] proveedores;
	private int numProveedores;
	private VentanaProveedor lectorProveedor;
	private Menu menu;
	
	private LectorDatos lectorD= new LectorDatos();
	private EscritorDatos escritorD=new EscritorDatos();
	public ControladorProveedor() {
	}
	public ControladorProveedor(Proveedor[] p, Menu m){
		proveedores = p;
        menu=m;
		int n = 0;	
		for(int i = 0; i<p.length; i++){
			if(p[i]!=null){
				n++;
			}
		}
		numProveedores=n;
	}
	public void addVista(Menu m){
		menu=m;
	}
public void addVentana(VentanaProveedor lector){
		lectorProveedor = lector;
	}
public boolean validarIngresoDatos(String[] datos) {
		for (int i = 0; i < datos.length; i++) {
			if (datos[i].equals("") || datos[i].equals("\n") || datos[i] == null) {
				return false;
			}
		}
		return true;
	}

	public void registrarProveedor(String[] datos){//Agregar proveedor
		if(numProveedores<proveedores.length){
			if(validarIngresoDatos(datos)){
				Proveedor proveedorAux;
				boolean b = true;
				proveedorAux = new Proveedor(datos[0], datos[1], datos[2]);
				for(int i = 0; i<numProveedores; i++){
					if(datos[0].equals(proveedores[i].getNombre())){
						System.out.println("Este proveedor ya esta registrado en el sistema");
						b = false;
					}
				}
				if(b==true){//Si el proveedor no existe se agrega al vector
					proveedores[numProveedores] = proveedorAux;
					System.out.println("Se ha registrado el proveedor");
					numProveedores++;
				}
			}else{
				System.out.println("Los datos ingresados contienen campos vacios\nError al crear el proveedor\n\n");
			}
		}else{
			System.out.println("No hay espacio para mas proveedores");
		}
		lectorProveedor.setVisible(true); 
		lectorProveedor.dispose();
		menu.iniciarMenu();
	}
	public void cerrarLector(){
	    lectorProveedor.setVisible(true); 
		lectorProveedor.dispose();
		menu.iniciarMenu();

	}
	

	public int buscarProveedor(String nombre){//Buscar un proveedor por su nombre
		int i = 0, b = -1;
		String nombreAux;
		while((i<numProveedores)&&(b==-1)){
			nombreAux = proveedores[i].getNombre();
			if(nombreAux.equals(nombre)){
				b = i;
			}else{
				i++;
			}
		}
		return b;
	}
	
	public void eliminarProveedor(){//Eliminar un proveedor por su nombre
		if(numProveedores>=1){
			int i = 0;
			mostrarProveedores();
			String nombre=lectorD.leerDatoStringMayus("Ingresa el nombre del proveedor a eliminar");
			int pos = buscarProveedor(nombre);
			if((pos<0)||(pos>=numProveedores)){
				System.out.println("El proveedor no existente en el registro");
			}else{
				for(i = pos+1; i<=numProveedores-1; i++){
					proveedores[i-1] = proveedores[i];
				}
				System.out.println("El proveedor "+nombre+" ha sido eliminado.");
				numProveedores--;
			}
		}else{
			System.out.println("No hay proveedores almacenados");
	}
	menu.iniciarMenu();

}

	public void consultarProveedor(){//Consultar un proveedor por su nombre
		if(numProveedores>=1){
			String nombre=lectorD.leerDatoStringMayus("Ingresa el nombre del proveedor a consultar");
			int pos = buscarProveedor(nombre);
			if(pos<0){
				System.out.println("El proveedor no existe en el registro");
			}else{
				String proveedor=proveedores[pos]+"";
				escritorD.consultarProveedor(proveedor);
			}
		}else{
			System.out.println("No hay proveedores registrados");
		}
		menu.iniciarMenu();

	}
	
    
	public void mostrarProveedores(){//Muestra todos los proveedores (en EscritorDatos)
		if(numProveedores>=1){
			String proveedor="";
			for(int i = 0; i<numProveedores; i++){
				proveedor+=proveedores[i]+"\n";
			}
			escritorD.mostrarVector(proveedor,"Lista de proveedores");
		}else{
			System.out.println("No hay proveedores registrados en el sistema");
		}

	}

	public void modificarProveedor(){//Modifica un proveedor
		if(numProveedores>=1){
			mostrarProveedores();
			String nombre=lectorD.leerDatoStringMayus("Ingresa el nombre del proveedor a modificar");
			int pos=buscarProveedor(nombre);//Retorna -1 si no existe
			if(pos==-1){
				System.out.println("El proveedor no existente en el registro");
			}else{
				int op=menu.menuModificacionProveedor();
				switch(op){
					case 1:
						String nom = lectorD.leerDatoStringMayus("Ingresa el nuevo nombre"); 
						proveedores[pos].setNombre(nom);
						System.out.println("Nombre modificado con exito.");
						break;
					case 2:
						String telefono = lectorD.leerDatoString("Ingresa el nuevo telefono");
						proveedores[pos].setTelefono(telefono);
						System.out.println("Telefono modificado con exito.");
						break;
					case 3:
						String correo = lectorD.leerDatoString("Ingresa el nuevo correo");
						proveedores[pos].setCorreo(correo);
						System.out.println("Correo modificado con exito.");
						break;
					default:
						System.out.println("Opcion no valida.");
						break;
				}
			}
		}else{
			System.out.println("No hay proveedores registrados en el sistema");
		}
		menu.iniciarMenu();
	}

	public Proveedor getProveedor(int i){
		return proveedores[i];
	}
	
	public int getNumProveedores(){
		return numProveedores;
	}
	public void addProveedor(Proveedor proveedor){
		if(numProveedores<=10){
			proveedores[numProveedores]=proveedor;
			numProveedores++;
		}else
			System.out.println("No hay espacio para mas proveedores");
	}
}
