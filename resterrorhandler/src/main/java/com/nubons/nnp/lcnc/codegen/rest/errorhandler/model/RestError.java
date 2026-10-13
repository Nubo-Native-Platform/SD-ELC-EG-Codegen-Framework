package com.nubons.nnp.lcnc.codegen.rest.errorhandler.model;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents a generic TM Forum compliant JSON model for a generic
 * error thrown from a Rest service. A specific implementation of nnp compliant
 * Rest service can use this, can override this or can opt for a different
 * implementation.
 * 
 * @author Gourab Guha
 * @since May, 25 2021
 *
 */
@ApiModel(description = "Used when an API throws an Error, typically with a HTTP error response-code (3xx, 4xx, 5xx)")
@Validated
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7574940884523873839L;

	@JsonProperty("code")
	private String code = null;

	@JsonProperty("reason")
	private String reason = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("status")
	private String status = null;

	@JsonProperty("referenceError")
	private String referenceError = null;

	@JsonProperty("@baseType")
	private String baseType = null;

	@JsonProperty("@schemaLocation")
	private String schemaLocation = null;

	@JsonProperty("@type")
	private String type = null;

	/**
	 * 
	 * @param code
	 * @return RestError
	 */
	public RestError code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * Application relevant detail, defined in the API or a common list.
	 * 
	 * @return code
	 **/
	@ApiModelProperty(value = "Application relevant detail, defined in the API or a common list.")
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param reason
	 * @return RestError
	 */
	public RestError reason(String reason) {
		this.reason = reason;
		return this;
	}

	/**
	 * Explanation of the reason for the error which can be shown to a client user.
	 * 
	 * @return reason
	 **/
	@ApiModelProperty(value = "Explanation of the reason for the error which can be shown to a client user.")
	public String getReason() {
		return reason;
	}

	/**
	 * 
	 * @param message
	 * @return RestError
	 */
	public RestError message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * More details and corrective actions related to the error which can be shown
	 * to a client user.
	 * 
	 * @return message
	 **/
	@ApiModelProperty(value = "More details and corrective actions related to the error which can be shown to a client user.")
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param status
	 * @return RestError
	 */
	public RestError status(String status) {
		this.status = status;
		return this;
	}

	/**
	 * HTTP Error code extension
	 * 
	 * @return status
	 **/
	@ApiModelProperty(value = "HTTP Error code extension")
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param referenceError
	 * @return RestError
	 */
	public RestError referenceError(String referenceError) {
		this.referenceError = referenceError;
		return this;
	}

	/**
	 * URI of documentation describing the error.
	 * 
	 * @return referenceError
	 **/
	@ApiModelProperty(value = "URI of documentation describing the error.")
	public String getReferenceError() {
		return referenceError;
	}

	/**
	 * 
	 * @param baseType
	 * @return RestError
	 */
	public RestError baseType(String baseType) {
		this.baseType = baseType;
		return this;
	}

	/**
	 * When sub-classing, this defines the super-class.
	 * 
	 * @return baseType
	 **/
	@ApiModelProperty(value = "When sub-classing, this defines the super-class.")
	public String getBaseType() {
		return baseType;
	}

	/**
	 * 
	 * @param schemaLocation
	 * @return RestError
	 */
	public RestError schemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
		return this;
	}

	/**
	 * A URI to a JSON-Schema file that defines additional attributes and
	 * relationships
	 * 
	 * @return schemaLocation
	 **/
	@ApiModelProperty(value = "A URI to a JSON-Schema file that defines additional attributes and relationships")
	public String getSchemaLocation() {
		return schemaLocation;
	}

	/**
	 * 
	 * @param type
	 * @return RestError
	 */
	public RestError type(String type) {
		this.type = type;
		return this;
	}

	/**
	 * When sub-classing, this defines the sub-class entity name.
	 * 
	 * @return type
	 **/
	@ApiModelProperty(value = "When sub-classing, this defines the sub-class entity name.")
	public String getType() {
		return type;
	}

}
