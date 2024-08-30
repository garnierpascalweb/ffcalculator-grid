package com.gpwsofts.ffcalculator.grilles.validator;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpwsofts.ffcalculator.grilles.model.InputGrid;
import com.gpwsofts.ffcalculator.grilles.properties.MaxPosProperties;

/**
 * Validation de l'input
 * Verifie que la somme des points est correcte (addition de la somme)
 * Verifie que la liste des points est en bon ordre (decroissant)
 * @author Pascal
 *
 */
@Component
public class InputGridValidator implements Validator<InputGrid> {
	private static Logger logger = LoggerFactory.getLogger(InputGridValidator.class);
	
	@Autowired
	MaxPosProperties maxpos;
	
	@Override
	public void validate(InputGrid inputGrid) throws ValidationException {
		String code = inputGrid.getCode();
		String inputTotalPts = inputGrid.getTotalpts();
		String inputPts = inputGrid.getPts();
		int totalPts = Integer.parseInt(inputTotalPts);
		//int maxPos = inputGrid.get
		// somme des points		
		int sum = Stream.of(inputPts.split(",", -1)).mapToInt(Integer::valueOf).sum();
		// comparaison de totalPts avec sum qui doit etre egal
		if (totalPts != sum){	
			logger.error("<{}> - le nombre total de points <{}> est different de la somme des points", code, totalPts, sum);
			throw new ValidationException(" code <"+ code + "> - grille de points incorrect : totalPts = <" + totalPts + "> - sum = <" + sum + ">");
		}		
		List<Integer> listPts =  Stream.of(inputPts.split(",", -1)).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
		// verification de la logique des pts decroissants		
		List<Integer> sortedPts = listPts.stream().mapToInt(Integer::valueOf).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		boolean sorted = sortedPts.equals(listPts);
		if (!sorted){
			logger.error("<{}> - la grille des points nest pas triée dans l'ordre decroissant", code);
			throw new ValidationException(" code <"+ code + "> - grille de points non triee : <" + listPts + "> <" + sortedPts + ">");
		}
		// verification qu'on ne retrouve pas plusieurs fois la meme valeur de points
		// set etant unique, si al longueur du Set et plus courte que la longueur de la liste, ya un probleme
		Set<Integer> setPts = new HashSet<Integer>();
		setPts.addAll(listPts);
		if (listPts.size() != setPts.size())
			throw new ValidationException(" code <"+ code + "> - doublon de valeur probable dans la liste : <" + listPts + ">");
		// On dirait que le nombre des places recompensees est toujours un miltiple de 5
		int maxPos = listPts.size();
		if (maxPos % 5 != 0){
			if (code.equals("3.58E")){
				logger.warn("<{}> - la taille de la liste nest pas un multiple de 5 mais cest normal : <{}>", code, maxPos);
			} else {
			logger.error("<{}> - la taille de la liste nest pas un multiple de 5 : <{}>", code, maxPos);
			throw new ValidationException(" code <"+ code + "> - la taille de la liste nest pas un multiple de 5 : " + maxPos);
			}
		}
		// verification que maxPos est cohérent avec
		final String propName = code.replace(".", "-");
		final String maxPosValue = maxpos.getMaxpos().getProperty(propName);
		if (maxPosValue != null){
			int maxPosValueInt = Integer.parseInt(maxPosValue);
			if (maxPosValueInt != maxPos)
				throw new ValidationException(" code <"+ code + "> - maxPos est different entre le fichier source csv " + maxPos + " et le fichier de propriété maxpos.properties " + maxPosValueInt);
		} else {
			logger.error("<{}> - maxpos n'a pas pu etre verifiee (absent du fichier de propriete maxpos.properties)", code, maxPos);
		}
	}

}
