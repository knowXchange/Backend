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
import KnowXchange.Backend.Application.Model.Answer;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.QuestionRepository;
import KnowXchange.Backend.Application.Repository.UserRepository;
import KnowXchange.Backend.Application.Repository.AnswerRepository;

import java.util.ArrayList;


@Controller
@CrossOrigin("*")
@RequestMapping(path="/answerController")
public class AnswerController {
	
	

	@Autowired
    private QuestionRepository questionRepository;
	
	@Autowired 
	private UserRepository userRepository;
	 
	@Autowired
	private LessonRepository lessonRepository;
		
	@Autowired
	private AnswerRepository answerRepository;
	
	
	
	
	
	
	//____________________________________________________________________________________________________________________

	/*
	AGREGAR RESPUESTA
	   
	 */
	
	
	@PostMapping(path="/addAnswerByPathVariable/{text}/{questionId}/{personId}")
	  public @ResponseBody Answer addAnswerByPathVariable(
			  @PathVariable(value = "text")String text,
			  @PathVariable(value = "questionId")Integer questionId,
			  @PathVariable(value = "personId")Integer personId
	      ) {
	   
		
	    Answer answer = new Answer();
	    String comentario = text;
	    answer.setText(comentario);
	    answer.setSolvedQuestion(questionRepository.findById(questionId).get());
	    answer.setPersonAnswering(userRepository.findById(personId).get());
	    answerRepository.save(answer);
	    return answer;
	    //return "Saved";
	}
	

@PostMapping(path="/addAnswerByRequestParameter")
	  public @ResponseBody Answer addAnswerByRequestParameter(
			  @RequestParam String text,
			  @RequestParam Integer questionId,
			  @RequestParam Integer personId
	      ) {
	   
		
	  	Answer answer = new Answer();
	  	String comentario = text;
	    answer.setText(comentario);
	    answer.setSolvedQuestion(questionRepository.findById(questionId).get());
	    answer.setPersonAnswering(userRepository.findById(personId).get());
	    answerRepository.save(answer);
	    return answer;
	    //return "Saved";
	}
	
	//________________________________________________________________________________________________________
	
	
	



//________________________________________________________________________________________________________

//OBTENER RESPUESTA POR ID DEL OBJETO


@GetMapping(path="/getByIdByPathVariable/{id}")
  public @ResponseBody Answer getQuestionByIdByPathVariable(@PathVariable(value = "id")Integer id) {
	  return answerRepository.findById(id).orElseThrow(() -> new RuntimeException());
}

@GetMapping(path="/getByIdByRequestParameter")
  public @ResponseBody Answer getQuestionByIdByRequestParameter(
		  @RequestParam Integer id) {
	return answerRepository.findById(id).orElseThrow(() -> new RuntimeException());
}


//____________________________________________________________________________________






//____________________________________________________________________________________

	//BORRAR-ELIMINAR UNA PREGUNTA
	  //se recibe el id de la pregunta a borrar
	
	
	
	@DeleteMapping(path = "/deleteByPathVariable/{id}")
	public @ResponseBody String deleteQuestionByPathVariable(@PathVariable(value = "id")Integer id) {
		  Answer answerReceived = answerRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  answerRepository.delete(answerReceived);
		  return "Deleted";
	}
	
	
	@DeleteMapping(path = "/deleteByRequestParameter")
	public @ResponseBody String deleteByRequestParameter(@RequestParam Integer id) {
		  Answer answerReceived = answerRepository.findById(id).orElseThrow(() -> new RuntimeException());
		  answerRepository.delete(answerReceived);
		  return "Deleted";
	}
	//__________________________________________________________________________________
	
	
	
	



//__________________________________________________________________________________

	//OBTENER TODAS LAS RESPUESTAS(OBJETOS RESPUESTA) A UNA PREGUNTA. 
	   //recibe el id de la pregunta de interes
	
	
	
	@GetMapping(path = "/getAnswersByPathVariable/{id}")
	public @ResponseBody ArrayList<Answer> getQuestionAnswersByPathVariable(@PathVariable(value = "id")Integer id) {
		  ArrayList<Answer> answers = new ArrayList<>();
		
			for(Answer c : answerRepository.findAll()) {
				if(c.getSolvedQuestion().getId() == id) {
					c.setSolvedQuestion(null);
					answers.add(c);				
				}
			}	
			return answers;
	}
	
	@GetMapping(path = "/getAnswersByRequestParameter")
	public @ResponseBody ArrayList<Answer> getQuestioAnswersByRequestParameter(@RequestParam Integer id) {
		ArrayList<Answer> answers = new ArrayList<>();
		
		for(Answer c : answerRepository.findAll()) {
			if(c.getSolvedQuestion().getId() == id) {
				c.setSolvedQuestion(null);
				answers.add(c);				
			}
		}	
		return answers;
	}
//___________________________________________________________________________________
	

//__________________________________________________________________________________

		//OBTENER TODAS LAS RESPUESTAS (lista de Strings) A UNA PREGUNTA. 
		   //recibe el id de la pregunta de interes
		
		
		
		@GetMapping(path = "/getQuestionAnswersStringByPathVariable/{id}")
		public @ResponseBody ArrayList<String> getQuestionAnswersStringByPathVariable(@PathVariable(value = "id")Integer id) {
			  ArrayList<String> answers = new ArrayList<>();
			
				for(Answer c : answerRepository.findAll()) {
					if(c.getSolvedQuestion().getId() == id) {
						answers.add(c.getText());				
					}
				}	
				return answers;
		}
		
		
		@GetMapping(path = "/getQuestionAnswersStringByRequestParameter")
		public @ResponseBody ArrayList<String> getQuestionAnswersStringByRequestParameter(@RequestParam Integer id) {
			 ArrayList<String> answers = new ArrayList<>();
				
				for(Answer c : answerRepository.findAll()) {
					if(c.getSolvedQuestion().getId() == id) {
						answers.add(c.getText());				
					}
				}	
				return answers;
		}
	//___________________________________________________________________________________
		

}
