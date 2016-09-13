package utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import classes.Adoption;
import classes.Animal;
import classes.AnimalList;
import classes.Category;
import classes.Lost;
import classes.Person;
import classes.PersonList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 * This Class is responsible for reading and writing to all text files
 */
public class ReadWrite {

	private static ObservableList<String> stringList;

	public static void readAnimals(AnimalList animalList, PersonList personList) throws FileNotFoundException
	{
		File file = new File("animals.txt");
		Scanner kb = new Scanner(file);

		int age;
		boolean male;
		String name, breed, desc, type;
		while (kb.hasNext()) {

			age = kb.nextInt();
			kb.nextLine();
			type = kb.nextLine();
			male = kb.nextBoolean();
			kb.nextLine();
			name = kb.nextLine();
			breed = kb.nextLine();
			desc = kb.nextLine();

			Animal animal = new Animal(age, type, male, name, breed, desc);
			if(animal.getAType().equalsIgnoreCase("Dog")){
				Person alan = new Person("alan","cork","@","48");
				personList.add(alan);
				Category lost = new Lost(null, "boston", alan);
				animal.setAnimalCat(lost);
			}
			else if(animal.getAType().equalsIgnoreCase("cat")){
				Person billy = new Person("Billy","c","@","08");
				Category adop = new Adoption(null);
				((Adoption) adop).setStatus("ready");
				((Adoption) adop).addPerson(billy);
				animal.setAdoption(adop);
				personList.add(billy);
			}
			else
			{
				Person jj = new Person("jj","cork","@","48");
				personList.add(jj);
				Category adop = new Adoption(null);
				((Adoption) adop).setStatus("in training");
				((Adoption) adop).addPerson(jj);
				animal.setAdoption(adop);
			}

			animalList.add(animal);
		}
		kb.close();
	}

	//writes animals to a file
	public static void write(AnimalList animalList) throws FileNotFoundException
	{
		File file = new File("animals.txt");
		PrintWriter outFile = new PrintWriter(file);

		for(int i = 0; i < animalList.size(); i++)

		{
			outFile.println(animalList.get(i).getID());
			outFile.println(animalList.get(i).getAge());
			outFile.println(animalList.get(i).getAType());
			outFile.println(animalList.get(i).getGender());
			outFile.println(animalList.get(i).getName());
			outFile.println(animalList.get(i).getBreed());
			outFile.println(animalList.get(i).getDescription());
		}
		outFile.close();
	}

	public static void mainWrite(File file)
	{
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = 0; i < stringList.size(); i++)
		{
			outFile.println(stringList.get(i));
		}
		outFile.close();
	}

	//populates the table with the values from the text file
	public static ObservableList<String> mainGetString(File file) throws FileNotFoundException{

		stringList = FXCollections.observableArrayList();
		Scanner kb = new Scanner(file);
		String item;

		while (kb.hasNext()) {
			item = kb.nextLine();
			stringList.add(item);
		}

		kb.close();
		return stringList;
	}

	public static void writeAnimalListSerial(AnimalList animalList) throws FileNotFoundException
	{
		String file = "AnimalList.ser";

		ObjectOutputStream outFile;
		try {
			outFile = new ObjectOutputStream(new FileOutputStream(file));
			outFile.writeObject(animalList);
			outFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	AlertBox.confirm("Complete", "Animals saved succesful");
	}

	public static void writePersonListSerial(PersonList personList) throws FileNotFoundException
	{
		String file = "PersonList.ser";

		ObjectOutputStream outFile;
		try {
			outFile = new ObjectOutputStream(new FileOutputStream(file));
			outFile.writeObject(personList);
			outFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AnimalList readAnimalListSerial(File file){
		AnimalList animalList = null;
		try
		{
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			animalList = (AnimalList) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
			i.printStackTrace();

		}catch(ClassNotFoundException c)
		{
			System.out.println("class not found");
			c.printStackTrace();

		}
		return animalList;
	}

	public static PersonList readPersonListSerial(File file){
		PersonList personList = null;
		try
		{
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			personList = (PersonList) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
			i.printStackTrace();

		}catch(ClassNotFoundException c)
		{
			System.out.println("class not found");
			c.printStackTrace();

		}
		return personList;
	}

	public static ObservableList<?> getAnimal(ArrayList list){
		ObservableList obList = FXCollections.observableArrayList();

		for(int i = 0; i < list.size(); i++){
			obList.add(list.get(i));
		}
		return obList;
	}

	public static ArrayList<String> readToCombo(File file) throws FileNotFoundException
	{
		Scanner kb = new Scanner(file);
		String item;
		ArrayList<String> list = new ArrayList<String>();
		while (kb.hasNext()) {
			item = kb.nextLine();
			list.add(item);
		}
		kb.close();
		return list;
	}

}


