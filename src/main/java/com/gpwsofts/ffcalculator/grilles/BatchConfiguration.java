package com.gpwsofts.ffcalculator.grilles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.gpwsofts.ffcalculator.grilles.model.InputGrid;
import com.gpwsofts.ffcalculator.grilles.model.OutputGrid;
import com.gpwsofts.ffcalculator.grilles.processor.GridItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private GridItemProcessor gridItemProcessor;
	/**
	 * @since 1.0.0 dossier de livraison
	 */
	private static final String OUTPUT_FOLDER = "dist";

	@Value("${file.input}")
	private String fileInput;
	
	@Value("${file.output.json}")
	private String fileOutputJson;
	
	@Value("${file.output.xml}")
	private String fileOutputXml;

	@Bean
	public Job job() {
		Step step = stepBuilderFactory.get("GridStep1").<InputGrid, OutputGrid>chunk(100)
				.reader(itemReader())
				.processor(gridItemProcessor)
				.writer(compositeItemWriter())
				.build();
		return jobBuilderFactory.get("GridJob").start(step).build();
	}

	@Bean
	public ItemReader<InputGrid> itemReader() {
		FlatFileItemReader<InputGrid> itemReader = new FlatFileItemReader<InputGrid>();
		itemReader.setName("InputGridItemReader");
		itemReader.setLineMapper(lineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(new ClassPathResource(fileInput));
		return itemReader;		
	}

	@Bean
	public LineMapper<InputGrid> lineMapper() {
		DefaultLineMapper<InputGrid> lineMapper = new DefaultLineMapper<InputGrid>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "code", "logo", "libelle", "vue", "type", "cal", "totalpts", "pts" });
		lineTokenizer.setDelimiter(";");
		BeanWrapperFieldSetMapper<InputGrid> fieldSetMapper = new BeanWrapperFieldSetMapper<InputGrid>();
		fieldSetMapper.setTargetType(InputGrid.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	@Bean
	public CompositeItemWriter<OutputGrid> compositeItemWriter(){
		CompositeItemWriter<OutputGrid> compositeItemWriter = new CompositeItemWriter<OutputGrid>();
        List<ItemWriter<? super OutputGrid>> delegates = new ArrayList<>();
        delegates.add(itemWriter());
        delegates.add(xmlItemWriter());
        compositeItemWriter.setDelegates(delegates);
        return compositeItemWriter;
	}

	@Bean
	public ItemWriter<OutputGrid> itemWriter() {
		JsonFileItemWriter<OutputGrid> itemWriter = new JsonFileItemWriterBuilder<OutputGrid>()
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())				
				.resource(new FileSystemResource(new StringBuilder().append(OUTPUT_FOLDER).append("/").append(fileOutputJson).toString()))				
				.name("JsonOutputGridItemWriter")
				.build();
		return itemWriter;
	}
	
	@Bean
	public ItemWriter<OutputGrid> xmlItemWriter() {
		StaxEventItemWriter<OutputGrid> itemWriter = new StaxEventItemWriterBuilder<OutputGrid>()
				.name("XmlOutputGridItemWriter")
				.marshaller(recordsMarshaller())
				.resource(new FileSystemResource(new StringBuilder().append(OUTPUT_FOLDER).append("/").append(fileOutputXml).toString()))		
				.rootTagName("records")
				.overwriteOutput(true)
				.build();
		return itemWriter;
	}
	
	@Bean
	public XStreamMarshaller  recordsMarshaller() {
		Map<String, Class> aliases = new HashMap<>();	
		aliases.put("code", String.class);
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(aliases);
		return marshaller;
	}
}
