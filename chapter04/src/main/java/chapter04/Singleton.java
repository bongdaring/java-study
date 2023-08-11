package chapter04;

public class Singleton {
	private static Singleton instance;
	
	private Singleton() {
		
	}

	public static Singleton getInstnace() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
