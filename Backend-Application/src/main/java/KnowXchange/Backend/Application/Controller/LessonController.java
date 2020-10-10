package KnowXchange.Backend.Application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import KnowXchange.Backend.Application.Model.Course;
import KnowXchange.Backend.Application.Model.Lesson;
import KnowXchange.Backend.Application.Model.Theme;
import KnowXchange.Backend.Application.Repository.CourseRepository;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.ThemeRepository;

@Controller
@CrossOrigin("*")
@RequestMapping(path="/lessonController")
public class LessonController {
	
	@Autowired
    private LessonRepository lessonRepository;
	
	@Autowired
    private CourseRepository courseRepository;
	
	@Autowired
    private ThemeRepository themeRepository;
	
	@PostMapping(path = "/addLesson")
	public @ResponseBody String addNewLesson(@PathVariable(value = "id")Integer Courseid
			,@RequestParam String title
			,@RequestParam String description) {
	    Course course = courseRepository.findById(Courseid).orElseThrow(() -> new RuntimeException());
	    Lesson lesson = new Lesson();
	    lesson.setTitle(title);
	    lesson.setDescription(description);
	    course.getLessons().add(lesson);
	    lessonRepository.save(lesson);
		return "Created";
	}
	
	@GetMapping(path="/getAllLessons")
	public @ResponseBody Iterable<Lesson> getAllLessons() {
	    return lessonRepository.findAll();
	}
	
	@GetMapping(path="/getLessonById/{id}")
	public @ResponseBody Lesson getLessonById(@PathVariable(value = "id")Integer id) {
	    return lessonRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	@PutMapping(path="modifyLessonById/{id}")
	public @ResponseBody String setLessonById(@PathVariable(value = "id")Integer id
			,@RequestParam String title
			,@RequestParam String description) {
		Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new RuntimeException());
		lesson.setTitle(title);
		lesson.setDescription(description);
		return "Modified";
	}
	
	@DeleteMapping("deleteLesson/{id}")
	public @ResponseBody String deleteLesson(@PathVariable(value = "id")Integer id){
		Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new RuntimeException());
		lesson.getCourse().getLessons().remove(lesson);
		lessonRepository.delete(lesson);
		return "Deleted";
	}
}
