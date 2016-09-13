package gui;

import java.io.File;
import java.io.FileNotFoundException;

import classes.Animal;
import classes.AnimalList;
import classes.Person;
import classes.PersonList;
import database.Database;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import utilites.ReadWrite;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */

public class TopMenuBar {

	private AnimalList animalList;
	private PersonList personList;
	private AdoptionTable adopTable;

	public MenuBar menuBar(BorderPane bP, AnimalList aList, PersonList pList){
		//creates file tab menu and creates it items

		animalList = aList;
		personList = pList;
		Database db = new Database();
		//Animal animal= new Animal( 8, "cat", false, "jack russel", "jack russel", "reggie");

		Menu fileMenu = new Menu("_File");
		MenuItem about = new MenuItem("_About...");
		//setting the action for the about button
		//calls the info method in alertbox class which displays information alert window with 2 strings that are passed in
		about.setOnAction(e -> AlertBox.info("About","Software Created By Alan Kelly\n\t\t of DNET2"));
		MenuItem maintenance = new MenuItem("_Maintenance...");
		//setting the action for the maintenance button
		//calls the display method in PasswordWindow class which displays a login window
		maintenance.setOnAction(e -> PasswordWindow.display());
		Menu loading = new Menu("_Load");
		MenuItem loadFile = new MenuItem("Load from file");
		loadFile.setOnAction(e -> {
			testSaveLoad("Loaded", loadSerialData(adopTable));
		});
		MenuItem loadData = new MenuItem("Load from Database");
		loading.getItems().addAll(loadFile,loadData);
		loadData.setOnAction(e -> {
			db.setDB();
	animalList = db.loadData(personList);
		});
		//adds 2 menuitems to a menu. this menu will then be added to the file menu therefore creating a sub menu
		Menu saving = new Menu("_Save");

		MenuItem saveFile = new MenuItem("Save to file");
		saveFile.setOnAction(e -> {
			testSaveLoad("Saved", saveSerialData());
		});
		MenuItem saveData = new MenuItem("Save to Database");
		saveData.setOnAction(e -> {
			db.setDB();
			personList.printPersonList2();
			saveToData(db, animalList);
		});
		saving.getItems().addAll(saveFile,saveData);
		MenuItem exit = new MenuItem("_Exit");
		//setting the action for the exit button
		//when exit is clicked it will close the application.
		exit.setOnAction(e -> {
			Platform.exit();
			e.consume();
//	        AlertBox.warn("Quit", "Are You sure u want to quit the application", primaryStage);
			System.exit(0);
		});
		//adds the menu items created to the filemenu
		fileMenu.getItems().addAll(about, new SeparatorMenuItem(), maintenance, new SeparatorMenuItem(), loading, saving, new SeparatorMenuItem(),exit);

		//creates animal tab menu and creates it items
		Menu animals = new Menu("_Animals");
		MenuItem allAnimals = new MenuItem("View All Animals...");
		MenuItem lostAnimals = new MenuItem("View Lost Animals...");
		MenuItem foundAnimals = new MenuItem("View Found Animals...");
		MenuItem adoptAnimals = new MenuItem("View Adoption Animals...");
		animals.getItems().addAll(allAnimals, lostAnimals, foundAnimals, adoptAnimals);

		//creates new instance of Tables class
		ReportTables rpTable = new ReportTables();
		LostTable lostTable = new LostTable();
		FoundTable foundTable = new FoundTable();
		adopTable = new AdoptionTable();

		//sets action for all the items in Animals menu
		allAnimals.setOnAction(e -> {
			bP.setCenter(new VBox(CustomItems.label("All Animals"), rpTable.animalTable(animalList), rpTable.button(personList)));
		});
		lostAnimals.setOnAction(e -> {
			bP.setCenter(new VBox(CustomItems.label("All Lost Animals"), lostTable.animalTable(animalList.getLostList()), lostTable.lostAnimal(animalList, personList, "Choose Date Lost & Location")));
		});
		foundAnimals.setOnAction(e -> {
			bP.setCenter(new VBox(CustomItems.label("All Found Animals"),foundTable.animalTable(animalList.getFoundList()), foundTable.foundAnimal(animalList, personList, "Choose Date Found & Location")));
		});
		adoptAnimals.setOnAction(e -> {
			bP.setCenter(new VBox(CustomItems.label("Found Animals"), adopTable.animalTable(animalList.getFoundList()), adopTable.addStoredAnimal(animalList), CustomItems.label("All Adoption Animals"), adopTable.animalAdoptionTable(animalList.getAdoptionList()), adopTable.adoptAnimal(animalList, personList)));
		});

		//creates reports tab menu and creates its items
		Menu reportMenu = new Menu("_Reports");
		Reports reports = new Reports();

		MenuItem displayAllAnimals = new MenuItem("Display All Animals...");
		displayAllAnimals.setOnAction(e -> {
			bP.setCenter(reports.allAnimals(animalList));	
		});

		MenuItem displayLostLoc = new MenuItem("Display Animals Lost by Location...");
		displayLostLoc.setOnAction(e -> {
			bP.setCenter(reports.lostLocation(animalList));
		});
		
		MenuItem displayLostLocDate = new MenuItem("Display Animals Lost by Location and Date...");
		displayLostLocDate.setOnAction(e -> {
			bP.setCenter(reports.lostLocationDate(animalList));
		});

		MenuItem displayFoundLoc = new MenuItem("Display Animals Found by Location...");
		displayFoundLoc.setOnAction(e -> {
			bP.setCenter(reports.foundLocation(animalList));
		});

		MenuItem displayFoundLocDate = new MenuItem("Display Animals Found by Location and Date...");
		displayFoundLocDate.setOnAction(e -> {
			bP.setCenter(reports.foundLocationDate(animalList));	
		});

		MenuItem displayFoundDateGender = new MenuItem("Display Animals Found by Date Sorted By Gender...");
		displayFoundDateGender.setOnAction(e -> {
			bP.setCenter(reports.foundDateGender(animalList));	
		});
		
		MenuItem displayAdoptionReadyName = new MenuItem("Display Adoption Ready Animals Sorted By Name...");
		displayAdoptionReadyName.setOnAction(e -> {
			bP.setCenter(reports.readyAdoption(animalList));		
		});
		
		MenuItem displayAdoptionReadyAge = new MenuItem("Display Adoption Ready Animals Sorted By Age...");
		displayAdoptionReadyAge.setOnAction(e -> {
			bP.setCenter(reports.readyAdoptionByAge(animalList));		
		});
		
		MenuItem displayPuppiesTrainingAdop = new MenuItem("Display Puppies in Training for Adoption...");
		displayPuppiesTrainingAdop.setOnAction(e -> {
			bP.setCenter(reports.puppiesTrainingAdoption(animalList));			
		});
		
		MenuItem displayTrainingAdop = new MenuItem("Display Animals in Training for Adoption...");
		displayTrainingAdop.setOnAction(e -> {
			bP.setCenter(reports.trainingAdoption(animalList));			
		});

		MenuItem displaySponsers = new MenuItem("Display Sponsors...");
		displaySponsers.setOnAction(e -> {
			bP.setCenter(reports.displaySponsors(personList.getList()));
		});
		
		reportMenu.getItems().addAll(displayAllAnimals, displayLostLoc, displayLostLocDate ,displayFoundLoc,displayFoundLocDate, displayFoundDateGender, displayAdoptionReadyName, displayAdoptionReadyAge, displayPuppiesTrainingAdop, displayTrainingAdop,displaySponsers);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, animals, reportMenu);
		bP.setTop(menuBar);

		return menuBar;
	}
	// loads the serialized animalList and initializes this animalList with his data
	public void setAnimalList(){
		int highest = 0;
		animalList = ReadWrite.readAnimalListSerial(new File("animalList.ser"));
		for(int i = 0; i < animalList.size(); i++){
			if(highest < animalList.get(i).getID()){
				highest = animalList.get(i).getID();
			}
		}
		Animal.setAnimalID(highest + 1);
		//searchs for animals that have been in found for more than a month and adds them to the adoption section
		adopTable.addlastMonthFoundAnimals(animalList);
		setPersonList();
	}
	// loads the serialized PersonList and initializes this personList with his data
	public void setPersonList(){
		int highest = 0;
		personList = ReadWrite.readPersonListSerial(new File("PersonList.ser"));
		for(int i = 0; i < personList.size(); i++){
			if(highest < personList.get(i).getID()){
				highest = personList.get(i).getID();
			}
		}
		Person.setStaticID(highest + 1);
	}

	public boolean loadSerialData(AdoptionTable adopTable) {
		setAnimalList();
        return true;
    }
	
	public boolean saveSerialData() {
		try {
			ReadWrite.writePersonListSerial(personList);
			ReadWrite.writeAnimalListSerial(animalList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return true;
    }
	//tests if serialised data has been Saved/loaded.
	public void testSaveLoad(String string, boolean b){
		if(b){
			AlertBox.confirm("Data " + string, "Animal Data has been succesfully " + string);
		}
		else
		{
			AlertBox.warn("error" ,"Data not " + string +" succesfully");
		}
	}
	
	public void saveToData(Database db, AnimalList animalList){
		db.dropTables();
		for(int i = 0; i < animalList.size(); i++){
		db.addAnimal(animalList.get(i));
		}
	}
}

