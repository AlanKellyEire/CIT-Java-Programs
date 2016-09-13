package gui;

import classes.AnimalList;
import classes.PersonList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class MainWindow {

	private BorderPane bP;

	private AnimalList animalList;
	private PersonList personList;
 
	public void startApp(){
		//creates a rectangle from the size of the users display. this rectangle is used then for the scene size.
		Rectangle2D display = Screen.getPrimary().getVisualBounds();

		//creates an animal list
		animalList = new AnimalList();
		personList = new PersonList();
		
		//creates an instance of TopMenuBar
		TopMenuBar tMenuBar = new TopMenuBar();
		
		//creates the borderapne bp
		bP = new BorderPane();
		//adds a menubar to the top of the borderpane 
		bP.setTop(tMenuBar.menuBar(bP, animalList, personList));
		//adds a label to the center of the borderpane
		bP.setCenter(new Label("welcome to Alan Java Pogram"));
		//adds the borderpane to the scene and uses the rectangle created from the users screen size to get the scene width and height
		Scene scene = new Scene(bP, display.getWidth(), display.getHeight());
		//gets the css containing the sytles for buttons, labels, menus, etc....
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		//creates a new stage
		Stage primaryStage = new Stage();
		//sets the title of the stage
		primaryStage.setTitle("Alan's AnimalShelter");
		//adds the scene to the stage
		primaryStage.setScene(scene);		
		
		// sets an action if the users hits the  button on the window.
		primaryStage.setOnCloseRequest(event -> {
	        event.consume();
	        AlertBox.warn("Quit", "Are You sure u want to quit the application", primaryStage);
	        
	    });
		
		//displays the stage
		primaryStage.show();
		AlertBox.loadData(tMenuBar);
	}

}
