package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Adoption;
import classes.Animal;
import classes.AnimalList;
import classes.Category;
import classes.Found;
import classes.Lost;
import classes.Person;
import classes.PersonList;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilites.ReadWrite;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class LostTable {

	private TableView table;
	private DatePicker dateLost;
	private ComboBox<String> animalCbLost, locationCbLost, breedCbLost;
	private ComboBox<Integer> ageLost;
	private ToggleGroup groupLost;
	private RadioButton maleLost, femaleLost;
	private TextField descriptionLost, nameLost, personNameLost, personNoLost, personAddressLost, personEmailLost;

	private final File types = new File("type.txt");
	private final File location = new File("location.txt");

	//returns tableview with containing all animals
	public TableView animalTable(ArrayList<Animal> list)
	{
		TableColumn<Animal, String> atypeColumn = new TableColumn<>("Animal Type");
		atypeColumn.setMinWidth(50);
		atypeColumn.setCellValueFactory(new PropertyValueFactory<>("aType"));

		TableColumn<Animal, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setMinWidth(150);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Animal, String> breedColumn = new TableColumn<>("Breed");
		breedColumn.setMinWidth(80);
		breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));

		TableColumn<Animal, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(80);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Animal, Integer > ageColumn = new TableColumn<>("Age");
		ageColumn.setMinWidth(50);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Animal, Integer > idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Animal, String > genColumn = new TableColumn<>("Gender");
		genColumn.setMinWidth(50);
		genColumn.setCellValueFactory(cellData -> {
			boolean gender = cellData.getValue().getGender();
			String genderAsString;
			if(gender == true)
			{
				genderAsString = "Male";
			}
			else
			{
				genderAsString = "Female";
			}

			return	new ReadOnlyStringWrapper(genderAsString);
		});

		TableColumn<Animal, String> dateColumn = new TableColumn<>("Date Lost");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(cellData -> {
			String date;
			date = cellData.getValue().getAnimalCat().getDate().toString();

			return	new ReadOnlyStringWrapper(date);
		});

		TableColumn<Animal, String> locationColumn = new TableColumn<>("Location Lost");
		locationColumn.setMinWidth(100);
		locationColumn.setCellValueFactory(cellData -> {
			return	new ReadOnlyStringWrapper(((Lost) cellData.getValue().getAnimalCat()).getLocation());
		});

		TableColumn<Animal, String > ownerIDColumn = new TableColumn<>("Owner ID");
		ownerIDColumn.setMinWidth(50);
		ownerIDColumn.setCellValueFactory(cellData -> {
			return	new ReadOnlyStringWrapper(String.valueOf(((Lost) cellData.getValue().getAnimalCat()).getContact().getID()));
		});

		TableColumn<Animal, String > ownerNameColumn = new TableColumn<>("Owner's Name");
		ownerNameColumn.setMinWidth(100);
		ownerNameColumn.setCellValueFactory(cellData -> {
			return	new ReadOnlyStringWrapper(String.valueOf(((Lost) cellData.getValue().getAnimalCat()).getContact().getName()));
		});

		TableColumn<Animal, String > ownerNoColumn = new TableColumn<>("Owner's Phone#");
		ownerNoColumn.setMinWidth(100);
		ownerNoColumn.setCellValueFactory(cellData -> {
			return	new ReadOnlyStringWrapper(String.valueOf(((Lost) cellData.getValue().getAnimalCat()).getContact().getPhoneNumber()));
		});

		table = new TableView<Animal>();
		table.setItems(getAnimal(list));
		table.getColumns().addAll(idColumn, nameColumn, atypeColumn, genColumn, ageColumn, breedColumn, descriptionColumn, dateColumn, locationColumn, ownerIDColumn, ownerNameColumn, ownerNoColumn);

		return table;
	}

	//adds list to an ObservableList for use with tableview
	//ObservableList not defined as its used for animals table and persons table
	public ObservableList<?> getAnimal(ArrayList list){
		ObservableList obList = FXCollections.observableArrayList();

		for(int i = 0; i < list.size(); i++){
			obList.add(list.get(i));
		}
		return obList;
	}

	public GridPane textFieldsLost() {

		Label mandatoryField = new Label("* MANDATORY FIELD");
		mandatoryField.setFont(new Font("Arial", 12));
		mandatoryField.setTextFill(Color.RED);

		nameLost = new TextField();
		nameLost.setPromptText("Name");
		nameLost.setMinWidth(75);

		ageLost = new ComboBox<Integer>();
		ageLost.getItems().addAll(
				1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
				);
		ageLost.setMinWidth(150);
		descriptionLost = new TextField();
		descriptionLost.setPromptText("description");
		descriptionLost.setMinWidth(150);

		breedCbLost = new ComboBox<String>();
		breedCbLost.setMinWidth(150);
		breedCbLost.setDisable(true);

		animalCbLost = new ComboBox<String>();
		animalCbLost.setMinWidth(150);
		animalCbLost.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String t, String t1) {
				if(!animalCbLost.getValue().equals(null) || animalCbLost.getValue().isEmpty()){
					breedCbLost.getItems().clear();
					breedCbLost.setDisable(false);
					try {
						breedCbLost.getItems().addAll(ReadWrite.readToCombo(new File(animalCbLost.getValue() + ".txt")));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}  
			}
		});

		locationCbLost = new ComboBox<String>();
		locationCbLost.setMinWidth(150);

		groupLost  = new ToggleGroup();
		maleLost = new RadioButton();
		maleLost = new RadioButton("Male");
		maleLost.setToggleGroup(groupLost);
		maleLost.setSelected(true);

		femaleLost = new RadioButton();
		femaleLost = new RadioButton("Female");
		femaleLost.setToggleGroup(groupLost);

		try {
			animalCbLost.getItems().addAll(readToCombo(types));
			locationCbLost.getItems().addAll(readToCombo(location));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dateLost = new DatePicker();

		GridPane grid = new GridPane();
		grid.centerShapeProperty();
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.addRow(0, CustomItems.label("Enter Animals Details"));
		grid.add(mandatoryField, 6, 0);
		grid.addRow(1, new Label("Name"), new Label("Age"), new Label("Animal Type *"), new Label("Animal Breed *"), new Label("Animal Description *"), new Label("Animal Gender *"));
		grid.addRow(2, nameLost, ageLost, animalCbLost, breedCbLost, descriptionLost, maleLost, femaleLost);
		grid.addRow(3, CustomItems.label("Choose Date Found & Location"));
		grid.addRow(4, new Label("Choose Date  *"),new Label("Enter Location *"));
		grid.addRow(5, dateLost, locationCbLost);

		return grid;
	}

	public HBox addRemoveLost(AnimalList animalList, PersonList personList) {

		Button addButton = new Button("Add");
		addButton.setOnAction(e -> {

			addButtonClickedLost(animalList, personList);});

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked(animalList));

		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10,10,10,10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(addButton, deleteButton);

		return hBox2;
	}

	//tests all fields are not empty, Creates animal, person and catergory from data in text fields.
	public void addButtonClickedLost(AnimalList animalList, PersonList personList){
		//tests mandatory fields are not empty
		//	System.out.println(animalCbLost.getValue() + descriptionLost.getText() + breedCbLost.getValue() + dateLost.getValue() + locationCbLost.getValue());
		if(animalCbLost.getValue() == null || descriptionLost.getText().isEmpty() || breedCbLost.getValue() == null)
		{
			AlertBox.warn("Some Animal Info Missing", "Please complete all animal mandatory fields");
		}
		else if(personNameLost.getText().isEmpty() || personAddressLost.getText().isEmpty() || personEmailLost.getText().isEmpty() || personNoLost.getText().isEmpty())
		{
			AlertBox.warn("Some Person Info Missing", "Please complete all Person mandatory fields");
		}
		else if(dateLost.getValue() == null)
		{
			AlertBox.warn("incorrect date", "Please Enter the correct date");
		}
		else if(dateLost.getValue().isAfter(LocalDate.now()))
		{
			AlertBox.warn("incorrect date", "The Lost date can not be in the future");
		}
		else{
			boolean b = false;
			if(groupLost.getSelectedToggle() == maleLost)
			{
				b = true;
			}
			if(ageLost.getValue() == null){
				ageLost.setValue(0);
			}
			//Creates an animal, person and catergory from data in text fields and links them together
			Animal animal = new Animal(ageLost.getValue(), animalCbLost.getValue(), b, breedCbLost.getValue(), descriptionLost.getText());
			//System.out.println(animal.getBreed());
			Person person = new Person(personNameLost.getText(),personAddressLost.getText(),personEmailLost.getText(), personNoLost.getText());
			//System.out.println(person.getName());
			Category lost = new Lost(dateLost.getValue(), locationCbLost.getValue(), person);
			//System.out.println(lost.getDate());
			if(nameLost.getText() != null){
				animal.setName(nameLost.getText());
			}
			animal.setAnimalCat(lost);
			animalList.add(animal);
			personList.add(person);
			//adds animal to the table
			table.getItems().add(animal);
			//returns textfields to default setting.
			nameLost.clear();
			ageLost.setValue(null);
			animalCbLost.setValue("Dog");
			descriptionLost.clear();

			personNameLost.clear();
			personAddressLost.clear();
			personEmailLost.clear();
			personNoLost.clear();

			locationCbLost.setValue(null);
			dateLost.setValue(null);
			animalList.printAnimalList();
		}
	}

	//Delete button clicked
	public void deleteButtonClicked(AnimalList animalList){
		if(table.getSelectionModel().getSelectedItems().isEmpty()){
			AlertBox.warn("No Animal Selected", "Please Select an Animal from the All Animals Table");
		}
		else{
			//creating ObservableLists containing all animals and selected animals
			ObservableList<Animal> animalSelected, allAnimals;
			allAnimals = table.getItems();
			//used for testing
			animalSelected = table.getSelectionModel().getSelectedItems();
			//creating a new animal from the animal that has been selected for deletion
			ArrayList<Animal> an = new ArrayList<Animal>(animalSelected);
			Animal animal = new Animal();
			animal = an.get(0);
			//removing the selected animal from the tableview
			animalSelected.forEach(allAnimals::remove);
			//removing the animal removed from the tableview from the animalList
			animalList.remove(animal);
		}
	}

	//populates the dropdown boxes from files
	public ArrayList<String> readToCombo(File file) throws FileNotFoundException
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

	public GridPane personFieldsLost(){

		personNameLost = new TextField();
		personNameLost.setPromptText("Name");
		personNameLost.setMinWidth(75);

		personAddressLost = new TextField();
		personAddressLost.setPromptText("Address");
		personAddressLost.setMinWidth(75);

		personNoLost  = new TextField();
		personNoLost.setPromptText("Phone Number");
		personNoLost.setMinWidth(75);

		personEmailLost  = new TextField();
		personEmailLost.setPromptText("Email");
		personEmailLost.setMinWidth(75);

		GridPane grid = new GridPane();
		grid.centerShapeProperty();
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.addRow(0, CustomItems.label("Enter Owners Details"));
		grid.addRow(1, new Label("Person Name *"), new Label(" Address *"), new Label("Phone Number *"), new Label("Email *"));
		grid.addRow(2, personNameLost, personAddressLost, personNoLost, personEmailLost);

		return grid;
	}

	public VBox lostAnimal(AnimalList animalList, PersonList personList, String string){

		VBox hBox = new VBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(textFieldsLost(), personFieldsLost(), addRemoveLost(animalList, personList));

		return hBox;
	}

}

