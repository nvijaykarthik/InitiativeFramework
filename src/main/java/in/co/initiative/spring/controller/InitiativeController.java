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

import java.util.logging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrator")
public class InitiativeController{
	
	private final Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	ServletContext context;
	
	@Autowired
	ModulesService moduleService;
	
	private String staticContent;
	
	
	@RequestMapping("/")
	public ModelAndView chooseTheme()
	{
		ModelAndView model = new ModelAndView();
		staticContent="themes/administrator";
		String layout="twocolumn.jsp";
		
		model.addObject("staticContent", staticContent);
		model.addObject("layout","../../layouts/"+layout);
		model.setViewName("administrator/index");
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
