package com.gpwsofts.ffcalculator.grilles.model;

/**
 * Input Grid
 * Classe à l'image du fichier CSV en entree
 * @author Pascal
 *
 */
public class InputGrid {
	/**
	 * champ code
	 */
	public String code = null;
	/**
	 * champ priority
	 */
	public String priority;
	/**
	 * champ logo
	 */
	public String logo = null;
	/**
	 * champ libelle
	 */
	public String libelle = null;
	/**
	 * champ vue
	 */
	public String vue = null;
	/**
	 * champ type
	 */
	public String type = null;
	/**
	 * Champ cal
	 */
	public String cal = null;
	/**
	 * Champ totalpts
	 */
	public String totalpts = null;
	/**
	 * Champ csv pts
	 */
	public String pts = null;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}	
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getVue() {
		return vue;
	}
	public void setVue(String vue) {
		this.vue = vue;
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
	public String getTotalpts() {
		return totalpts;
	}
	public void setTotalpts(String totalpts) {
		this.totalpts = totalpts;
	}
	public String getPts() {
		return pts;
	}
	public void setPts(String pts) {
		this.pts = pts;
	}
	
}
