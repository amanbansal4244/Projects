package io.aman.springbootstarter.topic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;

	/*This API going to return object is going to converted into JSON. We don't have to do anything.
	 * The Facts is when we annotated the class with @RestController, means whatever we return object, its going to get automatically converted into 
	 * JSON and send back as HTTP response.
	 * 
	 * Note : Generated JSON has key names corresponding to property names of the Topic class. The JSON values are the values of those properties.
	*/
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() { // By Default 'GET' Request
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{idName}") // By Default 'GET' Request
	public Optional<Topic> getTopic(@PathVariable("idName") String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/topics") // this method gets called when ever any 'POST' request comes to URL http://localhost:8080/topics/ 
	/*Spring MVC is smart enough to take 'Topic' object from request pay load and converted into the 'Topic' instance using @RequestBody annotation,
	 * Now 'Topic' instance is ready to add as a new record as 'Topic' at server side.
	*/
	public void addTopic(@RequestBody Topic topic) {
		 topicService.addTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT , value="/topics/{id}") 
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		 topicService.updateTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.DELETE , value="/topics/{id}") 
	public void deleteTopic(@PathVariable String id) {
		 topicService.deleteTopic(id);
	}
	
	@RequestMapping("/topics/byName/{name}") // we have to write 'byName' in @RequestMapping tag, not to have Ambiguity with "@RequestMapping("/topics/{idName}")"
	public List<Topic> getTopicByName(@PathVariable("name") String name) {
		return topicService.getTopicByName(name);
	}
	
	@RequestMapping("/topics/byDescription/{description}")// we have to write 'byDescription' in @RequestMapping tag, not to have Ambiguity with "@RequestMapping("/topics/{idName}")"
	public List<Topic> getTopicByDescription(@PathVariable("description") String des) {
		return topicService.getTopicByDescription(des);
	}
}
