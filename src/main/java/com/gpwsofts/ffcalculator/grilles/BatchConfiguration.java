package com.gpwsofts.ffcalculator.grilles;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.gpwsofts.ffcalculator.grilles.model.InputGrid;
import com.gpwsofts.ffcalculator.grilles.model.OutputGrid;
import com.gpwsofts.ffcalculator.grilles.processor.GridItemProcessor;
import com.gpwsofts.ffcalculator.grilles.reader.GridFlatFileItemReader;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	@Autowired private GridFlatFileItemReader gridItemReader;
	@Autowired private GridItemProcessor gridItemProcessor;
	@Autowired private ItemWriter<OutputGrid> gridItemWriter;
	 
	 @Value("${file.input}")
	    private String fileInput;
	 	 
	 @Bean
     public Job job() {
		 Step step = stepBuilderFactory.get("monStep").<InputGrid,OutputGrid>chunk(100)
				 .reader(reader())
				 .processor(gridItemProcessor)
				 .writer(writer())
				 .build();
         return jobBuilderFactory.get("monJob").start(step).build();
     }
	 
	 @Bean
	 public ItemReader reader() {
	     FlatFileItemReader<InputGrid> itemReader = new FlatFileItemReader<InputGrid>();
	     itemReader.setLineMapper(lineMapper());
	     itemReader.setLinesToSkip(1);
	     itemReader.setResource(new ClassPathResource(fileInput));
	     return itemReader;
	 }
	 
	 @Bean
	 public ItemWriter writer() {		
		 JsonFileItemWriter itemWriter = new JsonFileItemWriterBuilder<OutputGrid>()
         .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
         .resource(new ClassPathResource("grilles.json"))
         .name("grillesJsonFileItemWriter")
         .build();
		 return itemWriter;
	 }
	 
	 
	 @Bean
	  public LineMapper<InputGrid> lineMapper() {
	    DefaultLineMapper<InputGrid> lineMapper = new DefaultLineMapper<InputGrid>();
	    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	    lineTokenizer.setNames(new String[] { "code", "libelle", "vue", "type", "cal", "totalpts", "pts" });
	    lineTokenizer.setDelimiter(";");
	    BeanWrapperFieldSetMapper<InputGrid> fieldSetMapper = new BeanWrapperFieldSetMapper<InputGrid>();
	    fieldSetMapper.setTargetType(InputGrid.class);
	    lineMapper.setLineTokenizer(lineTokenizer);
	    lineMapper.setFieldSetMapper(fieldSetMapper);
	    
	    return lineMapper;
	  }
	 
	 
}
