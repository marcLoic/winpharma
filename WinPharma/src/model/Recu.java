package model;

import java.util.Date;

public class Recu {

	public int recuID;
	public int quantiter;
	public Date date;
	
	public Recu(int quantiter, Date date) {
		super();
		this.quantiter = quantiter;
		this.date = date;
	}
	public int getQuantiter() {
		return quantiter;
	}
	public void setQuantiter(int quantiter) {
		this.quantiter = quantiter;
	}
	public int getRecuID() {
		return recuID;
	}
	public void setRecuID(int recuID) {
		this.recuID = recuID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
}
