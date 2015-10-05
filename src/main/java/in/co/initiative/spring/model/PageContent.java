package in.co.initiative.spring.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PageContent {

	private Integer id;
	private String contentType;
	private Page page = new Page();
	private String position;
	private String content; //path to video, path to text, path to image, path to module etc etc.,
	private String index;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="content_type",nullable=false)
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pageid", nullable = false)
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	@Column(name="position")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="index")
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		return "PageContent [id=" + id + ", contentType=" + contentType + ", page=" + page + ", position=" + position
				+ ", content=" + content + ", index=" + index + "]";
	}
	
	
}
