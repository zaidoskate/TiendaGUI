package Vista;
import javax.swing.*;
import Controlador.ControladorVenta;
import java.awt.*;
import java.awt.event.*;
public class VentanaPedidos extends JFrame{
        private Menu menu;
        private ControladorVenta controladorVenta;
        private JButton bCancelar, bBuscar;
        private JTextField tfCodigo;
        Color colorFranjas = new Color(55,63,81);
        Color colorEtiquetas = new Color(218, 223, 234);
        Color colorBCancelar = new Color(218,164,154);
        Color colorFondo = new Color(80, 90, 112);
        Color colorBBuscar = new Color(88,164,176);
        Font f1 = new Font("Arial Rounded MT Bold", Font.BOLD, 16);
	     Font f2 = new Font("Arial", Font.PLAIN, 14);

        public VentanaPedidos(){
            super("Tienda");
		    setLayout(new BorderLayout());
		    addWindowListener(new CerrarVentana());
		    JPanel pTitulo = _crearPTitulo();
		    JPanel pCentral = _crearPCentral();
		    JPanel pCodigo = _crearPCodigo();
		    JPanel pBotones = _crearPBotones();
		
		    JPanel panelIzq = new JPanel();
		    panelIzq.setBackground(colorFondo);
		    add(panelIzq, BorderLayout.WEST);
		    JPanel panelDer = new JPanel();
		    panelDer.setBackground(colorFondo);
		    add(panelDer, BorderLayout.EAST);

		    add(pTitulo, BorderLayout.NORTH);
		    pCentral.add(pCodigo);
		    add(pCentral, BorderLayout.CENTER);
		    add(pBotones, BorderLayout.SOUTH);
		    bCancelar.addActionListener(new AlCancelar());
		    setBackground(colorFondo);	
        }
        public void addMenu(Menu vista){
            menu=vista;
        }
    
        public void mostrarVentana(){
            setSize(400,200);
            setVisible(true);
            setLocationRelativeTo(null);
        }
        public void addControlador(ControladorVenta cv){
            this.controladorVenta=cv;
        }
        public JPanel _crearPTitulo(){
            JPanel pp = new JPanel(new BorderLayout());
            JPanel pI = new JPanel();
            JPanel pD = new JPanel();
            pI.setBackground(colorFranjas);
            pD.setBackground(colorFranjas);
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lbTitle = new JLabel("Ingrece el codigo del producto");
            lbTitle.setFont(f1);
            lbTitle.setForeground(colorEtiquetas);
            p.add(lbTitle);
            p.setBackground(colorFranjas);
            pp.add(pI, BorderLayout.WEST);
            pp.add(pD, BorderLayout.EAST);
            pp.add(p, BorderLayout.CENTER);
            return pp;
        }
        public JPanel _crearPCentral(){
            JPanel p = new JPanel(new GridLayout(2,2));
            p.setBackground(colorFondo);
            return p;
        }
        public JPanel _crearPCodigo(){
            JPanel p = new JPanel(new GridLayout(2,1));
            JPanel plb1 = new JPanel(new BorderLayout());
            JPanel plb = new JPanel(new FlowLayout(FlowLayout.LEFT));
            plb1.add(plb, BorderLayout.SOUTH);
            JPanel ptf = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tfCodigo = new JTextField(17);
            tfCodigo.setBackground(colorEtiquetas);
            
            ptf.add(tfCodigo);
            p.add(plb1);
            p.add(ptf);
            p.setBackground(colorFondo);
            plb1.setBackground(colorFondo);
            plb.setBackground(colorFondo);
            ptf.setBackground(colorFondo);
            return p;
        }
        public JPanel _crearPBotones(){
            JPanel pp = new JPanel(new BorderLayout());
            JPanel pD = new JPanel();
            pD.setBackground(colorFranjas);
            pp.add(pD, BorderLayout.EAST);
            JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            bCancelar = new JButton("Cancelar");
            bBuscar = new JButton("Pedir");
            bCancelar.setBackground(colorBCancelar);
            bBuscar.setBackground(colorBBuscar);
            bBuscar.addActionListener(new AlBuscar());
            p.add(bCancelar);
            p.add(bBuscar);
            p.setBackground(colorFranjas);
            pp.add(p,BorderLayout.CENTER);
            return pp;
        }
        class CerrarVentana implements WindowListener{
            public void windowClosing(WindowEvent e){
                controladorVenta.cerrarLector();
            }
            public void windowActivated(WindowEvent e){}
            public void windowClosed(WindowEvent e){}
            public void windowDeactivated(WindowEvent e){}
            public void windowDeiconified(WindowEvent e){}
            public void windowIconified(WindowEvent e){}
            public void windowOpened(WindowEvent e){}
        }
        class AlBuscar implements ActionListener{
            public void actionPerformed(ActionEvent e){
                String cod=tfCodigo.getText();
                controladorVenta.generarPedido(cod);
            }
        }
    class AlCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			controladorVenta.cerrarLector();
		}
	}
        public static void main(String[] args){
            VentanaPedidos vp = new VentanaPedidos();
        }
    }