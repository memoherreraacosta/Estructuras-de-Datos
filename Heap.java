
public class Heap {

	private int size;
	private Integer[] arreglo;

	public Heap(Integer[] arreglo) {
		this.size = arreglo.length;
		this.arreglo = new Integer[arreglo.length];
		for (int i = 0; i < arreglo.length; i++) {
			this.arreglo[i] = arreglo[i];
		}

	}
 
	public Heap() {
		this(new Integer[15]);
	}

	public Integer [] getArreglo() {
		return this.arreglo;
	}
	
	public Integer getRaiz() {
		return this.arreglo[0];
	}
	
	public void heapify() {
		for (int i=this.arreglo.length / 2 - 1 ; i>=0;i--) {
			this.heapify(i);
		}
	}
	
	private void heapify(int index) {
		int largest = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        
        if (leftIndex < this.size && this.arreglo[index] < this.arreglo[leftIndex]) {
            largest = leftIndex;
        }
        if (rightIndex < this.size && this.arreglo[largest] < this.arreglo[rightIndex]) {
            largest = rightIndex;
        }
        if (largest != index) {
            this.swap(this.arreglo,index, largest);
            this.heapify(largest);
        }
	}
	public void insertar(Integer valor) {
		Integer [] tmp = new Integer[this.arreglo.length+1];
		for(int i=0;i<this.arreglo.length;i++) {
			tmp[i]=this.arreglo[i];
		}
		tmp[this.arreglo.length]=valor;
		this.arreglo=tmp;
		this.heapify();
		System.gc();
	}
	public Integer borrar() {
		Integer tmp=this.arreglo[0];
		Integer [] tem= new Integer[this.arreglo.length-1];
		for(int i=1;i<this.arreglo.length;i++) {
			tem[i-1]=this.arreglo[i];
		}
		this.arreglo=tem;
		this.heapify();
		System.gc();
		return tmp;
	}
	public void swap(Integer[] valores, int i, int j) {
		int tmp = valores[i];
		valores[i] = valores[j];
		valores[j] = tmp;
		System.gc();
	}
	public void imprime() {

		for (int i = 0; i < this.arreglo.length; i++) {
			System.out.print(this.arreglo[i] + ",");
		}
	}
	public static void main(String[] args) {
		Integer[] arreglo = {3,24,108,56,97,78,60,115,110,23,6,91,27,8,200};
		Heap heap = new Heap(arreglo);
		heap.heapify();
		heap.imprime();

	}
}
