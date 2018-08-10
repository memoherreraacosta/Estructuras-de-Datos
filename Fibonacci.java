
public class Fibonacci {
	public static long fibonacci(long n) {
		if(n==0 || n==1) {
			return 1;
		}else {
			return fibonacci(n-1)+fibonacci(n-2);
		}
	}
	
	public static long fibonacciTabla(int n) {
		long [] valores= new long[n];
		valores[0]=valores[1]=1L;
		return fibonacciTabla(n,valores);
	}
	
	public static long fibonacciTabla(int n, long [] valores) {
		 if(valores[n]!=0) {
			 return valores[n];
		 }else {
			valores[n]=fibonacciTabla(n-1,valores)+fibonacciTabla(n-2,valores);
			return valores [n];
		 }
	}
	
	public static void main(String[]args){
		//System.out.println(fibonacci(50));
		System.out.println(fibonacciTabla(300));
	}
}
