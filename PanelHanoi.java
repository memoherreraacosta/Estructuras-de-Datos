import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelHanoi extends JPanel implements Runnable{
	
	JSlider js;
	static final int FPS_MIN = 1;
	static final int FPS_MAX = 10;	
	static final int FPS_INIT = 1;  
	
	private	int contador=0;
	private int posTorreA=240;
	private int posTorreB=590;
	private int posTorreC=940;
	private int numDiscos;
	
	JButton botonStart;
	JTextField tfC,tfND;
	
	public PanelHanoi(){
		super();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(1200,400));
		this.setLocation(getX()/3, getY()/3);
		Thread hilo=new Thread(this);
		
		this.preguntarDiscos();
		
		this.botonStart= new JButton("Start/Stop");
		this.botonStart.addActionListener(new ActionListener() {
		       @Override 
		       public void actionPerformed(ActionEvent evt) {
		    	   		
		    	   		
		       }
		 });
		this.botonStart.setBounds(15, 15, 8, 8);
		this.add(this.botonStart);
		
		this.tfND= new JTextField(10);
		this.tfND.setEditable(false);
		this.tfND.setText("Nivel de velocidad: ");
		this.add(this.tfND);
		
		js= new JSlider(JSlider.HORIZONTAL,PanelHanoi.FPS_MIN,PanelHanoi.FPS_MAX,PanelHanoi.FPS_INIT);
		this.js.setPaintTicks(true);
		this.js.setPaintLabels(true);
		this.js.setMinorTickSpacing(1);
		this.js.setMajorTickSpacing(1);
		this.js.addChangeListener(new ChangeListener(){

			public void stateChanged(ChangeEvent arg0) {
				
				try {
					//System.out.println(120-js.getValue()*10);
					Thread.sleep(120-js.getValue()*10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		this.add(this.js);
		
		this.tfC= new JTextField(18);
		this.tfC.setEditable(false);
		this.tfC.setText("Numero de movimientos: "+this.getContador());
		this.add(this.tfC);
		
	}
	public void pintarDiscos(int numDiscos){
		for(int i=0; i<numDiscos;i++) {
			
		}
	}
	
	public void preguntarDiscos() {
		try {
			Integer valor=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el numero de discos entre 1 y 10"));
			if(1<=valor && valor<=10){
				this.setNumDiscos(valor);
			}else {
				this.preguntarDiscos();
			}
		}catch(java.lang.NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Ingreso un valor invalido\npor favor intentelo de nuevo");
			this.preguntarDiscos();
		}
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		g.setColor(new Color(0,76,153));
		g.fillRect(100, 350, 1000, 50); 	//Base
		
		g.setColor(new Color(102,205,170));
		g.fillRect(this.posTorreA, 100, 20, 250);	//Torre A
		g.fillRect(this.posTorreB, 100, 20, 250);	//Torre B
		g.fillRect(this.posTorreC, 100, 20, 250);	//Torre C
		
		g.setColor(Color.BLACK);
		g.drawString("Torre A",230 ,90);
		g.drawString("Torre B",580 ,90);
		g.drawString("Torre C",930 ,90);
		
	}
	
	public void sumarContador() {
		this.contador++;
		this.tfC.setText("Contador: "+this.getContador());
	}
	
	public String getContador() {
		this.paintImmediately(0, 0, this.getWidth(), this.getHeight());
		return this.contador+"";
	}
	public int getNumDiscos(){
		this.paintImmediately(0, 0, this.getWidth(), this.getHeight());
		return this.numDiscos;
	}
	
	public void setNumDiscos(int numDiscos) {
		this.numDiscos=numDiscos;
		this.paintImmediately(0, 0, this.getWidth(), this.getHeight());
	}
	public void move(int numberOfDiscs, char from, char to, char inter) {
		if (numberOfDiscs == 1) {
			System.out.println("Moving disc 1 from " + from + " to " + to);
			PanelHanoi.this.contador++;
		} else {
			move(numberOfDiscs - 1, from, inter, to);
			System.out.println("Moving disc " + numberOfDiscs + " from " + from + " to " + to);
			PanelHanoi.this.contador++;
			move(numberOfDiscs - 1, inter, to, from);
		}
	}
	
	public static void main(String[]args) {
		
		JFrame frame= new JFrame("Torre de Hanoi");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		PanelHanoi ph= new PanelHanoi();
		frame.add(ph, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
	}
}
