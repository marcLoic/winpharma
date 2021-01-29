package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.DBconnection;
import model.Historique;
import model.Produit;

public class Ajouter1Controller {

	@FXML
    private JFXTextField txtPresentation;

    @FXML
    private JFXTextField txtPuVenteFosa;

    @FXML
    private JFXTextField txtPuVenteFrsp;
    
    @FXML
    private JFXTextField txtQuantiterJour;

    @FXML
    private JFXTextField txtQuantiterNuit;

    @FXML
    private JFXTextField txtMtnJour;

    @FXML
    private JFXTextField txtMtnNuit;

    @FXML
    private JFXButton valider;

    @FXML
    private JFXButton annuler;

    @FXML
    private JFXTextField txtPuAchat;

    @FXML
    private JFXTextField txtNom;
    
    String st = "";
    Alerts alert = new Alerts();

    @FXML
    void annuler(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/views/Acceuil.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Node closeWindow = (Node) event.getSource();
		Stage stage = (Stage) closeWindow.getScene().getWindow();
		stage.close();
    }

    @FXML
    void valider(ActionEvent event) {
    	if (txtNom.getText().isEmpty()) {
			st = "Le champ nom est vide";
			alert.alertError(st);
		} else if (txtPresentation.getText().isEmpty()) {
			st = "Le champ presentation est vide";
			alert.alertError(st);
		} else if (txtPuAchat.getText().isEmpty()) {
			st = "Le champ PU achat est vide";
			alert.alertError(st);
		} else if (txtPuVenteFrsp.getText().isEmpty()) {
			st = "Le champ PU Vente frsp est vide";
			alert.alertError(st);
		} else if (txtPuVenteFosa.getText().isEmpty()) {
			st = "Le champ PU Vente fosa est vide";
			alert.alertError(st);
		} else if (txtQuantiterJour.getText().isEmpty()) {
			st = "Le champ quantiter jour est vide";
			alert.alertError(st);
		} else if (txtQuantiterNuit.getText().isEmpty()) {
			st = "Le champ quantiter nuit est vide";
			alert.alertError(st);
		} else if (txtMtnJour.getText().isEmpty()) {
			st = "Le champ montant jour est vide";
			alert.alertError(st);
		} else if (txtMtnNuit.getText().isEmpty()) {
			st = "Le champ montant nuit est vide";
			alert.alertError(st);
		} else {
			if(!valideNumber(txtPuAchat.getText())) {
				st = "Verifier que la valeur PU achat est valide - ";
				alert.alertError(st);
			}
			else if(!valideNumber(txtPuVenteFrsp.getText())) {
				st = "Verifier que la valeur PU Vente frsp est valide - ";
				alert.alertError(st);
			}
			else if (txtPuVenteFosa.getText().length()>5) {
				st = "Verifier que la valeur PU Vente fosa est valide - ";
				alert.alertError(st);
			}
			if(!valideNumber(txtQuantiterJour.getText())) {
				st = "Verifier que la valeur quantiter jour est valide - ";
				alert.alertError(st);
			}
			if(!valideNumber(txtQuantiterNuit.getText())) {
				st = "Verifier que la valeur quantiter nuit est valide - ";
				alert.alertError(st);
			}
			else if(!valideNumber(txtMtnJour.getText())) {
				st = "Verifier que la valeur montant jour est valide - ";
				alert.alertError(st);
			}
			else if (!valideNumber(txtMtnNuit.getText())) {
				st = "Verifier que la valeur montant nuit est valide - ";
				alert.alertError(st);
			}
			else {
				String nom = txtNom.getText();
				String presentation = txtPresentation.getText();
				int puAchat = Integer.parseInt(txtPuAchat.getText());
				int puVenteFrsp = Integer.parseInt(txtPuVenteFrsp.getText());
				int puVenteFosa = Integer.parseInt(txtPuVenteFosa.getText());
				int quantiterjour = Integer.parseInt(txtQuantiterJour.getText());
				int quantiternuit = Integer.parseInt(txtQuantiterNuit.getText());
				int mtnJour = Integer.parseInt(txtMtnJour.getText());
				int mtnNuit = Integer.parseInt(txtMtnNuit.getText());
				long milli = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(milli);
				int quantitertotal = quantiterjour + quantiternuit; 
				Historique h = new Historique(quantitertotal, date);
				Produit pt = new Produit(nom, presentation, puAchat, puVenteFrsp, puVenteFosa, quantiterjour, quantiternuit, mtnJour, mtnNuit);
				DBconnection dbconnection = new DBconnection();
				try {
					/*for(int i=0; i<=5; i++) {
						dbconnection.ajout1(pt, h);
					}*/
					dbconnection.ajout1(pt, h);
					st = "Enregistré avec success";
					alert.alertInformation(st);
					txtNom.clear();
					txtPresentation.clear();
					txtPuAchat.clear();
					txtPuVenteFrsp.clear();
					txtPuVenteFosa.clear();
					txtQuantiterJour.clear();
					txtQuantiterNuit.clear();
					txtMtnJour.clear();
					txtMtnNuit.clear();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
    
    @FXML
    void check(KeyEvent event) {
    	txtPuAchat.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	txtPuAchat.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	txtPuVenteFrsp.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	txtPuVenteFrsp.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	txtPuVenteFosa.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	txtPuVenteFosa.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	txtQuantiterJour.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	txtQuantiterJour.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	txtQuantiterNuit.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	txtQuantiterNuit.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	txtMtnJour.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	txtMtnJour.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	txtMtnNuit.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	txtMtnNuit.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    
    public boolean valideNumber(String text) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(text);
		if(m.find() && m.group().equals(text)) {
			return true;
		}
		else {
			return false;
		}
	}
}
