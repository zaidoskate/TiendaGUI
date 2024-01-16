package Vista;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Controlador.ControladorCliente;
public class VentanaCliente extends JFrame{
    private JTextField nombre, direccion, rfc, correo;
		private JButton guardar, cancelar;
		private ControladorCliente controladorCliente;
		private Menu menu; 
		Color colorBorde = new Color(55, 63, 81);
		Color colorCancelar = new Color(218, 164, 154);
		Color colorGuardar = new Color(88, 164, 176);
		Color colorFondo = new Color(80, 90, 112);

	public VentanaCliente(){
		super("TIENDA");
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
	public void addControlador(ControladorCliente c){
			this.controladorCliente=c;
	}
	private JPanel titulo(){
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ti = new JLabel("Registar Cliente");
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
		JPanel plDireccion = new JPanel(new BorderLayout());
		JPanel plRfc = new JPanel(new BorderLayout());
		JPanel plCorreo = new JPanel (new BorderLayout());

		
		JPanel pln = new JPanel( new FlowLayout (FlowLayout.LEFT));
		JPanel pld = new JPanel( new FlowLayout (FlowLayout.LEFT));
		JPanel plr = new JPanel (new FlowLayout (FlowLayout.LEFT));
		JPanel plc = new JPanel (new FlowLayout (FlowLayout.LEFT));
		
		JLabel lbNombre= new JLabel("Nombre");
		lbNombre.setFont(new Font("Arial", Font.BOLD, 17));
lbNombre.setForeground(Color.white);
		JLabel lbDireccion = new JLabel("Direccion");
		lbDireccion.setFont(new Font("Arial", Font.BOLD, 17));
lbDireccion.setForeground(Color.white);
		JLabel lbRfc = new JLabel("RFC");
		lbRfc.setFont(new Font("Arial", Font.BOLD, 17));
lbRfc.setForeground(Color.white);
		JLabel lbCorreo = new JLabel("Correo");
		lbCorreo.setFont(new Font("Arial", Font.BOLD, 17));
lbCorreo.setForeground(Color.white);

		pln.add(lbNombre);
		pln.setBackground(colorFondo);
		pld.add(lbDireccion);
		pld.setBackground(colorFondo);
		plr.add(lbRfc);
		plr.setBackground(colorFondo);
		plc.add(lbCorreo);
		plc.setBackground(colorFondo);

		plNombre.add(pln, BorderLayout.SOUTH);
		plDireccion.add(pld, BorderLayout.SOUTH);
		plRfc.add(plr, BorderLayout.SOUTH);
		plCorreo.add(plc, BorderLayout.SOUTH);

		JPanel tnombre = new JPanel( new FlowLayout(FlowLayout.LEFT));
		tnombre.setBackground(colorFondo);
		JPanel tdireccion = new JPanel( new FlowLayout(FlowLayout.LEFT));
		tdireccion.setBackground(colorFondo);
		JPanel trfc = new JPanel(new FlowLayout(FlowLayout.LEFT));
		trfc.setBackground(colorFondo);
		JPanel tcorreo = new JPanel ( new FlowLayout(FlowLayout.LEFT));
		tcorreo.setBackground(colorFondo);
	
		nombre = new JTextField(14);
		direccion = new JTextField(14);
		rfc = new JTextField(14);
		correo = new JTextField(14);

		tnombre.add(nombre);
		tdireccion.add(direccion);
		trfc.add(rfc);
		tcorreo.add(correo);
	
		g.add(plNombre);
		g.add(plDireccion);
		g.add(tnombre);
		g.add(tdireccion);
		g2.add(plRfc);
		g2.add(plCorreo);
		g2.add(trfc);
		g2.add(tcorreo);


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
		String [] d = new String [4];
		d[0] = LectorDatos.convertirString(nombre.getText());
		d[1] = correo.getText();
		d[2] = direccion.getText();
		d[3] = rfc.getText().toUpperCase();
		controladorCliente.registrarCliente(d);
		}
	}
	class CerrarVentana implements WindowListener{
		public void windowClosing(WindowEvent e){
			controladorCliente.cerrarLector();
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
			controladorCliente.cerrarLector();
		}
	}
	
	public static void main(String[] args){
		VentanaCliente ra = new VentanaCliente();
	}

}