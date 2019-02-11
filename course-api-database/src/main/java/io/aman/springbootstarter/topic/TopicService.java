package io.aman.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	public List<Topic> getAllTopics(){
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll()  // findAll() method "Returns all instances of the type" and returns in "java.lang.Iterable' form. So wee need to iterate from this and add into List.
		.forEach(topics :: add); //Here, we used 'Method Reference' and  Lambda Expression in Java 8. I am iterating over all the instances which is returned by 'findAll' using 'forEach'and calling the 'add' method on each of the instance of Iterable and adding into List 'topics'
		
		return topics;
	}
	
	public Optional<Topic> getTopic(String id) {
		return topicRepository.findById(id); // It Retrieves an entity by its id.
	}
	
	public void addTopic(Topic topic) {
		//Saves a given entity. Use the returned instance for further operations as the save operation might have changed the entity instance completely.
		topicRepository.save(topic);
	}

	public void updateTopic(Topic topic) {
		/* 'save' API does insert and update both because 'topic' know what is the id because 'topic' instance has id in entity class.
		 * If 'id' does not exist then its going to create a new record using above 'addTopic' service 
		 * if 'id' does exist then its going to update that record using this 'updateTopic' service .
		*/
		topicRepository.save(topic);
	}

	public void deleteTopic(String id) {
		topicRepository.deleteById(id); // It Deletes the entity with the given id.
	}

	public List<Topic> getTopicByName(String name) {
		return topicRepository.findByName(name); 
	}

	public List<Topic> getTopicByDescription(String des) {
		return topicRepository.findByDescription(des); 
	}
}
