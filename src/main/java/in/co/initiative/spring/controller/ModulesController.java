package in.co.initiative.spring.controller;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modules")
public class ModulesController{
	
	private final Logger log = Logger.getLogger(this.getClass());

	
	@RequestMapping("/")
	public ModelAndView loadModules()
	{
		log.info("Entering into Servlet");
		log.debug("Entering into Servlet");
		
		ModelAndView model = new ModelAndView();
		
		model.setViewName("index");
		//model.addObject("name", name);
				
    	return model;
	}
}
