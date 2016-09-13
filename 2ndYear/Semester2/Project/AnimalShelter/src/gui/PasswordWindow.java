package gui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class PasswordWindow {

	public static void display() {
		Stage window = new Stage();

		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Maintenance Login");
		window.setMinWidth(500);

		//creating a gridpane
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		//creating text and password fields
		TextField username = new TextField();
		//setting the text to be displayed in the textfield
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		//adding labels and text/password fields to the gird.
		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		//creating and setting the action of the login button to display the MaintenanceWindow if the correct credentials have been entered
		//if the wrong credentials entered an alertbox will display
		Button login = new Button("login");
		login.setDisable(true);
		login.setOnAction(e -> {
			if(password.getText().equalsIgnoreCase("pass") && username.getText().equalsIgnoreCase("admin"))
			{
				try {
					MaintenanceWindow maintenanceWindow = new MaintenanceWindow();
					maintenanceWindow.display();
					window.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				AlertBox.error("Wrong Password or Username Entered", "Please Enter the correct credentials");
				username.clear();
				password.clear();
			}
		});

		//creating and setting the action of the cancel button
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> window.close());

		//adding the login/cancel buttons to the gridpane
		grid.add(login, 2, 3);
		grid.add(cancel, 3, 3);

		//adding a listener to the username and password text fields so that user can only click login when textfields arent empty
		username.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String t, String t1) {
				test(username, password, login);
			}
		});
		
		password.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> ov, String t, String t1) {
				test(username, password, login);
			}
		});

		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(grid);
		window.setScene(scene);
		cancel.requestFocus();
		window.showAndWait();

	}
	
	//tests if both username and password field have been edited.
	public static void test(TextField username, TextField password, Button login){
		if(username.getText().isEmpty() || password.getText().isEmpty()){
			login.setDisable(true);
		}
		else
		{
			login.setDisable(false);
		login.setDefaultButton(true);
		}
	}
}
