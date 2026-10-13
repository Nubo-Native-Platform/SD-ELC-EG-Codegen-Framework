package com.nubons.nnp.lcnc.codegen.mapper;

import java.lang.reflect.Type;

import com.nubons.nnp.lcnc.codegen.mapper.exception.MapperException;

/**
 * Defines the interface of a DefaultMapper which is responsible to execute
 * mapping between source object and destination object using ModelMapper. It
 * defines most generic operations, with a default implementation.
 * 
 * @author Gourab Guha
 *
 */
public interface IDefaultMapper extends IMapper {

	/**
	 * 
	 * @param <D>
	 * @param sourceObj
	 * @param destType
	 * @param typeMapName
	 * @return
	 */
	default <D> D map(Object sourceObj, Class<D> destType, String typeMapName) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param <D>
	 * @param sourceObjArr
	 * @param destType
	 * @param typeMapName
	 * @return
	 */
	default <D> D map(Object[] sourceObjArr, Class<D> destType, String typeMapName) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param sourceObj
	 * @param destObj
	 * @param typeMapName
	 */
	default void map(Object sourceObj, Object destObj, String typeMapName) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param sourceObjArr
	 * @param destObj
	 * @param typeMapName
	 */
	default void map(Object[] sourceObjArr, Object destObj, String typeMapName) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param <D>
	 * @param sourceObj
	 * @param destType
	 * @param typeMapName
	 * @return
	 */
	default <D> D map(Object sourceObj, Type destType, String typeMapName) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

	/**
	 * 
	 * @param <D>
	 * @param sourceObjArr
	 * @param destType
	 * @param typeMapName
	 * @return
	 */
	default <D> D map(Object[] sourceObjArr, Type destType, String typeMapName) {
		throw new MapperException(
				"This Overloaded version of map method is not supported by your IMapper implementation");
	}

}
