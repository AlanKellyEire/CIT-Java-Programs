import java.util.Scanner;

public class week12_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		double side1, side2, side3;
		
		// Prompt user's for side1,
		System.out.println("what is the length of side 1");
		side1 = keyboard.nextDouble();

		// Prompt user's for side2,
		System.out.println("what is the length of side 2");
		side2 = keyboard.nextDouble();
		
		// Prompt user's for side2,
		System.out.println("what is the length of side 2");
		side3 = keyboard.nextDouble();
		
		if (side1 == side2 && side1 == side3)
			System.out.println("the triangle is equilateral");
		else if (side1 == side2 || side1 == side3 || side2 == side3)
			System.out.println("the triangle is isosceles");
		else
			System.out.println("the triangle is scalene");
	}

}
