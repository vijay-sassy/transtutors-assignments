package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
  @FXML
  private Button clientButton;
  
  @FXML
  private Button serverButton;
  
  public void clientAction(ActionEvent ae) {
	  try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ClientHome.fxml"));
			Scene scene = new Scene(root,254,154);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setTitle("Hills School");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
  }
  
  public void serverAction(ActionEvent ae) {
	  try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ServerHome.fxml"));
			Scene scene = new Scene(root,615,155);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setTitle("Server Running..");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
  }
}
