
public class Factorial {
	public static long factorial(long n) {
		if(n<=1) {
			return 1;
		}else {
			return n*factorial(n-1);
		}
	}
	public static long fibonacci(long n) {
		if(n==0 || n==1) {
			return 1;
		}else {
			return fibonacci(n-1)+fibonacci(n-2);
		}
	}
	public static void main(String []args) {
		/*
		 * Overflow en byte
		 */
		byte b=(byte)127;
		byte c=(byte)10;
		
		b+=c;
		
		//System.out.println(c);
		//System.out.println("El numero factorial de: 6 es: "+factorial(6));
		long f=42;
		long iniTime= System.nanoTime();
		long n=fibonacci(f);
		long finTime= System.nanoTime();
		
		System.out.println("Se resolvio en "+(finTime-iniTime)/1000000000.0 +" segundos");
		System.out.println("El numero fibonacci de "+f+" es: "+n);
	}
}
