package com.nubons.nnp.lcnc.codegen.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.nubons.nnp.lcnc.codegen.mapper.exception.MapperException;

/**
 * Implementation of a Mapper using ModelMapper with its default configuration.
 * 
 * @author Gourab Guha
 *
 */
@Component("defaultMapper")
public class DefaultMapper implements IDefaultMapper {

	@Autowired
	@Qualifier("defaultModelMapper")
	protected ModelMapper modelMapper;

	@Override
	public <D> D map(Object sourceObj, Class<D> destType) {

		try {
			D destObj = modelMapper.map(sourceObj, destType);
			return destObj;
		} catch (Exception ex) {
			throw new MapperException("Exception in default mapper " + ex.getLocalizedMessage(), ex);
		}
	}

	/**
	 * This overloaded method expects a single object in the source array.
	 */
	@Override
	public <D> D map(Object[] sourceObjArr, Class<D> destType) {

		try {

			if (null != sourceObjArr && sourceObjArr.length == 1) {
				D destObj = modelMapper.map(sourceObjArr[0], destType);
				return destObj;
			} else {
				throw new MapperException(
						"Exception in default mapper, Source Object array is either null or it's length is not 1. Actual length of the array is "
								+ sourceObjArr.length);
			}
		} catch (MapperException mx) {
			throw mx;
		} catch (Exception ex) {
			throw new MapperException("Exception in default mapper " + ex.getLocalizedMessage(), ex);
		}
	}

}
