package in.co.initiative;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import groovy.servlet.ServletBinding;

public class InitiativeServletBinding extends ServletBinding{

	final Logger logger; 
	
	public InitiativeServletBinding(HttpServletRequest request,
			HttpServletResponse response, ServletContext context) {
		
		super(request, response, context);
		
		String script=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		logger= Logger.getLogger(script);
		logger.trace("Using Initiative Framework Bindings");
		
		super.setVariable("logger", logger);
		super.setVariable("datasource", context.getAttribute("dataSource"));
	}

}
