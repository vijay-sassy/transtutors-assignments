package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class WallController {
	
	BorderPane buttons; 
	Button sos; 
	Button tempControl; 
	Button booking;
	Button test2; 
	Stage stage; 
	Button power;  
	
	
	WallController(int sizeX, int sizeY, int positionX, int positionY) {
		
		stage = new Stage(); 
		stage.setX(positionX);
		stage.setY(positionY);
		
		buttons = new BorderPane(); 
		sos = new Button("SOS"); 
		tempControl = new Button("Temperature Control"); 
		booking = new Button("Doctor Booking"); 
		test2 = new Button("PlaceHolder spot"); 
		power = new Button("Power"); 
		
		
		sos.setMaxWidth(Double.MAX_VALUE);
		sos.setMaxHeight(Double.MAX_VALUE);
		tempControl.setMaxWidth(Double.MAX_VALUE);
		tempControl.setMaxHeight(Double.MAX_VALUE); 
		booking.setMaxWidth(Double.MAX_VALUE);
		booking.setMaxHeight(Double.MAX_VALUE);
		test2.setMaxWidth(Double.MAX_VALUE);
		test2.setMaxHeight(Double.MAX_VALUE);
		
		buttons.setCenter(power);
		
		power.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				
				if (buttons.getBottom() == null) {
					buttons.setBottom(sos);
					buttons.setTop(tempControl);
					buttons.setRight(booking);
					buttons.setLeft(test2);
				}
				else {
					buttons.setBottom(null);
					buttons.setTop(null);
					buttons.setRight(null);
					buttons.setLeft(null);
				}

			}
		});
		
		sos.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				
				new SOS(600, 400, 200, 200); 

			}
		});
		
		tempControl.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent event) {
			//code to open up temperature control interface to be inserted here
			ACLauncher acl = new ACLauncher();
			acl.laucher();
			}
		});
		
		booking.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent event) {
				
				//code to open up doctor booking interface to be inserted here
				
			}
		});

		
		stage.setTitle("Wall Controller");
		
		Scene scene = new Scene(buttons, sizeX, sizeY);
		stage.setScene(scene); 
		
		stage.show(); 
		
	}

}
