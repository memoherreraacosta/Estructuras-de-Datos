/*
 * Tarea de implementar interface Iterator
 * 
 * Guillermo Herrera
 * A01400835
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class ListaEnlazada<E> {
	private Nodo<E> inicio,
					fin;
	private int size;

	public ListaEnlazada(){
		this.size=0;
		this.inicio=this.fin=null;
	}

	public ListaEnlazada(E[] datos){
		for(E dato:datos){
			this.insertaFin(dato);
		}
	}

	public E inicio(){
		try{
			return this.inicio.getDato();
		}catch(NullPointerException e){
			throw new NoSuchElementException("No se puede regresar el primer elemento de una lista vacia");
		}
	}

	public E fin(){
		try{
			return this.fin.getDato();
		}catch(NullPointerException e){
			throw new NoSuchElementException("No se puede regresar el ultimo elemento de una lista vacia");
		}
	}

	public int size(){
		return this.size;
	}

	public boolean estaVacia(){
		return this.size==0;
	}

	public void insertarInicio(E dato){
		Nodo<E> nvo=new Nodo<E>(dato,this.inicio);
		this.inicio=nvo;
		if(this.size==0){
			this.fin=nvo;
		}
		this.size++;
	}

	public void insertaFin(E dato){
		if(this.estaVacia()){
			this.insertarInicio(dato);
		}
		else{
			Nodo<E> nvo=new Nodo<E>(dato);
			this.fin.setNext(nvo);
			this.fin=nvo;
			this.size++;
		}
	}	

	public void insertaEn(int pos,E dato)throws IndexOutOfBoundsException{
		if(pos>this.size || pos<0){
			throw new IndexOutOfBoundsException("No se puede insertar en la posicion "+pos+ "en una lisa de tamano "+this.size);
		}
		if(pos==0){
			this.insertarInicio(dato);
		}
		else{
			Nodo<E> tmp=this.inicio;
			for(int i=0;i<pos-1;i++){
				tmp=tmp.getNext();
			}
			Nodo<E> nvo=new Nodo<>(dato,tmp.getNext());
			tmp.setNext(nvo);
			if(pos==this.size){
				this.fin=nvo;
			}
			this.size++;
		}
}
	
	public E borrarInicio() throws NoSuchElementException{
		try{
			E tmp=this.inicio();
			this.inicio=this.inicio.getNext();
			this.size--;
			if(this.size==0){
				this.fin=null;
			}
			System.gc();
			return tmp;
		}catch(NullPointerException e){
			throw new NoSuchElementException("No se puede borrar el inicio de una lista vacia");
		}
	}

	public E borrarFin() throws NoSuchElementException{
		try{
			if(this.size==1){
				return this.borrarInicio();
			}
			else{
				E tmp=this.fin();
				Nodo<E> current=this.inicio;
				for(int i=0;i<this.size-2;i++){
					current=current.getNext();
				}
				current.setNext(null);
				this.fin=current;
				this.size--;
				System.gc();
				return tmp;
			}
		}
		catch(NullPointerException e){
			throw new NoSuchElementException("No se puede borrar el fin de una lista vacia");
		}
	}
	
	public E borrarEn(int pos){
		if(pos<0 || pos >=size){
			throw new IndexOutOfBoundsException("No se puede borrar la posicion "+pos+" de una lista de tamano "+this.size);
		}
		if(pos==0){
			return this.borrarInicio();
		}
		else if(pos==this.size){
			return this.borrarFin();
		}
		else{
			Nodo<E> tmp=this.inicio;
			E dato;
			for(int i=0;i<pos-1;i++){
				tmp=tmp.getNext();
			}
			dato=tmp.getNext().getDato();
			tmp.setNext(tmp.getNext().getNext());
			this.size--;
			return dato;
		}
	}
	
	public void setAt(int pos,E dato){
		if(pos>=0 && pos<this.size){
			Nodo<E> current=this.inicio;
			for(int i=0;i<pos;i++){
				current=current.getNext();
			}
			current.setDato(dato);
		}
		else{
			throw new IllegalArgumentException("La posicion "+pos+" de una lista de tamano "+this.size+" es invalida en el metodo setAt");
		}
	}
	
	public E getAt(int pos) throws IndexOutOfBoundsException{
		if(pos<0 || pos>=this.size){
			throw new IndexOutOfBoundsException("El indice "+pos+" no es valido para una lista de tamano "+this.size);
		}
		Nodo<E> current=this.inicio;
		for(int i=0;i<pos;i++){
			current=current.getNext();
		}
		return current.getDato();
	}
	
	public String toString(){
		Nodo<E> current=this.inicio;
		String resultado="";
		for(int i=0;i<this.size;i++){
			resultado+=current+",";
			current=current.getNext();
		}
		return resultado;
	}
	
	public boolean isItemEqualAt(int pos, E element) throws ArrayIndexOutOfBoundsException{
		if(pos<0) {
			throw new ArrayIndexOutOfBoundsException("Introdujo posicion invalida");
		}else {
			return element==this.getAt(pos);
		}
	}
	
	public Iterator<E> iterator() {

		return new Iterator<E>() {

			private int current = 0;

			public boolean hasNext() {
				return current < size;
			}

			public E next() throws NoSuchElementException {

				if (hasNext()) {
					return getAt(current++);
				} else {
					throw new NoSuchElementException("No hay un elemento siguiente ");
				}
			}

		};
	}

	public static void main(String[] args) {
		ListaEnlazada<Integer> lista=new ListaEnlazada<>();
		
	//	System.out.println(lista);
		lista.insertarInicio(20);
		lista.insertarInicio(10);
		lista.insertarInicio(5);
	//	System.out.println(lista);
		lista.setAt(1, 100);
	//	System.out.println(lista);
		lista.borrarFin();
	//	System.out.println(lista);
		Stack<Integer> a=new Stack<>();
		a.push(10);
		a.push(20);
		a.push(30);
		a.push(40);
		a.insertElementAt(9, 2);
	//	System.out.println(a);
	
		/*
		 * Prueba tarea
		 */
		Iterator itr = lista.iterator();
	      
	    while(itr.hasNext()) {
	       Object element = itr.next();
	       System.out.print(element + ", ");
	    }
	    
	}
}

class Nodo<E>{
	private E dato;
	private Nodo<E> next;

	public Nodo(E dato,Nodo<E> next){
		this.dato=dato;
		this.next=next;
	}

	public Nodo(E dato){
		this(dato,null);
	}

	public E getDato() {
		return dato;
	}

	public void setDato(E dato) {
		this.dato = dato;
	}

	public Nodo<E> getNext() {
		return next;
	}

	public void setNext(Nodo<E> next) {
		this.next = next;
	}

	public String toString(){
		return this.dato.toString();
	}
}
