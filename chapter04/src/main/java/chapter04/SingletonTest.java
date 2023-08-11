package chapter04;

public class SingletonTest {

	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstnace();
		Singleton singleton2 = Singleton.getInstnace();
		
		System.out.println(singleton1 == singleton2);
	}

}
