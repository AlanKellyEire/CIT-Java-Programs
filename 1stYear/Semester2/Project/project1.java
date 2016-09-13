import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class project1 {

	public static final String DICTIONARY = "dic.txt";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);

		//Scanner file = new Scanner(new File("dic.txt"));

		final int SIZE = 100;
		int user, valid, i = 0, opt;
		int [] usage = new int [SIZE];
		boolean re = false;

		String [] acronyms = new String [SIZE];
		String [] trans = new String [SIZE];

		do
		{
			/*
			while(file.hasNext())
			{
				acronyms[i] = file.next();
				// eats up line
				file.nextLine();
				trans[i] = file.nextLine();
				usage[i] = file.nextInt();
				i++;
			}

			valid = validNo(trans);
			 */
			valid = readDict(acronyms, trans, usage);

			System.out.println("Welcome \nPlease select your user type.\n1.	Administrator\n2.	General User");
			user = readIntRange(1,2);


			if (user == 1)
				valid =	admin(acronyms, trans, usage, valid);
			else
				gen(acronyms, trans, usage, valid);
			
			writeDictionary(acronyms, trans, usage, valid);

			System.out.println("Would you like to continue??\n1. No\n2. Yes");
			user = readIntRange(1,2);
			if (user == 1)
			{
				re = false;
			}
			else
			{
				re = true;
			}
		} while(re);
		System.out.println("Thanks for using the program");
		keyboard.close();
		//file.close();
	}

	public static int readDict(String[] acronyms, String[] trans, int[] usage) throws IOException {
		Scanner file = new Scanner(new File(DICTIONARY));
		int i = 0;

		while(file.hasNext())
		{
			acronyms[i] = file.next();
			// eats up line
			file.nextLine();
			trans[i] = file.nextLine();
			usage[i] = file.nextInt();
			i++;
		}
		file.close();
		return i;
	}

	public static int translateline(String text, String[] acronyms, String[] trans, int[] usage ,int valid){
		Scanner keyboard = new Scanner(System.in);
		int numReplacemets = 0, index;
		String word;

		Scanner lineScan = new Scanner (text);
		while(lineScan.hasNext())
		{
			word = lineScan.next();
			index = findIndex(acronyms,word,valid);
			if(index != -1)
			{
				System.out.print(trans[index] + " ");
				usage[index] ++;
				numReplacemets ++;
			}
			else
			{
				System.out.print(word + " ");
			}
		}
		System.out.println();
		lineScan.close();
		return numReplacemets;
	}
	public static int findIndex(String[] acronyms, String word, int valid){
		int index = -1;
		for(int i = 0; i < valid; i++)
		{
			if(word.equalsIgnoreCase(acronyms[i]))
			{
				index = i;
			}
		}
		return index;
	}

	public static String verifyString()
	{
		Scanner keyboard = new Scanner(System.in);
		String input;

		do
		{
			input = keyboard.nextLine();
		} while(input.isEmpty());

		//keyboard.close();

		return input;
	}

	/*
	public static String verifyString()
	{
		Scanner keyboard = new Scanner(System.in);

		while(keyboard.equals(null) || keyboard.hasNextDouble())
		{
			keyboard.next();
			System.out.print("Invalid input! please enter an string? ");
		}
		return keyboard.nextLine();
	}*/
	public static int validNo(String[] trans){
		int validNo = 0;
		for (int a=0; a<=trans.length-1; a++)
		{if (trans[a]!=null)
			validNo += 1;
		}
		return validNo;
	}
	public static File getValidFile() {
		Scanner keyboard = new Scanner(System.in);
		File file;

		do
		{
			System.out.print("Enter the name of a file: ");
			String filename = keyboard.nextLine();
			file = new File(filename);
			if (!file.exists())
				System.out.println("The specifed file does not exist"
						+ " - please try again!");
		} while(!file.exists());

		return file;
	}
	public static int admin(String[] acronyms, String[] trans, int[] usage, int valid) throws IOException {
		int opt;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Administrator settings:\n1.	View all acronyms\n2.	Add an acronym\n3.	Edit an acronym\n4.	Delete an acronym\n5.	Statistics");
		opt = readIntRange(1,5);
		if (opt == 1)
			allAcronym(acronyms, trans, valid);
		else if (opt == 2){
			addAcronym(acronyms, trans, usage, valid);
			valid +=1;
		}
		else if (opt == 3)
			editAcronym(acronyms, trans, usage, valid);
		else if (opt == 4)
		{
			deleteAcronym(acronyms, trans, usage, valid);
			valid -=1;
		}
		else
			statistics(acronyms, trans, usage, valid);
		return valid;
	}
	public static void gen(String[] acronyms, String[] trans, int[] usage, int valid) throws FileNotFoundException {
		int opt;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("General User settings:\n1.	Keyboard input\n2.	File input\n3.	Statistics");
		opt = readIntRange(1,3);
		if (opt == 1)
			keyboardInput(acronyms, trans, usage, valid);
		else if (opt == 2)
			fileInput(acronyms, trans, usage, valid);
		else
			statistics(acronyms, trans, usage, valid);
	}

	public static int readIntRange(int a, int b) {
		int input;
		boolean valid = false;
		do
		{
			input = readInt();
			if (input < a || input > b)
				System.out.print("Invalid input. Enter " + a + " - " + b + ": ");
			else
				valid = true;
		} while(!valid);
		return input;
	}

	public static int readInt() {
		Scanner keyboard = new Scanner(System.in);
		while(!keyboard.hasNextInt())
		{
			keyboard.next();
			System.out.print("Invalid input. Enter an integer: ");
		}
		return keyboard.nextInt();
	}

	public static void statistics(String[] acronyms, String[] trans, int[] usage, int valid) {
		int opt;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Statistics\nplease enter the statistics you wish to view\n1. The usage statistics for a specific acronym the program has in its dictionary.\n2. The usage statistics for all acronyms the program has in its dictionary.\n3. The most frequently used acronym.\n4. The least frequently used acronym.");
		opt = readIntRange(1,4);
		if (opt == 1)
			viewSingle(acronyms, trans, usage, valid);
		else if (opt == 2)
			allAcronymStats(acronyms, trans, usage, valid);
		else if (opt == 3)
			most(acronyms, usage, valid);
		else
			least(acronyms, usage, valid);
	}
	public static void fileInput(String[] acronyms, String[] trans, int[] usage,  int valid) throws FileNotFoundException {
		int numReplacements = 0;
		File file = getValidFile();
		// Open the file for reading
		Scanner inFile = new Scanner(file);
		
		while (inFile.hasNextLine())
		{
			numReplacements += translateline(inFile.nextLine(), acronyms, trans, usage, valid);
		}
		System.out.println( "\nThere were " + numReplacements +" re-placements made to the text in the file");
		
		inFile.close();
	}
	
	public static void keyboardInput(String[] acronyms, String[] trans, int[] usage,  int valid){
		Scanner keyboard = new Scanner(System.in);

		String text;
		System.out.print("please enter the the line to be translated");

		text = verifyString();
		System.out.println( "\nThere were " + translateline(text, acronyms, trans, usage, valid) +" re-placements made to the text entered");
	}

	public static void viewSingle(String[] acronyms, String[] trans, int[] usage, int valid) {
		Scanner keyboard = new Scanner(System.in);
		String input;
		int i;
		System.out.println("to view an acronym stats please enter, the acronym");
		input = keyboard.nextLine();
		i = findIndex(acronyms, input, valid);
		while (i==-1) {
			System.out.println("Please enter an acronym contained in the Dictionary file");
			input = keyboard.nextLine();
			i = findIndex(acronyms, input, valid);	
		}
		if(i>-1 && i < valid);
		{
			System.out.println("acronym\t\ttranslation\t\tTimes Used");
			System.out.println(acronyms[i] + "\t\t" + trans[i]  + "\t\t" + usage[i]);
		}


	}
	public static void allAcronymStats(String[] acronyms, String[] trans, int[] usage, int valid) {
		System.out.println("these are all the acronyms and there translations and the number of times they were used");
		for(int i = 0; i<valid; i++)
		{
			System.out.println("\nNo. " + (i + 1) +"\tacronym: " + acronyms[i] + "\ttranslation: " + trans[i]  + "\tTimes Used: " + usage[i]);
		}
	}
	public static void most(String[] acronyms, int[] usage, int valid) {
		String maxAcr ="";
		int max;
		max = -1;

		for(int i = 0; i < valid; i++)
		{
			if(usage[i] > max)
			{
				max = usage[i];
				maxAcr = acronyms[i];
			}
			else if(usage[i] == max)
			{
				maxAcr += ", " + acronyms[i];
			}
		}
		System.out.println("the most used acronym\\'s was " + maxAcr + " and it\\they was used " + max + " times");
	}
	public static void least(String[] acronyms, int[] usage, int valid) {
		String minAcr ="";
		int min;
		min = 1000;

		for(int i = 0; i < valid; i++)
		{
			if(usage[i] < min)
			{
				min = usage[i];
				minAcr = acronyms[i];
			}
			else if(usage[i] == min)
			{
				minAcr += ", " + acronyms[i];
			}
		}
		System.out.println("the least used acronym\\'s was " + minAcr + " and it\\they was used " + min + " times");
	}

	public static void allAcronym(String[] acronyms, String[] trans, int valid) {

		System.out.println("These are all acronyms\tthese are the translations");

		for (int i = 0; i < valid; i++) {

			System.out.print(acronyms[i] + "\t" + trans[i] + "\n");

		}
	}
	public static void addAcronym(String[] acronyms, String[] trans, int[] usage, int valid)throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("to add an acronym please enter, the acronym");
		acronyms[valid] = verifyString();
		int i = findIndex(acronyms, acronyms[valid], valid);
		while (i !=-1) 
		{
			System.out.println("Dictionary File Already contains the " + acronyms[valid] + " acronym");
			acronyms[valid] = verifyString();
			i = findIndex(acronyms, acronyms[valid], valid);
		}
		System.out.println("please enter " + acronyms[valid] + " translation");
		trans[valid] = verifyString();
		usage[valid] = 0;
	}
	public static void editAcronym(String[] acronyms, String[] trans, int[] usage, int valid) {

		Scanner keyboard = new Scanner(System.in);
		String input;
		System.out.println("to edit an acronym please enter the acronym");
		input = verifyString();
		int i = findIndex(acronyms, input, valid);
		while (i==-1) 
		{
			System.out.println("invalid acronym!! Please enter an acronym contained in the Dictionary file");
			input = verifyString();
			i = findIndex(acronyms, input, valid);
		}

		System.out.println("please enter the translation of " + input);
		input = verifyString();
		trans[i] = input;
	}
	public static void deleteAcronym(String[] acronyms, String[] trans, int[] usage, int valid)throws IOException {
		//Scanner file = new Scanner(new File(DICTIONARY));
		//FileWriter fw = new FileWriter(DICTIONARY, true);
		//PrintWriter outFile = new PrintWriter(fw);

		Scanner keyboard = new Scanner(System.in);
		String input;
		System.out.println("to delete an acronym please enter the acronym");
		input = verifyString();
		int i = findIndex(acronyms, input, valid);
		while (i==-1) 
		{
			System.out.println("invalid acronym!! Please enter an acronym contained in the Dictionary file");
			input = verifyString();
			i = findIndex(acronyms, input, valid);
		}
		acronyms[i] = acronyms[valid-1];
		trans[i] = trans[valid-1];
		usage[i] = usage[valid-1];
		acronyms[valid-1] = null;
		trans[valid-1] = null;
		usage[valid-1] = 0;

		//outFile.println(acronyms[i]);

	}

	public static void writeDictionary(String[] acronyms, String[] trans, int[] usage, int valid) throws IOException {
		PrintWriter outFile = new PrintWriter(DICTIONARY);
		for(int i = 0; i < valid; i++)
		{
			outFile.println(acronyms[i]);
			outFile.println(trans[i]);
			outFile.println(usage[i]);
		}
		outFile.close();
	}
}