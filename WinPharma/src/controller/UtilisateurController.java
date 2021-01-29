package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DBconnection;
import model.Pharmacien;
import model.Produit;

public class UtilisateurController implements Initializable{

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private TableView<Pharmacien> table;

    @FXML
    private TableColumn<Pharmacien, String> tableNom;

    @FXML
    private TableColumn<Pharmacien, String> tablePrenom;

    @FXML
    private TableColumn<Pharmacien, String> tableUsername;

    @FXML
    private TableColumn<Pharmacien, Boolean> tableStatut;

    @FXML
    private TableColumn<Pharmacien, Date> tableDatedeCreation;

    @FXML
    private JFXButton btnAjouter;

    @FXML
    private JFXButton btnModifier;

    @FXML
    private JFXButton btnSupprimer;

    DBconnection db = new DBconnection();
    ResultSet rs;
    
    static ObservableList<Pharmacien> oblist = FXCollections.observableArrayList();
    FilteredList<Pharmacien> filteredData = new FilteredList<>(oblist, e -> true);
    String st = "";
    Alerts alert = new Alerts();
    int indexOfSelectedRow;
    public static Pharmacien pharmacien;
    
    @FXML
    void ajouterUser(ActionEvent event) throws IOException {
    	Stage stage;
		Parent root;
		if(event.getSource()==btnAjouter) {
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/views/AjouterUtilisateur.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(btnAjouter.getScene().getWindow());
			stage.showAndWait();
		}
		oblist.clear();
		try {
			rs = db.listUser();
			while (rs.next()) {
				oblist.addAll(new Pharmacien(rs.getInt("pharmacienid"), rs.getString("username"), rs.getString("pwd"), 
						rs.getString("nom"), rs.getString("prenom"), rs.getBoolean("statut"), rs.getDate("datedecreation")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setItems(oblist);
    }

    @FXML
    void modifierUser(ActionEvent event) throws IOException {
    	if(table.getSelectionModel().getSelectedItem()==null) {
			st="Choisisez une ligne";
			alert.alertError(st);
		}
		else {
			indexOfSelectedRow = table.getSelectionModel().getSelectedIndex();
			pharmacien = table.getItems().get(indexOfSelectedRow);
			Stage stage;
			Parent root;
			if(event.getSource()==btnModifier) {
				stage = new Stage();
				root = FXMLLoader.load(getClass().getResource("/views/ModifierUtilisateur.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(btnModifier.getScene().getWindow());
				stage.showAndWait();
			}
			oblist.clear();
			try {
				rs = db.listUser();
				while (rs.next()) {
					oblist.addAll(new Pharmacien(rs.getInt("pharmacienid"), rs.getString("username"), rs.getString("pwd"), 
							rs.getString("nom"), rs.getString("prenom"), rs.getBoolean("statut"), rs.getDate("datedecreation")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table.setItems(oblist);
		}
    }

    @FXML
    void searchproduit(KeyEvent event) {
    	txtUsername.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super Pharmacien>) pharmacien -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// String lowzeCaseFilter = newValue.toLowerCase();
				if (String.valueOf(pharmacien.getNom()).contains(newValue)) {
					return true;
				} else {
					return false;
				}
			});
		});
		SortedList<Pharmacien> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
    }

    @FXML
    void supprimerUser(ActionEvent event) throws IOException {
    	if(table.getSelectionModel().getSelectedItem()==null) {
			st="Choisisez une ligne";
			alert.alertError(st);
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION); alert.setTitle("Message");
		    alert.setHeaderText("Confirm la suppression de l'utilisateur");
	   		alert.setContentText(st);
			Optional <ButtonType> action = alert.showAndWait();
			if(action.get() == ButtonType.OK) {
				try {
					int pharmacienID = table.getSelectionModel().getSelectedItem().getPharmacienID();
					Pharmacien ut = new Pharmacien(pharmacienID);
					db.supprimerUtilisateur(ut);
					oblist.clear();
					try {
						rs = db.listUser();
						while (rs.next()) {
							oblist.addAll(new Pharmacien(rs.getInt("pharmacienid"), rs.getString("username"), rs.getString("pwd"), 
									rs.getString("nom"), rs.getString("prenom"), rs.getBoolean("statut"), rs.getDate("datedecreation")));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setItems(oblist);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tableNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		tablePrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		tableUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		tableStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
		tableDatedeCreation.setCellValueFactory(new PropertyValueFactory<>("dateDeCreation"));
		
		try {
			rs = db.listUser();
			while (rs.next()) {
				oblist.addAll(new Pharmacien(rs.getInt("pharmacienid"), rs.getString("username"), rs.getString("pwd"), 
						rs.getString("nom"), rs.getString("prenom"), rs.getBoolean("statut"), rs.getDate("datedecreation")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setItems(oblist);
	}

}
