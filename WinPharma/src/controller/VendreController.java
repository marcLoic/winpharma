package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.DBconnection;
import model.Produit;
import model.Recu;
import model.RecuProduit;

public class VendreController implements Initializable{

	 @FXML
	    private JFXTextField txtQuantiter;

	    @FXML
	    private JFXButton valider;

	    @FXML
	    private JFXButton annuler;

	    @FXML
	    private JFXButton ajouter;

	    @FXML
	    private JFXButton supprimer;

	    @FXML
	    private TableView<Produit> table1;

	    @FXML
	    private TableColumn<Produit, String> tableNom1;

	    @FXML
	    private TableColumn<Produit, String> tablePresetation1;

	    @FXML
	    private TableColumn<Produit, Integer> tableQuantiterJour1;

	    @FXML
	    private TableColumn<Produit, Integer> tableMontantJour1;

	    @FXML
	    private TableColumn<Produit, Integer> tableQuantiterNuit1;

	    @FXML
	    private TableColumn<Produit, Integer> tableMontantNuit1;

	    @FXML
	    private TableView<Produit> table2;

	    @FXML
	    private TableColumn<Produit, String> tableNom2;

	    @FXML
	    private TableColumn<Produit, String> tablePresetation2;

	    @FXML
	    private TableColumn<Produit, Integer> tableQuantiter2;

	    @FXML
	    private TableColumn<Produit, Integer> tableMontant2;

    
    ObservableList<Produit> olistTable1= FXCollections.observableArrayList();
    ObservableList<Produit> olistTable2 = FXCollections.observableArrayList();
    ObservableList<Produit> olistTable3 = FXCollections.observableArrayList();
    ObservableList<Produit> olistProduit = FXCollections.observableArrayList();
    Alerts alert = new Alerts();
	DBconnection db = new DBconnection();
	ResultSet rs;
	String st = "";

    @FXML
    void ajouter(ActionEvent event) {
    	if(txtQuantiter.getText().isEmpty()) {
			st = "Vous n'avez pas entré la quantiter du produit -";
			alert.alertError(st);
		}
    	else if (!valideNumber(txtQuantiter.getText())) {
			st = "Verifier que la valeur quantiter est valide - ";
			alert.alertError(st);
		}
		else if(table1.getSelectionModel().getSelectedItem()==null) {
			st="Choisisez une ligne";
			alert.alertError(st);
		}
		else {
			int qteOriginal = table1.getSelectionModel().getSelectedItem().getQteJour();
			int quantiter = Integer.parseInt(txtQuantiter.getText());
			if(quantiter > qteOriginal) {
				st = "La quantiter renseigné est plus que le stock disponible - ";
				alert.alertError(st);
			}
			else {
				int qteUpdate = qteOriginal - quantiter;
				int produitid = table1.getSelectionModel().getSelectedItem().getProduitID();
				String nomProduit = table1.getSelectionModel().getSelectedItem().getNom();
				String presentationProduit = table1.getSelectionModel().getSelectedItem().getPresentation();
				int montant= table1.getSelectionModel().getSelectedItem().getMontantNuit();
				Produit produit = new Produit(produitid, nomProduit, presentationProduit, quantiter, montant);
				olistTable2.add(produit);
				table2.setItems(olistTable2);
				txtQuantiter.clear();
				int index = table1.getSelectionModel().getSelectedIndex();
				olistTable1.get(index).setQteJour(qteUpdate);
				olistProduit.clear();
				for(Produit pro: olistTable1) {
					olistProduit.add(pro);
				}
				olistTable1.removeAll(olistTable1);
				for(Produit pro: olistProduit) {
					olistTable1.add(pro);
				}
			}
		}
    }

    @FXML
    void annuler(ActionEvent event) {
    	
    }

    @FXML
    void clearPresentation(ActionEvent event) {
    	//comboPresentation.getItems().clear();
    }

    @FXML
    void supprimer(ActionEvent event) {
    	if(table2.getSelectionModel().getSelectedItem()==null) {
			st="Choisisez une ligne";
			alert.alertError(st);
		}
		else {
			int produitid = table2.getSelectionModel().getSelectedItem().getProduitID();
			int quantiter = table2.getSelectionModel().getSelectedItem().getQteJour();
			table2.getItems().removeAll(table2.getSelectionModel().getSelectedItem());
			int i = 0;
			int previousQTE = 0;
			for(Produit pro: olistTable1) {
				if(pro.getProduitID() == produitid) {
					previousQTE = pro.getQteJour() + quantiter;
					olistTable1.get(i).setQteJour(previousQTE);
				}
				i++;
			}
			olistProduit.clear();
			for(Produit pro: olistTable1) {
				olistProduit.add(pro);
			}
			olistTable1.removeAll(olistTable1);
			for(Produit pro: olistProduit) {
				olistTable1.add(pro);
			}
		}
    }

    @FXML
    void valider(ActionEvent event) {
    	int qte=0;
    	for(int i=0; i<table2.getItems().size(); i++) {
    		Produit produit = table2.getItems().get(i);
    		qte = produit.getQteJour() + qte;
    	}
    	long milli = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(milli);
		Recu recu = new Recu(qte, date);
    	int recuid=0;
		try {
			recuid = db.insertRecu(recu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for(int i=0; i<table2.getItems().size(); i++) {
    		try {
				Produit produit = table2.getItems().get(i);
				db.updateProduit(produit);
				RecuProduit recuproduit = new RecuProduit(produit.getProduitID(), recuid, produit.getQteJour(), produit.getMontantJour());
				db.insertRecuProduit(recuproduit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    @FXML
    void selectNom(KeyEvent event) {
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tableNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
		tablePresetation1.setCellValueFactory(new PropertyValueFactory<>("presentation"));
		tableQuantiterJour1.setCellValueFactory(new PropertyValueFactory<>("qteJour"));
		tableQuantiterNuit1.setCellValueFactory(new PropertyValueFactory<>("qteNuit"));
		tableMontantJour1.setCellValueFactory(new PropertyValueFactory<>("montantJour"));
		tableMontantNuit1.setCellValueFactory(new PropertyValueFactory<>("montantNuit"));
		
		tableNom2.setCellValueFactory(new PropertyValueFactory<>("nom"));
		tablePresetation2.setCellValueFactory(new PropertyValueFactory<>("presentation"));
		tableQuantiter2.setCellValueFactory(new PropertyValueFactory<>("qteJour"));
		tableMontant2.setCellValueFactory(new PropertyValueFactory<>("montantJour"));
		
		try {
			rs = db.selectProduit();
			while (rs.next()) {
				olistTable1.addAll(new Produit(rs.getInt("produitID"), rs.getString("nom"), rs.getString("presentation"), 
						rs.getInt("qteJour"), rs.getInt("qteNuit"), rs.getInt("montantJour"), rs.getInt("montantJour")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table1.setItems(olistTable1);
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
