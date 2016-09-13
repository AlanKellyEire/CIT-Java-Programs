import java.util.Scanner;

public class Lab12_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		String f1, s1, f2, s2, name1, name2;
		
		// Prompt user's for first users name,
		System.out.println("what is first users name");
		f1 = keyboard.next();
		s1 = keyboard.next();
		
		// Prompt user's for second users name,
		System.out.println("what is first users name");
		f2 = keyboard.next();
		s2 = keyboard.next();
		
		name1 = s1 + ", " + f1;
		name2 = s2 + ", " + f2;
		
		if (name1.compareToIgnoreCase(name2) < 0)
			System.out.print(name1 + "\n" + name2);
		else if (name1.compareToIgnoreCase(name2) > 0)
			System.out.print(name2 + "\n" + name1);
		else
			System.out.print(name1 + " given twice.");
		
		keyboard.close();
			
	}

}
