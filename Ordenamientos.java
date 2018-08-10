
public class Ordenamientos {
	
	public <E extends Comparable<E>> void bubbleSort(E[] valores) {
		
		for(int i=0; i<valores.length-1;i++) {	
			for(int j=0;j<valores.length-1;j++) {
				if(valores[j].compareTo(valores[j+1])>0) {
					swap(valores,j,j+1);
				}
			}
		}
	}
	
	public <E extends Comparable<E>> void swap(E [] valores, int i, int j) {
		E tmp=valores[i];
		valores[i]=valores[j];
		valores[j]=tmp;
	}
	
	public <E extends Comparable<E>> void imprimeArreglo(E arreglo[]) {
		for(E a:arreglo){
			System.out.print(a+",");	
		}
	}
	
	public static void main(String [] args) {
		
		Ordenamientos op= new Ordenamientos();
		Integer arre[]= {1,35,3,6,7,12,13,98,32,134,1,24,45};
		op.bubbleSort(arre);
		op.imprimeArreglo(arre);
	}
}
