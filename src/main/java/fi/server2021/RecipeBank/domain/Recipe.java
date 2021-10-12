package fi.server2021.RecipeBank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private int time;
	private String link;
	private String source;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	
	public Recipe() {}

	public Recipe(String title, int time, String link, String source, Category category) {
		super();
		this.title = title;
		this.time = time;
		this.link = link;
		this.source = source;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", time=" + time + ", link=" + link + ", source=" + source
				+ ", category=" + category + "]";
	}

}
