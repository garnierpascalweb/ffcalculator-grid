package com.gpwsofts.ffcalculator.grilles.processor;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.gpwsofts.ffcalculator.grilles.model.InputGrid;
import com.gpwsofts.ffcalculator.grilles.model.OutputGrid;

@Component
public class GridItemProcessor implements ItemProcessor<InputGrid,List<OutputGrid>> {

	@Override
	public List<OutputGrid> process(InputGrid inputGrid) throws Exception {
		List<OutputGrid> outputGrids = null;
		String inputCode = null;
		StringTokenizer st = null;
		try{
			inputCode = inputGrid.getCode();
			st = new StringTokenizer(inputCode, ",");
			int tokens = st.countTokens();
			System.out.println(tokens + " tokens pour " + inputCode);
			for (int i=0; i<tokens; i++){
				OutputGrid outputGrid = null;
				try{
					outputGrid = new OutputGrid();
					
				} finally {
					
				}
			}
			
		} finally {
			
		}
		return outputGrids;
	}

	

}
