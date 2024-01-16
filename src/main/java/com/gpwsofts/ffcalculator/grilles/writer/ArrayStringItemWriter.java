package com.gpwsofts.ffcalculator.grilles.writer;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import com.gpwsofts.ffcalculator.grilles.model.OutputGrid;

public class ArrayStringItemWriter<OutputGrid> extends FlatFileItemWriter<OutputGrid> implements ItemWriter<OutputGrid> {	
	
	private static Logger logger = LoggerFactory.getLogger(ArrayStringItemWriter.class);
	@Override
	public void write(List<? extends OutputGrid> items) throws Exception {
		//List<String> listToWrite = items.stream()
		List<String> listToWrite = items.stream().filter(null)
		super.write(listToWrite);
	}

}
