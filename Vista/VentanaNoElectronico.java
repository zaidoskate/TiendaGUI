package Vista;
import javax.swing.*;
import Controlador.ControladorProducto;
import java.awt.*;
import java.awt.event.*;
public class VentanaNoElectronico extends JFrame{
	private ControladorProducto controladorProducto;
	private Menu menu;
    private JTextField tfCodigo;
    private JTextField tfPrecio;
    private JTextField tfDescuento;
	private JTextField tfPrecioCompra;
    private JTextField tfDescripcion;
    private JTextField tfCantidad;
    private JTextField tfNombreProveedor;
    private JTextField tfCorreoProveedor;
    private JTextField tfTelefonoProveedor;
    private JButton btnCancelar, btnGuardar;

	Color colorFranjas = new Color(55, 63, 81);
	Color colorCancelar = new Color(218, 164, 154);
	Color colorGuardar = new Color(88, 164, 176);
	Color colorFondo = new Color(80, 90, 112);

	public VentanaNoElectronico(){
		super("Tienda");
		addWindowListener(new cerrarVentana());
		Container content = getContentPane();
		setLayout(new BorderLayout());
		
		JPanel panelTitulo = _crearPTitulo();
		content.add(panelTitulo, BorderLayout.NORTH);

		JPanel panelIz = new JPanel();
		panelIz.setBackground(colorFondo);
		content.add(panelIz, BorderLayout.WEST);
		JPanel panelDe = new JPanel();
		panelDe.setBackground(colorFondo);
		content.add(panelDe, BorderLayout.EAST);

		JPanel panelCuerpo = new JPanel(new GridLayout(1, 2));
		JPanel panelCuerpoI = _crearPCuerpoI();
		panelCuerpo.add(panelCuerpoI);
		content.add(panelCuerpo, BorderLayout.CENTER);

		JPanel panelBotones = _crearPBotones();
		content.add(panelBotones, BorderLayout.SOUTH);

		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controladorProducto.cerrarLector();
			}
		});
	}	

	public void addMenu(Menu vista){
		menu = vista;
	}

	public void addControlador(ControladorProducto cnel){
		this.controladorProducto = cnel;
	}

	public void mostrarVentana(){
		setSize(450, 550);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public JPanel _crearPTitulo(){
		JPanel pan = new JPanel(new BorderLayout());
		JPanel pI = new JPanel();
		JPanel pD = new JPanel();
		pI.setBackground(colorFranjas);
		pD.setBackground(colorFranjas);
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("Registrar Producto No Electronico");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setForeground(Color.white);
		p.add(label);
		p.setBackground(colorFranjas);

		pan.add(pI, BorderLayout.WEST);
		pan.add(pD, BorderLayout.EAST);
		pan.add(p, BorderLayout.CENTER);
		return pan;
	}

	public JPanel _crearPCuerpoI(){
		JPanel p = new JPanel(new GridLayout(5, 1));
		JPanel p1 = _crearPCuerpoCodigoPrecio();
		JPanel p2 = _crearPCuerpoDescuentoPrecioCompra();
		JPanel p3 = _crearPCuerpoDescripcioCantidad();
		JPanel p4 = _crearPCuerpoNombreCorreo();
		JPanel p5 = _crearPCuerpoTelefono();
		
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		return p;
	}
	
    public JPanel _crearPCuerpoCodigoPrecio(){
		JPanel p = new JPanel(new GridLayout(1, 2));	
		JPanel pCodigo = _crearPCodigo();
		JPanel pPrecio = _crearPPrecio();
		p.add(pCodigo);
		p.add(pPrecio);
		
		return p;
	}

	public JPanel _crearPCodigo(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Codigo");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfCodigo = new JTextField(14);
		p2.add(tfCodigo);
		p2.setBackground(colorFondo);
		p.add(p2);


		return p;		
	}
    public JPanel _crearPPrecio(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Precio de Venta");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfPrecio = new JTextField(14);
		p2.add(tfPrecio);
		p2.setBackground(colorFondo);
		p.add(p2);


		return p;		
	}

	public JPanel _crearPCuerpoDescuentoPrecioCompra(){
		JPanel p = new JPanel(new GridLayout(1, 2));	
		JPanel pDescuento = _crearPDescuento();
		JPanel pPrecioCompra = _crearPPrecioCompra();
		p.add(pDescuento);
		p.add(pPrecioCompra);
		
		return p;
	}

	public JPanel _crearPDescuento(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Descuento");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfDescuento = new JTextField(14);
		p2.add(tfDescuento);
		p2.setBackground(colorFondo);
		p.add(p2);

		return p;	
	}
	public JPanel _crearPPrecioCompra(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Precio de Compra");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfPrecioCompra = new JTextField(14);
		p2.add(tfPrecioCompra);
		p2.setBackground(colorFondo);
		p.add(p2);


		return p;		
	}	
	
    public JPanel _crearPCuerpoDescripcioCantidad(){
		JPanel p = new JPanel(new GridLayout(1, 2));
		JPanel pDescripcion = _crearPDescripcion();
		JPanel pCantidad = _crearPCantidad();
		p.add(pCantidad);
		p.add(pDescripcion);
		return p;
	}
	public JPanel _crearPDescripcion(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Descripcion");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfDescripcion = new JTextField(14);
		p2.add(tfDescripcion);
		p2.setBackground(colorFondo);
		p.add(p2);

		return p;	
	}
	public JPanel _crearPCantidad(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Cantidad");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfCantidad = new JTextField(14);
		p2.add(tfCantidad);
		p2.setBackground(colorFondo);
		p.add(p2);

		return p;	
	}
	
	public JPanel _crearPCuerpoNombreCorreo(){
		JPanel p = new JPanel(new GridLayout(1, 2));	
		JPanel pNombre = _crearPNombre();
		JPanel pCorreo = _crearPCorreo();
		p.add(pNombre);
		p.add(pCorreo);
		
		return p;
	}	

	public JPanel _crearPNombre(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Nombre del Proveedor");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfNombreProveedor = new JTextField(14);
		p2.add(tfNombreProveedor);
		p2.setBackground(colorFondo);
		p.add(p2);

		return p;	
	}

	public JPanel _crearPCorreo(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Correo del Proveedor");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfCorreoProveedor = new JTextField(14);
		p2.add(tfCorreoProveedor);
		p2.setBackground(colorFondo);
		p.add(p2);

		return p;	
	}

	
	public JPanel _crearPCuerpoTelefono(){
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Telefono del Proveedor");
		label.setForeground(Color.white);
		p1.add(label);
		p1.setBackground(colorFondo);
		p.add(p1);
	
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfTelefonoProveedor = new JTextField(14);
		p2.add(tfTelefonoProveedor);
		p2.setBackground(colorFondo);
		p.add(p2);

		return p;	
	}

	public JPanel _crearPBotones(){
		JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(colorCancelar);
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(colorGuardar);
		btnGuardar.addActionListener(new alGuardar());
		p.add(btnCancelar);
		p.add(btnGuardar);
		p.setBackground(colorFranjas);
		return p;
	}

	class alGuardar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String[] datos = new String[9];
			datos[0] = tfCodigo.getText();
			datos[1] = tfPrecio.getText();
			datos[2] = tfPrecioCompra.getText();
			datos[3] = tfDescuento.getText();
			datos[4] = tfDescripcion.getText();
			datos[5] = LectorDatos.convertirString(tfNombreProveedor.getText());
			datos[6] = tfCorreoProveedor.getText();
			datos[7] = tfTelefonoProveedor.getText();
			datos[8] = tfCantidad.getText();
			controladorProducto.agregarProducto(datos);
		}
	}
	
	class cerrarVentana implements WindowListener{
		public void windowClosing(WindowEvent e){
			controladorProducto.cerrarLector();
		}
		public void windowActivated(WindowEvent e){}
		public void windowClosed(WindowEvent e){}
		public void windowDeactivated(WindowEvent e){}
		public void windowDeiconified(WindowEvent e){}
		public void windowIconified(WindowEvent e){}
		public void windowOpened(WindowEvent e){}
	}

	
	
	public static void main(String[] args){
		VentanaElectronico ve = new VentanaElectronico();
	}

	
}