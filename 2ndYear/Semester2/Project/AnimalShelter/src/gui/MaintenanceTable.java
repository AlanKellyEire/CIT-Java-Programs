package gui;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilites.ReadWrite;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 * this class contains all maintenance table and all the methods that interact with the table such as add and remove
 */
public class MaintenanceTable {

	private TableView table;
	private TextField input;
	private ObservableList<String> stringList;

	// returns a table after it populates it using the getString method
	public TableView<String> mainTable(String string,File file)
	{
		TableColumn<String, String> tColumn = new TableColumn<String, String>(string);
		table = new TableView<String>();

		tColumn.setCellValueFactory(cellData -> 
		new ReadOnlyStringWrapper(cellData.getValue()));

		try {
			table.setItems(ReadWrite.mainGetString(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.getColumns().addAll(tColumn);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		return table;
	}

	//adds the text in the textfield to the table
	public void mainAddButtonClicked(File file){
		
		try {
			stringList = ReadWrite.mainGetString(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stringList.add(input.getText());
		table.getItems().add(input.getText());
		input.clear();
	}

	//Delete button clicked
	//removes the selected item from the table
	public void mainDeleteButtonClicked(){
		ObservableList<String> allStrings, stringSelected;
		allStrings = table.getItems();		
		stringSelected = table.getSelectionModel().getSelectedItems();
		stringSelected.forEach(allStrings::remove);	
	}

	//returns a hbox with a text field
	public HBox mainTextFields() {
		input = new TextField();
		input.setPromptText("Input");
		input.setMinWidth(75);

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(input);

		return hBox;
	}

	//returns a hbox with the add/remove butttons and has the action events for each button
	public HBox mainAddRemove(File file, Stage window){
		//Button
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> mainAddButtonClicked(file));
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> mainDeleteButtonClicked());
		Button writeButton = new Button("Write to file");
		
			try {
				writeButton.setOnAction(e -> {AlertBox.confirm("Completed", "Data Written To File Succesfully");
				ReadWrite.mainWrite(file);
				});
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		Button closeButton = new Button("Close Window");
		closeButton.setOnAction(e -> window.close());

		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10,10,10,10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(addButton, deleteButton, writeButton, closeButton);

		return hBox2;
	}

	//returns a vbox with a label, text fields and add/remove buttons
	public VBox mainVBox(String string, File file, Stage window){
		Label label = new Label(string);
		label.setFont(new Font("Arial", 20));

		VBox vBox = new VBox();
		vBox.setSpacing(5);
		vBox.setPadding(new Insets(10,10, 10, 10));
		vBox.getChildren().addAll(label, mainTable(string,file), mainTextFields(), mainAddRemove(file,window));

		return vBox;
	}

}
