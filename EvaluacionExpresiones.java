/*
 * Guillermo Herrera Acosta
 */

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class EvaluacionExpresiones <E extends Comparable<E>>{
	
	private StackLE<String> pila= new StackLE<String>(); //Pila que almacena operandos
	
	public double evalua(String expresion) {
		
		Double resultado=0.0; 			//Expresion double a entregar
		String exp="";
		String elemento;
		
		StringTokenizer st= new StringTokenizer(expresion);
		
		 while (st.hasMoreTokens()) {
			 
	        elemento=st.nextToken();
	        
	        if(this.esNumero(elemento)) {
	        		//Trabajan numeros
	        		exp+=elemento;	
	        }else {
	        		//Trabajan operandos
	        		//If condicionales a la jerarquia de operandos
	        		
	        		
	        		if(elemento.compareTo("(")==0 || elemento.compareTo("{")==0 || elemento.compareTo("[")==0){
	        			this.pila.push(elemento); 
	        			
	        		}else if(elemento.compareTo("^")==0) {
	        			this.pila.push(elemento);
	        			
	        		}else if(elemento.compareTo("*")==0 || elemento.compareTo("/")==0) {
	        			
	        			String cast="";
	        			
	        			try {
	        				cast=this.pila.pop();
	        			}catch(NoSuchElementException ex){
	        				cast="";
	        			}
	        			
	        			
	        			if(cast.compareTo("*")==0 || cast.compareTo("/")==0){
	        				exp+=cast;
	        				this.pila.push(elemento);
	        			}else {
	        				this.pila.push(cast);
	        				this.pila.push(elemento);
	        			}
	        			
	        		}else if(elemento.compareTo("+")==0 || elemento.compareTo("-")==0) {
	        			
	        			String cast=this.pila.pop();
	        			
	        			if(cast.compareTo("+")==0 || cast.compareTo("-")==0){
	        				exp+=cast;
	        				this.pila.push(elemento);
	        			}else {
	        				this.pila.push(cast);
	        				this.pila.push(elemento);
	        			} 
	        			
	        		}else if(elemento.compareTo(")")==0 || elemento.compareTo("]")==0 || elemento.compareTo("}")==0) {
	        			
	        			this.pila.push(elemento);
	        			String valor="";
	        			
	        			while(!this.pila.isEmpty()) {
	        				
	        				valor=this.pila.pop();
	        				
	        				if(valor.compareTo("(")==0 || valor.compareTo("{")==0 || valor.compareTo("[")==0 || valor.compareTo(")")==0) {
	        					//exp+=valor;
	        				}else {
	        					exp+=valor;
	        				}
	        			
	        			}
	        		}else {
	        			//Caso en el que no exista el operando
	        			JOptionPane.showMessageDialog(null,"El operando: "+elemento+ " no pudo ser reconocido");
	        		}
	        		
	        }
	     }
		 
		 System.out.println(exp); 
		 /*
		 while(!this.pila.isEmpty()) {
				System.out.println(this.pila.pop());
			}
		*/
		 //Almacenar posfijo a queue para entregar operacion
		 
		return resultado;
	}
	
	public boolean esNumero(String palabra){
		try {
			Integer.parseInt(palabra);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}


	public static void main(String[]args) {
		EvaluacionExpresiones ee= new EvaluacionExpresiones();
		
		ee.evalua("( 6 + 2 ) *  3 / 2 * 2 - 4");
		
	}
}

/*
 * if(letraEvaluar=="[" || letraEvaluar=="{" ||
					letraEvaluar=="("){
				
				pila.push(letraEvaluar);
				System.out.println("inicio");
					
			}else if(letraEvaluar=="^"){
				
				pila.push(letraEvaluar); 
				
			}else if(letraEvaluar=="*" || letraEvaluar=="/"){
				pila.push(letraEvaluar);
				
			}else if(letraEvaluar=="+" || letraEvaluar=="-"){
				pila.push(letraEvaluar);
				
			}else if(letraEvaluar=="]"||letraEvaluar=="}" ||
					letraEvaluar==")"){
				
				System.out.println("fin");
				while(pila.isEmpty()!=true) {
					String valor=pila.pop();
					if(valor=="[" || valor=="{" || valor== "(" ||
						valor=="]" || valor=="}" || valor== ")"){
					
					}else {
						expresionPosfija+=valor;
					}
				}
			}else{

				try {
		            Integer.parseInt(letraEvaluar);
		            expresionPosfija+=letraEvaluar;
		        } catch (NumberFormatException excepcion) {
		        }
				
			}
 */
