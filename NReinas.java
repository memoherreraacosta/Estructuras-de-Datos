public class NReinas {
	
	public void nReinas(int n, int[]reinas) {
		 for(int i=0;i<reinas.length;i++) {
			 if(this.valido(reinas, n, i)==true) {
				 reinas[n]=i;
				 if(n==reinas.length-1) {
					 imprimeTablero(reinas);
				 }else {
					 this.nReinas(n+1, reinas);
				 }
			 }
		 }
	}
	private boolean valido(int [] reinas, int fila, int columna) {
				for(int i=0; i<fila; i++) {
					if(reinas[i]==columna) {
						return false;
					}else if(Math.abs(fila-i)==Math.abs(columna-reinas[i])) {  //Revisar que no haya otra reina en la diagonal
						return false;
					}
				}
				return true;  //Es una posición valida
	}	
	public void imprimeTablero(int []reinas) {
		for(int i=0;i<reinas.length;i++) {
			System.out.print(reinas[i]+", ");
		}
		System.out.println();
	}
	public static void main(String[]args) {
		NReinas nr= new NReinas();
		int []reinas= {-1,-1,-1,-1,-1,-1,-1,-1};
		nr.nReinas(0, reinas);
	}
}
