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
 * Created 2 Apr 2016
 */
public class FoundTable {

	private TableView table;
	private DatePicker dateFound;
	private ComboBox<String> animalCbFound, locationCbFound, breedCbFound;
	private ComboBox<Integer> ageFound;
	private ToggleGroup groupFound;
	private RadioButton maleFound, femaleFound;
	private TextField descriptionFound, nameFound, personNameFound, personNoFound, personAddressFound, personEmailFound;

	private File types = new File("type.txt");
	private File location = new File("location.txt");

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

		TableColumn<Animal, String> dateColumn = new TableColumn<>("Date Found");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(cellData -> {
			String date;
			date = cellData.getValue().getAnimalCat().getDate().toString();

			return	new ReadOnlyStringWrapper(date);
		});
		
		TableColumn<Animal, String> locationColumn = new TableColumn<>("Location Found");
		locationColumn.setMinWidth(100);
		locationColumn.setCellValueFactory(cellData -> {
			return	new ReadOnlyStringWrapper(((Found) cellData.getValue().getAnimalCat()).getLocation());
		});
		
		TableColumn<Animal, String > contactColumn = new TableColumn<>("Contact ID");
		contactColumn.setMinWidth(50);
		contactColumn.setCellValueFactory(cellData -> {
			return	new ReadOnlyStringWrapper(String.valueOf(((Found) cellData.getValue().getAnimalCat()).getContact().getID()));
		});
		
		TableColumn<Animal, String > contactNameColumn = new TableColumn<>("Contact's Name");
		contactNameColumn.setMinWidth(100);
		contactNameColumn.setCellValueFactory(cellData -> {
			return	new ReadOnlyStringWrapper(String.valueOf(((Found) cellData.getValue().getAnimalCat()).getContact().getName()));
		});
		
		TableColumn<Animal, String > contactNoColumn = new TableColumn<>("Contact's Phone#");
		contactNoColumn.setMinWidth(100);
		contactNoColumn.setCellValueFactory(cellData -> {
			return	new ReadOnlyStringWrapper(String.valueOf(((Found) cellData.getValue().getAnimalCat()).getContact().getPhoneNumber()));
		});

		
		table = new TableView<Animal>();
		table.setItems(getAnimal(list));
		table.getColumns().addAll(idColumn, nameColumn, atypeColumn, genColumn, ageColumn, breedColumn, descriptionColumn, dateColumn, locationColumn, contactColumn, contactNameColumn, contactNoColumn);

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

	public GridPane textFieldsFound() {

		Label mandatoryField = new Label("* MANDATORY FIELD");
		mandatoryField.setFont(new Font("Arial", 12));
		mandatoryField.setTextFill(Color.RED);

		nameFound = new TextField();
		nameFound.setPromptText("Name");
		nameFound.setMinWidth(75);

		ageFound = new ComboBox<Integer>();
		ageFound.getItems().addAll(
				1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
				);
		ageFound.setMinWidth(150);
		descriptionFound = new TextField();
		descriptionFound.setPromptText("description");
		descriptionFound.setMinWidth(150);

		breedCbFound = new ComboBox<String>();
		breedCbFound.setMinWidth(150);
		breedCbFound.setDisable(true);

		animalCbFound = new ComboBox<String>();
		animalCbFound.setMinWidth(150);
		animalCbFound.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String t, String t1) {
				if(!animalCbFound.getValue().equals(null)){
					breedCbFound.getItems().clear();
					breedCbFound.setDisable(false);
					try {
						breedCbFound.getItems().addAll(ReadWrite.readToCombo(new File(animalCbFound.getValue() + ".txt")));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}  
			}
		});

		locationCbFound = new ComboBox<String>();
		locationCbFound.setMinWidth(150);

		groupFound  = new ToggleGroup();
		maleFound = new RadioButton();
		maleFound = new RadioButton("Male");
		maleFound.setToggleGroup(groupFound);
		maleFound.setSelected(true);

		femaleFound = new RadioButton();
		femaleFound = new RadioButton("Female");
		femaleFound.setToggleGroup(groupFound);

		try {
			animalCbFound.getItems().addAll(readToCombo(types));
			locationCbFound.getItems().addAll(readToCombo(location));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dateFound = new DatePicker();

		GridPane grid = new GridPane();
		grid.centerShapeProperty();
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.addRow(0, CustomItems.label("Enter Animals Details"));
		grid.add(mandatoryField, 6, 0);
		grid.addRow(1, new Label("Name"), new Label("Age"), new Label("Animal Type *"), new Label("Animal Breed *"), new Label("Animal Description *"), new Label("Animal Gender *"));
		grid.addRow(2, nameFound, ageFound, animalCbFound, breedCbFound, descriptionFound, maleFound, femaleFound);
		grid.addRow(3, CustomItems.label("Choose Date Found & Location"));
		grid.addRow(4, new Label("Choose Date  *"),new Label("Enter Location *"));
		grid.addRow(5, dateFound, locationCbFound);

		return grid;
	}

	public HBox addRemoveFound(AnimalList animalList, PersonList personList) {

		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClickedFound(animalList, personList));
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked(animalList));

		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10,10,10,10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(addButton, deleteButton);

		return hBox2;
	}

	public void addButtonClickedFound(AnimalList animalList, PersonList personList){
		//tests all fields are not empty
		if(animalCbFound.getValue() == null || descriptionFound.getText().isEmpty() || breedCbFound.getValue() == null)
		{
			AlertBox.warn("Animal Info Missing", "Please complete all animal mandatory fields");
		}
		else if(personNameFound.getText().isEmpty() || personAddressFound.getText().isEmpty() || personEmailFound.getText().isEmpty() || personNoFound.getText().isEmpty())
		{
			AlertBox.warn("Person Info Missing", "Please complete all Person fields");
		}
		else if(dateFound.getValue() == null)
		{
			AlertBox.warn("incorrect date", "Please Enter the correct date");
		}
		else if(dateFound.getValue().isAfter(LocalDate.now()))
		{
			AlertBox.warn("incorrect date", "The Lost date can not be in the future");
		}
		else{
			boolean b = false;
			//System.out.println(groupFound.getSelectedToggle());
			if(groupFound.getSelectedToggle() == maleFound)
			{
				b = true;
			}
			//setting age to 0 as a found dog age would not be known
			if(ageFound.getValue() == null){
				ageFound.setValue(0);
			}
			//Creates an animal, person and catergory from data in text fields and links them together
			Animal animal = new Animal(ageFound.getValue(), animalCbFound.getValue(), b, breedCbFound.getValue(), descriptionFound.getText());
			if(nameFound.getText() != null){
				animal.setName(nameFound.getText());
			}
			else{
				animal.setName("UNKNOWN");
			}
			Person person = new Person(personNameFound.getText(),personAddressFound.getText(),personEmailFound.getText(), personNoFound.getText());
			Category found = new Found(dateFound.getValue(), locationCbFound.getValue(), person);

			animal.setAnimalCat(found);
			animalList.add(animal);
			personList.add(person);
			table.getItems().add(animal);

			nameFound.clear();
			ageFound.setValue(null);
			animalCbFound.setValue("Dog");
			descriptionFound.clear();

			personNameFound.clear();
			personAddressFound.clear();
			personEmailFound.clear();
			personNoFound.clear();

			locationCbFound.setValue(null);
			dateFound.setValue(null);
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
			//System.out.println(table.getSelectionModel().getSelectedItems());
			animalSelected = table.getSelectionModel().getSelectedItems();
			//System.out.println(animalSelected);
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

	public GridPane personFieldsFound(){

		personNameFound = new TextField();
		personNameFound.setPromptText("Name");
		personNameFound.setMinWidth(75);

		personAddressFound = new TextField();
		personAddressFound.setPromptText("Address");
		personAddressFound.setMinWidth(75);

		personNoFound = new TextField();
		personNoFound.setPromptText("Phone Number");
		personNoFound.setMinWidth(75);

		personEmailFound  = new TextField();
		personEmailFound.setPromptText("Email");
		personEmailFound.setMinWidth(75);

		GridPane grid = new GridPane();
		grid.centerShapeProperty();
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.addRow(0, CustomItems.label("Enter Persons Details"));
		grid.addRow(1, new Label("Person Name *"), new Label(" Address *"), new Label("Phone Number *"), new Label("Email *"));
		grid.addRow(2, personNameFound, personAddressFound, personNoFound, personEmailFound);

		return grid;
	}

	public VBox foundAnimal(AnimalList animalList, PersonList personList, String string){

		VBox hBox = new VBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(textFieldsFound(), personFieldsFound(), addRemoveFound(animalList, personList));

		return hBox;
	}


}

