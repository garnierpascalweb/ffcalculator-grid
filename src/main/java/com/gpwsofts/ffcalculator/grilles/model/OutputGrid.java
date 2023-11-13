package com.gpwsofts.ffcalculator.grilles.model;

import java.util.List;

/**
 * OutputGrid
 * Classe a l'image du fichier json en sortie
 * @author Pascal
 *
 */
public class OutputGrid {
	/**
	 * code
	 */
	public String code = null;
	/**
	 * libelle
	 */
	public String libelle = null;
	/**
	 * vues
	 */
	public List<String> vues = null;
	/**
	 * type
	 */
	public String type = null;
	/**
	 * cal
	 */
	public String cal = null;
	/**
	 * totalPts
	 */
	public int totalpts = 0;
	/**
	 * pts
	 */
	public List<Integer> pts = null;
	
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
	public List<Integer> getPts() {
		return pts;
	}
	public void setPts(List<Integer> pts) {
		this.pts = pts;
	}			
}
