package in.co.initiative.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="module")  
@Entity
@Table(name = "module")
public class Module {

	private Integer id;
	private String name;
	private String description;
	private String date;
	private String version;
	private String project;
	
	private boolean activated;
	private String refPath;
	
	public Module(){
		
	}
	public Module(Integer id, String name, String description, String date,
			String version, boolean activated, String refPath) {
		super();
		this.id=id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.version = version;
		this.activated = activated;
		this.refPath = refPath;
	}	
	
	@XmlElement(required=true,name="name" )
	@Column(name="name",unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(required=true,name="description")
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(required=true,name="date")
	@Column(name="date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@XmlElement(required=true,name="version")
	@Column(name="version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlElement(name="project")
	@Column(name="project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	@Column(name="activated")
	public boolean getActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	@Column(name="refpath")
	public String getRefPath() {
		return refPath;
	}
	public void setRefPath(String refPath) {
		this.refPath = refPath;
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", version="
				+ version + ", project=" + project + ", activated=" + activated + ", refPath=" + refPath + "]";
	}
	
}
