package KnowXchange.Backend.Application.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import KnowXchange.Backend.Application.Model.Course;
import KnowXchange.Backend.Application.Model.Lesson;
import KnowXchange.Backend.Application.Model.Question;
import KnowXchange.Backend.Application.Model.LearningResource;
import KnowXchange.Backend.Application.Model.Answer;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.QuestionRepository;
import KnowXchange.Backend.Application.Repository.UserRepository;
import KnowXchange.Backend.Application.Repository.AnswerRepository;
import KnowXchange.Backend.Application.Repository.LearningResourceRepository;

import java.util.ArrayList;


@Controller
@CrossOrigin("*")
@RequestMapping(path="/LearningResourceController")
public class LearningResourceController {
	

	@Autowired
	private LessonRepository lessonRepository;
	

	@Autowired
	private LearningResourceRepository learningResourceRepository;
	
	

	//____________________________________________________________________________________________________________________

	/*
	AGREGAR 
	   
	 */
	
	@PostMapping(path="/addResource/{id}")
	  public ResponseEntity<Void> addAnswerByPathVariable(
			  @PathVariable(value = "id") Integer id,
			  @RequestBody LearningResource lr
			  ) 
	{
		lr.setSupportedLesson(lessonRepository.findById(id).get());		
		learningResourceRepository.save(lr);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping(path="/addLearningResourceByPathVariable/{type}/{name}/{link}/{supported_lesson_Id}")
	  public @ResponseBody LearningResource addAnswerByPathVariable(
			  @PathVariable(value = "type")String type,
			  @PathVariable(value = "name")String name,
			  @PathVariable(value = "link")String link,
			  @PathVariable(value = "supported_lesson_Id") Integer supported_lesson_Id
	      ) {
	   
		
	    LearningResource learningResource = new LearningResource();
	    learningResource.setType(type);
	    learningResource.setName(name);
	    learningResource.setLink(link);
	    learningResource.setSupportedLesson(lessonRepository.findById(supported_lesson_Id).get());
	    learningResourceRepository.save(learningResource);
	    learningResource.setSupportedLesson(null);
	    return learningResource;
	    //return "Saved";
	}
	

@PostMapping(path="/addLearningResourceByRequestParameter")
	  public @ResponseBody LearningResource addLearningResourceByRequestParameter(
			  @RequestParam String type,
			  @RequestParam String name,
			  @RequestParam String link,
			  @RequestParam Integer supported_lesson_Id
	      ) {
	   
	
    LearningResource learningResource = new LearningResource();
    learningResource.setType(type);
    learningResource.setName(name);
    learningResource.setLink(link);
    learningResource.setSupportedLesson(lessonRepository.findById(supported_lesson_Id).get());
    learningResourceRepository.save(learningResource);
    learningResource.setSupportedLesson(null);
    return learningResource;
    //return "Saved";
	}
	
	//________________________________________________________________________________________________________
	


//________________________________________________________________________________________________________

//OBTENER RECURSO POR ID DEL OBJETO



@GetMapping(path="/getByIdByPathVariable/{id}")
public @ResponseBody LearningResource getLearningResourceByIdByPathVariable(@PathVariable(value = "id")Integer id) {
	  LearningResource l = learningResourceRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  l.setSupportedLesson(null);
	  return l;
}

@GetMapping(path="/getByIdByRequestParameter")
public @ResponseBody LearningResource getLearningResourceByIdByRequestParameter(
		  @RequestParam Integer id) {
	  LearningResource l = learningResourceRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  l.setSupportedLesson(null);
	  return l;
}


//____________________________________________________________________________________


	


//____________________________________________________________________________________

	//BORRAR-ELIMINAR 
	
	
	
	@DeleteMapping(path = "/deleteByPathVariable/{id}")
	public @ResponseBody String deleteLearningResourceByPathVariable(@PathVariable(value = "id")Integer id) {
		  LearningResource resourceReceived = learningResourceRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  learningResourceRepository.delete(resourceReceived);
		  return "Deleted";
	}
	
	
	@DeleteMapping(path = "/deleteByRequestParameter")
	public @ResponseBody String deleteLearningResourceByRequestParameter(@RequestParam Integer id) {
		  LearningResource resourceReceived = learningResourceRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  learningResourceRepository.delete(resourceReceived);
		  return "Deleted";
	}
	//__________________________________________________________________________________
	
	

	//__________________________________________________________________________________

		//OBTENER TODAS LOS RECURSOS DE UNA CLASE DADA
		
		
		
		@GetMapping(path = "/getLessonResourcesByPathVariable/{id}")
		public @ResponseBody ArrayList<LearningResource> getLessonResourcesByPathVariable(@PathVariable(value = "id")Integer id) {
			  ArrayList<LearningResource> learning_resources = new ArrayList<>();
			
				for(LearningResource c : learningResourceRepository.findAll()) {
					if(c.getSupportedLesson().getId() == id) {
						c.setSupportedLesson(null);
						learning_resources.add(c);
					}
				}	
				return learning_resources;
		}
		
		@GetMapping(path = "/getLessonResourcesByRequestParameter")
		public @ResponseBody ArrayList<LearningResource> getLessonResourcesByRequestParameter(@RequestParam Integer id) {
			  ArrayList<LearningResource> learning_resources = new ArrayList<>();
				
				for(LearningResource c : learningResourceRepository.findAll()) {
					if(c.getSupportedLesson().getId() == id) {
						c.setSupportedLesson(null);
						learning_resources.add(c);
					}
				}	
				return learning_resources;
		}
		
		
	//___________________________________________________________________________________
		
		
		
		
		//*************
		//Metodo con body para agregar recurso

		@PostMapping(path="/addResource/{id}")
		  public ResponseEntity<Void> addResourceByResponseEntityBody(
				  @PathVariable(value = "id") Integer id,
				  @RequestBody LearningResource lr
				  ) 
		{
			lr.setSupportedLesson(lessonRepository.findById(id).get());		
			learningResourceRepository.save(lr);
		    return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		
		//*************

}
