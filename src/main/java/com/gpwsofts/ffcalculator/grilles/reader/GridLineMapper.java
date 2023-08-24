package com.gpwsofts.ffcalculator.grilles.reader;

import java.util.StringTokenizer;

import org.springframework.batch.item.file.LineMapper;

import com.gpwsofts.ffcalculator.grilles.model.InputGrid;

public class GridLineMapper implements LineMapper<InputGrid> {

	@Override
	public InputGrid mapLine(String line, int number) throws Exception {
		StringTokenizer st = null;
		InputGrid grid = null;
		try{
			grid = new InputGrid();
			st = new StringTokenizer(line, ";");
			
		} catch (Exception e){
			
		} finally {
			
		}
		return grid;
	}

}
