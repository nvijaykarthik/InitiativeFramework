package in.co.initiative.spring.controller;



import in.co.initiative.spring.model.Modules;
import in.co.initiative.spring.service.ModulesService;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modules")
public class ModulesController{
	
	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	ServletContext context;
	
	@Autowired
	ModulesService moduleService;
	
	private List<Modules> modules = Collections.emptyList();
	
	@RequestMapping("/")
	public ModelAndView loadModules()
	{
		log.info("Entering into loadModules"+context.getRealPath("/modules/"));
		ModelAndView model = new ModelAndView();
		String contextPath=context.getRealPath("/modules/");
		File file = new File(contextPath);
		
		String[] directories = file.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
		});
		
		//LOOP ALL THE SUBFOLDER AND READ The XML
		try {
			modules=moduleService.loadModules(directories,contextPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("Modules loaded");
		
		model.addObject(modules);
		model.setViewName("modules");
    	return model;
	}
}
