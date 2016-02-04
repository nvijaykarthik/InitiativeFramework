import menu.lib.*;
import groovy.json.*;

log.info("getting List of menus");

if("GET".equals(request.getMethod()))
 {
   
 	def menu=new MenuDomain();
        menu.url="/module/index.html"
        menu.label="Module Management"
    
    def menus=[menu];
    
    def menu1=new MenuDomain();
        menu1.url="/module/index.html"
        menu1.label="Module Management"
     
     menus.push(menu1);
   
    response.contentType = 'application/json'
	out << JsonOutput.toJson(menus);

  }
  