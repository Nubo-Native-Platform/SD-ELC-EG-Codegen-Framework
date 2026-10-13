package com.nubons.nnp.lcnc.codegen.entitygen.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Gourab Guha
 *
 */
@Component
@PropertySource("classpath:database.driver.properties")
@ConfigurationProperties("driver")
public class DatabaseDriverNames {

	private Map<String, String> driver = new HashMap<>();

	public Map<String, String> getDriver() {
		return driver;
	}

	public void setDriver(Map<String, String> driver) {
		this.driver = driver;
	}

}
