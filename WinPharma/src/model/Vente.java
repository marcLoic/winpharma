package model;

import java.util.Date;

public class Vente {
	
	public int venteID;
	public int quantiter;
	public Date date;
	
	public Vente(int quantiter, Date date) {
		super();
		this.quantiter = quantiter;
		this.date = date;
	}
	public int getVenteID() {
		return venteID;
	}
	public void setVenteID(int venteID) {
		this.venteID = venteID;
	}
	public Date getDate() {
		return date;
	}
	public int getQuantiter() {
		return quantiter;
	}
	public void setQuantiter(int quantiter) {
		this.quantiter = quantiter;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
