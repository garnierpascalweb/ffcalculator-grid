package com.gpwsofts.ffcalculator.grilles.validator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.stereotype.Component;

import com.gpwsofts.ffcalculator.grilles.model.InputGrid;

/**
 * Validation de l'input
 * Verifie que la somme des points est correcte (addition de la somme)
 * Verifie que la liste des points est en bon ordre (decroissant)
 * @author Pascal
 *
 */
@Component
public class InputGridValidator implements Validator<InputGrid> {

	@Override
	public void validate(InputGrid inputGrid) throws ValidationException {
		String code = inputGrid.getCode();
		String inputTotalPts = inputGrid.getTotalpts();
		String inputPts = inputGrid.getPts();
		int totalPts = Integer.parseInt(inputTotalPts);
		// somme des points		
		int sum = Stream.of(inputPts.split(",", -1)).mapToInt(Integer::valueOf).sum();
		// comparaison de totalPts avec sum qui doit etre egal
		if (totalPts != sum){			
			throw new ValidationException(" code <"+ code + "> - grille de points incorrect : totalPts = <" + totalPts + "> - sum = <" + sum + ">");
		}
		List<Integer> listPts =  Stream.of(inputPts.split(",", -1)).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
		List<Integer> sortedPts = listPts.stream().mapToInt(Integer::valueOf).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		boolean sorted = sortedPts.equals(listPts);
		if (!sorted){
			throw new ValidationException(" code <"+ code + "> - grille de points non triee : <" + listPts + "> <" + sortedPts + ">");
		}
	}

}
