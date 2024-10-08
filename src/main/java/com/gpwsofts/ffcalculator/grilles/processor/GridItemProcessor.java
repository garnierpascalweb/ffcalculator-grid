package com.gpwsofts.ffcalculator.grilles.processor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static Logger logger = LoggerFactory.getLogger(GridItemProcessor.class);
		
	/**
	 * Validation du contenu du fichier en entrée
	 */
	@Autowired InputGridValidator validator;
	
	@Override
	public OutputGrid process(InputGrid inputGrid) throws Exception {		
		String inputCode = null;
		String inputPriority;
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
			logger.info("<{}> - traitement de la classe de course", inputCode);
			outputGrid.setCode(inputCode);
			// priority
			inputPriority = inputGrid.getPriority();
			outputGrid.setPriority(Integer.parseInt(inputPriority));
			logger.trace("<{}> - priority", outputGrid.getPriority());
			// logo
			inputLogo = inputGrid.getLogo();
			outputGrid.setLogo(inputLogo);
			logger.trace("<{}> - logo", outputGrid.getLogo());
			// libelle
			inputLibelle = inputGrid.getLibelle();
			outputGrid.setLibelle(inputLibelle);
			logger.trace("<{}> - libelle", outputGrid.getLibelle());
			// vue
			inputVue = inputGrid.getVue();			
			outputGrid.setVues(Stream.of(inputVue.split(",", -1)).collect(Collectors.toList()));
			logger.trace("<{}> - vues", outputGrid.getVues());
			// type
			inputType = inputGrid.getType();
			outputGrid.setType(inputType);
			logger.trace("<{}> - type", outputGrid.getType());
			// cal
			inputCal = inputGrid.getCal();
			outputGrid.setCal(inputCal);		
			logger.trace("<{}> - cal", outputGrid.getCal());
			// totalPts
			inputTotalPts = inputGrid.getTotalpts();
			outputGrid.setTotalpts(Integer.parseInt(inputTotalPts));
			logger.trace("<{}> - totalpts", outputGrid.getTotalpts());
			// pts et maxpos
			inputPts = inputGrid.getPts();
			outputGrid.setPts(Stream.of(inputPts.split(",", -1)).map(Integer::parseInt).collect(Collectors.toList()));
			outputGrid.setMaxPos(outputGrid.getPts().size());
			logger.trace("<{}> - maxpos", outputGrid.getMaxPos());
		} finally {
			logger.info("<{}> - fin traitement de la classe de course", inputCode);
		}		
		return outputGrid;
	}
}
