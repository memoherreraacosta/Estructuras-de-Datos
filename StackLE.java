import java.util.NoSuchElementException;

public class StackLE<E> {
	
	private ListaEnlazada<E> lista;

	public StackLE() {
		this.lista=new ListaEnlazada<E>();
	}
	
	public int size() {
		return this.lista.size();
	}
	
	public boolean isEmpty() {
		return this.lista.estaVacia();
	}
	
	public void push(E dato) {
		this.lista.insertarInicio(dato);
	}
	public E pop() {
		try {
			return this.lista.borrarInicio();
		}catch(NoSuchElementException ex) {
			throw new NoSuchElementException("No se puede hacer un dequeue de un Stack vacio");
		}
	}
	public void flush() {
		this.lista=new ListaEnlazada<>();
		System.gc();
	}
	
	public E next() throws NoSuchElementException {
		try {
			return this.lista.inicio();
		}catch(NoSuchElementException ex) {
			throw new NoSuchElementException("No se puede hacer un next de un Stack vacio");
		}
	}
	
	public String toString() {
		return this.lista.toString();
	}
	
	public static void main(String[] args) {
	
		StackLE <String> fila1= new StackLE <>();
		
		String[] nombres= {"Kevin","Eutimio","Hector", "Quirino", "Lafarga","Donovan","Elias"};
		for(String nombre:nombres) {
			fila1.push(nombre);
		}
		fila1.pop();
		
		while(!fila1.isEmpty()) {
			System.out.println(fila1.pop());
		}
	
	}
}
