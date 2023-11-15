package com.gpwsofts.ffcalculator.grilles.processor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpwsofts.ffcalculator.grilles.model.InputGrid;
import com.gpwsofts.ffcalculator.grilles.model.OutputGrid;
import com.gpwsofts.ffcalculator.grilles.validator.InputGridValidator;

/**
 * ItemProcessor
 * Permet de transformer InputGrid en OutputGrid
 * Valide le fichier en entrée (saisi manuellement) par InputGridValidator
 * @author Pascal
 *
 */
@Component
public class GridItemProcessor implements ItemProcessor<InputGrid,OutputGrid> {

	/**
	 * Validation du contenu du fichier en entrée
	 */
	@Autowired InputGridValidator validator;
	
	@Override
	public OutputGrid process(InputGrid inputGrid) throws Exception {		
		String inputCode = null;
		String inputLogo = null;
		String inputLibelle = null;
		String inputCal = null;
		String inputTotalPts = null;
		String inputType = null;
		String inputVue = null;
		String inputPts = null;		
		OutputGrid outputGrid = null;
		try{		
			validator.validate(inputGrid);
			outputGrid = new OutputGrid();
			// code;libelle;vue;type;cal;totalpts;pts
			//code
			inputCode = inputGrid.getCode();
			outputGrid.setCode(inputCode);
			// logo
			inputLogo = inputGrid.getLogo();
			outputGrid.setLogo(inputLogo);
			// libelle
			inputLibelle = inputGrid.getLibelle();
			outputGrid.setLibelle(inputLibelle);
			// vue
			inputVue = inputGrid.getVue();			
			outputGrid.setVues(Stream.of(inputVue.split(",", -1)).collect(Collectors.toList()));
			// type
			inputType = inputGrid.getType();
			outputGrid.setType(inputType);
			// cal
			inputCal = inputGrid.getCal();
			outputGrid.setCal(inputCal);			
			// totalPts
			inputTotalPts = inputGrid.getTotalpts();
			outputGrid.setTotalpts(Integer.parseInt(inputTotalPts));
			// pts et maxpos
			inputPts = inputGrid.getPts();
			outputGrid.setPts(Stream.of(inputPts.split(",", -1)).map(Integer::parseInt).collect(Collectors.toList()));
			outputGrid.setMaxPos(inputPts.length());
		} finally {
			
		}		
		return outputGrid;
	}
}
