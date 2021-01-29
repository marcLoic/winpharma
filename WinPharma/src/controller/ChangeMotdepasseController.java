package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.DBconnection;
import model.Pharmacien;

public class ChangeMotdepasseController {

    @FXML
    private JFXButton valider;

    @FXML
    private JFXButton annuler;

    @FXML
    private JFXPasswordField txtAncienPWD;

    @FXML
    private JFXPasswordField txtNouveauPWD;

    @FXML
    private JFXPasswordField txtConfirmerPWD;

    String st;
	Alerts alert = new Alerts();
	DBconnection db = new DBconnection();
	
    @FXML
    void annuler(ActionEvent event) {
    	Node closeWindow = (Node) event.getSource();
		Stage stag = (Stage) closeWindow.getScene().getWindow();
		stag.close();
    }

    @FXML
    void valider(ActionEvent event) {
    	if (txtAncienPWD.getText().isEmpty()) {
			st = "Le champ ancien mot de passe est vide";
			alert.alertError(st);
		} else if (txtNouveauPWD.getText().isEmpty()) {
			st = "Le champ nouveau mot de passe est vide";
			alert.alertError(st);
		} else if (txtConfirmerPWD.getText().isEmpty()) {
			st = "Le champ confirmer nouveau mot de passe est vide";
			alert.alertError(st);
		}

		else {
			String aM = txtAncienPWD.getText();
			String nM = txtNouveauPWD.getText();
			String cnM = txtConfirmerPWD.getText();
			long milli = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(milli);
			String pw1 = null;
    		try {
				// Create MessageDigest instance for MD5
				MessageDigest md = MessageDigest.getInstance("MD5");
				// Add password bytes to digest
				md.update(aM.getBytes());
				// Get the hash's bytes
				byte[] bytes = md.digest();
				// This bytes[] has bytes in decimal format;
				// Convert it to hexadecimal format
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				// Get complete hashed password in hex format
				pw1 = sb.toString();
				;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			if (!nM.equals(cnM)) {
				st = "Le nouveau mot de passe ne correspondent pas";
				alert.alertError(st);
			} 
			else if (aM.equals(nM)) {
				st = "Vous ne pouvez pas utiliser le meme mot de passe!";
				alert.alertError(st);
			} 
			else if (!pw1.equals("ee11cbb19052e40b07aac0ca060c23ee")) {
				st = "L'ancien mot de passe est incorect!";
				alert.alertError(st);
			} 
			else {
				try {
					// Create MessageDigest instance for MD5
					MessageDigest md = MessageDigest.getInstance("MD5");
					// Add password bytes to digest
					md.update(nM.getBytes());
					// Get the hash's bytes
					byte[] bytes = md.digest();
					// This bytes[] has bytes in decimal format;
					// Convert it to hexadecimal format
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < bytes.length; i++) {
						sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
					}
					// Get complete hashed password in hex format
					pw1 = sb.toString();
					;
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				try {
					db.updatePassword(pw1);
					st = "Votre mot de passe à été mis à jour";
					alert.alertInformation(st);
					Node closeWindow = (Node) event.getSource();
					Stage stag = (Stage) closeWindow.getScene().getWindow();
					stag.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    }

}
