/*
 * Guillermo Herrera Acosta
 * A01400835
 * 
 */

import java.util.NoSuchElementException;

public class ListaEnlazadaLE <E>{
       
		private Nodo <E> inicio,
						 fin;
		private int size;
		
		public ListaEnlazadaLE() {
			this.size=0;
			this.inicio=this.fin=null;
		}
		
		public ListaEnlazadaLE(E [] datos) {
	
			for(int i=0; i<datos.length; i++) {
				this.insertarEn(i, datos[i]);
			}

		}
		
		public E getAt(int pos) {
			if (pos >= 0 && pos < this.size) {
				Nodo<E> current = this.inicio;
				for (int i = 0; i < pos; i++) {
					current = current.getNext();
				}
				return current.getDato();
			} else {
				throw new IllegalArgumentException("La posicion" + pos + "tamano invalido");
			}
		}
		
		public E inicio() throws NoSuchElementException{
			try {	
				return this.inicio.getDato();	
			}catch(NullPointerException rt) {
				throw new NoSuchElementException ("No existe algún dato en la primera posion de la lista");
			}
		}
		
		public E fin() {
			try {	
				return this.fin.getDato();	
			}catch(NullPointerException rt) {
				throw new NoSuchElementException ("No existe algún dato en la ultima posion de la lista");
			}
		}
	
		public int size() {
			return this.size;
		}
		
		public boolean estaVacia() {
			return (this.size==0);
		}
		
		public void insertarInicio(E dato) {
			Nodo<E> nuevo= new Nodo <E>(dato, this.inicio);
			this.inicio= nuevo;
			if(this.size==0) {
				this.fin=nuevo;
			}
			this.size++;
		}
		
		public void insertaFin(E dato) {
			
			if(this.size==0){
				this.insertarInicio(dato);
			}else {
				
				Nodo<E> nuevo= new Nodo <E>(dato);
				this.fin.setNext(nuevo);
				this.fin=nuevo;
				this.size++;	
			}
		}
		
		public void insertarEn(int pos, E dato) {
			
			
			if(this.size<pos || pos<0) {
				throw new NullPointerException("No existe esta posicion en la lista enlazada");
			}else if(pos==0){
				this.insertarInicio(dato);
			}else if(pos==this.size){
				this.insertaFin(dato);
			}else {
				
				Nodo <E> nod=this.inicio;
				Nodo <E> nodo;
				
				for(int i=0;i<pos-1;i++) {
					nod=nod.getNext();
				}
				nodo=nod.getNext();
				Nodo<E> no= new Nodo <E> (dato);
				nod.setNext(no);
				no.setNext(nodo);
				this.size++;
			}
		}
		
		public E borrarInicio() throws NoSuchElementException{
			try {
				E tmp= this.inicio();
				this.inicio=this.inicio.getNext();
				System.gc();
				this.size--;
				if(this.size==0) {
					this.fin=null;
				}else if(this.size<0) {
					
				}
			return tmp;
			}catch(NullPointerException ex) {
				throw new NoSuchElementException("No se puede borrar el inicio de la lista");
			}
		}
		
		public E borrarFin() throws NoSuchElementException{
			try {	
				if(this.size==1) {	
					return this.borrarInicio();
				}else {
					E tmp=this.fin();
					Nodo<E> current=this.inicio;
					
					for(int i=0;i<this.size-2;i++) {
						current=current.getNext();
					}
					current.setNext(null);
					this.size--;
					return tmp;
				}
			}catch(NullPointerException  rt) {
				throw new NoSuchElementException ("No se puede borrar el fin de una lista vacia");
			}
		}
		
		public E borrarEn(Integer pos) {
			if(pos==0) {
				return this.borrarInicio();	
			}else if(pos==this.size-1) {
				return this.borrarFin();
			}else if (pos >= 0 && pos < this.size) {
				Nodo<E> current = this.inicio;
				for (int i = 0; i < pos - 1; i++) {
					current = current.getNext();
				}
				Nodo<E> tmp = current.getNext();
				current.setNext(current.getNext().getNext());
				this.size--;
				return tmp.getDato();
			} else {
				throw new IllegalArgumentException("La posicion" + pos + "tamano invalido");
			}
		}
		
		public void setAt(int pos, E dato) {
			if(pos>=0 && pos<this.size) {
				Nodo<E> current=this.inicio;
				for(int i=0;i<pos;i++) {
					current=current.getNext();
				}
				current.setDato(dato);
			}else {
				throw new IllegalArgumentException("La posicion "+pos+" de una lista de tamaño "+this.size+" es invalida");
			}
		}
		
		public String toString() {
			try {
				String result= "{";
				Nodo <E> current= this.inicio;
				
				for(int i=0;i<this.size-1;i++) {
					result+= current.toString()+",";
					current=current.getNext();
				}
				result+= current.toString();
				current=current.getNext();
				
				result+= "}";
				return result;
			}catch(NullPointerException rt) {
				return("La lista esta vacia");
			}
		}

		class Nodo<E>{
                private E dato;
                private Nodo<E> next;
                
                public Nodo(E dato, Nodo<E> next) {
                		this.dato=dato;
                		this.next=next;
                }
                public Nodo(E dato) {
                		this(dato, null);
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
                
				public String toString() {
					return this.dato.toString();
				}
        }
}
            