package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import classes.Adoption;
import classes.Animal;
import classes.AnimalList;
import classes.Found;
import classes.Lost;
import classes.Person;
import classes.PersonList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import utilites.ReadWrite;

/**
 * @author Alan Kelly R00052131
 * Created 4 Apr 2016
 *
 */
public class EditAnimalWindow {

	private TableView table;
	private TextField name;
	private ComboBox<Integer> age;
	private TextField description;
	private ComboBox<String> animalCb;
	private ComboBox<String> breedCb;
	private ComboBox<String> statusCb;
	private ComboBox<String> locationCb;
	private ComboBox<String> personsInterested;
	private ToggleGroup genderGroup, neuteredGroup, chippedGroup, vaccinatedGroup, reservedGroup;
	private RadioButton maleRb, neuteredYes, neuteredNo, chippedYes, chippedNo, vaccinatedNo, vaccinatedYes, reservedYes, reservedNo;
	private RadioButton femaleRb;
	private GridPane grid;
	private File types = new File("type.txt");
	private File status = new File("status.txt");
	private File location = new File("location.txt");
	private AddPersonAdop adp = new AddPersonAdop();
	private ImageView imageView;

	public void display(Animal animal, PersonList personList, TableView table)  {
		//MaintenanceTable aTable = new MaintenanceTable();
		final Stage  window = new Stage();
		BorderPane bP = new BorderPane();

		window.setTitle("Edit Animal");

		name = new TextField(animal.getName());
		name.setMinWidth(75);

		age = new ComboBox<Integer>();
		age.getItems().addAll(
				1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
				);
		age.setValue(animal.getAge());
		age.setMinWidth(150);

		description = new TextField();
		description.setMinWidth(150);

		animalCb = new ComboBox<String>();
		animalCb.setValue(animal.getAType());
		animalCb.setMinWidth(150);

		breedCb = new ComboBox<String>();
		breedCb.setValue(animal.getBreed());
		breedCb.setMinWidth(150);

		genderGroup  = new ToggleGroup();
		maleRb = new RadioButton("Male");
		maleRb.setToggleGroup(genderGroup);
		//maleRb.setSelected(true);

		femaleRb = new RadioButton();
		femaleRb = new RadioButton("Female");
		femaleRb.setToggleGroup(genderGroup);

		locationCb = new ComboBox<String>();
		locationCb.setMinWidth(150);

		statusCb = new ComboBox<String>();
		statusCb.setMinWidth(100);
		statusCb.setDisable(true);

		neuteredGroup  = new ToggleGroup();
		neuteredYes = new RadioButton("Yes");
		neuteredYes.setMaxWidth(50);
		neuteredYes.setToggleGroup(neuteredGroup);
		neuteredYes.setSelected(true);

		neuteredNo = new RadioButton();
		neuteredNo.setMaxWidth(50);
		neuteredNo = new RadioButton("No");
		neuteredNo.setToggleGroup(neuteredGroup);
		neuteredGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
					Toggle old_toggle, Toggle new_toggle) {
				showReservedTest(animal);
			}
		});

		chippedGroup  = new ToggleGroup();
		chippedYes = new RadioButton("Yes");
		chippedYes.setToggleGroup(chippedGroup);
		chippedYes.setSelected(true);

		chippedNo = new RadioButton();
		chippedNo = new RadioButton("No");
		chippedNo.setToggleGroup(chippedGroup);
		chippedGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
					Toggle old_toggle, Toggle new_toggle) {
				showReservedTest(animal);
			}
		});

		vaccinatedGroup  = new ToggleGroup();
		vaccinatedYes = new RadioButton("Yes");
		vaccinatedYes.setToggleGroup(vaccinatedGroup);
		vaccinatedYes.setSelected(true);

		vaccinatedNo = new RadioButton();
		vaccinatedNo = new RadioButton("No");
		vaccinatedNo.setToggleGroup(vaccinatedGroup);
		vaccinatedGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
					Toggle old_toggle, Toggle new_toggle) {
				showReservedTest(animal);
			}
		});

		reservedGroup  = new ToggleGroup();
		reservedYes = new RadioButton("Yes");
		reservedYes.setToggleGroup(reservedGroup);

		reservedGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
					Toggle old_toggle, Toggle new_toggle) {
				if (reservedGroup.getSelectedToggle() == reservedYes && animal.getAdoption().getContact() == null) {
					Person person = new Person();
					person = adp.display(personList, animal, "Select the Person the Animal is reserved for", "Add New Person");
					if(person.getName() != null){
						((Adoption) animal.getAdoption()).setContact(person);
						reservedYes.setSelected(true);
					}
					else{
						AlertBox.warn("Cannot Reserve", "Reserver not selected or added"
								+ "\nplease Select a person that the animal is reserved for");
						reservedNo.setSelected(true);
					}
				} 
			}
		});

		reservedNo = new RadioButton();
		reservedNo = new RadioButton("No");
		reservedNo.setSelected(true);
		reservedNo.setToggleGroup(reservedGroup);
		reservedNo.setDisable(true);
		reservedYes.setDisable(true);

		personsInterested = new ComboBox<String>();

		HBox mainAnimalDetailsLabels = new HBox(new Label("Name"), new Label("Age"), new Label("animal Type"), new Label("breed"), new Label("gender"));
		mainAnimalDetailsLabels.centerShapeProperty();
		mainAnimalDetailsLabels.setPadding(new Insets(10,10,10,10));
		HBox mainAnimalDetails = new HBox(name, age, animalCb, breedCb, maleRb, femaleRb);
		mainAnimalDetails.centerShapeProperty();
		mainAnimalDetails.setPadding(new Insets(10,10,10,10));
		grid = new GridPane();

		if(animal.getAdoption() instanceof Adoption){
			adoptionAnimal(window, animal, personList, table);
		}
		else if(animal.getAnimalCat() instanceof Found){
			locationCb.setValue(((Found) animal.getAnimalCat()).getLocation());
			TextField contactName = new TextField();
			contactName.setText(((Found) animal.getAnimalCat()).getContact().getName());
			TextField contactAdd = new TextField();
			contactAdd.setText(((Found) animal.getAnimalCat()).getContact().getAddress());
			TextField contactEmail = new TextField();
			contactEmail.setText(((Found) animal.getAnimalCat()).getContact().getEmail());
			TextField contactNo = new TextField();
			contactNo.setText(((Found) animal.getAnimalCat()).getContact().getPhoneNumber());

			Button save = new Button("Save Animal");
			save.setOnAction(e -> {saveAnimal(animal, table, window);
			savePerson(animal, table, contactName, contactAdd, contactEmail, contactNo);
			});
			Button cancel = new Button("Cancel");
			cancel.setOnAction(e -> window.close());

			grid.getChildren().addAll(animalFields(animal));
			grid.addRow(2, new Label("Location Lost"));
			grid.addRow(3, locationCb);
			grid.addRow(4, new Label("Contact"));
			grid.addRow(5, new Label("Name"), new Label("Address"), new Label("Email"), new Label("Phone Number"));
			grid.addRow(6, contactName, contactAdd, contactEmail, contactNo);
			grid.addRow(9, save, cancel);	
		}
		else if(animal.getAnimalCat() instanceof Lost){
			locationCb.setValue(((Lost) animal.getAnimalCat()).getLocation());
			TextField contactName = new TextField();
			contactName.setText(((Lost) animal.getAnimalCat()).getContact().getName());
			TextField contactAdd = new TextField();
			contactAdd.setText(((Lost) animal.getAnimalCat()).getContact().getAddress());
			TextField contactEmail = new TextField();
			contactEmail.setText(((Lost) animal.getAnimalCat()).getContact().getEmail());
			TextField contactNo = new TextField();
			contactNo.setText(((Lost) animal.getAnimalCat()).getContact().getPhoneNumber());

			Button save = new Button("Save Animal");
			save.setOnAction(e -> {saveAnimal(animal, table, window);
			savePerson(animal, table, contactName, contactAdd, contactEmail, contactNo);
			});
			Button cancel = new Button("Cancel");
			cancel.setOnAction(e -> window.close());

			grid.getChildren().addAll(animalFields(animal));
			grid.addRow(2, new Label("Location Found"));
			grid.addRow(3, locationCb);
			grid.addRow(4, new Label("Contact"));
			grid.addRow(5, new Label("Name"), new Label("Address"), new Label("Email"), new Label("Phone Number"));
			grid.addRow(6, contactName, contactAdd, contactEmail, contactNo);
			grid.addRow(9, save, cancel);
		}
		else
		{
			grid.getChildren().addAll(new Label("Name"), name, new Label("Age"), age, new Label("animal Type"), animalCb, new Label("breed"), breedCb, new Label("gender"), maleRb,femaleRb);
		}
		//sets the radio button to the gender
		if(animal.getGender() == true){
			maleRb.setSelected(true);
		}
		else{
			femaleRb.setSelected(true);
		}

		bP.setCenter(grid);
		Scene scene = new Scene(bP, 1000,500);

		window.setScene(scene);
		window.show();
	}

	public void set()
	{
		try {
			statusCb.getItems().addAll(ReadWrite.readToCombo(status));
			animalCb.getItems().addAll(ReadWrite.readToCombo(types));
			locationCb.getItems().addAll(ReadWrite.readToCombo(location));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GridPane adoptionAnimal(Stage window, Animal animal ,PersonList personList, TableView adopTable){
		if(((Adoption) animal.getAdoption()).getNeutered() == false){
			neuteredNo.setSelected(true);
		}
		if(((Adoption) animal.getAdoption()).getVaccinated() == false){
			vaccinatedNo.setSelected(true);
		}
		if(((Adoption) animal.getAdoption()).getChipped() == false){
			chippedNo.setSelected(true);
		}
		showReservedTest(animal);
		if(((Adoption) animal.getAdoption()).getReserved() == true){
			reservedYes.setSelected(true);
		}
		for(int i = 0; i <((Adoption) animal.getAdoption()).getInterested().size(); i++){
			personsInterested.getItems().add(((Adoption) animal.getAdoption()).getPerson(i).toString());
		}

		Button addButton = new Button("Add Person to interested list");
		addButton.setOnAction(e -> {
			Person person = new Person();
			person = adp.display(personList, animal, "Add person to Interested list", "Add New Person");
			if(person.getName() != null){
				table.getItems().add(person);
			}
		});
		Button deleteButton = new Button("Delete Person from interested list");
		deleteButton.setOnAction(e -> deleteButtonClicked(animal));

		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10,10,10,10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(addButton, deleteButton);


		Label status = new Label("Animal Status");
		status.setMinWidth(100);
		Label neutered = new Label("Neutered");
		neutered.setMinWidth(100);
		Label chipped = new Label("Chipped");
		chipped.setMinWidth(100);
		Label vaccinated = new Label("Vaccinated");
		vaccinated.setMinWidth(100);
		Label reserved = new Label("Reserved");
		reserved.setMinWidth(100);

		Button save = new Button("Save Animal");
		save.setOnAction(e -> {saveAnimal(animal, adopTable, window);
		});
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> window.close());

		statusCb.setValue(((Adoption) animal.getAdoption()).getStatus());

		grid.getChildren().addAll(animalFields(animal));
		grid.setHgap(10);
		grid.addRow(2, status, neutered, chipped, vaccinated, reserved);
		grid.addRow(3, statusCb,new HBox(neuteredYes, neuteredNo), new HBox(chippedYes, chippedNo), new HBox(vaccinatedYes, vaccinatedNo), new HBox(reservedYes, reservedNo));
		grid.addRow(4, new Label("people interested"));
		grid.addRow(6, sponsorsTable(((Adoption) animal.getAdoption()).getInterested()));
		GridPane.setColumnSpan(table, GridPane.REMAINING);
		grid.addRow(7, hBox2);
		GridPane.setColumnSpan(hBox2, GridPane.REMAINING);
		if(animal.getAdoption().getContact() != null){
			HBox hBox3 = new HBox(personReservedFields(animal));
			grid.addRow(8, hBox3);
			GridPane.setColumnSpan(hBox3, GridPane.REMAINING);
		}
		HBox hBox = new HBox(imageButton(animal));
		grid.addRow(9, hBox);
		GridPane.setColumnSpan(hBox, GridPane.REMAINING);
		grid.addRow(15, save, cancel);
		return grid;
	}

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

		table = new TableView<Person>();
		table.setMaxHeight(75);
		//table.setFixedCellSize(5);
		table.setItems(getAnimal(list));
		table.getColumns().addAll(nameColumn, addColumn, emailColumn, phoneNumberColumn);

		return table;
	}

	public static ObservableList<?> getAnimal(ArrayList list){
		ObservableList obList = FXCollections.observableArrayList();

		for(int i = 0; i < list.size(); i++){
			obList.add(list.get(i));
		}
		return obList;
	}

	public void deleteButtonClicked(Animal animal){
		if(table.getSelectionModel().getSelectedItems().isEmpty()){
			AlertBox.warn("No Animal Selected", "Please Select an Adoption Animals from the All Animals Table");
		}
		else{
			//creating ObservableLists containing all animals and selected animals
			ObservableList<Person> personSelected, allPersons;
			allPersons = table.getItems();
			personSelected = table.getSelectionModel().getSelectedItems();
			//creating a new animal from the animal that has been selected for deletion
			ArrayList<Person> an = new ArrayList<Person>(personSelected);
			Person person = new Person();
			person = an.get(0);
			//removing the selected animal from the tableview
			personSelected.forEach(allPersons::remove);
			//removing the animal removed from the tableview from the animalList
			((Adoption) animal.getAdoption()).removePerson(person);;
			table.getSelectionModel().clearSelection();
		}
	}

	public GridPane animalFields(Animal animal){
		name = new TextField(animal.getName());
		name.setMinWidth(75);

		age = new ComboBox<Integer>();
		age.getItems().addAll(
				1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
				);
		age.setValue(animal.getAge());
		age.setMinWidth(150);

		description = new TextField(animal.getDescription());
		description.setMinWidth(150);

		breedCb = new ComboBox<String>();
		breedCb.setValue(animal.getBreed());
		breedCb.setMinWidth(150);
		try {
			breedCb.getItems().addAll(ReadWrite.readToCombo(new File(animalCb.getValue() + ".txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		animalCb = new ComboBox<String>();
		animalCb.setValue(animal.getAType());
		animalCb.setMinWidth(150);
		animalCb.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String t, String t1) {

				breedCb.getItems().clear();
				try {
					breedCb.getItems().addAll(ReadWrite.readToCombo(new File(animalCb.getValue() + ".txt")));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}  

		});

		genderGroup  = new ToggleGroup();
		maleRb = new RadioButton("Male");
		maleRb.setToggleGroup(genderGroup);

		femaleRb = new RadioButton();
		femaleRb = new RadioButton("Female");
		femaleRb.setToggleGroup(genderGroup);

		set();

		grid = new GridPane();
		grid.addRow(0,new Label("Name"), new Label("Age"), new Label("Description"), new Label("animal Type"), new Label("breed"), new Label("gender"));
		grid.addRow(1, name, age, description, animalCb, breedCb, maleRb, femaleRb);
		grid.centerShapeProperty();
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));

		return grid;
	}

	public void saveAnimal(Animal animal, TableView table, Stage window){
		animal.setAge(age.getValue());
		boolean gender = false;
		if(genderGroup.getSelectedToggle() == maleRb){
			gender = true;
		}
		animal.setAType(animalCb.getValue());
		animal.setGender(gender);
		animal.setBreed(breedCb.getValue());
		animal.setDescription(description.getText());
		animal.setName(name.getText());
		if(animal.getAdoption() instanceof Adoption){
			boolean chipped = false, neutered = false, vaccinated = false, reserved = true;
			if(neuteredGroup.getSelectedToggle() == neuteredYes){
				neutered = true;
			}
			if(chippedGroup.getSelectedToggle() == chippedYes){
				chipped = true;
			}
			if(reservedGroup.getSelectedToggle() == reservedNo){
				reserved = false;
			}
			if(vaccinatedGroup.getSelectedToggle() == vaccinatedYes){
				vaccinated = true;
			}
			((Adoption) animal.getAdoption()).setChipped(chipped);
			((Adoption) animal.getAdoption()).setNeutered(neutered);
			((Adoption) animal.getAdoption()).setVaccinated(vaccinated);
			((Adoption) animal.getAdoption()).setReservered(reserved);
			if(imageView != null){
				animal.setPicture(imageView);
			}
			if(chipped == true && neutered == true && vaccinated == true){

				((Adoption) animal.getAdoption()).setStatus("Ready");
			}
			if(!((Adoption) animal.getAdoption()).getReserved() && animal.getAdoption().getContact() != null){
				animal.getAdoption().setContact(null);
			}
			if(!((Adoption) animal.getAdoption()).getChipped() || !((Adoption) animal.getAdoption()).getNeutered() || !((Adoption) animal.getAdoption()).getVaccinated()){
				animal.getAdoption().setStatus("In Training");
			}

		}
		else if(animal.getAnimalCat() instanceof Lost){
			((Lost) animal.getAnimalCat()).setLocation(locationCb.getValue());
		}
		else if(animal.getAnimalCat() instanceof Found){
			((Found) animal.getAnimalCat()).setLocation(locationCb.getValue());
		}
		refreshTable(table);
		window.close();
	}

	public GridPane personReservedFields(Animal animal){

		TextField personInterestName = new TextField();
		personInterestName.setText(animal.getAdoption().getContact().getName());
		personInterestName.setMinWidth(75);
		personInterestName.setDisable(true);

		TextField personInterestAddress = new TextField();
		personInterestAddress.setText(animal.getAdoption().getContact().getAddress());
		personInterestAddress.setMinWidth(75);
		personInterestAddress.setDisable(true);

		TextField personInterestNo = new TextField();
		personInterestNo.setText(animal.getAdoption().getContact().getPhoneNumber());
		personInterestNo.setMinWidth(75);
		personInterestNo.setDisable(true);

		TextField personInterestEmail = new TextField();
		personInterestEmail.setText(animal.getAdoption().getContact().getEmail());
		personInterestEmail.setMinWidth(75);
		personInterestEmail.setDisable(true);

		GridPane grid = new GridPane();
		grid.centerShapeProperty();
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.addRow(0, new Label("Reserved For"));
		grid.addRow(1, new Label("Person Name"), new Label(" Address"), new Label("Phone Number"), new Label("Email"));
		grid.addRow(2, personInterestName, personInterestAddress, personInterestNo, personInterestEmail);

		return grid;
	}

	public void savePerson(Animal animal, TableView table, TextField name, TextField add, TextField email, TextField no){
		animal.getAnimalCat().getContact().setName(name.getText());
		animal.getAnimalCat().getContact().setAddress(add.getText());
		animal.getAnimalCat().getContact().setEmail(email.getText());
		animal.getAnimalCat().getContact().setPhoneNumber(no.getText());
		refreshTable(table);
	}

	public void refreshTable(TableView table){
		//refreshing the table with the edited animal data
		((TableColumn) table.getColumns().get(0)).setVisible(false);
		((TableColumn) table.getColumns().get(0)).setVisible(true);
	}

	public void showReservedTest(Animal animal){
		if (chippedGroup.getSelectedToggle() == chippedYes && neuteredGroup.getSelectedToggle() == neuteredYes && vaccinatedGroup.getSelectedToggle() == vaccinatedYes) {
			reservedNo.setDisable(false);
			reservedYes.setDisable(false);
			statusCb.setValue("Ready");
			((Adoption) animal.getAdoption()).setStatus("Ready");
		}

	}

	public void fileChooser(){
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*,jpg", "*.png", "*.jpeg", "*.JPEG"));
		File selectedFile = fc.showOpenDialog(null);

		if(selectedFile != null){
			try {
				BufferedImage bufferedImage = ImageIO.read(selectedFile);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				imageView.setImage(image);
			} catch (IOException ex) {
				System.err.println("error");
			}
		}
		else{
			AlertBox.error("file??", "please select a file");
		}

	}

	public VBox imageButton(Animal animal){
		Button fc = new Button("Select Image");
		imageView = new ImageView(); 
		if(animal.getPicture() != null){
			imageView = animal.getPicture();
		}
		imageView.setFitHeight(200);
		imageView.setFitWidth(300);
		fc.setOnAction(e -> fileChooser());

		return new VBox(fc, imageView);
	}

}
