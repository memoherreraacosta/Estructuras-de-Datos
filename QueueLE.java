import java.util.NoSuchElementException;

public class QueueLE <E>{
	
	private ListaEnlazada<E> lista;

	public QueueLE() {
		this.lista=new ListaEnlazada<E>();
	}
	
	public int size() {
		return this.lista.size();
	}
	
	public boolean isEmpty() {
		return this.lista.estaVacia();
	}
	
	public void enqueue(E dato) {
		this.lista.insertaFin(dato);
	}
	public E dequeue() {
		try {
			return this.lista.borrarInicio();
		}catch(NoSuchElementException ex) {
			throw new NoSuchElementException("No se puede hacer un dequeue de una fila vacia");
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
			throw new NoSuchElementException("No se puede hacer un next de una cola vacia");
		}
	}
	
	
	public static void main(String[] args) {
		QueueLE <String> fila1= new QueueLE<>();
		
		String[] nombres= {"Kevin","Eutimio","Hector", "Quirino", "Lafarga","Donovan","Elias"};
		
		for(String nombre:nombres) {
			fila1.enqueue(nombre);
		}
		while(!fila1.isEmpty()) {
			System.out.println(fila1.dequeue());
		}
	}

}
