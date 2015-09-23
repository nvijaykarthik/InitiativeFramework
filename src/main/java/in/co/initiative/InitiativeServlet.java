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

import groovy.servlet.GroovyServlet;
import groovy.util.ResourceException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;


public class InitiativeServlet extends GroovyServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Logger log = Logger.getLogger(this.getClass());

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
}
