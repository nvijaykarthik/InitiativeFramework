/*
 * Copyright 2003-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.co.initiative;

import groovy.lang.Closure;
import groovy.servlet.GroovyServlet;
import groovy.servlet.ServletBinding;
import groovy.servlet.ServletCategory;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.groovy.runtime.GroovyCategorySupport;


public class InitiativeServlet extends GroovyServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Logger log = Logger.getLogger(this.getClass());

	private GroovyScriptEngine gse;

	
	public void init(ServletConfig config) throws ServletException {
	        super.init(config);

	        // Set up the scripting engine
	        gse = createGroovyScriptEngine();
	        
	        servletContext.log("Groovy servlet initialized on " + gse + " by Initiative Framework.");
	    }
	
    @Override
    public URLConnection getResourceConnection(String name) throws ResourceException {
    	log.trace("getResourceConnection : "+name);
        name = removeNamePrefix(name);
        name = name.replaceAll("\\\\", "/");

        //remove the leading / as we are trying with a leading / now
        if (name.startsWith("/")) name = name.substring(1);

        /*
        * Try to locate the resource and return an opened connection to it.
        */
        try {
            String tryScriptName = "/" + name;
            URL url = servletContext.getResource(tryScriptName);
            if (url == null) {
                tryScriptName = "/Modules" + name;
                url = servletContext.getResource("/Modules/" + name);
            }
          
            if (url == null) {
                throw new ResourceException("Resource \"" + name + "\" not found!");
            }
            return url.openConnection();
        } catch (IOException e) {
            throw new ResourceException("Problems getting resource named \"" + name + "\"!", e);
        }
    }
    
    private String removeNamePrefix(String name) throws ResourceException {
        URI uri = new File(servletContext.getRealPath("/")).toURI();
        try {
            String basePath = uri.toURL().toExternalForm();
            if (name.startsWith(basePath)) return name.substring(basePath.length());
        } catch (MalformedURLException e) {
            throw new ResourceException("Malformed URL for base path '"+ uri + "'", e);
        }
        
        try {
            URL res = servletContext.getResource("/"); 
            if (res!=null) uri = res.toURI();
        } catch (MalformedURLException e) {
            // ignore
        } catch (URISyntaxException e) {
            // ignore
        }

        if (uri!=null) {
            try {
                String basePath = uri.toURL().toExternalForm();
                if (name.startsWith(basePath)) return name.substring(basePath.length());
            } catch (MalformedURLException e) {
                throw new ResourceException("Malformed URL for base path '"+ uri + "'", e);
            }
        }
        return name;
    }
    

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	log.trace("Using Initiative Framework Servlet");
        // Get the script path from the request - include aware (GROOVY-815)
        final String scriptUri = getScriptUri(request);

        // Set it to HTML by default
        response.setContentType("text/html; charset="+encoding);

        // Set up the script context
        final ServletBinding binding = new InitiativeServletBinding(request, response, servletContext);
        setVariables(binding);

        // Run the script
        try {
            Closure closure = new Closure(gse) {

                public Object call() {
                    try {
                        return ((GroovyScriptEngine) getDelegate()).run(scriptUri, binding);
                    } catch (ResourceException e) {
                        throw new RuntimeException(e);
                    } catch (ScriptException e) {
                        throw new RuntimeException(e);
                    }
                }

            };
            GroovyCategorySupport.use(ServletCategory.class, closure);
        } catch (RuntimeException runtimeException) {
            StringBuffer error = new StringBuffer("GroovyServlet Error: ");
            error.append(" script: '");
            error.append(scriptUri);
            error.append("': ");
            Throwable e = runtimeException.getCause();
            /*
             * Null cause?!
             */
            if (e == null) {
                error.append(" Script processing failed.\n");
                error.append(runtimeException.getMessage());
                if (runtimeException.getStackTrace().length > 0)
                    error.append(runtimeException.getStackTrace()[0].toString());
                servletContext.log(error.toString());
                System.err.println(error.toString());
                runtimeException.printStackTrace(System.err);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, error.toString());
                return;
            }
            /*
             * Resource not found.
             */
            if (e instanceof ResourceException) {
                error.append(" Script not found, sending 404.");
                servletContext.log(error.toString());
                System.err.println(error.toString());
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            /*
             * Other internal error. Perhaps syntax?!
             */
            servletContext.log("An error occurred processing the request", runtimeException);
            error.append(e.getMessage());
            if (e.getStackTrace().length > 0)
                error.append(e.getStackTrace()[0].toString());
            servletContext.log(e.toString());
            System.err.println(e.toString());
            runtimeException.printStackTrace(System.err);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}
