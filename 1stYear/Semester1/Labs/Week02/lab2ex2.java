
public class lab2ex2 {

	public static void main(String[] args) {
		/*	Name: Alan Kelly
		 * 	ID: R00052131
		 */
		double sinx, cosx, sum, value = 0.5236;
		
		sinx = Math.sin(value);
		cosx = Math.cos(value);
		sum = Math.pow(sinx,2) + Math.pow(cosx,2);
		
		System.out.println("sine: " + sinx + " cosine: " + cosx + " sum: " + sum );
	}

}
