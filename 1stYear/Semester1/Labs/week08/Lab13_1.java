import java.util.Scanner;


public class Lab13_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		int number, count;

		System.out.print("Please enter a number to be counted to: ");
		number = keyboard.nextInt();

		while (number < 1)
		{
			System.out.print("Invalid input. Please enter a number to be counted to: ");
			number = keyboard.nextInt();
		}
		
		System.out.print(1);
		count=3;
		
		while (count<=number){
			System.out.print(", " + count);
			count += 2;
		}

		keyboard.close();
		
	}
	

}
