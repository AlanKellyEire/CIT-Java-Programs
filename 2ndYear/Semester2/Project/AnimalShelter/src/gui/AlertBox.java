package gui;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 * this class contains all the alert boxes.
 */
public class AlertBox {

	public static void error(String title, String message) {
		//creates new error alert
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText(title);
		alert.setContentText(message);
		alert.setWidth(250);
		//Display window and wait for it to be closed before returning
		alert.showAndWait();
	}

	public static void info(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(title);
		alert.setContentText(message);
		alert.setWidth(250);
		alert.showAndWait();
	}

	public static void warn(String title, String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("ABOUT");
		alert.setHeaderText(title);
		alert.setContentText(message);
		alert.setWidth(250);
		alert.showAndWait();
	}

	public static void confirm(String title, String message){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(title);
		alert.setContentText(message);

		ButtonType OK = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(OK);

		Thread thread = new Thread(() -> {
			try {
				// Wait for 3 secs
				Thread.sleep(3000);
				if (alert.isShowing()) {
					Platform.runLater(() -> alert.close());
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		});

		thread.setDaemon(true);
		thread.start();
		alert.showAndWait();
	}

	public static void warn(String title, String message, Stage primaryStage) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(title);
		alert.setContentText(message);
		alert.setWidth(250);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			// ... user chose OK
			Platform.exit();
			System.exit(0);
		} else {
			// ... user chose CANCEL or closed the dialog
		}
	}

	public static void loadData(TopMenuBar tmb){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("LOAD DATA");
		alert.setHeaderText("Would You like to load the Animals And Persons");
		//alert.setContentText("If you choose no there will no da");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			tmb.setAnimalList();
			tmb.setPersonList();
		}
	}

}
