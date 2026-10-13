package com.nubons.nnp.lcnc.codegen.entitygen.dto;

import java.io.Serializable;

/**
 * 
 * @author Gourab Guha
 *
 */
public class EntityGenRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7370687000192049226L;

	private String requestId;
	private String outputDir;
	private String databaseSchema;
	private String databaseUrl;
	private String databaseUser;
	private String databasePassword;
	private String jpaPackage;

	public static enum DATABASE {
		ORACLE, MQSQL, SQLSERVER, DB2, POSTGRESQL 
	};

	private DATABASE database;
	
	public EntityGenRequest() {}
	
	public EntityGenRequest(String requestId, String outputDir, String databaseSchema, String databaseUrl,
			String databaseUser, String databasePassword, DATABASE database, String jpaPackage) {
		super();
		this.requestId = requestId;
		this.outputDir = outputDir;
		this.databaseSchema = databaseSchema;
		this.databaseUrl = databaseUrl;
		this.databaseUser = databaseUser;
		this.databasePassword = databasePassword;
		this.database = database;
		this.jpaPackage = jpaPackage;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getDatabaseSchema() {
		return databaseSchema;
	}

	public void setDatabaseSchema(String databaseSchema) {
		this.databaseSchema = databaseSchema;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public DATABASE getDatabase() {
		return database;
	}

	public void setDatabase(DATABASE database) {
		this.database = database;
	}

	public String getJpaPackage() {
		return jpaPackage;
	}

	public void setJpaPackage(String jpaPackage) {
		this.jpaPackage = jpaPackage;
	}

	@Override
	public String toString() {
		return "EntityGenRequest [requestId=" + requestId + ", outputDir=" + outputDir + ", databaseSchema="
				+ databaseSchema + ", databaseUrl=" + databaseUrl + ", databaseUser=" + databaseUser
				+ ", databasePassword=" + databasePassword + ", jpaPackage=" + jpaPackage + ", database=" + database
				+ "]";
	}

}
