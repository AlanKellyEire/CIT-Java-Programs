package gui;

import java.util.ArrayList;

import classes.AnimalList;
import classes.Person;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class Reports {

	private ReportTables rT = new ReportTables();
	private Label listType;

	//returns Vbox containing the lost animals table sorted by Location
	public VBox allAnimals(AnimalList animalList){
		listType = CustomItems.label("All Animals");
		VBox lostLocation = new VBox(rT.listButtons(animalList, listType), listType, rT.animalTable(animalList));
		lostLocation.getChildren().addAll(rT.animalFieldsAdoption(listType, animalList), rT.searchAnimalButtons());
		return lostLocation;
	}
	//returns VBox with all lost animals and a combo to search by location
	public VBox lostLocation(AnimalList animalList){
		VBox lostLoc = new VBox(CustomItems.label("Lost By Location"), rT.animalTable(animalList));
		rT.lostAnimals(animalList);
		lostLoc.getChildren().addAll(rT.locationCb(animalList, "lost"), rT.searchSave("location"));

		return lostLoc;
	}
	//returns VBox with all lost animals and a combo to search by location and date
	public VBox lostLocationDate(AnimalList animalList){
		VBox lostLocDate = new VBox(CustomItems.label("Lost By Location And Date"), rT.animalTable(animalList));
		rT.lostAnimals(animalList);
		lostLocDate.getChildren().addAll(rT.locationCb(animalList, "lost"), rT.dateRange(animalList, "lost"), rT.searchSave("date"));

		return lostLocDate;
	}
	//returns Vbox containing the lost animals table sorted by gender
	public VBox foundLocation(AnimalList animalList){
		VBox foundLocation = new VBox(CustomItems.label("Found By Location"), rT.animalTable(animalList));
		rT.foundAnimals(animalList);
		foundLocation.getChildren().addAll(rT.locationCb(animalList, "found"), rT.searchSave("location"));

		return foundLocation;
	}
	//returns Vbox containing the lost animals table sorted by gender
	public VBox foundLocationDate(AnimalList animalList){
		VBox lostLocDate = new VBox(CustomItems.label("Found By Location And Date"), rT.animalTable(animalList));
		rT.foundAnimals(animalList);
		lostLocDate.getChildren().addAll(rT.locationCb(animalList, "found"), rT.dateRange(animalList, "found"), rT.searchSave("date"));

		return lostLocDate;
	}
	//returns Vbox containing the lost animals table sorted by gender
	public VBox foundDateGender(AnimalList animalList){
		VBox lostLocDate = new VBox(CustomItems.label("Found By Date sorted by gender"), rT.animalTable(animalList));
		rT.foundAnimals(animalList);
		lostLocDate.getChildren().addAll(rT.dateRange(animalList, "found"), rT.searchSave("date"));
		rT.sortByGender();

		return lostLocDate;
	}
	//returns Vbox containing the Adoption animals that are status ready
	public VBox readyAdoption(AnimalList animalList){
		VBox readyAdoption = new VBox(CustomItems.label("All Animals Ready For Adoption"), rT.animalAdoptionTable(animalList));
		rT.readyAdoptionAnimals(animalList);
		rT.sortByName();
		return readyAdoption;
	}
	//returns Vbox containing the Adoption animals that are status ready
	public VBox readyAdoptionByAge(AnimalList animalList){
		VBox readyAdoption = new VBox(CustomItems.label("Animals In Ready For Adoption"), rT.animalAdoptionTable(animalList));
		rT.readyAdoptionAnimals(animalList);
		rT.sortByAge();
		readyAdoption.getChildren().addAll(rT.animalComboBox(animalList, "ready"), rT.searchSave("ready"));
		return readyAdoption;
	}
	//returns Vbox containing the Adoption animals that are status in training
	public VBox puppiesTrainingAdoption(AnimalList animalList){
		VBox puppiesTrainingAdoption = new VBox(CustomItems.label("Puppies In Training For Adoption"), rT.animalAdoptionTable(animalList));
		rT.puppiesInTrainingAdoptionAnimals(animalList);
		puppiesTrainingAdoption.getChildren().addAll(rT.animalComboBox(animalList, "puppies"), rT.searchSave("training"));
		return puppiesTrainingAdoption;
	}
	//returns Vbox containing the Adoption animals that are status in training
	public VBox trainingAdoption(AnimalList animalList){
		VBox trainingAdoption = new VBox(CustomItems.label("Animals In Training For Adoption"), rT.animalAdoptionTable(animalList));
		rT.inTrainingAdoptionAnimals(animalList);
		trainingAdoption.getChildren().addAll(rT.animalComboBox(animalList, "training"), rT.searchSave("training"));
		return trainingAdoption;
	}
	//returns Vbox containing the sponsors(persons)
	public VBox displaySponsors(ArrayList<Person> personList){
		VBox displaySponsors = new VBox(CustomItems.label("Sponsors List"), rT.sponsorsTable(personList));		
		displaySponsors.getChildren().addAll(rT.searchPersonFields(personList));
		return displaySponsors;
	}


}
