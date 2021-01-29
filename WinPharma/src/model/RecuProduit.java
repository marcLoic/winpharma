package model;

public class RecuProduit {

	public int produitID;
	public int recuID;
	public int quatiter;
	public int montant;
	
	public RecuProduit(int produitID, int recuID, int quatiter, int montant) {
		super();
		this.produitID = produitID;
		this.recuID = recuID;
		this.quatiter = quatiter;
		this.montant = montant;
	}
	public int getProduitID() {
		return produitID;
	}
	public void setProduitID(int produitID) {
		this.produitID = produitID;
	}
	public int getRecuID() {
		return recuID;
	}
	public void setRecuID(int recuID) {
		this.recuID = recuID;
	}
	public int getQuatiter() {
		return quatiter;
	}
	public void setQuatiter(int quatiter) {
		this.quatiter = quatiter;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
}
