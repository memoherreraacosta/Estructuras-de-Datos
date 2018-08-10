/*
 * Guillermo Herrera Acosta
 * A01400835
 */

public class QuickSort {
	
	public <E extends Comparable<E>> void quicksort(E [] arreglo) {
		quicksort(arreglo, 0, arreglo.length-1);
	}
	public <E extends Comparable<E>> void quicksort(E [] arreglo, int min, int max) {
		if(min<max) {
			int posPivote= particionar(arreglo, min, max);
			quicksort(arreglo, min, posPivote-1);
			quicksort(arreglo, posPivote+1, max);
		}
	}
	public <E extends Comparable<E>> int particionar(E [] arreglo, int min, int max) {
		E pivote= arreglo[(min+max)/2];
		swap(arreglo,min,(min+max)/2);
		int i=min+1;
		
		for(int j=min+1; j<=max; j++) {
			if(arreglo[j].compareTo(pivote)<0) {
				swap(arreglo,i,j);
				i++;
			}
		}
		swap(arreglo,min,i-1);
		return i-1;
	}
	
	public <E extends Comparable<E>> void swap(E [] arreglo, int origen, int destino) {
		E tmp=arreglo[origen];
		arreglo[origen]=arreglo[destino];
		arreglo[destino]=tmp;
	}
	
	public <E extends Comparable<E>> void imprimeArreglo(E arreglo[]) {
		for(E a:arreglo){
			System.out.print(a+",");
		}
	}
	
	public static void main(String[]args) {
		/*
		Double [] calix= {1.9,4.32,90.12,2.4,3.21,-100.0,23.0};
		
		QuickSort qs= new QuickSort();
		qs.imprimeArreglo(calix);
		
		qs.quicksort(calix);
		System.out.println();
		
		qs.imprimeArreglo(calix);
		*/
	}
}
