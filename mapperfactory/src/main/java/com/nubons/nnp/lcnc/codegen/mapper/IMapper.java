package com.nubons.nnp.lcnc.codegen.mapper;

import java.lang.reflect.Type;

import com.nubons.nnp.lcnc.codegen.mapper.exception.MapperException;

/**
 * Defines the interface of a Mapper which is responsible to execute mapping
 * between source object and destination object. It defines most generic
 * operations, with a default implementation.
 * 
 * @author Gourab Guha
 *
 */
public interface IMapper {

	/**
	 * 
	 * @param <D>
	 * @param sourceObj
	 * @param destType
	 * @return
	 */
	default <D> D map(Object sourceObj, Class<D> destType) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param <D>
	 * @param sourceObjArr
	 * @param destType
	 * @return
	 */
	default <D> D map(Object[] sourceObjArr, Class<D> destType) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param sourceObj
	 * @param destObj
	 */
	default void map(Object sourceObj, Object destObj) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param sourceObjArr
	 * @param destObj
	 */
	default void map(Object[] sourceObjArr, Object destObj) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param <D>
	 * @param sourceObj
	 * @param destType
	 * @return
	 */
	default <D> D map(Object sourceObj, Type destType) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param <D>
	 * @param sourceObjArr
	 * @param destType
	 * @return
	 */
	default <D> D map(Object[] sourceObjArr, Type destType) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}
}
