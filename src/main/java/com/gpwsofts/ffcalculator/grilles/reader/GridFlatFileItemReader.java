package com.gpwsofts.ffcalculator.grilles.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import com.gpwsofts.ffcalculator.grilles.model.InputGrid;

public class GridFlatFileItemReader extends FlatFileItemReader<InputGrid> {

	public GridFlatFileItemReader() {
		this.setResource(new FileSystemResource(""));
		this.setLinesToSkip(1);
		this.setLineMapper(new DefaultLineMapper() {
			{				
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "id", "firstName", "lastName" });
					}
				});
				// Set values in Employee class
				setFieldSetMapper(new BeanWrapperFieldSetMapper<InputGrid>() {
					{
						setTargetType(InputGrid.class);
					}
				});
			}
		});
	}
}
