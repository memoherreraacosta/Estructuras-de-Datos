import java.awt.BorderLayout;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	private Laberinto laberinto;
    private ControlPanel cp;
    
    public Ventana(){
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.cp = new ControlPanel();
        this.laberinto = new Laberinto(this.cp);
        this.cp.addLaberinto(this.laberinto);
        
        this.add(this.laberinto, BorderLayout.WEST);
        this.add(this.cp);
        this.pack();
        this.setLocation( this.getWidth()/10, this.getHeight()/10);
        
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Ventana();
    }

}
