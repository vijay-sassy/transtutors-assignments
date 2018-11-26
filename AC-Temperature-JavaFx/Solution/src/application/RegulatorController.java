package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegulatorController {
	private int value = 18;
	@FXML
	private TextField screen;
	@FXML
	private Button up,down,power;

	public void upAction(ActionEvent ae) {
		if (!screen.getText().isEmpty() && value < 30) {
			screen.setText(++value + "°C");
		}
	}

	public void downAction(ActionEvent ae) {
		if (!screen.getText().isEmpty() && value > 18) {
			screen.setText(--value + "°C");
		}
	}

	public void powerAction(ActionEvent ae) {
		if (screen.getText().isEmpty()) {
			screen.setText(value + "°C");
		} else {
			screen.clear();
		}
	}
}
