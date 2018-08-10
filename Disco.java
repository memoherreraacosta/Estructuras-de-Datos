import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Disco{

	private int ancho=60;
	private int alto=30;
	private int numeroDisco;
	
	Color [] colores= {Color.RED, Color.BLACK, Color.BLUE, Color.PINK, Color.ORANGE};
	
	public Disco(int numeroDisco){
		this.numeroDisco=numeroDisco;
		double num= ((11-numeroDisco)/10.0)+1;
		this.ancho*=num;	
	}
	
	public int getAncho() {
		return this.ancho;
	}
	public int getAlto() {
		return this.alto;
	}
	public Color getColor() {
		int numD;
		if(numeroDisco>5) {
			numD=numeroDisco-5;
		}else {
			numD=numeroDisco;
		}
		return colores[numD];
	}
	
}
