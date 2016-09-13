package gui;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utilites.ReadWrite;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class MaintenanceWindow {

	public void display() throws FileNotFoundException  {
		MaintenanceTable aTable = new MaintenanceTable();
		final File types = new File("type.txt");
		final File location = new File("location.txt");
		final File status = new File("status.txt");
		final Stage  window = new Stage();
		BorderPane bP = new BorderPane();

		window.setTitle("Maintenance Window");

		//creates the menu bar and its items and sets each buttons actions.
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("_Add/Remove From DropDowns");
		MenuItem editLocation = new MenuItem("_Edit Locations");
		MenuItem editAtypes = new MenuItem("_Edit Animal Types");
		MenuItem editStatus = new MenuItem("_Edit Status's");
		MenuItem editBreeds = new MenuItem("_Edit Breed's");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> CustomItems.closeApp(window));
		fileMenu.getItems().addAll(editLocation, editAtypes, editStatus, editBreeds, new SeparatorMenuItem(),exit);

		menuBar.getMenus().addAll(fileMenu);
		editLocation.setOnAction(e -> {
			bP.setCenter(aTable.mainVBox("Locations", location, window));        
		});

		editAtypes.setOnAction(e -> {
			bP.setCenter(aTable.mainVBox("Animal Types", types, window));		        
		});

		editStatus.setOnAction(e -> {
			bP.setCenter(aTable.mainVBox("Status", status, window));	        
		});

		editBreeds.setOnAction(e -> {
			try {
				breedSelection(bP, aTable, types, window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	        
		});

		bP.setTop(menuBar);
		Scene scene = new Scene(bP, 500,600);

		Label label = new Label("welcome to the maintenance window");

		bP.setCenter(label);

		window.setScene(scene);
		window.show();

	}
	
	public void breedSelection(BorderPane bP, MaintenanceTable aTable, File types, Stage window) throws FileNotFoundException{
		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Select Animal Type");
		ComboBox<String> aTypeCb = new ComboBox<String>();
		aTypeCb.setValue("Choose Animal");
		Button resume = new Button("Continue");
		resume.setMinSize(75, 25);
		resume.setDisable(true);
		aTypeCb.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {
	        	if(aTypeCb.getValue() != "Choose Animal")
	    		resume.setDisable(false);
	          }    
	      });
		Button close = new Button("Cancel");
		close.setMinSize(75, 25);
		resume.setOnAction(e -> {
			bP.setCenter(aTable.mainVBox(aTypeCb.getValue() + " Breeds", new File(aTypeCb.getValue() + ".txt"), window));
			popup.close();
		});
		close.setOnAction(e -> {
				popup.close();
		});
		aTypeCb.getItems().addAll(ReadWrite.readToCombo(types));
	
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25,25,25,25));
		vbox.setSpacing(25);
		vbox.setAlignment(Pos.CENTER);
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(25,25,25,25));
		hbox.setSpacing(25);
		hbox.getChildren().addAll(resume, close);
		vbox.getChildren().addAll(new Label("Select Animal Type to edit it's Breeds"), aTypeCb, hbox);

		Scene scene = new Scene(vbox, 300, 180);

		popup.setScene(scene);
		popup.showAndWait();
	}
}


