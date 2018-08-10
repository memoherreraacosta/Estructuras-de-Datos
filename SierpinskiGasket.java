import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SierpinskiGasket extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int level;
	private Point a= new Point(20,380),
				  b= new Point(620,180),
				  c= new Point(320,70);

	
	public SierpinskiGasket() {
		super("Fractal SierpinskiGasket");
		this.setSize(640,480);
		this.level=Integer.parseInt(JOptionPane.showInputDialog("Introduce el nivel del fractal"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(this.getX()/2, this.getY()/2);
		
		this.a= new Point(20,380);
		this.b= new Point(620,180);
		this.c= new Point(320,70);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.pintaTriangulos(g,level,this.a, this.b, this.c);	
	}
	
	private void pintaLineas(Graphics g,Point a, Point b) {
		g.drawLine(a.x,a.y,b.x,b.y);
	}
	
	private Point puntoMedio(Point a, Point b) {
		return new Point ((a.x+b.x)/2, (b.y+a.y)/2);
	}
	
	private void pintaTriangulos(Graphics g, int n, Point a, Point b, Point c) {
		if(n<=0) {
			this.pintaLineas(g, a, b);
			this.pintaLineas(g, b, c);
			this.pintaLineas(g, c, a);
		}else {
			Point pmAB=this.puntoMedio(a,b),
				  pmBC=this.puntoMedio(b,c),
				  pmCA=this.puntoMedio(c,a);
			
			this.pintaTriangulos(g, n-1, a, pmAB, pmCA);
			this.pintaTriangulos(g, n-1, pmAB, b, pmBC);
			this.pintaTriangulos(g, n-1, pmCA, pmBC, c);
		}
	}
	public static void main(String []args) {
		new SierpinskiGasket();	
	}
	
}
