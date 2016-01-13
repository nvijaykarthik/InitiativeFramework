package in.co.initiative.spring.controller;



import in.co.initiative.spring.model.Module;
import in.co.initiative.spring.service.ModulesService;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	private List<Module> modules = new ArrayList<Module>();
	
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
		
		log.info("Modules loaded"+modules);
		
		model.addObject("modules",modules);
		model.setViewName("modules");
    	return model;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping("/activate")
	public ModelAndView activateModule(@ModelAttribute("module") Module module)
	{
		
		log.info("Activating Module"+module.getName());
		
		ModelAndView model = new ModelAndView();
		if(module==null)
		{
			model.setViewName("ModuleActivationFail");
			return model;
		}
		module.setActivated(true);
		String contextPath=context.getRealPath("/modules/");
		module.setRefPath(contextPath+module.getName()+File.separator);
		moduleService.activateModule(module);
		model.setViewName("ModuleActivationSuccess");
    	return model;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/deActivate")
	public ModelAndView deActivateModule(@ModelAttribute("module") Module module)
	{
		
		log.info("DeActivating Module"+module.getName());
		
		ModelAndView model = new ModelAndView();
		if(module==null)
		{
			model.setViewName("ModuleActivationFail");
			return model;
		}
		module.setActivated(true);
		String contextPath=context.getRealPath("/modules/");
		module.setRefPath(contextPath+module.getName()+File.separator);
		moduleService.deActivateModule(module);
		model.setViewName("ModuleActivationSuccess");
    	return model;
	}
}
