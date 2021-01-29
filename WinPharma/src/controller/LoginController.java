package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Admin;
import model.DBconnection;
import model.Pharmacien;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;

public class LoginController {
	
	@FXML
    private JFXButton loginbtn;
	
	@FXML
    private Text forgottenpwd;
	
	@FXML
	JFXTextField txtUsername;
	
	@FXML
	JFXPasswordField txtPassword;
	
	String st = "";
	Alerts alert = new Alerts();
	OpenAcceuil openAcceuil = new OpenAcceuil();
	public static int id_utilisateur;
	public static boolean gererUtilisateur = Boolean.FALSE;
	
	// Event Listener on JFXButton.onAction
	@FXML
	public void loginIntoApplication(ActionEvent event) throws IOException, SQLException {
		if (txtUsername.getText().isEmpty()) {
			st = "Le champ username est vide";
			alert.alertError(st);
		} else if (txtPassword.getText().isEmpty()) {
			st = "Le champ password est vide";
			alert.alertError(st);
		} else {
			String username = txtUsername.getText();
			String password = txtPassword.getText();
			String pw1 = null;
			try {
				// Create MessageDigest instance for MD5
				MessageDigest md = MessageDigest.getInstance("MD5");
				// Add password bytes to digest
				md.update(password.getBytes());
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
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			Pharmacien pharmacien = new Pharmacien(username, pw1);
			Admin admin = new Admin(username, password);
			DBconnection dbc = new DBconnection();
			ResultSet rsSelectUser = dbc.selectUser(pharmacien);
			ResultSet rsSelectAdmin = dbc.selectAdmin(admin);
			if(rsSelectAdmin.next()) {
				gererUtilisateur = true;
				id_utilisateur = rsSelectAdmin.getInt("adminid");
				openAcceuil.open("/views/Acceuil.fxml");
				System.out.println(new Date());
				Node closeWindow = (Node) event.getSource();
				Stage stage = (Stage) closeWindow.getScene().getWindow();
				stage.close();
			}
			else if (rsSelectUser.next()) {
				if(rsSelectUser.getString("pwd").equals("ee11cbb19052e40b07aac0ca060c23ee")) {
					id_utilisateur = rsSelectUser.getInt("pharmacienid");
					Stage stage11;
					Parent root11;
					if(event.getSource()==loginbtn) {						
						stage11 = new Stage();
						FXMLLoader  loader = new FXMLLoader(getClass().getResource("/views/ChangerMotdepasse.fxml"));
						root11 = loader.load();
						Scene scene = new Scene(root11);
						stage11.setScene(scene);
						stage11.initModality(Modality.APPLICATION_MODAL);
						stage11.initOwner(loginbtn.getScene().getWindow());
						stage11.showAndWait();
					}
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
					primaryStage.centerOnScreen();
					primaryStage.initStyle(StageStyle.UNDECORATED);
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					Node closeWindow = (Node) event.getSource();
					Stage stage = (Stage) closeWindow.getScene().getWindow();
					stage.close();
				} else {
					openAcceuil.open("/views/Acceuil.fxml");
					id_utilisateur = rsSelectUser.getInt("pharmacienid");
					System.out.println(new Date());
					Node closeWindow = (Node) event.getSource();
					Stage stage = (Stage) closeWindow.getScene().getWindow();
					stage.close();
				}
			} else {
				st = "Invalid account";
				alert.alertError(st);
				txtUsername.clear();
				txtPassword.clear();
			}
		}
	}
	
	public void closeapp(MouseEvent event) throws IOException {
		Node closeWindow = (Node) event.getSource();
		Stage stage = (Stage) closeWindow.getScene().getWindow();
		stage.close();
	}
}
