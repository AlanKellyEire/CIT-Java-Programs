package gui;

import java.util.ArrayList;

import classes.Adoption;
import classes.Animal;
import classes.AnimalList;
import classes.Category;
import classes.Found;
import classes.Lost;
import classes.Person;
import classes.PersonList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilites.ReadWrite;

/**
 * @author Alan Kelly R00052131
 * Created 5 Apr 2016
 *
 */
public class AddPersonAdop {

	private TextField personNameAdoption, personAddressAdoption, personNoAdoption, personEmailAdoption;
	private Stage window;
	private TableView table;

	public Person display(PersonList personList, Animal animal, String titleWindowText, String addPersonText)  {
		Person person = new Person();
		window = new Stage();
		BorderPane bP = new BorderPane();

		window.setTitle(titleWindowText);

		Button addPerson = new Button(addPersonText);
		addPerson.setOnAction(e -> {
			addNewPerson(person, personList, animal);
		});

		Button closeButton = new Button("Close Window");
		closeButton.setOnAction(e -> window.close());

		Button addOldPerson = new Button("Add Selected Person");
		addOldPerson.setOnAction(e -> { 
			addSavedPerson(person, personList, animal);
		});

		VBox vbox = new VBox(personFieldsAdoption(), addPerson, new Label(), sponsorsTable(personList.getList()), new Label("To add a person already in the system please select the person and click add"), addOldPerson, closeButton);
		vbox.getChildren().addAll();


		bP.setCenter(vbox);
		Scene scene = new Scene(bP, 800,800);

		window.setScene(scene);
		window.showAndWait();
		return person;
	}

	public GridPane personFieldsAdoption(){

		personNameAdoption = new TextField();
		personNameAdoption.setPromptText("Name");
		personNameAdoption.setMinWidth(75);

		personAddressAdoption = new TextField();
		personAddressAdoption.setPromptText("Address");
		personAddressAdoption.setMinWidth(75);

		personNoAdoption = new TextField();
		personNoAdoption.setPromptText("Phone Number");
		personNoAdoption.setMinWidth(75);

		personEmailAdoption = new TextField();
		personEmailAdoption.setPromptText("Email");
		personEmailAdoption.setMinWidth(75);

		GridPane grid = new GridPane();
		grid.centerShapeProperty();
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.addRow(0, CustomItems.label("Enter Persons Details"));
		grid.addRow(1, new Label("Person Name *"), new Label(" Address *"), new Label("Phone Number *"), new Label("Email *"));
		grid.addRow(2, personNameAdoption, personAddressAdoption, personNoAdoption, personEmailAdoption);

		return grid;
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
		table.setItems(ReadWrite.getAnimal(list));
		table.getColumns().addAll(idColumn, nameColumn, addColumn, emailColumn, phoneNumberColumn);

		return table;
	}

	public void addNewPerson(Person person, PersonList personList, Animal animal){

		if(personNameAdoption.getText().isEmpty() || personAddressAdoption.getText().isEmpty() || personEmailAdoption.getText().isEmpty() || personNoAdoption.getText().isEmpty())
		{
			AlertBox.warn("Some Person Info Missing", "Please complete all Person mandatory fields");
		}
		else{
			person.setName(personNameAdoption.getText());
			person.setAddress(personAddressAdoption.getText());
			person.setEmail(personEmailAdoption.getText());
			person.setPhoneNumber(personNoAdoption.getText());
			person.setID();
			personList.add(person);
			((Adoption) animal.getAdoption()).addPerson(person);
			window.close();
		}

	}

	public void addSavedPerson(Person person, PersonList personList, Animal animal){

		if(table.getSelectionModel().getSelectedItems().isEmpty()){
			AlertBox.warn("No Animal Selected", "Please Select an Animal from the All Animals Table");
		}
		else{
			ObservableList<Person> personSelected, allPersons;
			personSelected = table.getItems();
			allPersons = table.getItems();

			ArrayList arrayAll = new ArrayList(allPersons);
			personSelected = table.getSelectionModel().getSelectedItems();

			Boolean b = false;
			for(int i = 0; i < ((Adoption) animal.getAdoption()).getInterested().size(); i++){
				if(personSelected.get(0).equals(((Adoption) animal.getAdoption()).getInterested().get(i)))
				{
					b = true;
				}
			}
			if(b == true){
				AlertBox.warn("Duplicate Person", "Person Already in List");
			}
			else{
				ArrayList<Person> an = new ArrayList<Person>(personSelected);
				person.setName(an.get(0).getName());
				person.setAddress(an.get(0).getAddress());
				person.setEmail(an.get(0).getEmail());
				person.setPhoneNumber(an.get(0).getPhoneNumber());
				((Adoption) animal.getAdoption()).addPerson(person);
				window.close();
			}
		}
	}

}
