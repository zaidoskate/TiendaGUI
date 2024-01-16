package Vista;
import java.util.Scanner;

public class LectorDatos {//Clase para ingresar datos
	private Scanner sc = new Scanner(System.in);
	public String[] leerDatosProductos(){//Leer datos de productos no electronicos
		String[] datos = new String[8];
		String[] datosProveedor=new String[3];
		System.out.println("Codigo del Producto: ");
		datos[0]=sc.nextLine();
		System.out.println("Precio de venta del Producto: ");
		datos[1]=sc.nextLine();
		System.out.println("Precio de compra del Producto: ");
		datos[2]=sc.nextLine();
		System.out.println("Descuento del Producto: ");
		datos[3]=sc.nextLine();
		System.out.println("Descripcion del Producto: ");
		datos[4]=LectorDatos.convertirString(sc.nextLine());
		System.out.println("---Datos del provedor del producto---");
		datosProveedor=leerDatosProveedor();
		datos[5]=datosProveedor[0];
		datos[6]=datosProveedor[1];
		datos[7]=datosProveedor[2];
		System.out.println("Stock del producto: ");
		datos[8]=sc.nextLine();	
		return datos;	
	}

	public String[] leerDatosElectronicos(){//Leer datos de productos electronicos
		String [] datosProveedor=new String [3];
		String[] datos = new String[10];
		System.out.println("Codigo del Producto: ");
		datos[0]=sc.nextLine();
		System.out.println("Precio de venta del Producto: ");
		datos[1]=sc.nextLine();
		System.out.println("Precio de compra del Producto: ");
		datos[2]=sc.nextLine();
		System.out.println("Descuento del Producto: ");
		datos[3]=sc.nextLine();
		System.out.println("Descripcion del Producto: ");
		datos[4]=LectorDatos.convertirString(sc.nextLine());
		System.out.println("---Datos del provedor del producto electronico---");
		datosProveedor=leerDatosProveedor();
		datos[5]=datosProveedor[0];
		datos[6]=datosProveedor[1];
		datos[7]=datosProveedor[2];
		System.out.println("Stock del producto: ");
		datos[8]=sc.nextLine();	
        System.out.println("Numero de serie ");
		datos[9]=sc.nextLine();	
		return datos;			
	}

	public String[] leerDatosCliente(){//Leer datos de clientes
		String[] datos = new String [4];
		System.out.println("Nombre: ");
		datos[0]=LectorDatos.convertirString(sc.nextLine());
		System.out.println("Correo: ");
		datos[1]=sc.nextLine().toLowerCase();
		System.out.println("Direccion: ");
		datos[2]=LectorDatos.convertirString(sc.nextLine());
		datos[3]=leerStringMayus("RFC");
		return datos;
	}

	public String[] leerDatosProveedor(){//Leer datos de proveedor
		String[] datos = new String [3];
		System.out.println("Nombre: ");
		datos[0]=LectorDatos.convertirString(sc.nextLine());
        System.out.println("Correo: ");
		datos[1]=sc.nextLine().toLowerCase();
        System.out.println("Telefono: ");
		datos[2]=sc.nextLine();
		return datos;
	}
	
	public String leerDatoStringMayus(String parametro){//Lee un dato String y devuelve la entrada con mayusculas y minusculas segun corresponda
		System.out.println(parametro+":");
		String n=convertirString(sc.nextLine());
		return n;
	}

	public String leerDatoString(String parametro){//Lee un dato String y lo retorna tal cual
		System.out.println(parametro+":");
		String n=sc.nextLine();
		return n;
	}
	public String leerStringMayus(String parametro) {
    System.out.println(parametro + ":");
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine().toUpperCase();
    return input;
}
	public float leerDatoFloat(String parametro){//Lee un dato float y lo retorna
		System.out.println(parametro+":");
		float n=sc.nextFloat();
		return n;
	}
	public int leerDatoInt(String parametro){//Lee un dato Int y lo retorna
		System.out.println(parametro+":");
		int n=sc.nextInt();
		return n;
	}
	public static String convertirString(String entrada) {//Recibe un String como parametro, lo capitaliza segun corresponda
    	char[] chars = entrada.toCharArray();
    	boolean capitalizar = true;//Inicia en true para capitalizar siempre la primera
    	for (int i = 0; i < chars.length; i++) {
			if(chars[i]==' '){
				capitalizar=true;//Cuando haya un espacio la siguiente letra sera mayuscula
			}
        	else if (capitalizar) {//Si capitaliza una letra la siguiente sera minuscula
            	chars[i] = Character.toUpperCase(chars[i]);
            	capitalizar = false;
        	} else {//Minuscula
            chars[i] = Character.toLowerCase(chars[i]);
        }
    }
    return new String(chars);//Return del String nuevo
	}
}
