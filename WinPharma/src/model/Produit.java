package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Produit extends RecursiveTreeObject<Produit> {
	
	public int produitID;
	public String nom;	
	public String presentation;
	public int prixAchat;
	public int marge;
	public int prixVenteFRSP;
	public int prixVenteFOSA;
	public int qteJour;
	public int qteNuit;
	public int montantJour;
	public int montantNuit;
	
	public Produit(String nom, String presentation, int prixAchat, int prixVenteFRSP, int prixVenteFOSA, int qteJour,
			int qteNuit, int montantJour, int montantNuit) {
		super();
		this.nom = nom;
		this.presentation = presentation;
		this.prixAchat = prixAchat;
		this.prixVenteFRSP = prixVenteFRSP;
		this.prixVenteFOSA = prixVenteFOSA;
		this.qteJour = qteJour;
		this.qteNuit = qteNuit;
		this.montantJour = montantJour;
		this.montantNuit = montantNuit;
	}
	
	public Produit(int produitID, String nom, String presentation, int qteJour, int qteNuit, int montantJour,
			int montantNuit) {
		super();
		this.produitID = produitID;
		this.nom = nom;
		this.presentation = presentation;
		this.qteJour = qteJour;
		this.qteNuit = qteNuit;
		this.montantJour = montantJour;
		this.montantNuit = montantNuit;
	}

	public Produit(int produitID, String nom, String presentation, int qteJour, int montantJour) {
		super();
		this.produitID = produitID;
		this.nom = nom;
		this.presentation = presentation;
		this.qteJour = qteJour;
		this.montantJour = montantJour;
	}

	public Produit(String nom, String presentation, int qteJour, int montantJour) {
		super();
		this.nom = nom;
		this.presentation = presentation;
		this.qteJour = qteJour;
		this.montantJour = montantJour;
	}

	public int getProduitID() {
		return produitID;
	}
	public void setProduitID(int produitID) {
		this.produitID = produitID;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPresentation() {
		return presentation;
	}
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	public int getPrixAchat() {
		return prixAchat;
	}
	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}
	public int getMarge() {
		return marge;
	}
	public void setMarge(int marge) {
		this.marge = marge;
	}
	public int getPrixVenteFRSP() {
		return prixVenteFRSP;
	}
	public void setPrixVenteFRSP(int prixVenteFRSP) {
		this.prixVenteFRSP = prixVenteFRSP;
	}
	public int getPrixVenteFOSA() {
		return prixVenteFOSA;
	}
	public void setPrixVenteFOSA(int prixVenteFOSA) {
		this.prixVenteFOSA = prixVenteFOSA;
	}
	public int getQteJour() {
		return qteJour;
	}
	public void setQteJour(int qteJour) {
		this.qteJour = qteJour;
	}
	public int getQteNuit() {
		return qteNuit;
	}
	public void setQteNuit(int qteNuit) {
		this.qteNuit = qteNuit;
	}
	public int getMontantJour() {
		return montantJour;
	}
	public void setMontantJour(int montantJour) {
		this.montantJour = montantJour;
	}
	public int getMontantNuit() {
		return montantNuit;
	}
	public void setMontantNuit(int montantNuit) {
		this.montantNuit = montantNuit;
	}
	
	
}
