package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;

public class AcceuilController implements Initializable {
	@FXML
	private JFXButton vendre;
	
	@FXML
	private JFXButton ajouter;
	
	@FXML
	private JFXButton consulter;
	
	@FXML
	private JFXButton bilan;
	
	@FXML
	private JFXButton quitter;
	
	@FXML
    private BorderPane mainPane;
	
	@FXML
	private JFXButton gestionUtilisateur;
	
	public static String st = "Welcome";
	public boolean gererUtilisateur = controller.LoginController.gererUtilisateur;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		gestionUtilisateur.setVisible(gererUtilisateur);
		FmxlLoader object = new FmxlLoader();
		Pane view = object.getPage(st);
		mainPane.setCenter(view);
	}

	// Event Listener on JFXButton[#vendre].onAction
	@FXML
	public void vendre(ActionEvent event) {
		FmxlLoader object = new FmxlLoader();
		Pane view = object.getPage("Vendre");
		mainPane.setCenter(view);
	}
	
	// Event Listener on JFXButton[#ajouter].onAction
	@FXML
	public void ajouter(ActionEvent event) {
		FmxlLoader object = new FmxlLoader();
		Pane view = object.getPage("Ajouter1");
		mainPane.setCenter(view);		
	}
	
	// Event Listener on JFXButton[#consulter].onAction
	@FXML
	public void consulter(ActionEvent event) {
		FmxlLoader object = new FmxlLoader();
		Pane view = object.getPage("Consulter");
		mainPane.setCenter(view);
	}
	
	// Event Listener on JFXButton[#bilan].onAction
	@FXML
	public void bilan(ActionEvent event) {
		// TODO Autogenerated
	}
	
	// Event Listener on JFXButton[#quitter].onAction
	@FXML
	public void quitter(ActionEvent event) {
		Node closeWindow = (Node) event.getSource();
		Stage stage = (Stage) closeWindow.getScene().getWindow();
		stage.close();
	}
	
	@FXML
    void gestionUtilisateur(ActionEvent event) {
		FmxlLoader object = new FmxlLoader();
		Pane view = object.getPage("Utilisateur");
		mainPane.setCenter(view);
    }
	
}
