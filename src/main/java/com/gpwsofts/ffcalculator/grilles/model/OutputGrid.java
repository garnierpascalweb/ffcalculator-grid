package com.gpwsofts.ffcalculator.grilles.model;

import java.util.List;

public class OutputGrid {
	public String code = null;
	public String libelle = null;
	public List<String> vues = null;
	public String type = null;
	public String cal = null;
	public int totalpts = 0;
	public int[] pts = null;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}	
	public List<String> getVues() {
		return vues;
	}
	public void setVues(List<String> vues) {
		this.vues = vues;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCal() {
		return cal;
	}
	public void setCal(String cal) {
		this.cal = cal;
	}
	public int getTotalpts() {
		return totalpts;
	}
	public void setTotalpts(int totalpts) {
		this.totalpts = totalpts;
	}
	public int[] getPts() {
		return pts;
	}
	public void setPts(int[] pts) {
		this.pts = pts;
	}
	
	
}
