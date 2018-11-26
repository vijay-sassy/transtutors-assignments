package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerHomeController implements Initializable{
	@FXML
	private Button clear;

	@FXML
	private TextArea logs;
	
	@FXML
	private Button startup;
	
	public void startAction(ActionEvent ae) {
	  ArrayList<String> data = new UDPServer().action(1);
	  for(String a:data) {
	    logs.setText(a + "\n"); 
	  }
	}
	
	public void clearAction(ActionEvent ae) {
	  new UDPServer().action(2);  	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	  new UDPServer().action(3);	
	}
}
