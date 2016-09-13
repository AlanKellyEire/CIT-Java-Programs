package utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import classes.Animal;

/**
 * @author Alan Kelly R00052131
 * Created 30 Mar 2016
 *
 */
public class SerialFile {

	public static void test()
	{
		Animal e = null;
		try
		{
			FileInputStream fileIn = new FileInputStream("animalSerial.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Animal) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c)
		{
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Deserialized Employee...");
		System.out.println("Name: " + e.getName());
		System.out.println("Address: " + e.getAge());
		System.out.println("SSN: " + e.getBreed());
		System.out.println("Number: " + e.getID());
	}
}

