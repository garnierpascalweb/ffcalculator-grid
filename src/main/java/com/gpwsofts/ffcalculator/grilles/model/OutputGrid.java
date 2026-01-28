package com.gpwsofts.ffcalculator.grilles.model;

import java.util.List;

/**
 * OutputGrid
 * Classe a l'image du fichier json en sortie
 * @author Pascal
 *
 */
public class OutputGrid implements Comparable {
	/**
	 * code : la classe de course (exemple 1.25.1)
	 */
	public String code = null;
	/**
	 * priority : la priorité pour affichage (exemple 104)
	 */
	public int priority;
	/**
	 * logo : un abrege du libelle
	 */	
	public String logo = null;
	/**
	 * shortLabel : le label court (exemple : Open 3 Access)
	 */	
	public String shortLabel = null;
	/**
	 * longLabel : le label long (exemple : Open 3 Access (1.25.1))
	 */
	public String longLabel = null;
	/**
	 * vues : la liste des vues elligibles
	 */
	public List<String> vues = null;
	/**
	 * type : le type de course (en ligne, etape, general)
	 */
	public String type = null;
	/**
	 * cal : le calendrier concerné (reg, nat, uci)
	 */
	public String cal = null;
	/**
	 * maxpos : le nombre de places dans les points
	 */
	public int maxPos;
	/**
	 * totalPts : le nombre total de points distribués
	 */
	public int totalpts = 0;
	/**
	 * pts : un tableau de la liste des points distribues
	 */
	public List<Integer> pts = null;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
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
	
	public int getMaxPos() {
		return maxPos;
	}
	public void setMaxPos(int maxPos) {
		this.maxPos = maxPos;
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
	
	public String getShortLabel() {
		return shortLabel;
	}
	public void setShortLabel(String shortLabel) {
		this.shortLabel = shortLabel;
	}
	public String getLongLabel() {
		return longLabel;
	}
	public void setLongLabel(String longLabel) {
		this.longLabel = longLabel;
	}
	@Override
	public int compareTo(Object other) {
		return code.compareTo(((OutputGrid)other).getCode());
	}	
}
