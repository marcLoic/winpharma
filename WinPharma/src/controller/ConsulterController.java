package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.DBconnection;
import model.Produit;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class ConsulterController implements Initializable {

	@FXML
    private JFXTextField txtSearchProduit;

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
    
    ObservableList<Produit> oblist = FXCollections.observableArrayList();
    FilteredList<Produit> filteredData = new FilteredList<>(oblist, e -> true);
    ResultSet rs;
    DBconnection db = new DBconnection();
    
    @FXML
    void searchproduit(KeyEvent event) {
    	txtSearchProduit.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super Produit>) produit -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// String lowzeCaseFilter = newValue.toLowerCase();
				if (String.valueOf(produit.getNom()).contains(newValue)) {
					return true;
				} else {
					return false;
				}
			});
		});
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table1.comparatorProperty());
		table1.setItems(sortedData);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tableNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
		tablePresetation1.setCellValueFactory(new PropertyValueFactory<>("presentation"));
		tableQuantiterJour1.setCellValueFactory(new PropertyValueFactory<>("qteJour"));
		tableQuantiterNuit1.setCellValueFactory(new PropertyValueFactory<>("qteNuit"));
		tableMontantJour1.setCellValueFactory(new PropertyValueFactory<>("montantJour"));
		tableMontantNuit1.setCellValueFactory(new PropertyValueFactory<>("montantNuit"));
		
		try {
			rs = db.selectProduit();
			while (rs.next()) {
				oblist.addAll(new Produit(rs.getInt("produitID"), rs.getString("nom"), rs.getString("presentation"), 
						rs.getInt("qteJour"), rs.getInt("qteNuit"), rs.getInt("montantJour"), rs.getInt("montantJour")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table1.setItems(oblist);
	}
    
}
