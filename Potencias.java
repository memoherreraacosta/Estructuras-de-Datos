import javax.swing.JOptionPane;

public class Potencias {

	public static double potencia(int base, int exponente) {
		if(exponente==0) {
			return 1;
		}else {
			return base*potencia(base,exponente-1);
		}
	}
	
	public static void main(String[]args) {
		
		boolean chec=true;
		
		while(chec) {
			int reply = JOptionPane.showConfirmDialog(null, "¿Quieres calcular una operación de potencias de enteros?", "", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
		        	try {
		    			int base= Integer.parseInt(JOptionPane.showInputDialog("Ingresa como número entero, la base de la potencia"));
		    			int exp= Integer.parseInt(JOptionPane.showInputDialog("Ingresa como número entero, la potencia de la potencia"));
		    			
		    			if(exp<0) {
		    				JOptionPane.showMessageDialog(null,"El resultado de la operación: "+base+"^"+-exp+" es igual a: "+ (1/potencia(base,-exp)));
		    			}else {
		    				JOptionPane.showMessageDialog(null,"El resultado de la operación: "+base+"^"+exp+" es igual a: "+potencia(base,exp));
		    			}
		    			
		    		}catch(java.lang.NumberFormatException exc) {
		    			JOptionPane.showMessageDialog(null, "Ingresó mal el dato, intente de nuevo");
		    		}
	        }
	        else {
	          chec=false;
	        }
		}
		System.exit(0);
	}
	
}
