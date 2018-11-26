package application;
	
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ACLauncher {
	public void laucher() {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("regulator.fxml"));
			Scene scene = new Scene(root,474,328);
			Stage stage = new Stage();
			stage.setTitle("AC Regulator");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
