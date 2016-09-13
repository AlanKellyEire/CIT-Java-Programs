package gui;
/**
 * @author Alan Kelly R00052131
 * Created 13 Apr 2016
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import classes.Adoption;
import classes.Animal;
import classes.AnimalList;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import utilites.ReadWrite;

public class ReportTables {

	private TableView table;
	private TextField personName;
	private TextField personAddress;
	private TextField personNo;
	private TextField personEmail;
	private TextField personID;
	private TextField name;
	private ComboBox<String> age;
	private TextField description;
	private ComboBox<String> breedCb;
	private ComboBox<String> animalCb;
	private ComboBox<String> genderCb;
	private ComboBox<String> statusCb;
	private ComboBox<String> locationCb = new ComboBox<String>();
	private DatePicker from, to;
	private TableColumn<Animal, String > genColumn, nameColumn;
	private TableColumn<Animal, Integer > ageColumn;

	//returns tableview with containing all animals
	public TableView animalTable(AnimalList list)
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

		TableColumn<Animal, String > nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(120);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Animal, Integer > ageColumn = new TableColumn<>("Age");
		ageColumn.setMinWidth(50);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Animal, Integer > idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		genColumn = new TableColumn<>("Gender");
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

		TableColumn<Animal, String> dateColumn = new TableColumn<>("Date Lost/Found");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getAnimalCat() != null){
			return	new ReadOnlyStringWrapper(cellData.getValue().getAnimalCat().getDate().toString());
			}
			else{
			return	new ReadOnlyStringWrapper("N/A");
			}
		});
		
		TableColumn<Animal, String> adoptionColumn = new TableColumn<>("Adoption Status");
		adoptionColumn.setMinWidth(50);
		adoptionColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getAdoption() instanceof Adoption)
			{
				return	new ReadOnlyStringWrapper(cellData.getValue().getAdoption().getStatus());
			}
			else
			{
				return	new ReadOnlyStringWrapper("N/A");
			}
		});

		TableColumn<Animal, String> originalCatColumn = new TableColumn<>("Category");
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

		TableColumn<Animal, String> contactIDColumn = new TableColumn<>("Contact's ID");
		contactIDColumn.setMinWidth(20);
		contactIDColumn.setCellValueFactory(cellData -> {
			String contact = "N/A";
			if(cellData.getValue().getAnimalCat() instanceof Lost){
				return	new ReadOnlyStringWrapper(String.valueOf(((Lost) cellData.getValue().getAnimalCat()).getContact().getID()));
			}
			else if(cellData.getValue().getAnimalCat() instanceof Found){
				return	new ReadOnlyStringWrapper(String.valueOf(((Found) cellData.getValue().getAnimalCat()).getContact().getID()));
			}

			return	new ReadOnlyStringWrapper(contact);
		});

		TableColumn<Animal, String > ownerNameColumn = new TableColumn<>("Contacts's Name");
		ownerNameColumn.setMinWidth(100);
		ownerNameColumn.setCellValueFactory(cellData -> {
			String contact = "N/A";
			if(cellData.getValue().getAnimalCat() instanceof Lost){
				return	new ReadOnlyStringWrapper(String.valueOf(((Lost) cellData.getValue().getAnimalCat()).getContact().getName()));
			}
			else if(cellData.getValue().getAnimalCat() instanceof Found){
				return	new ReadOnlyStringWrapper(String.valueOf(((Found) cellData.getValue().getAnimalCat()).getContact().getName()));
			}

			return	new ReadOnlyStringWrapper(contact);
		});

		TableColumn<Animal, String > ownerNoColumn = new TableColumn<>("Contact's Phone#");
		ownerNoColumn.setMinWidth(100);
		ownerNoColumn.setCellValueFactory(cellData -> {
			String contact = "N/A";
			if(cellData.getValue().getAnimalCat() instanceof Lost){
				return	new ReadOnlyStringWrapper(String.valueOf(((Lost) cellData.getValue().getAnimalCat()).getContact().getPhoneNumber()));
			}
			else if(cellData.getValue().getAnimalCat() instanceof Found){
				return	new ReadOnlyStringWrapper(String.valueOf(((Found) cellData.getValue().getAnimalCat()).getContact().getPhoneNumber()));
			}

			return	new ReadOnlyStringWrapper(contact);
		});

		TableColumn<Animal, String > ownerEmailColumn = new TableColumn<>("Contact's Email");
		ownerEmailColumn.setMinWidth(100);
		ownerEmailColumn.setCellValueFactory(cellData -> {
			String contact = "N/A";
			if(cellData.getValue().getAnimalCat() instanceof Lost){
				return	new ReadOnlyStringWrapper(String.valueOf(((Lost) cellData.getValue().getAnimalCat()).getContact().getEmail()));
			}
			else if(cellData.getValue().getAnimalCat() instanceof Found){
				return	new ReadOnlyStringWrapper(String.valueOf(((Found) cellData.getValue().getAnimalCat()).getContact().getEmail()));
			}

			return	new ReadOnlyStringWrapper(contact);
		});

		locationCb.setValue("");

		table = new TableView();
		table.setItems((ObservableList<Animal>) getAnimal(list.getList()));
		table.getColumns().addAll(idColumn, nameColumn, atypeColumn, genColumn, ageColumn, breedColumn, descriptionColumn, originalCatColumn, dateColumn, adoptionColumn, picColumn, contactIDColumn, ownerNameColumn, ownerNoColumn, ownerEmailColumn);

		return table;
	}

	public TableView animalAdoptionTable(AnimalList list)
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

		nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(120);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		ageColumn = new TableColumn<>("Age");
		ageColumn.setMinWidth(50);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Animal, Integer > idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Animal, String> genColumn = new TableColumn<>("Gender");
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

		table = new TableView<Animal>();
		table.setItems((ObservableList<Animal>) getAnimal(list.getList()));
		table.getColumns().addAll(idColumn, nameColumn, atypeColumn, genColumn, ageColumn, breedColumn, descriptionColumn, originalCatColumn, adoptionColumn, picColumn, neuteredColumn, vaccinatedColumn, chippedColumn, reservedColumn, reserverNameColumn, reserverNoColumn);

		return table;
	}

	//returns table full of sponsors(persons)
	public TableView sponsorsTable(ArrayList<Person> list)
	{
		TableColumn<Person, String> addColumn = new TableColumn<>("Address");
		addColumn.setMinWidth(200);
		addColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setMinWidth(200);
		emailColumn.setSortable(false);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn<Person, String> phoneNumberColumn = new TableColumn<>("phoneNumber");
		phoneNumberColumn.setMinWidth(80);
		phoneNumberColumn.setSortable(false);
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setSortable(false);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Person, Integer > idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setSortable(false);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		table = new TableView<Person>();
		table.setItems(getAnimal(list));
		table.getColumns().addAll(idColumn, nameColumn, addColumn, emailColumn, phoneNumberColumn);

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

	public Button button(PersonList personList)
	{
		Button addStoredAnimal = new Button("Edit Selected Animal");
		addStoredAnimal.setOnAction(e -> 		buttonAction(personList)		);

		return addStoredAnimal;
	}

	public void buttonAction(PersonList personList){
		if(table.getSelectionModel().getSelectedItem() != null) 
		{    
			EditAnimalWindow edit = new EditAnimalWindow();
			ObservableList<Animal> array = table.getSelectionModel().getSelectedItems();
			Animal animal = array.get(0);

			edit.display(animal, personList, table);
		}
	}

	public HBox searchPersonFields(ArrayList<Person> list){

		HBox hbox = new HBox();
		personName = new TextField();
		personName.setPromptText("Name");
		personName.setMinWidth(75);
		personName.textProperty().addListener((observable, oldValue, newValue) -> {
			if(personName.getText().isEmpty()){
				table.setItems((ObservableList<Animal>) getAnimal(list));
			}
		});

		personAddress = new TextField();
		personAddress.setPromptText("Address");
		personAddress.setMinWidth(75);
		personAddress.textProperty().addListener((observable, oldValue, newValue) -> {
			if(personAddress.getText().isEmpty()){
				table.setItems((ObservableList<Animal>) getAnimal(list));
			}
		});

		personNo = new TextField();
		personNo.setPromptText("PhoneNumber");
		personNo.setMinWidth(75);
		personNo.textProperty().addListener((observable, oldValue, newValue) -> {
			if(personNo.getText().isEmpty()){
				table.setItems((ObservableList<Animal>) getAnimal(list));
			}
		});

		personEmail = new TextField();
		personEmail.setPromptText("Email");
		personEmail.setMinWidth(75);
		personEmail.textProperty().addListener((observable, oldValue, newValue) -> {
			if(personEmail.getText().isEmpty()){
				table.setItems((ObservableList<Animal>) getAnimal(list));
			}
		});

		personID = new TextField();
		personID.setPromptText("Person ID");
		personID.setMinWidth(75);
		personID.textProperty().addListener((observable, oldValue, newValue) -> {
			if(personID.getText().isEmpty()){
				table.setItems((ObservableList<Animal>) getAnimal(list));
			}
		});

		hbox.getChildren().addAll(personID, personName, personAddress, personEmail, personNo, searchPersonButtons());

		return hbox;
	}
	
	public Button saveButton(){
		Button save = new Button("Save Report");
		save.setOnAction(e -> directoryChooser());
		
		return save;
	}

	public HBox searchPersonButtons(){
		
		Button search = new Button("search persons");
		search.setOnAction(e -> searchPerson());

		Label lb = new Label("         ");

		return new HBox(search, lb, saveButton());
	}

	public HBox searchAnimalButtons(){
		
		Button search = new Button("search Animals");
		search.setOnAction(e -> searchAnimals());

		Label lb = new Label("         ");

		return new HBox(search, lb, saveButton());
	}

	public GridPane animalFieldsAdoption(Label listType, AnimalList animalList) {

		Label mandatoryField = new Label("* MANDATORY FIELD");
		mandatoryField.setFont(new Font("Arial", 12));
		mandatoryField.setTextFill(Color.RED);

		name = new TextField();
		name.setPromptText("Name");
		name.setMinWidth(75);
		name.textProperty().addListener((observable, oldValue, newValue) -> {
			if(name.getText().isEmpty() && listType.getText().equalsIgnoreCase("all animals")){
				allAnimalsList(animalList);
			}
			else if(name.getText().isEmpty() && listType.getText().equalsIgnoreCase("Adoption Animals")){
				adoptionAnimals(animalList);
			}
			else if(name.getText().isEmpty() && listType.getText().equalsIgnoreCase("Lost Animals")){
				lostAnimals(animalList);
			}
			else if(name.getText().isEmpty() && listType.getText().equalsIgnoreCase("Found Animals")){
				foundAnimals(animalList);
			}
		});

		age = new ComboBox<String>();
		age.getItems().addAll(
				"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
				);
		age.setMinWidth(150);
		age.setValue("");

		description = new TextField();
		description.setPromptText("description");
		description.setMinWidth(150);

		breedCb = new ComboBox<String>();
		breedCb.setMinWidth(150);
		breedCb.setValue("");

		animalCb = new ComboBox<String>();
		animalCb.getItems().addAll("");
		animalCb.setMinWidth(150);
		animalCb.setValue("");

		genderCb = new ComboBox<String>();
		genderCb.getItems().addAll(
				"Male", "Female", ""
				);
		genderCb.setMinWidth(150);
		genderCb.setValue("");

		statusCb = new ComboBox<String>();
		statusCb.getItems().addAll("");
		statusCb.setMinWidth(150);
		statusCb.setValue("");

		try {
			breedCb.getItems().addAll(ReadWrite.readToCombo(new File("Cat.txt")));
			breedCb.getItems().addAll(ReadWrite.readToCombo(new File("Dog.txt")));
			statusCb.getItems().addAll(ReadWrite.readToCombo(new File("status.txt")));
			animalCb.getItems().addAll(ReadWrite.readToCombo(new File("type.txt")));
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
		grid.addRow(2, name, age, animalCb, breedCb, description, statusCb, genderCb);

		return grid;
	}
	public ComboBox animalComboBox(AnimalList animalList, String string) {

		animalCb = new ComboBox<String>();
		animalCb.setMinWidth(150);
		animalCb.setValue("");
		animalCb.valueProperty().addListener((observable, oldValue, newValue) -> {
			//populates from varies list depending on the string value passed as parameter.
			restoreTable(animalList, string);
		});

		try {
			animalCb.getItems().addAll(ReadWrite.readToCombo(new File("type.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return animalCb;

	}
	//search the persons in the table and populates the table with the people matching the criteria
	public void searchPerson(){

		ObservableList obList = FXCollections.observableArrayList();
		if(personName.getText() == null && personName.getText().isEmpty())
		{
			personName.setText(null);
		}
		for(int i = 0; i<table.getItems().size(); i++){

			if(		((Person) table.getItems().get(i)).getName().toLowerCase().contains(personName.getText().toLowerCase()) &&
					((Person) table.getItems().get(i)).getAddress().toLowerCase().contains(personAddress.getText().toLowerCase()) &&
					((Person) table.getItems().get(i)).getPhoneNumber().toLowerCase().contains(personNo.getText().toLowerCase()) &&
					((Person) table.getItems().get(i)).getEmail().toLowerCase().contains(personEmail.getText().toLowerCase()) &&
					String.valueOf(((Person) table.getItems().get(i)).getID()).toLowerCase().contains(personID.getText().toLowerCase()))
			{
				obList.add(table.getItems().get(i));
			}
		}
		table.getItems().clear();
		table.setItems(obList);
	}
	//the action of the save button used in the reports section.
	public void directoryChooser() {
		//creates a file chooser that is used to select where the report file is saved
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose location To Save Report");
		chooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text File", "*.txt", "*..rtf"));
		File selectedFile = chooser.showSaveDialog(null);
		if(selectedFile != null){
			PrintWriter outFile = null;
			try {
				outFile = new PrintWriter(selectedFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//writes to the file
			if(table.getItems().size() != 0){
				for(int i = 0; i<table.getItems().size(); i++){
					outFile.println(table.getItems().get(i).toString());
				}
			}
			outFile.close();
		}
	}
	//populates the table with lost animals
	public void lostAnimals(AnimalList animalList){
		ObservableList lostList = FXCollections.observableArrayList(animalList.getLostList());
		table.getItems().clear();
		table.setItems(lostList);
	}
	//populates the table with found animals
	public void foundAnimals(AnimalList animalList){
		ObservableList foundList = FXCollections.observableArrayList(animalList.getFoundList());
		table.getItems().clear();
		table.setItems(foundList);
	}
	//populates the table with adoption animals
	public void adoptionAnimals(AnimalList animalList){
		ObservableList adoptionList = FXCollections.observableArrayList(animalList.getAdoptionList());
		table.getItems().clear();
		table.setItems(adoptionList);
	}
	//populates the table with ready adoption animals
	public void readyAdoptionAnimals(AnimalList animalList){
		ObservableList readyAdoptionList = FXCollections.observableArrayList(animalList.getAdoptionReadyList());
		table.getItems().clear();
		table.setItems(readyAdoptionList);
	}
	//populates the table with inTraining adoption animals
	public void inTrainingAdoptionAnimals(AnimalList animalList){
		ObservableList trainingAdoptionList = FXCollections.observableArrayList(animalList.getAdoptionTrainingList());
		table.getItems().clear();
		table.setItems(trainingAdoptionList);
	}
	//populates the table with inTraining adoption animals
	public void puppiesInTrainingAdoptionAnimals(AnimalList animalList){
		ObservableList puppiesAdoptionList = FXCollections.observableArrayList(animalList.getPuppiesAdoptionTrainingList());
		table.getItems().clear();
		table.setItems(puppiesAdoptionList);
	}
	//populates the table with all animals
	public void allAnimalsList(AnimalList animalList){
		ObservableList allList = FXCollections.observableArrayList(animalList.getList());
		table.getItems().clear();
		table.setItems(allList);
	}
	// buttons used to filter the list of animals.
	public HBox listButtons(AnimalList animalList, Label listType){
		Button allAnimals = new Button("All Animals");
		allAnimals.setOnAction(e -> {allAnimalsList(animalList);
		listType.setText("All Animals");});
		Button adoptionAnimals = new Button("Adoption Animals");
		adoptionAnimals.setOnAction(e -> {adoptionAnimals(animalList);
		listType.setText("Adoption Animals");});
		Button lostAnimals = new Button("Lost Animals");
		lostAnimals.setOnAction(e -> 
		{listType.setText("Lost Animals");
		lostAnimals(animalList);});
		Button foundAnimals = new Button("Found Animals");
		foundAnimals.setOnAction(e -> {listType.setText("Found Animals");
		foundAnimals(animalList);});

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(allAnimals, adoptionAnimals, lostAnimals, foundAnimals);

		return hBox;
	}
	//searches items in the table and adds them if they match the users inpt to an observable list that is then used to repopulate the table
	public void searchAnimals(){

		ObservableList obList = FXCollections.observableArrayList();

		for(int i = 0; i<table.getItems().size(); i++){
			if(		((Animal) table.getItems().get(i)).getName().toLowerCase().contains(name.getText().toLowerCase()) &&
					((Animal) table.getItems().get(i)).getBreed().toLowerCase().contains(breedCb.getValue().toLowerCase()) &&
					((Animal) table.getItems().get(i)).getAType().toLowerCase().contains(animalCb.getValue().toLowerCase()) &&
					//((Animal) table.getItems().get(i)).getEmail().toLowerCase().contains(personEmail.getText().toLowerCase()) &&
					String.valueOf(((Animal) table.getItems().get(i)).getAge()).toLowerCase().contains(age.getValue()))
			{
				obList.add(table.getItems().get(i));
			}
		}
		table.getItems().clear();
		table.setItems(obList);
	}
	//creates an observble list and adds items from the table if they match the location selected by the user
	public void searchLoc(){
		ObservableList obList = FXCollections.observableArrayList();
		if(!table.getItems().isEmpty()){
			if( ((Animal) table.getItems().get(0)).getAnimalCat() instanceof Lost){
				for(int i = 0; i<table.getItems().size(); i++){
					if( ((Lost) ((Animal) table.getItems().get(i)).getAnimalCat()).getLocation().toLowerCase().contains(locationCb.getValue().toLowerCase()) )
					{
						obList.add(table.getItems().get(i));
					}
				}
			}
			else{
				for(int i = 0; i<table.getItems().size(); i++){
					if( ((Found) ((Animal) table.getItems().get(i)).getAnimalCat()).getLocation().toLowerCase().contains(locationCb.getValue().toLowerCase()) )
					{
						obList.add(table.getItems().get(i));
					}
				}
			}
			table.getItems().clear();
			table.setItems(obList);
		}
	}
	//used to populate the location combobox
	public ComboBox<String> locationCb(AnimalList animalList, String string){

		//locationCb = new ComboBox<String>();
		locationCb.getItems().clear();
		locationCb.setMinWidth(150);
		//adds a listener to the combobox. populates the combobox with all original items when value changed
		locationCb.valueProperty().addListener((observable, oldValue, newValue) -> {
			//populates from varies list depending on the string value passed as parameter.
			restoreTable(animalList, string);
		});
		locationCb.setValue("");

		try {
			locationCb.getItems().addAll(ReadWrite.readToCombo(new File("location.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return locationCb;
	}
	//returns a hbox cntains 2 date pickers that are used for the date range in the search
	public HBox dateRange(AnimalList animalList, String string){

		from = new DatePicker();
		from.valueProperty().addListener((observable, oldValue, newValue) -> {
			//populates from varies list depending on the string value passed as parameter.
			restoreTable(animalList, string);
		});
		to = new DatePicker();

		Label lb = new Label("   To  ");

		return new HBox(new Label("   From  "),from, lb, to);
	}
	//returns a hbox that contains buttons for search the table and saving.
	public HBox searchSave(String string){

		//sets the save buttons action depending on the string value passed into the method
		Button search = new Button("Search Animals");
		if(string.equalsIgnoreCase("date")){
			search.setOnAction(e -> validatesLocDate());
		}
		else if(string.equalsIgnoreCase("location")){
			search.setOnAction(e -> searchLoc());
		}
		else if(string.equalsIgnoreCase("ready")){
			search.setOnAction(e -> searchByAnimalType());
		}
		else if(string.equalsIgnoreCase("training")){
			search.setOnAction(e -> searchByAnimalType());
		}
		// blank label used as a spacer between the 2 buttons.
		Label lb = new Label("         ");

		return new HBox(search, lb, saveButton());
	}

	public void validatesLocDate(){
		//checking that dates have been set
		if(from.getValue() == null || to.getValue() == null){
			//if no dates have been set. this sets one or both 
			if(from.getValue() == null &&  to.getValue() == null){
				from.setValue(LocalDate.of(1970, 1, 1));
				to.setValue(LocalDate.now());
			}
			else if(from.getValue() == null){
				from.setValue(LocalDate.of(1970, 1, 1));
			}
			else{
				to.setValue(LocalDate.now());
			}
			searchLocDate();
		}
		//if from date is after to date or vice versa. warns user.
		else if(from.getValue().isAfter(to.getValue())){
			AlertBox.error("Error", "Date from can not be after Date to");
		}
		//creates an observablelist that is used to populate the table
		else{
			searchLocDate();
		}
	}

	public void searchLocDate(){
		ObservableList obList = FXCollections.observableArrayList();
		//checks what instance the first table item is
		if( ((Animal) table.getItems().get(0)).getAnimalCat() instanceof Lost){
			//loops through table and adds the items that match the search criteria
			for(int i = 0; i<table.getItems().size(); i++){
				if( ((Lost) ((Animal) table.getItems().get(i)).getAnimalCat()).getLocation().toLowerCase().contains(locationCb.getValue().toLowerCase()) && from.getValue().minusDays(1).isBefore(((Animal) table.getItems().get(i)).getAnimalCat().getDate()) && to.getValue().plusDays(1).isAfter(((Animal) table.getItems().get(i)).getAnimalCat().getDate()))
				{
					obList.add(table.getItems().get(i));
				}
			}
		}
		else if(( ((Animal) table.getItems().get(0)).getAnimalCat() instanceof Found)){
			for(int i = 0; i<table.getItems().size(); i++){
				if( ((Found) ((Animal) table.getItems().get(i)).getAnimalCat()).getLocation().toLowerCase().contains(locationCb.getValue().toLowerCase()) && from.getValue().minusDays(1).isBefore(((Animal) table.getItems().get(i)).getAnimalCat().getDate()) && to.getValue().plusDays(1).isAfter(((Animal) table.getItems().get(i)).getAnimalCat().getDate()))
				{
					obList.add(table.getItems().get(i));
				}
			}
		}
		//clears the table and populates it with the observable list that was populated with items that match the search criteria
		table.getItems().clear();
		table.setItems(obList);
	}
	//restores the table to its original contains
	public void restoreTable(AnimalList animalList, String string){
		if(string.equalsIgnoreCase("lost")){
			lostAnimals(animalList);
		}
		else if(string.equalsIgnoreCase("found")){
			foundAnimals(animalList);
		}
		else if(string.equalsIgnoreCase("ready")){
			readyAdoptionAnimals(animalList);
		}
		else if(string.equalsIgnoreCase("training")){
			inTrainingAdoptionAnimals(animalList);
		}
		else if(string.equalsIgnoreCase("puppies")){
			puppiesInTrainingAdoptionAnimals(animalList);
		}
	}
	//sorts the table by gender
	public void sortByGender(){
		table.getSortOrder().add(genColumn);
	}
	//sorts the table by name
	public void sortByName(){
		table.getSortOrder().add(nameColumn);
	}
	//sorts the table by age
	public void sortByAge(){
		table.getSortOrder().add(ageColumn);
	}

	public void searchByAnimalType(){
		ObservableList obList = FXCollections.observableArrayList();

		//loops through table and adds the items that match the search criteria
		for(int i = 0; i<table.getItems().size(); i++){
			if(((Animal) table.getItems().get(i)).getAType().equalsIgnoreCase(animalCb.getValue()))
			{
				obList.add(table.getItems().get(i));
			}
		}
		//clears the table and populates it with the observable list that was populated with items that match the search criteria
		table.getItems().clear();
		table.setItems(obList);
	}
}

