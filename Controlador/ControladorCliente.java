package Controlador;
import java.util.Scanner;
import Modelo.*;
import Modelo.personas.*;
import Vista.VentanaCliente;
import java.awt.event.*;
import Vista.*;
public class ControladorCliente {
    private Cliente[] clientes;
	private int numClientes;
	private VentanaCliente lectorCliente;
	private Menu menu;
	Scanner sc = new Scanner(System.in);
	private LectorDatos lectorD = new LectorDatos();
	private EscritorDatos escritorD=new EscritorDatos();

	public ControladorCliente() {
	}

	public ControladorCliente(Cliente[] e, Menu m){
		clientes=e;
		menu=m;
		int n = 0;	
		for(int i = 0; i<e.length; i++){
			if(e[i]!=null){
				n++;
			}
		}
		numClientes=n;
	}

	public void addVista(Menu m){
		menu=m;
	}
	public void addVentana(VentanaCliente lector){
		lectorCliente = lector;
	}
	public boolean validarIngresoDatos(String[] datos){//Verifica que todos los datos contengan informacion
		for(int i=0;i<datos.length;i++){
			if(datos[i].equals("")||datos[i].equals("\n")||datos[i].equals(null)){
				return false;
			}
		}
		return true;
	}

	public void registrarCliente(String[] datos){//Crear cliente
		if(numClientes<clientes.length){
			if(validarIngresoDatos(datos)){
				Cliente clienteAux;
				boolean b = true;
				clienteAux = new Cliente(datos[0], datos[1], datos[2], datos[3]);
				for(int i = 0; i<numClientes; i++){
					if((datos[3].equals(clientes[i].getRfc()))){//Si ya existe un cliente con un rfc existente
						System.out.println("Este cliente ya esta registrado en el sistema");
						b = false;
					}
				} 
				if(b){
					clientes[numClientes] = clienteAux;
					System.out.println("Se ha registrado el cliente");
					numClientes++;
				}
			}else{
				System.out.println("Los datos ingresados contienen campos vacios\nError al crear el cliente\n\n");
			}
		}else{
			System.out.println("No hay espacio para mas clientes");
		}
		lectorCliente.setVisible(true);
		lectorCliente.dispose();
		 menu.iniciarMenu();
	}
	public void cerrarLector(){
		lectorCliente.setVisible(true);
		lectorCliente.dispose();
	     menu.iniciarMenu();

	}

	public int buscarCliente(String rfc){//Busca un cliente especifico por su rfc
		int i=0, b=-1;
		String rfcAux;
		while((i<numClientes)&&(b==-1)){
			rfcAux=clientes[i].getRfc();
			if(rfcAux.equals(rfc)){
				b=i;
			}else{
				i++;
			}
		}
		return b;//Retorna -1 si no existe
	}
	
	public void eliminarCliente(){//Elimina un cliente por su rfc
		if(numClientes>=1){
			int i=0;
			mostrarClientes();
			String rfc=lectorD.leerStringMayus("Ingresa el RFC del cliente a eliminar");
			int pos=buscarCliente(rfc);
			if((pos<0)||(pos>=clientes.length)){
				System.out.println("El RFC del cliente no existente en el registro");
			}else{
				for(i=pos+1; i<=numClientes-1;i++){
					clientes[i-1]=clientes[i];
				}
				System.out.println("El cliente con RFC "+rfc+" ha sido eliminado");
				numClientes--;
			}

		}else{
			System.out.println("No hay clientes registrados en el sistema");
		}
		 menu.iniciarMenu();
	}

	public void modificarCliente(){//Modifica atributos de un cliente
		if(numClientes>=1){
			mostrarClientes();
			String rfc=lectorD.leerStringMayus("Ingresa el RFC del cliente a modificar");
			int pos=buscarCliente(rfc);
			if((pos<0)||(pos>=numClientes)){
				System.out.println("El RFC del cliente no existente en el registro");
			}else{
				String modif;
				int opc=menu.menuModificacionCliente();
				switch(opc){
					case 1:
						modif=lectorD.leerDatoStringMayus("Ingresa el nuevo nombre");
						clientes[pos].setNombre(modif);
						System.out.println("Nombre modificado con exito.");
						break;
					case 2:
						modif=lectorD.leerDatoStringMayus("Ingresa la nueva direccion");
						clientes[pos].setDireccion(modif);
						System.out.println("Direccion modificada con exito.");
						break;
					case 3:
						modif=lectorD.leerDatoString("Ingresa el nuevo correo");
						clientes[pos].setCorreo(modif);
						System.out.println("Correo modificado con exito.");
						break;
					case 4:
						boolean c=false;
						modif=lectorD.leerStringMayus("Ingresa el nuevo RFC");
						for(int i=0;i<numClientes;i++){
							if(modif.equals(clientes[i].getRfc())){
								System.out.println("Este RFC ya lo tiene otra Persona");
								c=true;
							}
						}
						if(c==false){
							clientes[pos].setRfc(modif);
							System.out.println("RFC modificado con exito.");
						}
						break;
				}
			}
		}else{
			System.out.println("No hay clientes registrados en el sistema");
		}
		menu.iniciarMenu();
	}

	public void consultarCliente(){//Consulta un cliente en particular
		if(numClientes>=1){
			String nombre=lectorD.leerStringMayus("Ingresa el RFC del cliente a consultar");
			int pos = buscarCliente(nombre);
			if(pos==-1){
				System.out.println("El cliente no existe en el registro");
			}else{
				String cliente=clientes[pos]+"";
				escritorD.consultarCliente(cliente);
			}
		}else{
			System.out.println("No hay clientes registrados en el sistema");
		}
		menu.iniciarMenu();
	}

	public void mostrarClientes(){//Muestra todos los clientes (con EscritorDatos)
		if(numClientes>=1){
			String cliente="";
			for(int i = 0; i<numClientes; i++){
				cliente+=clientes[i]+"\n";
			}
			escritorD.mostrarVector(cliente,"Lista de clientes");
		}else{
			System.out.println("No hay clientes registrados en el sistema");
		}
	}

	public Cliente getCliente(int i){
		return clientes[i];
	}

	public int getNumClientes(){
		return numClientes;
	}
	public void addCliente(Cliente cliente){
		if(numClientes<=10){
			clientes[numClientes]=cliente;
			numClientes++;
		}else{
			System.out.println("No hay espacio para mas clientes");
		}
	}
}
