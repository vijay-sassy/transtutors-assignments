package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ClientHomeController implements Initializable{
	@FXML
	private ComboBox<String> locationIp;

	@FXML
	private Button submit;

	@FXML
	private Button close;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
      ObservableList<String> obList = FXCollections.observableArrayList(
        "Melbourne","Sydney","Brisbane"
      );
      locationIp.setItems(obList);	
	}

	public void submitAction(ActionEvent ae) {
		System.out.println(locationIp.getValue());
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("ClientMain.fxml"));
			Scene scene = new Scene(fxmlLoader.load(),691,154);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setTitle("Hills School--"+locationIp.getValue());
			stage.setScene(scene);
			stage.show();
		
		ClientMainController cmc = fxmlLoader.getController();
		cmc.setLocation(locationIp.getValue());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    public void closeAction(ActionEvent ae) {
    	Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
	}
}
