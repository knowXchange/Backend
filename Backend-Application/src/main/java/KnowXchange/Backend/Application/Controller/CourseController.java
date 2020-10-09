package KnowXchange.Backend.Application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import KnowXchange.Backend.Application.Model.Course;
import KnowXchange.Backend.Application.Model.User;
import KnowXchange.Backend.Application.Repository.CourseRepository;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.TackleRepository;
import KnowXchange.Backend.Application.Repository.TakesRepository;

@Controller
@RequestMapping(path="/courseController")
public class CourseController {
	@Autowired
    private CourseRepository courseRepository;
	
	@Autowired
    private LessonRepository lessonRepository;
	
	@Autowired
    private TakesRepository takesRepository;
	
	@Autowired
	private TackleRepository tackleRepository;
	
	@PostMapping(path="/add")
	  public @ResponseBody String addNewCourse (@RequestParam String title
	      , @RequestParam String description
	      ,@RequestParam Long tokensCost) {
	   
	    Course course = new Course();
	    course.setDescription(description);
	    course.setTitle(title);
	    course.setTokensCost(tokensCost);
	    courseRepository.save(course);
	    return "Saved";
	}
	
	@GetMapping(path="/getAll")
	  public @ResponseBody Iterable<Course> getAllCourse() {
	    // This returns a JSON or XML with the users
	    return courseRepository.findAll();
	}
	
	@GetMapping(path="/getById/{id}")
	  public @ResponseBody Course getCourseById(@PathVariable(value = "id")Integer id) {
		  return courseRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	@PutMapping(path = "/modifyCourse/{id}")
	  public @ResponseBody String modifyCourse(@PathVariable(value = "id")Integer id,@RequestBody Course course) {
		  Course courseReceived = courseRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  courseReceived.setTitle(course.getTitle());
		  courseReceived.setDescription(course.getDescription());
		  courseReceived.setTokensCost(course.getTokensCost());
		  return "Modified";
	}
	
	@DeleteMapping(path = "/deleteCourse/{id}")
	public @ResponseBody String deleteCourse(@PathVariable(value = "id")Integer id) {
		  Course courseReceived = courseRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  courseRepository.delete(courseReceived);
		  return "Deleted";
	}
}
