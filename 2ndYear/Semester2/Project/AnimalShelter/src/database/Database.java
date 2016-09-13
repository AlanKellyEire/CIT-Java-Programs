package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import classes.Adoption;
import classes.Animal;
import classes.AnimalList;
import classes.Category;
import classes.Found;
import classes.Lost;
import classes.Person;
import classes.PersonList;

/**
* @author Alan Kelly R00052131
* Created 9 May 2016
*
*/
public class Database {
	public final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public final String JDBC_URL = "jdbc:derby:test;create=true";
	private Connection connection;
	private Statement stmt = null;

	public void setDB(){
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(JDBC_URL);
			//	connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createTable(){
		setDB();
		try {
			//connection.createStatement().execute("DROP TABLE AdoptionTable");
			//connection.createStatement().execute("create table animalTable(ID INTEGER NOT NULL, Age INTEGER NOT NULL, Name VARCHAR(10), AType VARCHAR(10) NOT NULL, Breed VARCHAR(20) NOT NULL, AnimalCat INTEGER, Adoption INTEGER, Gender boolean DEFAULT FALSE, Description VARCHAR(20), PRIMARY KEY (ID))");
			//		connection.createStatement().execute("insert into animalTable values (3, 2, 'billy', 'Dog', 'jack russell', 1, 1, TRUE, 'dead')");
			//			connection.createStatement().execute("DROP TABLE CategoryTable");
			//			connection.createStatement().execute("DROP TABLE PersonTable");
			//			connection.createStatement().execute("DROP TABLE InterestedTable");
			connection.createStatement().execute("create table animalTable(ID INTEGER NOT NULL, Age INTEGER NOT NULL, Name VARCHAR(10), AType VARCHAR(10) NOT NULL, Breed VARCHAR(20) NOT NULL, AnimalCat INTEGER, Adoption INTEGER, Gender boolean DEFAULT FALSE, Description VARCHAR(20), PRIMARY KEY (ID))");
			connection.createStatement().execute("create table CategoryTable(ID INTEGER NOT NULL, Category VARCHAR(5), Date DATE, Contact INTEGER NOT NULL, Location VARCHAR(20) NOT NULL, Owner INTEGER, PRIMARY KEY (ID))");
			connection.createStatement().execute("create table AdoptionTable(ID INTEGER NOT NULL, Neutered boolean NOT NULL, Chipped boolean NOT NULL, Vaccinated boolean NOT NULL, Reserved boolean NOT NULL, Status VARCHAR(12), AdoptionDate DATE, Interested INTEGER, Reserver VARCHAR(25), PRIMARY KEY (ID))");
			connection.createStatement().execute("create table PersonTable(ID INTEGER NOT NULL, Name VARCHAR(20), Address VARCHAR(20), Phone VARCHAR(10), Email VARCHAR(20), PRIMARY KEY (ID))");
			connection.createStatement().execute("create table InterestedTable(ID INTEGER NOT NULL, PersonID INTEGER NOT NULL, PRIMARY KEY (ID))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dropTables(){
		setDB();
		try {
			connection.createStatement().execute("DROP TABLE AnimalTable");
			connection.createStatement().execute("DROP TABLE AdoptionTable");
			connection.createStatement().execute("DROP TABLE CategoryTable");
			connection.createStatement().execute("DROP TABLE PersonTable");
			connection.createStatement().execute("DROP TABLE InterestedTable");
			connection.createStatement().execute("create table animalTable(ID INTEGER NOT NULL, Age INTEGER NOT NULL, Name VARCHAR(10), AType VARCHAR(10) NOT NULL, Breed VARCHAR(20) NOT NULL, AnimalCat INTEGER, Adoption INTEGER, Gender boolean DEFAULT FALSE, Description VARCHAR(20), PRIMARY KEY (ID))");
			connection.createStatement().execute("create table CategoryTable(ID INTEGER NOT NULL, Category VARCHAR(5), Date DATE, Contact INTEGER NOT NULL, Location VARCHAR(20) NOT NULL, Owner INTEGER, PRIMARY KEY (ID))");
			connection.createStatement().execute("create table AdoptionTable(ID INTEGER NOT NULL, Neutered boolean NOT NULL, Chipped boolean NOT NULL, Vaccinated boolean NOT NULL, Reserved boolean NOT NULL, Status VARCHAR(12), AdoptionDate DATE, Interested INTEGER, Reserver INTEGER, PRIMARY KEY (ID))");
			connection.createStatement().execute("create table PersonTable(ID INTEGER NOT NULL, Name VARCHAR(20), Address VARCHAR(20), Phone VARCHAR(10), Email VARCHAR(20), PRIMARY KEY (ID))");
			connection.createStatement().execute("create table InterestedTable(ID INTEGER NOT NULL, PersonID INTEGER NOT NULL, PRIMARY KEY (ID))");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void getAnimals(){
		setDB();
		try
		{
			stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * from animalTable");
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();
			for (int i=1; i<=numberCols; i++)
			{
				//print Column Names
				System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
			}

			System.out.println("\n-------------------------------------------------");

			while(results.next())
			{
				int ID = results.getInt(1);
				int Age = results.getInt(2);
				String Name  = results.getString(3);
				String AType = results.getString(4);
				String Breed = results.getString(5);
				String AnimalCat = results.getString(6);
				String Adoption = results.getString(7);
				String Gender = results.getString(8);
				String Description = results.getString(9);

				System.out.println(ID + "\t\t" + Age + "\t\t" + Name + "\t\t" + AType + "\t\t" + Breed + "\t\t" + AnimalCat + "\t\t\t" + Adoption + "\t\t" + Gender + "\t\t" + Description);
			}
			results.close();
			stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
	}

	public void getPerson(){
		setDB();
		try
		{
			stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * from personTable");
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();
			for (int i=1; i<=numberCols; i++)
			{
				//print Column Names
				System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
			}

			System.out.println("\n-------------------------------------------------");

			while(results.next())
			{
				int ID = results.getInt(1);            
				String Name  = results.getString(2);
				String Address = results.getString(3);
				String Phone = results.getString(4);
				String Email = results.getString(5);

				System.out.println(ID + "\t\t" + Name + "\t\t" + Address + "\t\t" + Phone + "\t\t" + Email);
			}
			results.close();
			stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
	}

	public void getCategory(){
		setDB();
		try
		{
			stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * from CategoryTable");
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();
			for (int i=1; i<=numberCols; i++)
			{
				//print Column Names
				System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
			}

			System.out.println("\n-------------------------------------------------");

			while(results.next())
			{
				int ID = results.getInt(1);            
				String Name  = results.getString(2);
				Date Date = results.getDate(3);
				int Contact = results.getInt(4);

				System.out.println(ID + "\t\t" + Name + "\t\t" + Date + "\t\t" + Contact );
			}
			results.close();
			stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
	}

	public void getAdoption(){
		setDB();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * from AdoptionTable");
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();
			for (int i=1; i<=numberCols; i++)
			{
				//print Column Names
				System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
			}

			System.out.println("\n-------------------------------------------------");

			while(results.next())
			{
				int ID = results.getInt(1);            
				//boolean New  = results.getBoolean(2);
				//boolean Vac  = results.getBoolean(3);
				Date Date = results.getDate(7);

				System.out.println(ID + "\t\t" + Date );//+ "\t\t" + Vac + "\t\t" );
			}
			results.close();
			stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
	}

	public void getDBversion(){
		DatabaseMetaData dbmd;
		try {
			dbmd = connection.getMetaData();
			String productName = dbmd.getDatabaseProductName();
			String productVersion = dbmd.getDatabaseProductVersion();
			System.out.println("Using " + productName + " " + productVersion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                        
	}

	public AnimalList loadAnimals(){
		setDB();
		boolean gen = false;
		Animal animal = null;
		AnimalList animalList = new AnimalList();
		try
		{
			stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * from animalTable");
			while(results.next())
			{
				int ID = results.getInt(1);
				int Age = results.getInt(2);
				String Name  = results.getString(3);
				String AType = results.getString(4);
				String Breed = results.getString(5);
				String AnimalCat = results.getString(6);
				String Adoption = results.getString(7);
				String Gender = results.getString(8);
				String Description = results.getString(9);
				if(Gender == "Male"){
					gen = true;
				}

				animal = new Animal(Age, AType, gen, Breed, Description, Name);
				animalList.add(animal);
				//                if(AnimalCat != null){
				//                	ResultSet animalCat = stmt.executeQuery("SELECT * from AdoptionTable where id = AnimalCat");
				//                	 ResultSetMetaData catData = animalCat.getMetaData();
				//                	 if(animalCat.getString(2) == "Lost"){
				//                	 Category adoption = new Lost(animalCat.getString(3), )
				//                	 }
				//                }
			}
			results.close();
			stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}  
		return animalList;
	}

	public AnimalList loadData(PersonList personList){
		setDB();
		boolean gen = false;
		Animal animal = null;
		ResultSet results = null;
		AnimalList animalList = new AnimalList();

		try {
			animalList = createObjects(personList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return animalList;
	}

	public void deleteRow(){
		setDB();
		try {
			connection.createStatement().execute("DELETE FROM AnimalTable WHERE id = 2");
			connection.createStatement().execute("DELETE FROM AnimalTable WHERE id = 3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	public void deleteRow(int id){
		setDB();
		try {
			connection.createStatement().execute("DELETE FROM AnimalTable WHERE id = " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	public void addAnimal(Animal animal){
		setDB();
		int id = animal.getID();
		int age = animal.getAge();
		String name = animal.getName();
		String aType = animal.getAType();
		String breed = animal.getBreed();
		String des = animal.getDescription();
		boolean gen = animal.getGender();
		int adoption = -1;
		int animalCat = -1;

		if(animal.getAdoption() != null){
			
			saveAdoption(animal);
			saveInterested(animal);
		}
		if(animal.getAnimalCat() != null){
			saveCategory(animal);
			checkPersons(animal);
		}

		if(animal.getAdoption() != null){
			adoption = id;
		}

		if(animal.getAnimalCat() != null){
			animalCat = id;
		}

		try {
			//connection.createStatement().execute("insert into animalTable values (" +id+ ", " +age+ ", 'reggie', 'Dog', 'jack russel', 1, 1, TRUE, 'dead')");
			connection.createStatement().execute("insert into animalTable values (" +id+ ", " +age+ ", '" +name+ "', '" +aType+ "', '" +breed+ "', " +animalCat+ ", " +adoption+ ", " +gen+ ", '" +des+ "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	public void addtestinfo(){
		setDB();
		//CategoryTable(ID INTEGER NOT NULL, Category VARCHAR(5), Date DATE, Contact INTEGER NOT NULL, Location VARCHAR(20) NOT NULL, Owner INTEGER, PRIMARY KEY (ID))");
		try {
			connection.createStatement().execute("insert into animalTable values (1, 1 , 'reggie', 'Dog', 'jack russel', 1, 1, TRUE, 'dead')");
			connection.createStatement().execute("insert into CategoryTable values ( 1, 'Lost' , '06/06/2012', 1, 'dublin', 0)");
			connection.createStatement().execute("insert into InterestedTable values ( 1, 1)");
			connection.createStatement().execute("insert into AdoptionTable values (" + 1 + ", " + false + ", " + false + ", " + false + ", " + true + ", '" + "Ready" + "', '" + "06/06/2012" + "', " + 1 + ", " + 1 + ")");
			connection.createStatement().execute("insert into PersonTable values (" + 1 + ", '" + "name" + "', '" +  "add" + "', '" +  "ph" + "', '" +  "email" + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
	}

	public AnimalList createObjects(PersonList personList) throws SQLException{

		Animal animal = null;
		ResultSet results = null;
		AnimalList animalList = new AnimalList();
		boolean gen = false;


		stmt = connection.createStatement();
		results = stmt.executeQuery("SELECT * from animalTable");

		int AnimalCat = -1, Adoption = -1;

		while(results.next()) 
		{
			int ID = results.getInt(1);
			int Age = results.getInt(2);
			String Name  = results.getString(3);
			String AType = results.getString(4);
			String Breed = results.getString(5);
			AnimalCat = results.getInt(6);
			Adoption = results.getInt(7);
			String Gender = results.getString(8);
			String Description = results.getString(9);
			if(Gender == "Male"){
				gen = true;
			}

			animal = new Animal(Age, AType, gen, Breed, Description, Name);
			animalList.add(animal);
			animal.setThisAnimalID(ID);

			if(AnimalCat != -1){
				Statement stmt2 = connection.createStatement();
				ResultSet resultCat = stmt2.executeQuery("SELECT * from CategoryTable where ID = " + AnimalCat);
				//connection.setAutoCommit(false);
				resultCat.next();
				//	System.out.println(resultCat.getInt(1) +"\n\n  " + resultCat.getString(2));
				String Category = resultCat.getString(2);
				//	System.out.println(Category);
				if(Category.equalsIgnoreCase("Lost")){
					String input = resultCat.getString(3);
					//					System.out.println(input);

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate dt = LocalDate.parse(input, formatter);

					Statement stmt3 = connection.createStatement();
					ResultSet personData = stmt3.executeQuery("SELECT * from PersonTable where ID = " + resultCat.getInt(4));
					personData.next();
					Person person = new Person(personData.getString(2), personData.getString(3), personData.getString(4), personData.getString(5));
					person.setPersonId(personData.getInt(1));
					Category lost = new Lost(dt, resultCat.getString(5), person);
					animal.setAnimalCat(lost);
					personList.add(person);
					resultCat.close();
					personData.close();

					stmt2.close();
					stmt3.close();  
				}
				else{
					Statement stmt5 = connection.createStatement();
					ResultSet result5 = stmt5.executeQuery("SELECT * from CategoryTable where ID = " + AnimalCat);
					result5.next();
					String input = result5.getString(3);
					//					System.out.println(input);

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate dt = LocalDate.parse(input, formatter);

					Statement stmt3 = connection.createStatement();
					ResultSet personData = stmt3.executeQuery("SELECT * from PersonTable where ID = " + result5.getInt(4));
					
					personData.next();
					Person person = new Person(personData.getString(2), personData.getString(3), personData.getString(4), personData.getString(5));
					Category found = new Lost(dt, resultCat.getString(5), person);
					animal.setAnimalCat(found);
					
					if(!(testPersonExists(person, personList))){personList.add(person);}
					resultCat.close();
					personData.close();
					stmt5.close();
					stmt2.close();
					stmt3.close();
					result5.close();
					
				}
				if(Adoption != -1){
//					System.out.println(Adoption + "int adoption num");
					animal.setAdoption(adoptionObject(Adoption, personList));
				}
			}

		}
		stmt.close();
		return animalList;
	}

	public Category adoptionObject(int adoptionId, PersonList personList) throws SQLException{
		int interestedID = -1;

		Statement stmt = connection.createStatement();
		ResultSet adoptionData = stmt.executeQuery("SELECT * from AdoptionTable where ID = " + adoptionId);
		adoptionData.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String input = adoptionData.getString(7);
		LocalDate dt = LocalDate.parse(input, formatter);

		Adoption adoption = new Adoption(dt);
		adoption.setChipped(adoptionData.getBoolean(3));
		adoption.setNeutered(adoptionData.getBoolean(2));
		adoption.setVaccinated(adoptionData.getBoolean(4));
		adoption.setReservered(adoptionData.getBoolean(5));
		adoption.setStatus(adoptionData.getString(6));
		interestedID = adoptionData.getInt(8);
		if (interestedID != -1) {
//			System.out.println("aasadasasasaxfsa dtatabase");
			Statement stmt1 = connection.createStatement();
			ResultSet interestData = stmt1.executeQuery("SELECT * from InterestedTable where ID = " + interestedID);
			while(interestData.next()){
//				System.out.println(interestData.getInt(2) + "int data");
//				System.out.println(personList.size() + "pl size");
			//	System.out.println(!(personList.get((interestData.getInt(2)-1)).equals(null)) + "person test");
//				System.out.println(personList.size() + "pl size");
				if(interestData.getInt(2) <= personList.size() && !(personList.get((interestData.getInt(2)-1)).equals(null))){
					adoption.addPerson(personList.get(interestData.getInt(2) - 1));
//					System.out.println("test add person");
				}
				else if(interestData.getInt(2) <= personList.size() && !(personList.get(interestData.getInt(2)-1) != null))
					System.out.println();
				Statement stmt2 = connection.createStatement();
				ResultSet personData = stmt2.executeQuery("SELECT * from personTable where ID = " + interestData.getInt(2));
				while(interestData.next()){
					Person person = new Person(personData.getString(2), personData.getString(3), personData.getString(4),
							personData.getString(5));
					person.setPersonId(personData.getInt(1));
					if(!(testPersonExists(person, personList))){
					//	System.out.println("test if person added");
						adoption.addPerson(person);
						stmt.close();
						interestData.close();
						personData.close();
					}
					else{
					//	System.out.println("test if person added 2");
						adoption.addPerson(personList.get(findPersonIndex(person, personList)));
					//	System.out.println(adoption.getContact().getName());
					}

				}
			}
		}
		return adoption;
	}

	public void saveCategory(Animal animal){
		if(animal.getAnimalCat() != null){
			String cat = "Found", loc;

			if (animal.getAnimalCat() instanceof Lost){
				cat = "Lost";
				loc = ((Lost) animal.getAnimalCat()).getLocation();
			}
			else{
				loc = ((Found) animal.getAnimalCat()).getLocation();
			}

			int c = animal.getAnimalCat().getContact().getID();
			//System.out.println(animal.getAnimalCat().getContact().getID() + "ann contact");
			LocalDate date = animal.getAnimalCat().getDate();
			try {

				connection.createStatement().execute("insert into CategoryTable values (" + animal.getID() + ", '" + cat + "', '" + date + "', " + animal.getAnimalCat().getContact().getID() + ", '" + loc + "', " + c + ")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void saveAdoption(Animal animal){
		//System.out.println(animal.getAdoption().getContact());
		int c = -1;
		if(animal.getAdoption() != null){
			animal.getAdoption().getChipped();
			animal.getAdoption().getNeutered();
			animal.getAdoption().getVaccinated();
			animal.getAdoption().getReserved();
			animal.getAdoption().getStatus();
			animal.getAdoption().getContact();
			if(animal.getAdoption().getContact() != null){
				c = animal.getAdoption().getContact().getID();
			}
			LocalDate date = animal.getAdoption().getDate();
			try {
				connection.createStatement().execute("insert into AdoptionTable values (" + animal.getID() + ", " + animal.getAdoption().getNeutered() + ", " + animal.getAdoption().getChipped() + ", " + animal.getAdoption().getVaccinated() + ", " + animal.getAdoption().getReserved() + ", '" + animal.getAdoption().getStatus() + "', '" + date + "', " + animal.getID() + ", " + c + ")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void saveInterested(Animal animal){
		if(animal.getAdoption().getContact() != null){


			try {
				connection.createStatement().execute("insert into InterestedTable values (" + animal.getID() + ", " + animal.getAdoption().getContact().getID() + ")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void checkPersons(Animal animal){
		if(animal.getAdoption() != null && animal.getAdoption().getContact() != null){
			savePerson(animal.getAdoption().getContact());
		}
		if(animal.getAnimalCat() != null){
			savePerson(animal.getAnimalCat().getContact());
		}
	}
	
	public void savePerson(Person person){
	
			try {
				connection.createStatement().execute("insert into PersonTable values (" + person.getID() + ", '" + person.getName() + "', '" +  person.getAddress() + "', '" +  person.getPhoneNumber() + "', '" +  person.getEmail() + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void modTable(){
		try {
			connection.createStatement().execute("ALTER TABLE AdoptionTable ADD Reserver VARCHAR(25)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean testPersonExists(Person person, PersonList personList){
		for(int i = 0; i < personList.size(); i++){
			if(person.equals(personList.get(i))){
				return true;
			}
		}
		return false;
	}

	public int findPersonIndex(Person person, PersonList personList){
		for(int i = 0; i < personList.size(); i++){
			if(person.equals(personList.get(i))){
				return i;
			}
		}
		return 0;
	}

}
