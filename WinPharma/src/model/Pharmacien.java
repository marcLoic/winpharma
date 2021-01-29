package model;

import java.util.Date;

public class Pharmacien {
	
	public int pharmacienID;
	public String username;
	public String pwd;
	public String nom;
	public String prenom;
	public boolean statut;
	public Date dateDeCreation;
	
	public Pharmacien(int pharmacienID) {
		super();
		this.pharmacienID = pharmacienID;
	}

	public Pharmacien(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	
	public Pharmacien(int pharmacienID, String username, String pwd, String nom, String prenom, boolean statut,
			Date dateDeCreation) {
		super();
		this.pharmacienID = pharmacienID;
		this.username = username;
		this.pwd = pwd;
		this.nom = nom;
		this.prenom = prenom;
		this.statut = statut;
		this.dateDeCreation = dateDeCreation;
	}
	
	public Pharmacien(int pharmacienID, String username, String nom, String prenom) {
		super();
		this.pharmacienID = pharmacienID;
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Pharmacien(String username, String pwd, String nom, String prenom, boolean statut, Date dateDeCreation) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.nom = nom;
		this.prenom = prenom;
		this.statut = statut;
		this.dateDeCreation = dateDeCreation;
	}

	public int getPharmacienID() {
		return pharmacienID;
	}
	public void setPharmacienID(int pharmacienID) {
		this.pharmacienID = pharmacienID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public boolean getStatut() {
		return statut;
	}
	public void setStatut(boolean statut) {
		this.statut = statut;
	}
	public Date getDateDeCreation() {
		return dateDeCreation;
	}
	public void setDateDeCreation(Date dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}
}
