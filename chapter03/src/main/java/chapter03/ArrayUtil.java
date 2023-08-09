package chapter03;

public class ArrayUtil {

	public static double[] intToDouble(int[] a) {
		double[] d = new double[a.length];
		for(int i = 0; i < a.length; i++) {
			d[i] = a[i];
		}

		return d;
	}
	
	public static int[] doubleToInt(double[] d) {
		int[] a = new int[d.length];
		for(int i = 0; i < d.length; i++) {
			a[i] = (int)d[i];
		}
		
		return a;
	}

	public static int[] concat(int[] is, int[] is2) {
		int[] result = new int[is.length + is2.length];
		
		for(int i = 0; i < is.length; i++) {
			result[i] = is[i];
		}
		
		for(int i = 0; i < is2.length; i++) {
			result[i + is.length] = is2[i];
		}
		return result;
	}
	
}
