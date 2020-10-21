package KnowXchange.Backend.Application.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import KnowXchange.Backend.Application.Model.FieldBranch;
import KnowXchange.Backend.Application.Model.KnowledgeField;
import KnowXchange.Backend.Application.Model.Theme;
import KnowXchange.Backend.Application.Model.User;
import KnowXchange.Backend.Application.Repository.CourseRepository;
import KnowXchange.Backend.Application.Repository.FieldBranchRepository;
import KnowXchange.Backend.Application.Repository.KnowledgeFieldRepository;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.TackleRepository;
import KnowXchange.Backend.Application.Repository.TakesRepository;
import KnowXchange.Backend.Application.Repository.ThemeRepository;

import java.util.ArrayList;

@Controller
@CrossOrigin("*")
@RequestMapping(path="/courseController")
public class CourseController {
	@Autowired
    private CourseRepository courseRepository;
	
	@Autowired
    private  KnowledgeFieldRepository KnowledgeFieldRepository;
	
	@Autowired
    private LessonRepository lessonRepository;
	
	@Autowired
    private TakesRepository takesRepository;
	
	@Autowired
	private TackleRepository tackleRepository;
	
	@Autowired
    private ThemeRepository themeRepository;
	
	@Autowired
    private FieldBranchRepository FieldBranchRepository;
	
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
	
	//________________________________________________________________
	
	/*
 _____                       _____                      
/  __ \                     /  __ \                     
| /  \/_ __ ___  __ _ _ __  | /  \/_   _ _ __ ___  ___  
| |   | '__/ _ \/ _` | '__| | |   | | | | '__/ __|/ _ \ 
| \__/\ | |  __/ (_| | |    | \__/\ |_| | |  \__ \ (_) |
 \____/_|  \___|\__,_|_|     \____/\__,_|_|  |___/\___/ 
	 */
	
	
	@PostMapping(path="/addNewKXCourse")
	  public @ResponseBody String addNewKXCourse 
	     (@RequestParam String title
	    , @RequestParam String description
	       ,@RequestParam Long tokensCost, 
	      @RequestParam String branch_title) {
	   
		
	    Course course = new Course();
	    course.setDescription(description);
	    course.setTitle(title);
	    course.setTokensCost(tokensCost);
	  
	    course.setBranch(getBranchbyTitle(branch_title));
	    
	    
	    
	    courseRepository.save(course);
	    return "Saved";
	}
	
	
	@GetMapping(path="/pruebaInsercionCurso")
	 public @ResponseBody ArrayList<Course> pruebaInsercionCurso (){
  
		
		
		ArrayList<Course> ListaCursos = new ArrayList();
		
		for (Course t : courseRepository.findAll()) {
			ListaCursos.add(t);
		}
		
		return ListaCursos;
    
}
	
	
	
	//___________________________________________________________
	
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
	
	
	
	
	
	
	
	//_______________________________________________________________________________________________-
	//Funcion que regresa el el tema con un titulo dado
		public Theme getThemebyTitle(String title){
			Theme answer = null;
			for (Theme t : themeRepository.findAll()) {
				if(t.getTitle().equalsIgnoreCase(title)) {
					answer = t;
				}
				
			}
			
			return answer;
		}
		
		
		
		
		//Funcion que regresa el el Area con un titulo dado
		public KnowledgeField getKnowledgeField(String title){
			KnowledgeField answer = null;
			for (KnowledgeField t : KnowledgeFieldRepository.findAll()) {
				if(t.getTitle().equalsIgnoreCase(title)) {
					answer = t;
				}
				
			}
			
			return answer;
		}
		
	
		
		//Funcion que regresa la rama con un titulo dado
		public FieldBranch getBranchbyTitle(String title){
			FieldBranch answer = null;
			for (FieldBranch t : FieldBranchRepository.findAll()) {
				if(t.getTitle().equalsIgnoreCase(title)) {
					answer = t;
				}
				
			}

			return answer;
		}
	
	//___________________________________________________________________________________________________
}
