package chapter03;

import mypackage.Value;

public class SwapTest03 {

	public static void main(String[] args) {
		Value a = new Value(10);
		Value b = new Value(20);

		System.out.println("a="+a.val+", "+"b="+b.val);
		
		swap(a, b);
//		int tmp = a;
//		a = b;
//		b = tmp;
		
		System.out.println("a="+a.val+", "+"b="+b.val);
	}

	private static void swap(Value m, Value n) {
		int tmp = m.val;
		m.val = n.val;
		n.val = tmp;
	}
}
