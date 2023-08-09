package chapter03;

public class SwapTest02 {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;

		System.out.println("a="+a+", "+"b="+b);
		
		swap(a, b);
//		int tmp = a;
//		a = b;
//		b = tmp;
		
		System.out.println("a="+a+", "+"b="+b);
	}

	private static void swap(int m, int n) {
		int tmp = m;
		m = n;
		n = tmp;
	}
}
