import java.util.NoSuchElementException;

class NodoHT <K,V>{
	private K llave;
	private V valor;
	
	public NodoHT(K key,V value){
		super();
		this.llave=key;
		this.valor=value;
	}
	
	public K getLlave() {
		return this.llave;
	}

	public V getValor() {
		return this.valor;
	}
	
	public void setValor(V valor) {
		this.valor = valor;
	}
}

public class TablaHash<K, V>{

	private ListaEnlazada<NodoHT<K, V>>[] tabla;
	private final static double LOAD_FACTOR = 0.8;
	private int size;

	public TablaHash() {
		this(17);
	}
	
	public TablaHash(int size) {
		this.tabla = (ListaEnlazada<NodoHT<K, V>>[]) new ListaEnlazada[size];

		for (int i = 0; i < this.tabla.length; i++) {
			this.tabla[i] = new ListaEnlazada<NodoHT<K, V>>();
		}
		this.size = 0;
	}

	public void put(K llave, V valor) {

		if ((double) this.size / this.tabla.length >= TablaHash.LOAD_FACTOR) {
			rehashing();
		}
		
		int hash = Math.abs(llave.hashCode()) % tabla.length;
		ListaEnlazada<NodoHT<K,V>> bucket = this.tabla[hash];
		bucket.insertaFin(new NodoHT<K,V>(llave, valor)); 
		this.size++;
	}
	
	public void rehashing() {
		TablaHash<K,V> tablaTmp = new TablaHash<K,V>((this.tabla.length<< 1) + 1); //(this.tabla.length()*2) + 1 || (this.tabla.length()<< 1) + 1
		ListaEnlazada<NodoHT<K, V>> bucket;
		NodoHT<K, V> current;
		
		for (int i = 0; i < this.tabla.length; i++) {
			bucket = this.tabla[i];
			for (int j = 0; j < bucket.size(); j++) {
				current = (NodoHT<K, V>)bucket.getAt(j);
				tablaTmp.put(current.getLlave(),current.getValor());
			}
		}
		this.tabla=tablaTmp.tabla;
		this.size=tablaTmp.size;
	}

	public V get(K llave) throws NoSuchElementException {
		int hash = Math.abs(llave.hashCode()) % tabla.length;
		ListaEnlazada<NodoHT<K, V>> bucket = this.tabla[hash];
		NodoHT<K, V> current;
		
		for (int i = 0; i < bucket.size(); i++) {
			
			current =bucket.getAt(i);
			if (current.getLlave().equals(llave)) {
				return current.getValor();
			}
		}
		throw new NoSuchElementException("No se encontro el valor");
	}
	
	public V delete(K llave) throws NoSuchElementException{
		int hash = Math.abs(llave.hashCode()) % tabla.length;
		ListaEnlazada<NodoHT<K, V>> bucket = this.tabla[hash];
		NodoHT<K, V> current;
		
		for (int i = 0; i < bucket.size(); i++) {
			current =bucket.getAt(i);
			
			if (current.getLlave().equals(llave)) {
				return (V)bucket.borrarEn(i).getValor(); 
			}
		}
		throw new NoSuchElementException("No se encontro el valor");
	}
	
	public void clear() {
		
		this.tabla = (ListaEnlazada<NodoHT<K, V>>[]) new ListaEnlazada[11];

		for (int i = 0; i < this.tabla.length; i++) {
			this.tabla[i] = new ListaEnlazada<NodoHT<K, V>>();
		}
		this.size = 0;
		System.gc();
	}
	
	public boolean containKey(K llave) {
		try {
			this.get(llave);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		TablaHash<Integer,String> a= new TablaHash<>();
		
		a.put(1,"one");
		a.put(2, "valor");
		a.put(3,"ff");
		a.put(4,"ff");
		a.put(5,"ff");
		a.put(6,"ff");
		a.put(7,"ff");
		a.put(8,"ff");
		a.put(9,"ff");
		a.put(10,"ff");
		a.put(11,"ff");
		a.put(12,"ff");
		a.put(13,"ff");
		a.put(14,"ff");
		a.put(15,"ff");
		a.put(16,"ff");
		a.put(17,"ff");
		a.delete(2);
		a.delete(1);
		
	}
}