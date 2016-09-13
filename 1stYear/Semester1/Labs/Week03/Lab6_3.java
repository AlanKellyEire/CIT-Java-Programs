package src;

public class Lab6_3 {

	public static void main(String[] args) {
		/*	Name: Alan Kelly
		 * 	ID: R00052131
		 */
		double sinx, cosx, sum, radians, degrees = 30;
		
		radians = degrees * Math.PI/180;
		sinx = Math.sin(radians);
		cosx = Math.cos(radians);
		sum = Math.pow(sinx,2) + Math.pow(cosx,2);
		
		System.out.println("sine: " + sinx + " cosine: " + cosx + " sum: " + sum );

	}

}
