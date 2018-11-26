package application;

import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientMainController implements Initializable {
	@FXML
	private TextField mobileNumber;

	@FXML
	private TextField pinNumber;

	@FXML
	private ComboBox<String> reason;

	@FXML
	private Button transmit;

	@FXML
	private Button clear;

	@FXML
	private Label status;

	@FXML
	private Label locationOp;

	public void setLocation(String location) {
		locationOp.setText(location);
	}

	public void sendAction(ActionEvent ae) {
		try {
			String update = new UDPClient().send(locationOp.getText(), mobileNumber.getText(), pinNumber.getText(),
					reason.getValue());
			status.setText(update);
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		}
	}

	public void clearAction(ActionEvent ae) {
		mobileNumber.clear();
		pinNumber.clear();
		reason.setValue(null);
		status.setText(null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> obList = FXCollections.observableArrayList("Sign In", "Sign Out");
		reason.setItems(obList);
	}
}
