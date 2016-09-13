
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
//  Name : Alan Kelly
//  StudentID: R00052131
//  Class: ITM
//
import java.util.Scanner;

public class Hotels {

	public static final int MAX = 9;

	public static void main(String[] args) throws IOException {
		String[] hotelNames = { "Avon Rose", "Cherry Tree", "River Lodge",
				"Gold Coast", "White Tops", "", "", "", "" };
		int[] starRatings = { 3, 3, 4, 4, 2, 0, 0, 0, 0 };
		int[] oneNightRoomRates = { 100, 120, 200, 80, 80, 0, 0, 0, 0 };
		int[] reductionForFiveNights = { 50, 50, 200, 75, 150, 0, 0, 0, 0 };
		int numberOfHotelsListed = 5;
		int choice = -1;

		Scanner keyboard = new Scanner(System.in);

		do {
			printTitle();
			System.out.println("1.  Display Hotels with Desired Star Rating");
			System.out.println("2.  Display All Hotels showing Star Ratings");
			System.out.println("3.  Show the Cheapest hotel for 1 Night");
			System.out.println("4.  Add New Hotel Information from a File");
			System.out.println("5.  Create Flyers");
			System.out.println("6.  Exit");

			while (!keyboard.hasNextInt()) {
				keyboard.next();
				System.out.println("Enter an number from 1 - 6");
			}
			choice = keyboard.nextInt();

			switch (choice) {
			case 1:
				System.out.println("To Do Option 1: Display Hotel Prices given min star");
				// Question 1
				displayHotelPrices(hotelNames, oneNightRoomRates, starRatings, numberOfHotelsListed);
				break;
			case 2:
				// Question 2
				System.out.println("To Do Option 2: Display Hotel with Visual Star Rating");
				displayStars(hotelNames, oneNightRoomRates, starRatings, numberOfHotelsListed);
				break;
			case 3:
				// Question 3
				System.out.println("To Do Option 3: Show Cheapest One Night Stay");
				displayCheapestForOneNight( hotelNames, oneNightRoomRates, starRatings, numberOfHotelsListed);
				break;
			case 4:
				// Question 4:
				System.out.println("To Do Option 4: Update Hotel Info from a file");
				numberOfHotelsListed = updateFromFile(hotelNames, oneNightRoomRates, reductionForFiveNights, starRatings, numberOfHotelsListed);
				break;
			case 5:
				// Question 5:
				System.out.println("To Do Option 5: Create Flyers");
				System.out.println(numberOfHotelsListed);
				createFlyers(hotelNames, 
						oneNightRoomRates, reductionForFiveNights, starRatings, numberOfHotelsListed);
				break;

			}

			if ((choice >= 1) && (choice <= 5)) {
				keyboard.nextLine();
				System.out.println("\nPress Return to continue"); 
				keyboard.nextLine();
			} 
			else if (choice != 6) 
			{ 
				keyboard.nextLine();
				System.out.println("\nPress Return to continue"); 
				keyboard.nextLine();
			}

		} while (choice != 6);

		System.out.println("Exiting....");
		keyboard.close();
	}

	public static void displayHotelPrices(String[] hotelNames,
			int[] oneNightRoomRates, int[] starRatings, int numberOfHotelsListed) {
		printTitle();
		int opt, c=0;
		System.out.println("Enter the minimum number of stars required");
		opt = readIntRange(1, 5);
		System.out.println("Hotel Name\tOne Night\n----------\t---------");
		for (int i = 0; i < numberOfHotelsListed; i++)
			if(starRatings[i] >= opt)
			{System.out.println(hotelNames[i] + "\t" + oneNightRoomRates[i]);
			c++;}
		if (c==0)
			System.out.println("No Hotels meet the criteria");

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
	public static void displayStars(String[] hotelNames, 
			int[] oneNightRoomRates, int[] starRatings, int numberOfHotelsListed) {
		printTitle();
		System.out.println("Hotel Name\tGrade\n----------\t-----");
		for (int i = 0; i < numberOfHotelsListed; i++)
			System.out.println(hotelNames[i] + "\t" + printStars(starRatings[i]));
	}
	public static String printStars(int numStars) {
		String stars = "";
		for(int i = 1; i <= numStars; i++)
			stars += "*";
		return stars;
	}

	public static void displayCheapestForOneNight(String[] hotelNames, 
			int[] oneNightRoomRates, int[] starRatings, int numberOfHotelsListed) {
		String hotels ="";
		int min;
		min = 1000000;

		for(int i = 0; i < numberOfHotelsListed; i++)
		{
			if(oneNightRoomRates[i] < min)
			{
				hotels = hotelNames[i];
				min = oneNightRoomRates[i];

			}
			else if(oneNightRoomRates[i] == min)
			{
				hotels += "\n" + hotelNames[i];
			}
		}
		System.out.println("The cheapest Hotel(s) for one night at " + min + " are:\n");
		System.out.println(hotels);
	}
	public static void printTitle() {
		System.out.println("\nGlobal Hotels Prices\n---------------------------------\n");
	}
	public static int updateFromFile(String[] hotelNames, 
			int[] oneNightRoomRates, int[] reductionForFiveNights, int[] starRatings, int numberOfHotelsListed) throws IOException {
		if(numberOfHotelsListed == MAX)
		{
			System.out.println("No more hotels are being accepted.");
		}
		else
		{
			File newEntrant = new File("HotelsInfo.txt");
			if(!newEntrant.exists())
			{
				System.out.println("File newEntrant.txt not found.");
			}
			else
			{
				Scanner inFile = new Scanner(newEntrant);
				if(inFile.hasNextLine())
					hotelNames[numberOfHotelsListed] = inFile.nextLine();
				if(inFile.hasNextInt())
					oneNightRoomRates[numberOfHotelsListed] = inFile.nextInt();
				if(inFile.hasNextInt())
					reductionForFiveNights[numberOfHotelsListed] = inFile.nextInt();
				if(inFile.hasNextInt())
				{
					starRatings[numberOfHotelsListed] = inFile.nextInt();
					numberOfHotelsListed++;
				}
				else
				{
					System.out.println("Incomplete data inHotelsInfo.txt. hotel not added.");
				}

				inFile.close();
			}
		}
		System.out.println("The Hotel has been added");
		return numberOfHotelsListed;
	}
	public static void createFlyers(String[] hotelNames, 
			int[] oneNightRoomRates, int[] reductionForFiveNights, int[] starRatings, int numberOfHotelsListed) throws IOException {
		printTitle();
		System.out.println("Flyers have been created for:");
		for(int i = 0; i < numberOfHotelsListed; i++)
		{
			System.out.println(hotelNames[i]);
			String filename = hotelNames[i] + ".txt";
			PrintWriter cert = new PrintWriter(filename);
			cert.println("Stay at the "+ hotelNames[i]);
			cert.println("-------------------------------");
			cert.println(" ");
			cert.println("A " + starRatings[i] + " Star Hotel");
			cert.println("One Night for " + oneNightRoomRates[i] + " Euros");
			cert.println("Five Nights for " + ((oneNightRoomRates[i]*5)-reductionForFiveNights[i]) + " Euros");
			cert.close();
		}
	}
}