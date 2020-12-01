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
import KnowXchange.Backend.Application.Model.Takes;
import KnowXchange.Backend.Application.Model.Theme;
import KnowXchange.Backend.Application.Model.User;
import KnowXchange.Backend.Application.Repository.CourseRepository;
import KnowXchange.Backend.Application.Repository.FieldBranchRepository;
import KnowXchange.Backend.Application.Repository.KnowledgeFieldRepository;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.TackleRepository;
import KnowXchange.Backend.Application.Repository.TakesRepository;
import KnowXchange.Backend.Application.Repository.ThemeRepository;
import KnowXchange.Backend.Application.Repository.UserRepository;

import java.util.ArrayList;

import java.util.Random;


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
    private FieldBranchRepository fieldBranchRepository;
	
	//Agregue el userRepository
	@Autowired
	private UserRepository userRepository;
	
	
	//____________________________________________________________________________________________________________________
	//Metodo para insertar un curso
	/*
	 El post se envia asi por ejemplo:
	 localhost:8080/courseController/addNewCoursebyPathVariable/Violin 2/el violin es bello/10/2/3
	 */
	@PostMapping(path="/addNewCoursebyPathVariable/{title}/{description}/{tokensCost}/{branch_id}/{ownerId}")
		  public @ResponseBody Course addNewCoursebyPathVariable(
				  @PathVariable(value = "title")String title,
				  @PathVariable(value = "description")String description,
				  @PathVariable(value = "tokensCost")Long tokensCost,
				  @PathVariable(value = "branch_id")Integer branch_id,
				  @PathVariable(value = "ownerId")Integer ownerId
				  
		      ) {
		   
			
		    Course course = new Course();
		    course.setDescription(description);
		    course.setTitle(title);
		    course.setTokensCost(tokensCost);	  
		    course.setBranch(fieldBranchRepository.findById(branch_id).get());
		    course.setUserOwner(userRepository.findById(ownerId).get()); 
		    
		    courseRepository.save(course);
		    
		      
		    return course;
		    //return "Saved";
		}
		//________________________________________________________________________________________________________
	
	
	//===================================================================================================================
	
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
	
	
	//_______________________________________________________________________________________________________
	/*
	Metodo para obtener un curso por su identificador
	
	   -recibe identificador
	   -devuelve el objeto del curso buscado
	 */
	
	@GetMapping(path="/getByIdKX")
	  public @ResponseBody Course getCourseByIdKX(@RequestParam Integer id) {
		  return courseRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	//_______________________________________________________________________________________________________
	
	
	
	
	
	
	//_______________________________________________________________________________________________________
	
	/*
	-->Este metodo edita un curso SIN AFECTAR LAS RELACIONES CURSO-RAMA
	 
	Metodo para editar los datos basicos de un curso (nombre, descripcion y costo en tokens)
	
	   recibe:
	      -identificador del curso a editar
	      -nuevo titulo
	      -nueva descripcion
	      -nuevo costo en tokens
	      
	     devuelve mensaje "Modified" si la operacion tiene exito"
	     
	 */
	
	
	@PostMapping(path = "/editCoursePlainData")
	  public @ResponseBody String editCoursePlainData(
			  @RequestParam Integer id,
			  @RequestParam String title, 
			  @RequestParam String description,
			  @RequestParam Long tokensCost) {
		
		  Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  
		  course.setDescription(description);
		  course.setTitle(title);
		  course.setTokensCost(tokensCost);	  
		  courseRepository.save(course);
		  return "Modified";
	}
	//_______________________________________________________________________________________________________-
	
	
	
	
	
	
	
	
	//_______________________________________________________________________________________________________
	
	/*
	-->Este metodo edita un curso y SI MODIFICA LA RELACION CURSO-RAMA
	 
	Metodo para editar los datos basicos de un curso (nombre, descripcion y costo en tokens)
	
	   recibe:
	      -identificador del curso a editar
	      -nuevo titulo
	      -nueva descripcion
	      -nuevo costo en tokens
	      -identificador de la rama a la cual se debe cambiar la asociacion
	      
	   
	   devuelve mensaje "Modified" si la operacion tiene exito"
	      
	 */
	
	@PostMapping(path = "/editCourseAllData")
	  public @ResponseBody String editCourseAllData(
			  @RequestParam Integer id,
			  @RequestParam String title, 
			  @RequestParam String description,
			  @RequestParam Long tokensCost,
			  @RequestParam Integer branch_id) {
		
		  Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  
		  course.setDescription(description);
		  course.setTitle(title);
		  course.setTokensCost(tokensCost);	  
		  course.setBranch(fieldBranchRepository.findById(branch_id).get());
		  courseRepository.save(course);
		  
		  return "Modified";
	}
	//_______________________________________________________________________________________________________
	
	
	//==================================================================================================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//Modifique este metodo, le agregue unos parametros y objetos
	@PostMapping(path="/addNewKXCourse")
		  public @ResponseBody Course addNewKXCourse(
				  @RequestParam String title, 
				  @RequestParam String description,
				  @RequestParam Long tokensCost,
				  @RequestParam Integer branch_id,
				  @RequestParam Integer ownerId
		      ) {
		   
			
		    Course course = new Course();
		    course.setDescription(description);
		    course.setTitle(title);
		    course.setTokensCost(tokensCost);	  
		    course.setBranch(fieldBranchRepository.findById(branch_id).get());
		    course.setUserOwner(userRepository.findById(ownerId).get()); 
		    
		    courseRepository.save(course);
		    
		    return course;
		    //return "Saved";
		}


	//Agregue este metodo
	@GetMapping(path="/getByOwner/{id}")
		  public @ResponseBody ArrayList<Course> getCourseByOwner(@PathVariable(value = "id")Integer id) {
			ArrayList<Course> courses = new ArrayList<>();
			for(Course c : courseRepository.findAll()) {
				if(c.getUserOwner().getId().equals(id)) {
					c.setListTakes(null);  
					courses.add(c);
				}
			}
			return courses;
		}
	
	//_____________________________________________________________________________________-
	
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
	
	//_____________________________________________________________________________________________
		
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
	
	/*
	 * org.apache.commons.lang3.StringUtils.containsIgnoreCase(CharSequence str,
                                     CharSequence searchStr);
	 * */
	
	@GetMapping(path="/getByWord/{word}")
	  public @ResponseBody ArrayList<Course> getCourseByWord(@PathVariable(value = "word")String word) {
		ArrayList<Course> courses = new ArrayList<>();
		for(Course c : courseRepository.findAll()) {
			if(c.getTitle().toUpperCase().contains(word.toUpperCase())) {
				c.setListTakes(null);
				courses.add(c);				
			}
			
		}
		return courses;
	}
	
	@GetMapping(path="/getByBranch/{branchId}")
	  public @ResponseBody ArrayList<Course> getCourseByFieldId(@PathVariable(value = "branchId")Integer fieldId) {
		ArrayList<Course> courses = new ArrayList<>();
		for(Course c : courseRepository.findAll()) {
			if(c.getFieldId() == fieldId) {
				c.setListTakes(null);
				courses.add(c);				
			}
		}
		return courses;
	}
	
	@GetMapping(path="/getAllCourses")
	  public @ResponseBody ArrayList<Course> getAllCourses() {
		ArrayList<Course> courses = new ArrayList<>();
		for(Course c : courseRepository.findAll()) {
			c.setListTakes(null);
			courses.add(c);
		}
		return courses;
	}
	
	@GetMapping(path="/getCourseById/{id}")
	  public @ResponseBody ArrayList<Course> getCourseByIdWithArray(@PathVariable(value = "id")Integer id) {
		ArrayList<Course> courses = new ArrayList<>();
		for(Course c : courseRepository.findAll()) {
			if(c.getId() == id) {
				c.setListTakes(null);
				courses.add(c);
				break;
			}
		}
		return courses;
	}
	
	@GetMapping(path="/getCoursesEnrrolled/{id}")
	  public @ResponseBody ArrayList<Course> getCoursesEnrrolled(@PathVariable(value = "id")Integer id) {
		ArrayList<Course> courses = new ArrayList<>();
		for(Takes t : takesRepository.findAll() ) {
			if(t.getUserAssistant().getId() == id) {
				t.getCourseTaken().setListTakes(null);
				courses.add(t.getCourseTaken());
			}
		}
		return courses;
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
			for (FieldBranch t : fieldBranchRepository.findAll()) {
				if(t.getTitle().equalsIgnoreCase(title)) {
					answer = t;
				}
				
			}

			return answer;
		}
	
	//___________________________________________________________________________________________________
		
		
		
		
		
		//FUNCION PARA OBTENER UNA LISTA ALEATORIA DE CURSOS
		/*
		 -La funcion recibe un entero cursos_aleatorios que indica cuantos cursos se quieren.
		 -La funcion devuelve una lista de cursos.
		 
		 -La lista que se devuelve no tiene cursos repetidos.
		 
		 -si se piden mas cursos de los que hay, se devuelven todos los que hay sin repeticiones.
		 Es decir si el sistema tiene 3 cursos y se piden 5 se devuelven los 3.
		 -se se piden 0 cursos se devuelve lista vacia [].
		 -
		 */
		//==========================================================================================
		@GetMapping(path="/getRandomCoursesByPathVariable/{cursos_aleatorios}")
		  public @ResponseBody ArrayList<Course> getRandomCoursesByPathVariable(@PathVariable(value = "cursos_aleatorios")Integer cursos_aleatorios) {
			ArrayList<Course> courses = new ArrayList<>();
			ArrayList<Long> repeticiones = new ArrayList<>();
			
			//caso vacio
			if (cursos_aleatorios==0) {
				return courses;
			}
			
			//Numero de cursos en el sistema
			long l =0;
			for(Course c : courseRepository.findAll()) {
				l++;
			}
			
			//A lo mas devolver una lista con todos los cursos
			long n = cursos_aleatorios;
			if(l<= cursos_aleatorios) {
				n = l;
			}
			
			
			//System.out.println("tamano: " + l);
			
			for (int i=0;i<n;i++) {
				
			      double al = Math.random();
			      long iesimo = ((long) (al * (l)))%l;
			      
					while(repeticiones.contains(iesimo)) {
						  al = Math.random();
					      iesimo = ((long) (al * (l)))%l;
					}
					
					repeticiones.add(iesimo);
					int r =0;
					for(Course c : courseRepository.findAll()) {
						if(r == iesimo) {
							c.setListTakes(null);
							c.setListTackles(null);
							courses.add(c);
							break;
						}
						r++;
					}
			}
				      
			  return courses;
		}
		
		//==================================================================================
		
		
		

		
		//==========================================================================================
		@GetMapping(path="/getRandomCoursesByRequestParameter")
		  public @ResponseBody ArrayList<Course> getRandomCoursesByRequestParameter(
				  @RequestParam Integer cursos_aleatorios) {
			
			ArrayList<Course> courses = new ArrayList<>();
			ArrayList<Long> repeticiones = new ArrayList<>();
			
			//caso vacio
			if (cursos_aleatorios==0) {
				return courses;
			}
			
			//Numero de cursos en el sistema
			long l =0;
			for(Course c : courseRepository.findAll()) {
				l++;
			}
			
			//A lo mas devolver una lista con todos los cursos
			long n = cursos_aleatorios;
			if(l<= cursos_aleatorios) {
				n = l;
			}
			
			
			//System.out.println("tamano: " + l);
			
			for (int i=0;i<n;i++) {
				
			      double al = Math.random();
			      long iesimo = ((long) (al * (l)))%l;
			      
					while(repeticiones.contains(iesimo)) {
						  al = Math.random();
					      iesimo = ((long) (al * (l)))%l;
					}
					
					repeticiones.add(iesimo);
					int r =0;
					for(Course c : courseRepository.findAll()) {
						if(r == iesimo) {
							c.setListTakes(null);
							c.setListTackles(null);
							courses.add(c);
							break;
						}
						r++;
					}
			}
				      
			  return courses;
		}
		
		//==================================================================================
		
		
}
