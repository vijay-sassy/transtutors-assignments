package application;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SOS {
	
	Label sos; 
	Button cancel;
	Stage stage;
	BorderPane sosLayout; 
	FadeTransition fadeTransition; 
	
	
	SOS(int sizeX, int sizeY, int positionX, int positionY) {
		
		stage = new Stage(); 
		stage.setX(positionX);
		stage.setY(positionY);
		
		sos = new Label("SOS"); 
		sosLayout = new BorderPane(); 
		cancel = new Button("Cancel alert"); 
		cancel.setMaxWidth(Double.MAX_VALUE);
		
		sos.setFont(new Font(250));
		
		fadeTransition = new FadeTransition(Duration.seconds(0.1), sos); 
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
		fadeTransition.play();
		
		sosLayout.setBottom(cancel);
		sosLayout.setCenter(sos);
		
		Scene scene = new Scene(sosLayout, sizeX, sizeY); 
		stage.setTitle("SOS");
		stage.setScene(scene);
		stage.show(); 
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				
				stage.close();

			}
		});
		
		
		
	}

}
