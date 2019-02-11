package io.aman.springbootstarter.topic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity  // With the help of this annotation , JPA will create a table named 'Topic' with three columns named 'id', 'name' , 'description'
public class Topic {

	@Id // this is to make 'id' member variable as Primary Key.
	private String id;
	private String name;
	private String description;
	
	public Topic() {
		
	}
	
	public Topic(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
}
