package com.nubons.nnp.lcnc.codegen.mapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nubons.nnp.lcnc.codegen.mapper.IMapper;
import com.nubons.nnp.lcnc.codegen.mapper.factory.MapperFactory;

/**
 * 
 * Configuration class of Mapper. 
 * 
 * @author Gourab Guha
 *
 */
@Configuration
public class MapperFactoryConfig {

	@Autowired
	private MapperFactory mapperFactory;

	@Bean("defaultModelMapper")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean("mapper")
	public IMapper mapper() {
		return mapperFactory.getMapper();
	}

}
