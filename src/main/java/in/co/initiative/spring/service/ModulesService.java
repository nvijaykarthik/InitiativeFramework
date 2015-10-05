package in.co.initiative.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.oxm.Unmarshaller;

import javax.annotation.PostConstruct;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import in.co.initiative.spring.model.Module;


@Component
public class ModulesService extends HibernateDaoSupport  {

	private final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("jaxbMarshallerBean")
	Unmarshaller unmarshaller;

	@PostConstruct
	public void init()
	{
		super.setSessionFactory(sessionFactory);
	}
	public List<Module> loadModules(String[] directories,String contextPath) throws IOException
	{
		log.info("Loading Modules");
		Map<String ,Module> mapModule=new HashMap<String,Module>();
		
		for(String directory:directories)
		{
			File info=new File(contextPath+directory+File.separator+"initiative-info.xml");
			Module m=loadModule(info);
			mapModule.put(m.getName(), m);
			log.debug("Module Added"+directory);
		}
		
		List<Module> mergedModule = getModuleFromDB();
		
		for(Module module:mergedModule)
		{
			log.info(module);
			mapModule.put(module.getName(),module);
		}
		List<Module> modules = new ArrayList<Module>(mapModule.values());
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
	
	@Transactional(readOnly = false)
	public void activateModule(Module module)
	{
		log.info("Inserting into DB"+module.toString());
		getHibernateTemplate().save(module);
	}
	
	@Transactional(readOnly=false)
	public void deActivateModule(Module module)
	{
		log.info("Deleting from DB"+module.toString());
		getHibernateTemplate().delete(module);
	}
	
	private List<Module> getModuleFromDB()
	{
		List<Module> modules=getHibernateTemplate().loadAll(Module.class);
		return modules;
	}
}
