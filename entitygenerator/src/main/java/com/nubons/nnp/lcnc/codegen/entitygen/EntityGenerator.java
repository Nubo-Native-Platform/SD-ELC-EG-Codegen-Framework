package com.nubons.nnp.lcnc.codegen.entitygen;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.apache.maven.shared.utils.cli.CommandLineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.nubons.nnp.lcnc.codegen.entitygen.dto.DatabaseDriverNames;
import com.nubons.nnp.lcnc.codegen.entitygen.dto.EntityGenRequest;
import com.nubons.nnp.lcnc.codegen.entitygen.exception.EntityGenerationException;

/**
 * This class is used to generate JPA2 entities using hibernate compliant maven plugin.
 * 
 * @author Gourab Guha
 *
 */
@Service
public class EntityGenerator {

	private final static Logger logger = LoggerFactory.getLogger(EntityGenerator.class);

	private static String genPomTemplateLoc;
	private static String genPomDirLoc;

	private final static String _SRC_DIR = "target" + File.separator + "generated-sources";
	private final static String _POM_XML = "pom.xml";
	private final static String _HIBERNATE_PROPERTIES = "hibernate.properties";
	private final static String _HIBERNATE_REVENG_XML = "hibernate.reveng.xml";
	private final static String _GOAL_CLEAN = "clean";
	private final static String _GOAL_CODEGEN = "hibernate-tools:hbm2java";
	private final static String _TEMPLATE_POM_XML = "template.pom.xml";
	private final static String _TEMPLATE_HIBERNATE_REVENG_XML = "template.hibernate.reveng.xml";
	private final static String _HIBERNATE_DTD = "hibernate-reverse-engineering-3.0.dtd";
	private final static String _ENTITY = "entity";
	private final static String _GEN_POM = "genPom";
	private final static String _REPLACE_SCHEMA = "#replaceschema";
	private final static String _REPLACE_PACKAGE = "#replacepackage";

	private static volatile EntityGenerator _instance = new EntityGenerator();

	private EntityGenerator() {
	}

	public static EntityGenerator getInstance() {
		return _instance;
	}

	@Autowired
	private DatabaseDriverNames databaseDriverNames;

	public void generateEntity(final EntityGenRequest req) {

		try {

			boolean tempExist = checkIfTemplateFilesExist(genPomTemplateLoc);

			if (tempExist) {

				createGenPomDir(genPomDirLoc);
				createHibernateProperties(genPomDirLoc, req);
				createHibernateRevEngXml(genPomTemplateLoc, genPomDirLoc, req);
				copyHibernateDtd(genPomTemplateLoc, genPomDirLoc);
				createPomXml(genPomTemplateLoc, genPomDirLoc, req);
				String goals[] = new String[] { _GOAL_CLEAN, _GOAL_CODEGEN };
				executeGoals(genPomDirLoc, goals);
				copyCode(genPomDirLoc, req);
				deleteGenPomDir(genPomDirLoc);

			} else {

				EntityGenerationException egx = new EntityGenerationException(
						"Error in entity generation: template files (pom and / or hibernate reverse eng) doesn't exist");
				throw egx;
			}
		} catch (EntityGenerationException egx) {
			logger.error("Error in entity generation", egx);
			throw egx;
		} catch (Exception ex) {
			logger.error("Error in entity generation", ex);
			EntityGenerationException egx = new EntityGenerationException("Error in entity generation", ex);
			throw egx;
		}
	}

	private boolean checkIfTemplateFilesExist(final String genPomTemplateLoc) {

		String templatePomLoc = genPomTemplateLoc + File.separator + _TEMPLATE_POM_XML;
		logger.info(" @@@ templatePomLoc" + templatePomLoc);
		Path templatePomPath = Paths.get(templatePomLoc);
		String templateHibernateRevEngloc = genPomTemplateLoc + File.separator + _TEMPLATE_HIBERNATE_REVENG_XML;
		logger.info(" @@@ templateHibernateRevEngloc " + templateHibernateRevEngloc);
		Path templateHibernateRevEngPath = Paths.get(templateHibernateRevEngloc);
		boolean tempExist = Files.exists(templatePomPath) && Files.exists(templateHibernateRevEngPath);

		return tempExist;
	}

	private void createGenPomDir(final String genPomDirLoc) throws IOException {

		Path genPomPath = Paths.get(genPomDirLoc);
		deleteGenPomDir(genPomPath);
		Files.createDirectory(genPomPath);
	}

	private void createHibernateProperties(final String genPomDirLoc, final EntityGenRequest req) throws IOException {

		// setting driver
		Map<String, String> driver = databaseDriverNames.getDriver();
		Properties hibernateProperties = new Properties();

		switch (req.getDatabase()) {
		case DB2:
			hibernateProperties.setProperty("hibernate.connection.driver_class", driver.get("db2"));
			hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.DB2Dialect");
			break;
		case MQSQL:
			hibernateProperties.setProperty("hibernate.connection.driver_class", driver.get("mysql"));
			hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			break;
		case ORACLE:
			hibernateProperties.setProperty("hibernate.connection.driver_class", driver.get("oracle"));
			hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
			break;
		case POSTGRESQL:
			hibernateProperties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
			hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			break;
		case SQLSERVER:
			hibernateProperties.setProperty("hibernate.connection.driver_class", driver.get("sqlserver"));
			hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		}

		// setting connection url
		hibernateProperties.setProperty("hibernate.connection.url", req.getDatabaseUrl());

		// setting username
		hibernateProperties.setProperty("hibernate.connection.username", req.getDatabaseUser());

		// setting password
		hibernateProperties.setProperty("hibernate.connection.password", req.getDatabasePassword());

		String hibernatePropsFileLoc = genPomDirLoc + File.separator + _HIBERNATE_PROPERTIES;
		try (PrintWriter writer = new PrintWriter(hibernatePropsFileLoc)) {
			for (Enumeration<?> names = hibernateProperties.propertyNames(); names.hasMoreElements();) {
				String key = (String) names.nextElement();
				writer.println(key + "=" + hibernateProperties.getProperty(key));
			}
		}

	}

	private void createHibernateRevEngXml(final String genPomTemplateLoc, final String genPomDirLoc,
			final EntityGenRequest req) throws IOException {

		String templateHibernateRevEngloc = genPomTemplateLoc + File.separator + _TEMPLATE_HIBERNATE_REVENG_XML;
		Path templateHibernateRevEngPath = Paths.get(templateHibernateRevEngloc);
		String hibernateRevEngLoc = genPomDirLoc + File.separator + _HIBERNATE_REVENG_XML;
		Path hibernateRevEngPath = Paths.get(hibernateRevEngLoc);
		String readContent = new String(Files.readAllBytes(templateHibernateRevEngPath));
		String schema = req.getDatabaseSchema();
		String updatedContent = readContent.replaceFirst(_REPLACE_SCHEMA, schema);
		logger.debug("hibernate reverse engineering xml after replacement " + updatedContent);
		Files.write(hibernateRevEngPath, updatedContent.getBytes());

	}
	
	private void copyHibernateDtd(final String genPomTemplateLoc, final String genPomDirLoc) throws IOException {
		
		String srcHibernateDtdlLoc = genPomTemplateLoc + File.separator + _HIBERNATE_DTD;
		Path srcHibernateDtdPath = Paths.get(srcHibernateDtdlLoc);
		String readContent = new String(Files.readAllBytes(srcHibernateDtdPath));
		String targetHibernateDtdLoc = genPomDirLoc + File.separator + _HIBERNATE_DTD;
		Path targetHibernateDtdPath = Paths.get(targetHibernateDtdLoc);
		
		Files.write(targetHibernateDtdPath, readContent.getBytes());
		
	}

	private void createPomXml(final String genPomTemplateLoc, final String genPomDirLoc, final EntityGenRequest req)
			throws IOException {

		String templatePomloc = genPomTemplateLoc + File.separator + _TEMPLATE_POM_XML;
		Path templatePomPath = Paths.get(templatePomloc);
		String pomLoc = genPomDirLoc + File.separator + _POM_XML;
		Path pomPath = Paths.get(pomLoc);
		String readContent = new String(Files.readAllBytes(templatePomPath));
		String jpaPackage = req.getJpaPackage();
		String updatedContent = readContent.replaceFirst(_REPLACE_PACKAGE, jpaPackage);
		logger.debug("pom xml after replacement " + updatedContent);
		Files.write(pomPath, updatedContent.getBytes());
	}

	private void executeGoals(final String genPomDirLoc, final String[] goals)
			throws MavenInvocationException, CommandLineException {

		InvocationRequest request = new DefaultInvocationRequest();
		String pomLoc = genPomDirLoc + File.separator + _POM_XML;
		request.setPomFile(new File(pomLoc));
		request.setGoals(Arrays.asList(goals));

		Invoker invoker = new DefaultInvoker();

		InvocationResult result = invoker.execute(request);
		int exitCode = result.getExitCode();
		if (exitCode != 0) {
			CommandLineException cle = result.getExecutionException();
			if (null != cle) {
				throw cle;
			} else {
				EntityGenerationException ege = new EntityGenerationException("Error to execute goals " + goals);
				throw ege;
			}
		}

	}

	private void copyCode(final String genPomDirLoc, final EntityGenRequest req) throws IOException {

		String srcLoc = genPomDirLoc + File.separator + _SRC_DIR;
		String targerLoc = req.getOutputDir();
		Path targetPath = Paths.get(targerLoc);
		Files.createDirectories(targetPath);
		FileUtils.copyDirectory(new File(srcLoc), new File(targerLoc));
	}

	private void deleteGenPomDir(final Path genPomPath) throws IOException {

		FileSystemUtils.deleteRecursively(genPomPath);
	}

	private void deleteGenPomDir(final String genPomLoc) throws IOException {

		FileSystemUtils.deleteRecursively(Paths.get(genPomLoc));
	}

	@Value("${nnp.template.location.genms}")
	public void setGenPomTemplateLoc(String genMsTemplateLoc) {
		logger.info(" @@@@@@ genMsTemplateLoc " + genMsTemplateLoc);
		EntityGenerator.genPomTemplateLoc = genMsTemplateLoc + File.separator + _ENTITY + File.separator + _GEN_POM;
		logger.info(" @@@@@@ EntityGenerator.genPomTemplateLoc " + EntityGenerator.genPomTemplateLoc);
	}

	@Value("${nnp.genPom.location}")
	public void setGenPomDirLoc(String genPomDirLoc) {
		EntityGenerator.genPomDirLoc = genPomDirLoc;
		logger.info(" @@@@@@@@ EntityGenerator.genPomDirLoc " + EntityGenerator.genPomDirLoc);
	}
}
