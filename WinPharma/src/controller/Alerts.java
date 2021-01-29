package controller;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	
	public Alerts() {
		
	}
	
	public void alertError(String st) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error message");
		alert.setHeaderText("Error");
		alert.setContentText(st);
		alert.showAndWait();
	}
	
	public void alertInformation(String st) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information message");
		alert.setHeaderText("BIEN");
		alert.setContentText(st);
		alert.showAndWait();
	}
	
	public Boolean alertConfirmation(String st) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Message");
		alert.setHeaderText("Confirmer");
		alert.setContentText(st);
		Optional <ButtonType> action = alert.showAndWait();
		if(action.get() == ButtonType.OK) {
			return true;
		}
		else {
			return false;
		}
	}
}
