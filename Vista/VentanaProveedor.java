package Vista;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Controlador.ControladorProveedor;
public class VentanaProveedor extends JFrame{
    private JTextField nombre, correo, telefono;
		private JButton guardar, cancelar;
		private ControladorProveedor controladorProveedor;
		private Menu menu; 
		Color colorBorde = new Color(55, 63, 81);
		Color colorCancelar = new Color(218, 164, 154);
		Color colorGuardar = new Color(88, 164, 176);
		Color colorFondo = new Color(80, 90, 112);

	public VentanaProveedor(){
		super("Registrar Proveedor");
		setLayout(new BorderLayout());
		addWindowListener(new CerrarVentana());
		JPanel pNorth= titulo();
		add(pNorth, BorderLayout.NORTH);

		JPanel pCenter= datos();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setBackground(colorFondo);

 		JPanel pSouth=botones();
		add(pSouth, BorderLayout.SOUTH);
	
		cancelar.addActionListener(new AlCancelar());
	

	}	

	public void addMenu(Menu vista){
		menu=vista;
	}

	public void mostrarVentana(){
		setSize(600,250);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void addControlador(ControladorProveedor p){
			this.controladorProveedor=p;
	}
	private JPanel titulo(){
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ti = new JLabel("Registar Proveedor");
		ti.setFont(new Font("Arial", Font.BOLD, 17));
		ti.setForeground(Color.white);
		p.add(ti);
		p.setBackground(colorBorde);
			return p;
	}
	

	private JPanel datos(){
		JPanel g = new JPanel(new GridLayout(2,2));
		JPanel g2= new JPanel(new GridLayout(2,3));
		JPanel fl = new JPanel(new FlowLayout());
		JPanel p2 = new JPanel(new BorderLayout());

		JPanel plNombre = new JPanel(new BorderLayout());
		JPanel plCorreo = new JPanel(new BorderLayout());
		JPanel plTelefono = new JPanel(new BorderLayout());

		
		JPanel pln = new JPanel( new FlowLayout (FlowLayout.LEFT));
		JPanel plc = new JPanel( new FlowLayout (FlowLayout.LEFT));
		JPanel plt = new JPanel (new FlowLayout (FlowLayout.LEFT));
		
		JLabel lbNombre= new JLabel("Nombre");
		lbNombre.setFont(new Font("Arial", Font.BOLD, 17));
lbNombre.setForeground(Color.white);
		JLabel lbCorreo = new JLabel("Correo");
		lbCorreo.setFont(new Font("Arial", Font.BOLD, 17));
lbCorreo.setForeground(Color.white);
		JLabel lbTelefono = new JLabel("Telefono");
		lbTelefono.setFont(new Font("Arial", Font.BOLD, 17));
lbTelefono.setForeground(Color.white);

		pln.add(lbNombre);
		pln.setBackground(colorFondo);
		plc.add(lbCorreo);
		plc.setBackground(colorFondo);
		plt.add(lbTelefono);
		plt.setBackground(colorFondo);;

		plNombre.add(pln, BorderLayout.SOUTH);
		plCorreo.add(plc, BorderLayout.SOUTH);
		plTelefono.add(plt, BorderLayout.SOUTH);

		JPanel tnombre = new JPanel( new FlowLayout(FlowLayout.LEFT));
		tnombre.setBackground(colorFondo);
		JPanel tcorreo = new JPanel( new FlowLayout(FlowLayout.LEFT));
		tcorreo.setBackground(colorFondo);
		JPanel ttelefono = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ttelefono.setBackground(colorFondo);
	
		nombre = new JTextField(14);
		correo = new JTextField(14);
		telefono = new JTextField(14);

		tnombre.add(nombre);
		tcorreo.add(correo);
		ttelefono.add(telefono);
	
		g.add(plNombre);
		g.add(plCorreo);
		g.add(plTelefono);
		g.add(tnombre);
		g.add(tcorreo);
		g.add(ttelefono);


		p2.add(g, BorderLayout.NORTH);
		p2.add(g2, BorderLayout.SOUTH);
		fl.add(p2);
		return fl;
		
		}

	private JPanel botones(){
		JPanel p= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cancelar = new JButton("Cancelar");
		guardar = new JButton("Guardar");
		guardar.addActionListener(new guardarDatos());
		cancelar.setBackground(colorCancelar);
		guardar.setBackground(colorGuardar);
		p.add(cancelar);
		p.add(guardar);
		p.setBackground(colorBorde);
		return p;
		
	}

	class guardarDatos implements ActionListener{
		public void actionPerformed(ActionEvent e){
		String [] d = new String [3];
		d[0] = LectorDatos.convertirString(nombre.getText());
		d[1] = correo.getText();
		d[2] = telefono.getText();
		controladorProveedor.registrarProveedor(d);
		}
	}
	class CerrarVentana implements WindowListener{
		public void windowClosing(WindowEvent e){
			controladorProveedor.cerrarLector();
		}
		public void windowActivated(WindowEvent e){}
		public void windowClosed(WindowEvent e){}
		public void windowDeactivated(WindowEvent e){}
		public void windowDeiconified(WindowEvent e){}
		public void windowIconified(WindowEvent e){}
		public void windowOpened(WindowEvent e){}
	}
	class AlCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			controladorProveedor.cerrarLector();
		}
	}
	
	public static void main(String[] args){
		VentanaProveedor ra = new VentanaProveedor();
	}

}