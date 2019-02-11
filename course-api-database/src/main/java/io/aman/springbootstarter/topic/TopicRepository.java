package io.aman.springbootstarter.topic;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String>{

	public List<Topic> findByName(String name);
	
	public List<Topic> findByDescription(String description);
}
 