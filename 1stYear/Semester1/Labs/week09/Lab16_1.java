import java.util.Scanner;

public class Lab16_1 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
      
		int number, max, min, total = 0, count = 0;
		double average;
		
		System.out.print("Enter a positive integer or 0 to finish");
		number = keyboard.nextInt();
      
		while (number < 0)
		{ 
			System.out.print("invalid input, Enter a positive integer or 0 to finish");
			number = keyboard.nextInt();
		}
		
		max = number;
		min = number;
				
		while (number != 0)
		{
			count++;
			total += number;
			if (number > max)
				max = number;
			if (number < min)
				min = number;
			System.out.print("Enter a positive integer or 0 to finish");
			number = keyboard.nextInt();
			while (number < 0)
			{ 
				System.out.print("invalid input, Enter a positive integer or 0 to finish");
				number = keyboard.nextInt();
			}
    	 
		}
     
		if (count > 0)
		{
			average = (double) total / count;
     
			System.out.println("Your average is " + average);
			System.out.println("Maximum is " + max);
			System.out.println("Minimum is " + min);
		}
		else
			System.out.println("No input!");
    
		keyboard.close();

	}

}
