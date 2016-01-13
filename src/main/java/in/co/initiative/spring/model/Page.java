package in.co.initiative.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="page")
public class Page {

	private Integer id;
	private String name;
	private String description;
	private String menuPath;
	private Set<PageContent> pageContent= new HashSet<PageContent>();
	private String layout; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name",unique=true,nullable=false )
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="menupath")
	public String getPath() {
		return menuPath;
	}
	public void setPath(String path) {
		this.menuPath = path;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="page")
	public Set<PageContent> getPageContent() {
		return pageContent;
	}
	
	public void setPageContent(Set<PageContent> pageContent) {
		this.pageContent = pageContent;
	}
	
	@Column(name="layout",nullable=true)
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	@Override
	public String toString() {
		return "Page [id=" + id + ", name=" + name + ", description=" + description + ", path=" + menuPath
				+ ", pageContent=" + pageContent + ", layout=" + layout + "]";
	}
	
}
