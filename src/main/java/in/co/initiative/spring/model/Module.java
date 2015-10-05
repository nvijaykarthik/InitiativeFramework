package in.co.initiative.spring.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="module")  
public class Module {

	private String name;
	private String description;
	private String date;
	private String version;
	private String project;
	
	public Module(){
		
	}
	public Module(String name, String description, String date,
			String version /*boolean activated, String refPath*/) {
		super();
		this.name = name;
		this.description = description;
		this.date = date;
		this.version = version;
	/*	this.activated = activated;
		this.refPath = refPath;*/
	}
	
	@XmlElement(required=true,name="name" )
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(required=true,name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(required=true,name="date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@XmlElement(required=true,name="version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	/*public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public String getRefPath() {
		return refPath;
	}
	public void setRefPath(String refPath) {
		this.refPath = refPath;
	}*/
	
	@Override
	public String toString() {
		return "Modules [name=" + name + ", description=" + description
				+ ", date=" + date + ", version=" + version + "]";
	}
	
	@XmlElement(name="project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
}
