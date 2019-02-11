package io.aman.springbootstarter.course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.aman.springbootstarter.topic.Topic;

@Entity  // With the help of this annotation , JPA will create a table named 'Topic' with three columns named 'id', 'name' , 'description'
public class Course {

	@Id // this is to make 'id' member variable as Primary Key.
	private String id;
	private String name;
	private String description;
	
	/*This is the 'ManyToOne' relationship means many courses and one topic 
	 * This below topic is foreign key of course entity.
	 */
	@ManyToOne
	private Topic topic;

	public Course() {
		
	}
	
	public Course(String id, String name, String description, String topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.topic = new Topic(topicId, "", "");
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
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
}
