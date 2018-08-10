/*
 * Guillermo Herrera Acosta
 * A01400835
 */

public class EjemploPilas {
	
	
	public StackLE<Integer> clonarPila(StackLE<Integer> pila){
		
		StackLE<Integer> nuevaPila= new StackLE<Integer>();
		StackLE<Integer> pil=pila;
		if(pila.isEmpty()){
			return nuevaPila;
		}else {
			int tamanopila=pil.size();
			Integer [] datos= new Integer[tamanopila];
			for(int i=0; i<tamanopila;i++) {
				datos[i]= pil.pop();
			}
			
			for(int i=tamanopila-1; i>0; i--) {
				nuevaPila.push(datos[i]);
			}
			nuevaPila.push(datos[0]);
			return nuevaPila;
		}
	}
	
	public void borrarValor(StackLE<Integer> pila, int valor) {
		int contador=pila.size();
		Integer[] datos= new Integer[contador];
		StackLE<Integer>nuevaP=pila;
		int dato;
		
		for(int i=0;i<contador;i++) {
			dato=nuevaP.pop();
			
			if(dato!=valor) {
				datos[i]=dato;
			}
		}
		pila.flush();
		for(int i=datos.length-1;i>0;i--) {
			if(datos[i]!=null) {
				pila.push(datos[i]);
			}

		}
		pila.push(datos[0]);
	}
	
	public static void main(String []args) {
		
		EjemploPilas ep= new EjemploPilas();
		StackLE<Integer> pila= new StackLE<>();
		
		pila.push(1);
		pila.push(2);
		pila.push(3);
		pila.push(2);
		pila.push(4);
		pila.push(4);
		pila.push(5);
		pila.push(6);
		
		System.out.println(pila.toString());
		ep.borrarValor(pila,6);
		System.out.println(pila.toString());
		
	}

}
