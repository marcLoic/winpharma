package model;

import java.util.Date;

public class Historique {
	
	public int historiqueID;
	public int quantiter;
	public Date date;
	
	public Historique(int quantiter, Date date) {
		super();
		this.quantiter = quantiter;
		this.date = date;
	}
	public int getHistoriqueID() {
		return historiqueID;
	}
	public void setHistoriqueID(int historiqueID) {
		this.historiqueID = historiqueID;
	}
	public int getQuantiter() {
		return quantiter;
	}
	public void setQuantiter(int quantiter) {
		this.quantiter = quantiter;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
