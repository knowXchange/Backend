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
	
	
	
	
//=======================================================================================================================
	 
	/*
___  ___     _            _             _____            _       _        _____ 
|  \/  |    | |    /     | |           /  ___|          (_)     | |      |____ |
| .  . | ___| |_ ___   __| | ___  ___  \ `--. _ __  _ __ _ _ __ | |_       _ / /
| |\/| |/ _ \ __/ _ \ / _` |/ _ \/ __|  `--. \ '_ \| '__| | '_ \| __|     |    \
| |  | |  __/ || (_) | (_| | (_) \__ \ /\__/ / |_) | |  | | | | | |_     ._‾_/ /
\_|  |_/\___|\__\___/ \__,_|\___/|___/ \____/| .__/|_|  |_|_| |_|\__|    \____/ 
                                             | |       
                                              ‾                         
	 */
	
	
	
	//______________________________________________________________________________________
	/*
	 Metodo para agregar una clase
	 
	 recibe:
	 
	    -id del curso al que pertenece
	    -titulo de la clase
	    -descripcion de la clase
	 
	 devuelve:
	    -mensaje de "created" si hay exito en la operacion, error en otro caso
	 
	 */
	
	@PostMapping(path = "/addLessonKX")
	public @ResponseBody String addNewLessonKX(@RequestParam Integer Courseid
			,@RequestParam String title
			,@RequestParam String description) {
	    Course course = courseRepository.findById(Courseid).orElseThrow(() -> new RuntimeException());
	    Lesson lesson = new Lesson();
	    lesson.setTitle(title);
	    lesson.setDescription(description);
	    lesson.setCourse(course);
	    course.getLessons().add(lesson);
	    lessonRepository.save(lesson);
		return "Created";
	}
	//______________________________________________________________________________________
	
	
	
	
	
	
	
	//______________________________________________________________________________________
	/*
	 Metodo para borrar una clase
	 
	 recibe:
	    -identificador de la clase
	    
	 devuelve:
	     -mensaje de "deleted" si hay exito en la operacion, error en otro caso
	 */
	
	@DeleteMapping("/deleteLessonKX")
	public @ResponseBody String deleteLessonKX(@RequestParam Integer id){
		Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new RuntimeException());
		lessonRepository.delete(lesson);
		return "Deleted";
	}
	
	//______________________________________________________________________________________
	
	
	
	
	
	
	
	
	
	//______________________________________________________________________________________
	/*
	 Metodo para obtener un objeto clase por su identificador
	 
	 recibe:
	    -Identificador de la clase
	    
	 devuelve
	    -Objeto Lesson de la clase buscada
	 */
	
	@GetMapping(path="/getLessonByIdKX")
	public @ResponseBody Lesson getLessonByIdKX(@RequestParam Integer id) {
	    return lessonRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	//______________________________________________________________________________________
	
	
	
	
	
	
	
	//______________________________________________________________________________________
	/*
	Metodo para editar una clase
	   --> SOLO SE EDITA TITULO Y DESCRIPCION, LA RELACION QUE ASOCIA LA CLASE A SU CURSO NO SE VE AFECTADA.
	   
	   recibe:
	      -Identificador de la clase a editar
	      -nuevo titulo
	      -nueva descripcion
	      
	   devuelve:
	     -mensaje de "Modified" si hay exito en la operacion, error en otro caso
	
	 */
	@PostMapping(path="editLessonByIdKX")
	public @ResponseBody String editLessonByIdKX(@RequestParam Integer id
			,@RequestParam String title
			,@RequestParam String description) {
		Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new RuntimeException());
		lesson.setTitle(title);
		lesson.setDescription(description);
		lessonRepository.save(lesson);
		return "Modified";
	}
	//______________________________________________________________________________________
	
	
	
	
	
	
//=======================================================================================================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//______________________________________________________________________________________
	
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
	
	//______________________________________________________________________________________-
	

	
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
