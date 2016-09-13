import java.util.Scanner;

public class Lab7_1 {

	public static void main(String[] args) {
		String firstName, surname, course, courseUpper;
		int age;
		char firstIntial, secondIntial;
		
		Scanner keyboard = new Scanner(System.in);
		
		// Prompt user's firstName,
	    System.out.print("What is your first name? ");
	    firstName = keyboard.nextLine();
		
	    // Prompt user's surname
	    System.out.print("What is your surname? ");
	    surname = keyboard.nextLine();
	    
	    // Prompt user's age
	    System.out.print("What is your age? ");
	    age = keyboard.nextInt();

	   	// Get user's course
	   	System.out.print("What is your course? ");
	    keyboard.nextLine();
	   	course = keyboard.nextLine();
	  
	   	firstIntial = firstName.charAt(0);
	   	secondIntial = surname.charAt(0);
	   	courseUpper = course.toUpperCase();

	   	// Display the resulting information
	   	System.out.print(firstIntial + ". " + secondIntial + ".\t" + age + "\t" + courseUpper);

	}

}
