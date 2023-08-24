package com.gpwsofts.ffcalculator.grilles.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.gpwsofts.ffcalculator.grilles.model.OutputGrid;

@Component
public class GridItemWriter implements ItemWriter<OutputGrid> {

	@Override
	public void write(List<? extends OutputGrid> arg0) throws Exception {
		System.out.println("WRITOS ! ");
		
	}

}
