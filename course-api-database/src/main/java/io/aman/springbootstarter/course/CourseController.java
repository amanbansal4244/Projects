package io.aman.springbootstarter.course;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.aman.springbootstarter.topic.Topic;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@RequestMapping("/topics/{topicId}/courses")
	public List<Course> getAllCourses(@PathVariable("topicId") String id) { 
		return courseService.getAllCourses(id);
	}
	
	@RequestMapping("/topics/{topicId}/courses/{courseId}") 
	public Optional<Course> getCourse(@PathVariable("courseId") String id) {
		return courseService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/topics/{topicId}/courses") 
	
	public void addCourse(@RequestBody Course course,@PathVariable("topicId") String id) {
		 course.setTopic(new Topic(id, "", ""));
		 courseService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT , value="/topics/{topicId}/courses/{courseId}") 
	public void updateCourse(@RequestBody Course course, @PathVariable String topicId, @PathVariable String courseId) {
		 course.setTopic(new Topic(topicId, "", ""));
		 courseService.updateCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.DELETE , value="/topics/{topicId}/courses/{courseId}") 
	public void deleteCourse(@PathVariable String courseId) {
		 courseService.deleteCourse(courseId);
	}
}
