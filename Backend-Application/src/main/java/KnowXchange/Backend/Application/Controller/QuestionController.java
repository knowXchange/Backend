package KnowXchange.Backend.Application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import KnowXchange.Backend.Application.Model.Course;
import KnowXchange.Backend.Application.Model.Lesson;
import KnowXchange.Backend.Application.Model.Question;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.QuestionRepository;
import KnowXchange.Backend.Application.Repository.UserRepository;
import KnowXchange.Backend.Application.Repository.AnswerRepository;

import java.util.ArrayList;


@Controller
@CrossOrigin("*")
@RequestMapping(path="/questionController")
public class QuestionController {

	
	@Autowired
    private QuestionRepository questionRepository;
	
	 @Autowired 
	 private UserRepository userRepository;
	 
	@Autowired
	private LessonRepository lessonRepository;
		
	@Autowired
	private QuestionRepository answerRepository;
	
	
	
	//____________________________________________________________________________________________________________________

	/*
	AGREGAR PREGUNTA 
	   Recibe el asunto, descripcion, id de la persona que pregunta 
	   e id de la clase donde se surge.
	 */
	
	
	
	
	@PostMapping(path="/addQuestionByPathVariable/{topic}/{text}/{studentId}/{lessonId}")
		  public @ResponseBody Question addQuestionByPathVariable(
				  @PathVariable(value = "topic")String topic,
				  @PathVariable(value = "text")String text,
				  @PathVariable(value = "studentId")Integer studentId,
				  @PathVariable(value = "lessonId")Integer lessonId
		      ) {
		   
			
		    Question question = new Question();
		    question.setTopic(topic);
		    question.setText(text); 
		    question.SetAsking_user( userRepository.findById(studentId).get()  );
		    question.setLesson(lessonRepository.findById(lessonId).get());
		    questionRepository.save(question);
		    return question;
		    //return "Saved";
		}
		
	
	@PostMapping(path="/addQuestionByRequestParameter")
		  public @ResponseBody Question addQuestionByRequestParameter(
				  @RequestParam String topic,
				  @RequestParam String text,
				  @RequestParam Integer studentId,
				  @RequestParam Integer lessonId
		      ) {
		   
			
		    Question question = new Question();
		    question.setTopic(topic);
		    question.setText(text); 
		    question.SetAsking_user( userRepository.findById(studentId).get()  );
		    question.setLesson(lessonRepository.findById(lessonId).get());
		    questionRepository.save(question);
		    return question;
		    //return "Saved";
		}
	
	//________________________________________________________________________________________________________
	
	//OBTENER PREGUNTA POR ID DEL OBJETO PREGUNTA
	
	
	@GetMapping(path="/getByIdByPathVariable/{id}")
	  public @ResponseBody Question getQuestionByIdByPathVariable(@PathVariable(value = "id")Integer id) {
		  return questionRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	@GetMapping(path="/getByIdByRequestParameter")
	  public @ResponseBody Question getQuestionByIdByRequestParameter(
			  @RequestParam Integer id) {
		  return questionRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	
	
	//____________________________________________________________________________________
	
	//BORRAR-ELIMINAR UNA PREGUNTA
	  //se recibe el id de la pregunta a borrar
	
	
	
	@DeleteMapping(path = "/deleteByPathVariable/{id}")
	public @ResponseBody String deleteQuestionByPathVariable(@PathVariable(value = "id")Integer id) {
		  Question questionReceived = questionRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  questionRepository.delete(questionReceived);
		  return "Deleted";
	}
	
	
	@DeleteMapping(path = "/deleteByRequestParameter")
	public @ResponseBody String deleteByRequestParameter(@RequestParam Integer id) {
		  Question questionReceived = questionRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  questionRepository.delete(questionReceived);
		  return "Deleted";
	}
	//__________________________________________________________________________________
	
	//OBTENER TODAS LAS PREGUNTAS LIGADAS A UNA CLASE-LECCION 
	   //recibe el id de la clase de interes
	
	
	
	@GetMapping(path = "/getLessonQuestionsByPathVariable/{id}")
	public @ResponseBody ArrayList<Question> getLessonQuestionsByPathVariable(@PathVariable(value = "id")Integer id) {
		  ArrayList<Question> questions = new ArrayList<>();
		
			for(Question c : questionRepository.findAll()) {
				if(c.getLesson().getId() == id) {
					questions.add(c);				
				}
			}	
			return questions;
	}
	
	@GetMapping(path = "/getLessonQuestionsByRequestParameter")
	public @ResponseBody ArrayList<Question> getLessonQuestionsByRequestParameter(@RequestParam Integer id) {
		  ArrayList<Question> questions = new ArrayList<>();
		
			for(Question c : questionRepository.findAll()) {
				if(c.getLesson().getId() == id) {
					questions.add(c);				
				}
			}	
			return questions;
	}
	//___________________________________________________________________________________
	
	
}
