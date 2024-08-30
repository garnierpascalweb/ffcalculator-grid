package com.gpwsofts.ffcalculator.grilles.properties;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:maxpos.properties")
@ConfigurationProperties(prefix = "grille")
public class MaxPosProperties {
	private Properties maxpos = new Properties();

	public Properties getMaxpos() {
		return maxpos;
	}

	public void setMaxpos(Properties maxpos) {
		this.maxpos = maxpos;
	}
	
}