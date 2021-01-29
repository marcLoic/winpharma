package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.DBconnection;
import model.Pharmacien;

public class ModifierUtilisateurController implements Initializable {

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtPrenom;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private Label labelDate;

    @FXML
    private JFXButton valider;

    @FXML
    private JFXButton annuler;
    
    DBconnection db = new DBconnection();
    ResultSet rs;
    String st = "";
    Alerts alerts = new Alerts();
    public Pharmacien pharmaciens = controller.UtilisateurController.pharmacien;

    @FXML
    void annuler(ActionEvent event) {
    	Node closeWindow = (Node) event.getSource();
		Stage stage1 =(Stage) closeWindow.getScene().getWindow();
		stage1.close();
    }

    @FXML
    void valider(ActionEvent event) {
    	if(txtNom.getText().isEmpty()) {
			st = "Veillez entrer le nom";
			alerts.alertError(st);
		}
		else if(txtPrenom.getText().isEmpty()) {
			st = "Veillez entrer le prenom";
			alerts.alertError(st);
		}
		else if(txtUsername.getText().isEmpty()) {
			st = "Veillez entrer le username";
			alerts.alertError(st);
		}
    	else {
    		String nom = txtNom.getText();
        	String prenom = txtPrenom.getText();
    		String username = txtUsername.getText();
    		Pharmacien pharmacien = new Pharmacien(pharmaciens.getPharmacienID(), username, nom, prenom);
    		try {
    			Alert alert = new Alert(AlertType.CONFIRMATION); alert.setTitle("Message");
    		    alert.setHeaderText("Confirmer");
    	   		alert.setContentText(st);
    			Optional <ButtonType> action = alert.showAndWait();
    			if(action.get() == ButtonType.OK) {
    				db.modifierUtilisateur(pharmacien);
    			}
    			controller.UtilisateurController.oblist.clear();
    			rs = db.listUser();
    			while (rs.next()) {
    				controller.UtilisateurController.oblist.addAll(new Pharmacien(rs.getInt("pharmacienid"), rs.getString("username"), 
    						rs.getString("pwd"), rs.getString("nom"), rs.getString("prenom"), rs.getBoolean("statut"), 
    						rs.getDate("datedecreation")));
    			}
    			st = "Modifier avec success";
    			alerts.alertInformation(st);
    			Node closeWindow = (Node) event.getSource();
    			Stage stage1 =(Stage) closeWindow.getScene().getWindow();
    			stage1.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		long milli=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(milli);
		DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		String stringdate = dateformat.format(date);
		labelDate.setText(stringdate);
		
		txtNom.setText(pharmaciens.getNom());
		txtPrenom.setText(pharmaciens.getPrenom());
		txtUsername.setText(pharmaciens.getUsername());
	}
}
