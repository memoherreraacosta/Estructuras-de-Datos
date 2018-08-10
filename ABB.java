import java.util.NoSuchElementException;

public class ABB <E extends Comparable<E>> {
	private NodoABB<E> raiz;
	private int size;
	
	public ABB() {
		this.raiz=null;
		this.size=0;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void insertar(E valor) {
		NodoABB<E> nuevo = new NodoABB<>(valor);
		if(this.raiz==null) {
			this.raiz = nuevo;
		}
		else {
			NodoABB<E> parent,
					   current;
			current=parent=this.raiz;
			
			while(current!=null) {
				parent = current;
				if(valor.compareTo(current.getValor())<0) {
					current = current.getIzq();
					if(current==null) {
						parent.setIzq(nuevo);
					}
				}
				else {
					current = current.getDer();
					if(current==null) {
						parent.setDer(nuevo);
					}
				}
			}
		}
		
		this.size++;
	}
	
	public E buscar(E dato) {
		NodoABB<E> current= this.raiz;
		
		while(current!= null && current.getValor().compareTo(dato)!=0) {
			if(dato.compareTo(current.getValor())<0){
				current=current.getIzq();
			}else if(dato.compareTo(current.getValor())>0){
				current=current.getDer();
			}
		}
		if(current==null) {
			return null;
		}else {
			return current.getValor();
		}
		
	}
	
	private void preOrden(NodoABB<E> nodo) {
		if(nodo!=null) {
			System.out.print(nodo.getValor()+",");
			this.preOrden(nodo.getIzq());
			this.preOrden(nodo.getDer());
		}
	}
	
	public void preOrden() {
		this.preOrden(this.raiz);
		System.out.println();
	}
	private void inOrden(NodoABB<E> nodo) {
		if(nodo!=null) {
			this.inOrden(nodo.getIzq());
			System.out.print(nodo.getValor()+",");
			this.inOrden(nodo.getDer());
		}
	}
	public void inOrden() {
		this.inOrden(this.raiz);
	}
	
	private void posOrden(NodoABB<E> nodo) {
		if(nodo!=null) {
			this.posOrden(nodo.getIzq());
			this.posOrden(nodo.getDer());
			System.out.print(nodo.getValor()+",");
		}
	}
	public void posOrden() {
		this.posOrden(this.raiz);
	}
	private NodoABB<E> predecesor(NodoABB<E> nodo){
		
		NodoABB <E> current=nodo.getIzq();
		
		while(current.getDer()!=null) {
			current=current.getDer();
		}
		return current;
		
	}
	
	public void nivel() {
		QueueLE <NodoABB<E>> cola= new QueueLE<>();
		NodoABB<E>nodo;
		
		if(this.raiz!=null) {
			cola.enqueue(this.raiz);
		}
		
		while (!cola.isEmpty()) {
			
			nodo=cola.dequeue();
			System.out.print(nodo+",");
			
			if(nodo.getIzq()!=null) {
				cola.enqueue(nodo.getIzq());
			}
			if(nodo.getDer()!=null) {
				cola.enqueue(nodo.getDer());
			}
		}
		System.out.println();
	}
	
	public E eliminar(E dato) {
		//Caso borrar hoja
		NodoABB<E> current=this.raiz, 
				   parent=this.raiz;
		while(current!=null && !current.getValor().equals(dato)) {
			parent=current;
			current=dato.compareTo(current.getValor())<0?current.getIzq():current.getDer();
		}
		
		if(current==null) {
			throw new NoSuchElementException("No se encontro "+dato+" en el arbol");
		}
		
		if(current.getIzq()==null && current.getDer()==null) {
			if(parent.getIzq()==current) {
				parent.setIzq(null);
			}else {
				parent.setDer(null);
			}
			
			//Caso borrar un hijo	
		}else if(current.getIzq()==null) {
			if(parent.getIzq()==current) {
				parent.setIzq(current.getDer());
			}
			
		}else if(current.getDer()==null) {
			if(parent.getIzq()==current) {
				parent.setIzq(current.getIzq());
			}else {
			parent.setDer(current.getIzq());
			}
		}
		else{
			if(current==parent.getIzq()) {
				NodoABB<E>temporal=predecesor(parent);
				parent.setIzq(temporal);
				temporal.setIzq(current.getIzq());
				
			}else {
				//:((((
			}
		}
		return dato;
		
	}
	
	
	public static void main(String[] args) {
		ABB<Integer> arbol= new ABB<Integer>();
		arbol.insertar(50);
		arbol.insertar(20);
		arbol.insertar(100);
		arbol.insertar(12);
		arbol.insertar(27);
		arbol.insertar(2);
		arbol.insertar(279);
		arbol.insertar(154);
		arbol.insertar(32);
		arbol.insertar(-2);
		System.out.println(arbol.buscar(50));
		//arbol.inOrden();
		arbol.nivel();
	}
	
}
	
class NodoABB<E>{	
	private E valor;
	private NodoABB<E> izq,
			   		   der;
	
	public NodoABB(E valor) {
		this(valor, null,null);
	}
	
	public NodoABB(E valor, NodoABB<E> izq, NodoABB<E> der) {
		this.valor=valor;
		this.izq=izq;
		this.der=der;
	}
	
	public String toString() {
		return this.valor.toString();
	}
	
	public E getValor() {
		return valor;
	}
	
	public void setValor(E valor) {
		this.valor = valor;
	}
	
	public NodoABB<E> getIzq() {
		return izq;
	}
	
	public void setIzq(NodoABB<E> izq) {
		this.izq = izq;
	}
	
	public NodoABB<E> getDer() {
		return der;
	}
	
	public void setDer(NodoABB<E> der) {
		this.der = der;
	}
	
}
