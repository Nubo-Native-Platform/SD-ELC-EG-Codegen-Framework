package com.nubons.nnp.lcnc.codegen.mapper.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.nubons.nnp.lcnc.codegen.mapper.DefaultMapper;
import com.nubons.nnp.lcnc.codegen.mapper.IMapper;
import com.nubons.nnp.lcnc.codegen.mapper.exception.MapperException;

/**
 * Implementation of a factory to get a Mapper instance.
 * 
 * @author Gourab Guha
 *
 */
@Component
public class MapperFactory {

	@Autowired
	private ApplicationContext applicationContext;

	/*
	 * Following property has to be configured in application-<profile>.properties.
	 * This property value denotes the bean name of the Mapper implementation class.
	 */
	@Value("${mapper.impl}")
	private String mapperImplBean;

	/*
	 * Reference of the default mapper
	 */
	@Autowired
	@Qualifier("defaultMapper")
	private DefaultMapper defaultMapper;

	/**
	 * This method returns the instance of an IMapper which is configured in
	 * application-<profile>.properties file. In case it is not found then
	 * DefaultMapper will be returned.
	 * 
	 * In case configured property, i.e. mapper.impl refers to something other than
	 * a IMapper then MapperException will be thrown.
	 * 
	 * @return IMapper
	 * @throws MapperException
	 */
	public IMapper getMapper() {

		IMapper mapper = null;

		try {
			if (null == mapperImplBean || mapperImplBean.isEmpty()) {
				mapper = defaultMapper;
			} else {
				Object _mapper = applicationContext.getBean(mapperImplBean);
				if (_mapper instanceof IMapper) {
					mapper = (IMapper) _mapper;
				} else {
					throw new MapperException("Mapper configuration in application-<profile>.properties is incorrect, "
							+ _mapper.getClass().getName() + " is not a correct Mapper implementation");
				}

			}
		} catch (MapperException mEx) {
			throw mEx;
		} catch (Exception ex) {

		}

		return mapper;

	}
}
