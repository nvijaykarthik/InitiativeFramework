package in.co.initiative;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;



public class GroovyContextListener implements ServletContextListener {
	private final Logger logger = Logger.getLogger(this.getClass());
	private String[] initScripts;
	private String[] destroyScripts;
	private GroovyScriptEngine scriptEngine;

	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		
		String initScriptsStr = ctx.getInitParameter("initScripts");
		if (initScriptsStr != null && !initScriptsStr.trim().equals(""))
			initScripts = initScriptsStr.split(",");
		String destroyScriptsStr = ctx.getInitParameter("destroyScripts");
		if (destroyScriptsStr != null && !destroyScriptsStr.trim().equals(""))
			destroyScripts = destroyScriptsStr.split(",");

		try {
			String webappDir = ctx.getRealPath("");
			logger.debug(webappDir);
			String[] dirs = new String[] { webappDir,"/" };
			scriptEngine = new GroovyScriptEngine(dirs);

			if (initScripts != null) {
				Binding binding = createBinding(ctx);
				for (String filename : initScripts)
					runScript(filename, binding);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to init webapp with scripts.", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ServletContext ctx = sce.getServletContext();
			if (destroyScripts != null) {
				Binding binding = createBinding(ctx);
				for (String filename : destroyScripts)
					runScript(filename, binding);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to destroy webapp with scripts.", e);
		}
	}

	private Binding createBinding(ServletContext ctx) {
		Logger scriptLogger = Logger.getLogger(GroovyContextListener.class.getName() + "_script");
		Binding binding = new Binding();
		binding.setVariable("logger", scriptLogger);
		binding.setVariable("servletContext", ctx);
		return binding;
	}

	private void runScript(String filename, Binding binding) throws Exception {
		if (filename.startsWith("/"))
			filename = filename.substring(1);
		logger.info("Running script: " + filename);
		scriptEngine.run(filename, binding);
	}
}
