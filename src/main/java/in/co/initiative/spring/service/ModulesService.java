package in.co.initiative.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import in.co.initiative.spring.model.xml.Module;


@Component
public class ModulesService {

	private final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("jaxbMarshallerBean")
	Unmarshaller unmarshaller;

	public List<Module> loadModules(String[] directories,String contextPath) throws IOException
	{
		log.info("Loading Modules");
		
		List<Module> modules = new ArrayList<Module>();
		
		
		for(String directory:directories)
		{
			File info=new File(contextPath+directory+File.separator+"initiative-info.xml");
			modules.add(loadModule(info));
			log.debug("Module Added"+directory);
		}
		
		log.info("Loaded Modules"+modules);
		
		return modules;
	}
		
	protected Module loadModule(File info) {
        Module module = null;
        try(FileInputStream is = new FileInputStream(info)) {
        	
            log.debug("UnMarshalling "+info);
            module=(Module) unmarshaller.unmarshal(new StreamSource(is));
            log.debug("UnMarshalling"+module.toString());
        } catch(Exception e){
        	e.printStackTrace();
        }
        return module;
    }
	
}
