/*Equipo 5
 * Atzin Olarte Erick Abdiel
 * Cumplido Negrete Juan Eduardo
 * Vazquez Ramirez Zaid Alexis
 * PROYECTO PUNTO DE VENTA TIENDA (FINAL)
 */
import Vista.*;
import Controlador.*;
import Modelo.personas.*;
import Modelo.productos.*;
import Modelo.Venta;
import java.io.*;
public class TiendaMain {
	private static FileInputStream fi;
	private static ObjectInputStream ois;
	public static void main(String [] args){
		Menu m = new Menu();
		Producto [] pt = new Producto[10];
		Electronico[] e = new Electronico[10];
		Cliente[] c = new Cliente[10];
		Proveedor[] pv = new Proveedor[10];
		Venta[] v = new Venta[20];
		cargarDatosCliente(c);
		cargarDatosProveedor(pv);
		cargarDatosElectronico(e);
		cargarDatosNoElectronico(pt);
		boolean exist = cargarDatosVenta(v);
		ControladorCliente cc = new ControladorCliente(c,m);
		ControladorProveedor cpr = new ControladorProveedor(pv,m);
		ControladorElectronico ce = new ControladorElectronico(e,m,cpr);
		ControladorProducto cp = new ControladorProducto(pt,m,cpr,ce);
		ControladorVenta cv = new ControladorVenta(v,m,cc,cp,ce,exist);
		ce.addControladorProducto(cp);
		cv.addControladorProducto(cp);
		cv.addControladorElectronico(ce);
		cv.addControladorCliente(cc);
		m.addControladorProducto(cp);
		m.addControladorElectronico(ce);
		m.addControladorCliente(cc);
		m.addControladorProveedor(cpr);
		m.addControladorVenta(cv);
		cp.addVista(m);
		ce.addVista(m);
		cc.addVista(m);
		cpr.addVista(m);
		cv.addVista(m);
		m.iniciarMenu();
	}
		public static void cargarDatosCliente(Cliente[] c){
			try{
				int i = 0;
				File f = new File("Clientes.datos");
				if(f.exists()){
					fi = new FileInputStream(f);
					ois = new ObjectInputStream(fi);
					Cliente e = null;
					while(fi.available()>0){
						e = (Cliente) ois.readObject();
						c[i] = e;
						i++;
					}
					ois.close();
					System.out.println("Datos de Clientes cargados");
				}		
			}catch(IOException e){e.printStackTrace();}
			catch(ClassNotFoundException cnf){cnf.printStackTrace();}
		}
		
	
		public static void cargarDatosProveedor(Proveedor[] pv){
			try{
				int i = 0;
				File f = new File("Proveedores.datos");
				if(f.exists()){
					fi = new FileInputStream(f);
					ois = new ObjectInputStream(fi);
					Proveedor p = null;
					while(fi.available()>0){
						p = (Proveedor) ois.readObject();
						pv[i] = p;
						i++;
					}
					ois.close();
					System.out.println("Datos de proveedores cargados");
				}		
			}catch(IOException e){e.printStackTrace();}
			catch(ClassNotFoundException cnf){cnf.printStackTrace();}
		}
	
		public static void cargarDatosNoElectronico(Producto[] pt){
			try{
				int i = 0;
				File f = new File("NoElectronicos.datos");
				if(f.exists()){
					fi = new FileInputStream(f);
					ois = new ObjectInputStream(fi);
					Producto l = null;
					while(fi.available()>0){
						l = (Producto) ois.readObject();
						pt[i] = l;
						i++;
					}
					ois.close();
					System.out.println("Datos de productos no electronicos cargados");
				}		
			}catch(IOException e){e.printStackTrace();}
			catch(ClassNotFoundException cnf){cnf.printStackTrace();}
		}
	
		public static void cargarDatosElectronico(Electronico[] e){
			try{
				int i = 0;
				File f = new File("Electronicos.datos");
				if(f.exists()){
					fi = new FileInputStream(f);
					ois = new ObjectInputStream(fi);
					Electronico t = null;
					while(fi.available()>0){
						t = (Electronico) ois.readObject();
						e[i] = t;
						i++;
					}
					ois.close();
					System.out.println("Datos de Productos electronico cargados");
				}		
			}catch(IOException e1){e1.printStackTrace();}
			catch(ClassNotFoundException cnf){cnf.printStackTrace();}
		}
		public static boolean cargarDatosVenta(Venta[] v){
			boolean existencia = false;
			try{
				int i = 0;
				File f = new File("Ventas.datos");
				if(f.exists()){
					fi = new FileInputStream(f);
					ois = new ObjectInputStream(fi);
					Venta dv = null;
					while(fi.available()>0){
						dv = (Venta) ois.readObject();
						v[i] = dv;
						i++;
						existencia = true;
					}
					ois.close();
					System.out.println("Datos de Ventas cargados");
				}
			}catch(IOException e){e.printStackTrace();}
			catch(ClassNotFoundException cnf){cnf.printStackTrace();}
			return existencia;
		}
	}
