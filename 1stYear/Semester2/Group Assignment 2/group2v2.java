
import java.util.Scanner;
import java.io.*;

public class group2v2 {
	public static final String ACCOUNTS = "accounts.txt";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);

		final int SIZE = 50;
		int user, valid, i = 0, opt, accNot = 123;
		String [] accNo = new String [SIZE];
		String [] name = new String [SIZE];
		double [] bal = new double [SIZE];

		System.out.println("CIT Banking System - [Main Menu] 30-April-2015\n=======================================================================");
		System.out.println("1 – Open an account\n2 – List all account holder details\n3 – Display account balance for a given student\n4 – Deposit money into an account\n5 – Withdraw money from an account\n6 – Display number of students with balance below a given value\n7 – Exit");

		do
		{
			valid = readDict(accNo, name, bal);
			System.out.println("\nPlease choose an option:[1-7]");
			user = readIntRange(1,7);
			if (user == 1)
			{
				if (valid == SIZE)
				{
					System.out.println("The arrays are at capacity!!! You cannot add an account!!!\nPlease Choose another option");
				}
				else
					addAccount(accNo, name, bal, valid);
				valid += 1;
			}
			else if (user == 2)
			{
				allAccounts(accNo, name, bal, valid);    
			}
			else if (user == 3)
			{
				viewSingle(accNo, name, bal, valid);
			}
			else if (user == 4)
			{
				deposit(accNo, name, bal, valid);
			}
			else if (user == 5)
			{
				with(accNo, name, bal, valid);
			}
			else if (user == 6)
			{
				below(bal, valid);
			}
			writeDictionary(accNo, name, bal, valid);
		} while(user != 7);
		System.out.print("Thank you for using this application.");
		keyboard.close();
	}
	public static int readDict(String[] accNo, String[] name, double[] bal) throws IOException {
		Scanner file = new Scanner(new File(ACCOUNTS));
		int i = 0;

		while(file.hasNext())
		{
			accNo[i] = file.next();
			// eats up line
			file.nextLine();
			name[i] = file.nextLine();
			bal[i] = file.nextDouble();
			i++;
		}
		file.close();
		return i;
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
	public static double readDouble() {
		Scanner keyboard = new Scanner(System.in);
		while(!keyboard.hasNextDouble())
		{
			keyboard.next();
			System.out.print("Invalid input. Enter an Double: ");
		}
		return keyboard.nextDouble();
	}
	public static double posDouble() {
		double input;
		boolean valid = false;
		do
		{
			input = readDouble();
			if (input < 0)
				System.out.print("Invalid input. Enter a postive double ");
			else
				valid = true;
		} while(!valid);
		return input;
	}
	public static void addAccount(String[] accNo, String[] name, double[] bal, int valid)throws IOException {
		Scanner keyboard = new Scanner(System.in);
		accNo[valid] = "CIT" + (101 + valid);
		System.out.println("Student name:");
		name[valid] = verifyString();
		System.out.println("Student added successfully.  The account number for this student is " + accNo[valid]);
		bal[valid] = 0;
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
	public static int findIndex(String[] accNo, String accNot, int valid){
		int index = -1;
		for(int i = 0; i < valid; i++)
		{
			if(accNot.equalsIgnoreCase(accNo[i]))
			{
				index = i;
			}
		}
		return index;
	}
	public static void allAccounts(String[] accNo, String[] name, double[] bal, int valid) {
		System.out.println("Account Details\n=======================================================================\nNumber\t\tName\t\tAccount Balance");
		for(int i = 0; i<valid; i++)
		{
			System.out.println(accNo[i] + "\t\t" + name[i]  + "\t\t" + bal[i]);
		}
	}
	public static void writeDictionary(String [] accNo, String[] name, double[] bal, int valid) throws IOException {
		PrintWriter outFile = new PrintWriter(ACCOUNTS);
		for(int i = 0; i < valid; i++)
		{
			outFile.println(accNo[i]);
			outFile.println(name[i]);
			outFile.println(bal[i]);
		}
		outFile.close();
	}
	public static void viewSingle(String[] accNo, String[] name, double[] bal, int valid) {
		Scanner keyboard = new Scanner(System.in);
		String input;
		int i;
		System.out.println("Please enter account number:\t");
		input = verifyString();

		i = findIndex(accNo, input, valid);
		while (i==-1) {
			System.out.println("There is no account with this number");
			input = verifyString();
			i = findIndex(accNo, input, valid);    
		}
		if(i>-1 && i < valid);
		{

			System.out.println("The account contains\t€" + bal[i]);
		}
	}

	public static void deposit(String[] accNo, String[] name, double[] bal, int valid) {
		Scanner keyboard = new Scanner(System.in);
		String input; 
		int i;
		double deposit;
		System.out.println("Please enter account number:\t");
		input = verifyString();
		i = findIndex(accNo, input, valid);
		while (i==-1) {
			System.out.println("There is no account with this number");
			input = verifyString();
			i = findIndex(accNo, input, valid);    
		}            
		System.out.println(    "Please enter the amount you wish to deposit:€");
		deposit = posDouble();
		bal[i] += deposit;
		System.out.println("Your account has been updated.  Your balance is now €" + bal[i]);

	}
	public static void with(String[] accNo, String[] name, double[] bal, int valid) {
		Scanner keyboard = new Scanner(System.in);
		String input;
		int i;
		double with;
		System.out.println("Please enter account number:\t");
		input = verifyString();
		i = findIndex(accNo, input, valid);
		while (i==-1) {
			System.out.println("There is no account with this number");
			input = verifyString();
			i = findIndex(accNo, input, valid);    
		}
		if (bal[i] == 0)
		{
			System.out.println("No funds to withdraw\nPlease deposit funds in " +accNo[i] );
		}
		else
		{
		System.out.println("Please enter the amount you wish to withdraw:€");
		with = posDouble();
		while(with > bal[i])
		{
			System.out.println("Insufficient funds!!  Please enter the amount less than or equal to:€" + bal[i] + ": €" );
			with = posDouble();
		}
		bal[i] -= with;
		System.out.println("Your account has been updated.  Your balance is now €" + bal[i]);
		}

	}
	public static void below(double[] bal, int valid) {
		Scanner keyboard = new Scanner(System.in);
		int no = 0;
		double amount;
		System.out.println("Please enter an amount:");
		amount = posDouble();
		for(int i = 0; i < valid; i++)
		{
			if(amount >= (bal[i]))
			{
				no += 1;
			}
		}
		System.out.println("The number of accounts with balances below €" + amount +" is " + no);
	}
}