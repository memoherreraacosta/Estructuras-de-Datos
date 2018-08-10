import java.util.Iterator;
import java.util.NoSuchElementException;

public class EjemploIterador<E> implements Iterable<E> {

	private E[] datos;
	private int num;

	public EjemploIterador() {
		this.datos = (E[]) new Object[10];
		this.num = 0;
	}

	public EjemploIterador(E[] datos) {
		this.datos = datos;
		this.num = datos.length;
	}

	public Iterator<E> iterator() {

		return new Iterator<E>() {

			private int current = 0;

			public boolean hasNext() {
				return current < num;
			}

			public E next() throws NoSuchElementException {

				if (hasNext()) {
					return datos[current++];
				} else {
					throw new NoSuchElementException("No hay un elemento siguiente ");
				}
			}

		};
	}

	public static void main(String[] args) {
		Integer[] datos= {12,34,23,2,34,-3};
		EjemploIterador<Integer> numeros= new EjemploIterador<>(datos);
		
		
		for(Integer a:numeros) {
		//		System.out.print(a+", ");
		}
		System.out.println();
	}
}
