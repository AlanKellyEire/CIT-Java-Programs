package main;

import gui.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class Starter extends Application{

	public void start(Stage primaryStage) {
		try{
			//creates a new mainWindow instance
			MainWindow window = new MainWindow();
			//calls the startApp method in mainWindow instance that was created
			window.startApp();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		launch(args);
	}

}
