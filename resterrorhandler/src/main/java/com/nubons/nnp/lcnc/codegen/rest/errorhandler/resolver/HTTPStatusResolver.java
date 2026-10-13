package com.nubons.nnp.lcnc.codegen.rest.errorhandler.resolver;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * A Resolver class used to resolve HTTPStatus object from a status string.
 *  
 * @author Gourab Guha (egouguh)
 * @since May, 25 2021
 *
 */
@Component
@Slf4j
public class HTTPStatusResolver {

	/**
	 * 
	 * @param code the status code
	 * @return HttpStatus
	 * @throws NumberFormatException in case code is not a correct integer
	 * 		   IllegalArgumentException in case code is not a valid HTTPStatus
	 */
	public HttpStatus resolveHttpStatusFromCode(final String code) {
		log.debug("Status code input to HTTPStatusResolver " + code);
		try {
			return HttpStatus.valueOf(Integer.parseInt(code));
		} catch (NumberFormatException nx) {
			log.error("Invalid status code input to HTTPStatusResolver", nx);
			throw nx;
		} catch (IllegalArgumentException ix) {
			log.error("Incorrect status code input to HTTPStatusResolver", ix);
			throw ix;
		}
	}
}
