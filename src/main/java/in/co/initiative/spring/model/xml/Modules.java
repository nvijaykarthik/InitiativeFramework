package in.co.initiative.spring.model.xml;

import javax.persistence.Entity;

import org.springframework.stereotype.Repository;

@Repository
public class Modules {

	private String name;
	private String description;
	private String date;
	private String version;
	private boolean activated ;
	private String refPath;
	
	public Modules(){
		
	}
	public Modules(String name, String description, String date,
			String version, boolean activated, String refPath) {
		super();
		this.name = name;
		this.description = description;
		this.date = date;
		this.version = version;
	/*	this.activated = activated;
		this.refPath = refPath;*/
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
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
				+ ", date=" + date + ", version=" + version + ", activated="
				+ activated + ", refPath=" + refPath + "]";
	}
	
}
