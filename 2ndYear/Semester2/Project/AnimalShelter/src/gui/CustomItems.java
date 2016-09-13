package gui;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 * this class contains custom items such as labels or button, etc making them easy to find and reducing duplication of code
 */
public class CustomItems {
	//creates label size 20 and colour magenta
	public static Label label(String string){
		Label label = new Label(string);
		label.setFont(new Font("Arial", 20));
		label.setTextFill(Color.DARKMAGENTA);
		return label;
	}
	//closes the stage of the stage passed in
	public static void closeApp(Stage window) {
		window.close();
	}
	
}
