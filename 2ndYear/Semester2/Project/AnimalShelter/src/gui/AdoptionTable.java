package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

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
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utilites.ReadWrite;

/**
 * @author Alan Kelly R00052131
 * Created 30 Mar 2016
 *
 */
public class AdoptionTable {

	private TableView table, tableAdoption;
	private ComboBox<Integer> ageAdoption;
	private ComboBox<String> animalCbAdoption;
	private ComboBox<String> statusCb, newAnimalStatusCb;
	private ComboBox<String> breedCbAdoption;
	private ToggleGroup groupAdoption;
	private RadioButton maleAdoption;
	private RadioButton femaleAdoption;
	private TextField descriptionAdoption, nameAdoption;

	private File types = new File("type.txt");
	private File status = new File("status.txt");

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
			if(cellData.getValue().getGender())
			{
				return	new ReadOnlyStringWrapper("Male");
			}
			else
			{
				return	new ReadOnlyStringWrapper("Female");
			}
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
		table.setItems(ReadWrite.getAnimal(list));
		table.getColumns().addAll(idColumn, nameColumn, atypeColumn, genColumn, ageColumn, breedColumn, descriptionColumn, dateColumn, locationColumn, contactColumn, contactNameColumn, contactNoColumn);

		return table;
	}


	//returns tableview with containing all animals
	public TableView animalAdoptionTable(ArrayList<Animal> list)
	{
		TableColumn<Animal, String> atypeColumn = new TableColumn<>("Animal Type");
		atypeColumn.setMinWidth(50);
		atypeColumn.setCellValueFactory(new PropertyValueFactory<>("aType"));

		TableColumn<Animal, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setMinWidth(100);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Animal, String> breedColumn = new TableColumn<>("Breed");
		breedColumn.setMinWidth(80);
		breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));

		TableColumn<Animal, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(120);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Animal, Integer> ageColumn = new TableColumn<>("Age");
		ageColumn.setMinWidth(50);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Animal, Integer > idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Animal, String> genColumn = new TableColumn<>("Gender");
		genColumn.setMinWidth(50);
		genColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getGender())
			{
				return	new ReadOnlyStringWrapper("Male");
			}
			else
			{
				return	new ReadOnlyStringWrapper("Female");
			}
		});

		TableColumn<Animal, String> adoptionColumn = new TableColumn<>("Adoption Status");
		adoptionColumn.setMinWidth(50);
		adoptionColumn.setCellValueFactory(cellData -> {
			String status;
			if(cellData.getValue().getAdoption() instanceof Adoption)
			{
				status = cellData.getValue().getAdoption().getStatus();
			}
			else
			{
				status = "N/A";
			}

			return	new ReadOnlyStringWrapper(status);
		});

		TableColumn<Animal, String> originalCatColumn = new TableColumn<>("Lost/Found Status");
		originalCatColumn.setMinWidth(50);
		originalCatColumn.setCellValueFactory(cellData -> {
			String category;
			if(cellData.getValue().getAnimalCat() == null)
			{
				category = "N/A";
			}
			else if(cellData.getValue().getAnimalCat() instanceof Lost)
			{
				category = "Lost";
			}
			else
			{
				category = "Found";
			}

			return	new ReadOnlyStringWrapper(category);
		});

		TableColumn<Animal, String> picColumn = new TableColumn<>("Picture");
		picColumn.setMinWidth(20);
		picColumn.setCellValueFactory(cellData -> {
			String category;
			if(cellData.getValue().getPicture() != null)
			{
				category = "Yes";
			}
			else
			{
				category = "No";
			}

			return	new ReadOnlyStringWrapper(category);
		});

		TableColumn<Animal, String> chippedColumn = new TableColumn<>("Chipped");
		chippedColumn.setMinWidth(20);
		chippedColumn.setCellValueFactory(cellData -> {
			String chipped;
			if(cellData.getValue().getAdoption() instanceof Adoption){
				if(cellData.getValue().getAdoption().getChipped()){
					chipped = "Yes";
				}
				else
				{
					chipped = "No";
				}
			}
			else{
				chipped = "N/A";
			}

			return	new ReadOnlyStringWrapper(chipped);
		});

		TableColumn<Animal, String> vaccinatedColumn = new TableColumn<>("Vaccinated");
		vaccinatedColumn.setMinWidth(20);
		vaccinatedColumn.setCellValueFactory(cellData -> {
			String vaccinated;
			if(cellData.getValue().getAdoption() instanceof Adoption){
				if(cellData.getValue().getAdoption().getVaccinated()){
					vaccinated = "Yes";
				}
				else
				{
					vaccinated = "No";
				}
			}
			else{
				vaccinated = "N/A";
			}

			return	new ReadOnlyStringWrapper(vaccinated);
		});

		TableColumn<Animal, String> neuteredColumn = new TableColumn<>("Neutered");
		neuteredColumn.setMinWidth(20);
		neuteredColumn.setCellValueFactory(cellData -> {
			String neutered;
			if(cellData.getValue().getAdoption() instanceof Adoption){
				if(cellData.getValue().getAdoption().getNeutered()){
					neutered = "Yes";
				}
				else
				{
					neutered = "No";
				}
			}
			else{
				neutered = "N/A";
			}

			return	new ReadOnlyStringWrapper(neutered);
		});

		TableColumn<Animal, String> reservedColumn = new TableColumn<>("Reserved");
		reservedColumn.setMinWidth(20);
		reservedColumn.setCellValueFactory(cellData -> {
			String reserved;
			if(cellData.getValue().getAdoption() instanceof Adoption){
				if(cellData.getValue().getAdoption().getReserved()){
					reserved = "Yes";
				}
				else
				{
					reserved = "No";
				}
			}
			else{
				reserved = "N/A";
			}

			return	new ReadOnlyStringWrapper(reserved);
		});

		TableColumn<Animal, String> reserverNameColumn = new TableColumn<>("Reserver Name");
		reserverNameColumn.setMinWidth(20);
		reserverNameColumn.setCellValueFactory(cellData -> {
			String reserved;
			if(cellData.getValue().getAdoption() instanceof Adoption){
				if(cellData.getValue().getAdoption().getContact() != null){
					reserved = String.valueOf(cellData.getValue().getAdoption().getContact().getName());			
				}
				else{
					reserved = "N/A";
				}
			}
			else{
				reserved = "N/A";
			}

			return	new ReadOnlyStringWrapper(reserved);
		});

		TableColumn<Animal, String> reserverNoColumn = new TableColumn<>("Reserver PhoneNo");
		reserverNoColumn.setMinWidth(20);
		reserverNoColumn.setCellValueFactory(cellData -> {
			String reserved;
			if(cellData.getValue().getAdoption() instanceof Adoption){
				if(cellData.getValue().getAdoption().getContact() != null){
					reserved = String.valueOf(cellData.getValue().getAdoption().getContact().getPhoneNumber());
				}
				else{
					reserved = "N/A";
				}
			}
			else{
				reserved = "N/A";
			}

			return	new ReadOnlyStringWrapper(reserved);
		});

		tableAdoption = new TableView<Animal>();
		tableAdoption.setItems(ReadWrite.getAnimal(list));
		tableAdoption.getColumns().addAll(idColumn, nameColumn, atypeColumn, genColumn, ageColumn, breedColumn, descriptionColumn, originalCatColumn, adoptionColumn, picColumn, neuteredColumn, vaccinatedColumn, chippedColumn, reservedColumn, reserverNameColumn, reserverNoColumn);

		return tableAdoption;
	}

	public GridPane textFieldsAdoption() {

		Label mandatoryField = new Label("* MANDATORY FIELD");
		mandatoryField.setFont(new Font("Arial", 12));
		mandatoryField.setTextFill(Color.RED);

		nameAdoption = new TextField();
		nameAdoption.setPromptText("Name");
		nameAdoption.setMinWidth(75);

		ageAdoption = new ComboBox<Integer>();
		ageAdoption.getItems().addAll(
				1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
				);
		ageAdoption.setMinWidth(150);
		descriptionAdoption = new TextField();
		descriptionAdoption.setPromptText("description");
		descriptionAdoption.setMinWidth(150);

		breedCbAdoption = new ComboBox<String>();
		breedCbAdoption.setMinWidth(150);
		breedCbAdoption.setDisable(true);

		animalCbAdoption = new ComboBox<String>();
		animalCbAdoption.setMinWidth(150);
		animalCbAdoption.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String t, String t1) {
				if(!animalCbAdoption.getValue().equals(null)){
					breedCbAdoption.getItems().clear();
					breedCbAdoption.setDisable(false);
					try {
						breedCbAdoption.getItems().addAll(ReadWrite.readToCombo(new File(animalCbAdoption.getValue() + ".txt")));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}  
			}
		});

		groupAdoption  = new ToggleGroup();
		maleAdoption = new RadioButton();
		maleAdoption = new RadioButton("Male");
		maleAdoption.setToggleGroup(groupAdoption);
		maleAdoption.setSelected(true);

		femaleAdoption = new RadioButton();
		femaleAdoption = new RadioButton("Female");
		femaleAdoption.setToggleGroup(groupAdoption);

		newAnimalStatusCb = new ComboBox<String>();
		newAnimalStatusCb.setMinWidth(150);

		try {
			newAnimalStatusCb.getItems().addAll(ReadWrite.readToCombo(status));
			animalCbAdoption.getItems().addAll(ReadWrite.readToCombo(types));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		GridPane grid = new GridPane();
		grid.centerShapeProperty();
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.addRow(0, CustomItems.label("Enter Animals Details"));
		grid.add(mandatoryField, 6, 0);
		grid.addRow(1, new Label("Name"), new Label("Age"), new Label("Animal Type *"), new Label("Animal Breed *"), new Label("Animal Description *"), new Label("Status*"), new Label("Animal Gender *"));
		grid.addRow(2, nameAdoption, ageAdoption, animalCbAdoption, breedCbAdoption, descriptionAdoption, newAnimalStatusCb, maleAdoption, femaleAdoption);

		return grid;
	}

	public VBox addStoredAnimal(AnimalList animalList){

		Button addStoredAnimal = new Button("Add Selected Animal");
		addStoredAnimal.setOnAction(e -> addButtonClickedStoredAnimal(animalList));

		statusCb = new ComboBox<String>();
		statusCb.setMinWidth(150);
		try {
			statusCb.getItems().addAll(ReadWrite.readToCombo(status));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VBox vBox = new VBox(new Label("To add a Lost/Found Animal to the Adoption List please Selected the animal and choose its status"), statusCb, addStoredAnimal);

		return vBox;
	}

	public void addButtonClickedStoredAnimal(AnimalList animalList){

		if(table.getSelectionModel().getSelectedItems().isEmpty()){
			AlertBox.warn("No Animal Selected", "Please Select an Animal from the All Animals Table");
		}
		else{
			ObservableList<Animal> animalSelected, adoptionAnimals, allAnimals;
			adoptionAnimals = tableAdoption.getItems();
			allAnimals = tableAdoption.getItems();

			ArrayList arrayAll = new ArrayList(allAnimals);
			animalSelected = table.getSelectionModel().getSelectedItems();

			ArrayList<Animal> an = new ArrayList<Animal>(animalSelected);
			Animal animal = new Animal();
			animal = an.get(0);

			Boolean b = false;
			for(int i = 0; i < adoptionAnimals.size(); i++){
				if(animalSelected.get(0).equals(adoptionAnimals.get(i)))
				{
					b = true;
				}
			}
			if(b == true){
				AlertBox.warn("Duplicate Animal", "Animal Already in Adoption");
			}
			else{
				if(statusCb.getValue() == null){
					AlertBox.error("No Status", "An Adoption Animal must have a Status"
							+ "Please choose A status");
				}
				else{

					Category adop = new Adoption(new DatePicker().getValue().now());
					((Adoption) adop).setStatus(statusCb.getValue());

					animal.setAdoption(adop);
					tableAdoption.getItems().add(animal);
					statusCb.setValue(null);
				}
			}
		}
		table.getSelectionModel().clearSelection();
		tableAdoption.getSelectionModel().clearSelection();
		AlertBox.confirm("Completed", "Animal Succesfully Added to Adoption");

	}

	public HBox addRemoveAdoption(AnimalList animalList, PersonList personList) {

		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClickedAdoption(animalList, personList));
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked(animalList));

		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10,10,10,10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(addButton, deleteButton);

		return hBox2;
	}

	public void addButtonClickedAdoption(AnimalList animalList, PersonList personList){
		//tests mandatory fields are not empty
		if(animalCbAdoption.getValue() == null || descriptionAdoption.getText().isEmpty() || breedCbAdoption.getValue() == null)
		{
			AlertBox.warn("Some Animal Info Missing", "Please complete all animal mandatory fields");
		}
		else{
			boolean b = false;
			if(groupAdoption.getSelectedToggle() == maleAdoption)
			{
				b = true;
			}
			if(ageAdoption.getValue() == null){
				ageAdoption.setValue(0);
			}
			//Creates an animal, person and catergory from data in text fields and links them together
			Animal animal = new Animal(ageAdoption.getValue(), animalCbAdoption.getValue(), b, breedCbAdoption.getValue(), descriptionAdoption.getText());
			//Person person = new Person(personNameAdoption.getText(),personAddressAdoption.getText(),personEmailAdoption.getText(), personNoAdoption.getText());
			Category adoption = new Adoption(new DatePicker().getValue().now());
			((Adoption) adoption).setStatus(newAnimalStatusCb.getValue());
			if(nameAdoption.getText() != null){
				animal.setName(nameAdoption.getText());
			}
			else{
				animal.setName("UNKNOWN");
			}
			animal.setAdoption(adoption);
			animalList.add(animal);

			//returns textfields to default setting.
			nameAdoption.clear();
			ageAdoption.setValue(null);
			animalCbAdoption.setValue("Dog");
			breedCbAdoption.setValue(null);
			descriptionAdoption.clear();

			tableAdoption.getItems().add(animal);
		}

	}

	//Delete button clicked
	public void deleteButtonClicked(AnimalList animalList){
		if(tableAdoption.getSelectionModel().getSelectedItems().isEmpty()){
			AlertBox.warn("No Animal Selected", "Please Select an Adoption Animals from the All Animals Table");
		}
		else{
			//creating ObservableLists containing all animals and selected animals
			ObservableList<Animal> animalSelected, allAnimals;
			allAnimals = tableAdoption.getItems();

			animalSelected = tableAdoption.getSelectionModel().getSelectedItems();
			//creating a new animal from the animal that has been selected for deletion
			ArrayList<Animal> an = new ArrayList<Animal>(animalSelected);
			Animal animal = new Animal();
			animal = an.get(0);
			//removing the selected animal from the tableview
			animalSelected.forEach(allAnimals::remove);
			//removing the animal removed from the tableview from the animalList
			animalList.remove(animal);
			tableAdoption.getSelectionModel().clearSelection();
		}
	}

	public VBox adoptAnimal(AnimalList animalList, PersonList personList){

		VBox hBox = new VBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(textFieldsAdoption(), addRemoveAdoption(animalList, personList), button(personList));

		return hBox;
	}

	public Button button(PersonList personList)
	{
		Button editAnimal = new Button("Edit Selected Animal");
		editAnimal.setOnAction(e -> buttonAction(personList));
		return editAnimal;
	}


	public void buttonAction(PersonList personList){
		if(tableAdoption.getSelectionModel().getSelectedItem() != null) 
		{    
			EditAnimalWindow edit = new EditAnimalWindow();
			ObservableList<Animal> array = tableAdoption.getSelectionModel().getSelectedItems();
			Animal animal = array.get(0);

			edit.display(animal, personList, tableAdoption);
		}
	}
	//searchs for animals that have been in found for more than a month and adds them to the adoption section
	public void addlastMonthFoundAnimals(AnimalList animalList){
		for(int i =0; i < animalList.getFoundList().size(); i++){
			if(animalList.getFoundList().get(i).getAnimalCat().getDate().plusMonths(1).isBefore(LocalDate.now()) && animalList.getFoundList().get(i).getAdoption() == null){
				Adoption adoption = new Adoption(LocalDate.now());
				adoption.setStatus("in training");
				animalList.getFoundList().get(i).setAdoption(adoption);				
			}
		}
	}

}
