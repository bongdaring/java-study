package prob01;

public class Printer {
//	public void println(int number) {
//		System.out.println(number);
//	}
//	public void println(boolean b) {
//		System.out.println(b);
//	}
//	public void println(double d) {
//		System.out.println(d);
//	}
//	public void println(String name) {
//		System.out.println(name);
//	}
	
	public <T> void println(T t) {
		System.out.println(t);
	}
	
	// 완전 동일
//	public <T> void println(Object t) {
//		System.out.println(t);
//	}
	
	public int sum(Integer... nums) {
		int s = 0;
		for(Integer i : nums) {
			s += i;
		}
		
		return s;
	}
	
	
	
	public <T> void println(T... ts) {
		for(T t : ts) {
			System.out.println(t);
		}
	}
	
}
