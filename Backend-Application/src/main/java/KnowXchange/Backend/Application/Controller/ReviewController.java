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

import KnowXchange.Backend.Application.Model.Answer;
import KnowXchange.Backend.Application.Model.Course;
import KnowXchange.Backend.Application.Model.Lesson;
import KnowXchange.Backend.Application.Model.Review;
import KnowXchange.Backend.Application.Repository.ReviewRepository;
import KnowXchange.Backend.Application.Repository.QuestionRepository;
import KnowXchange.Backend.Application.Repository.UserRepository;
import KnowXchange.Backend.Application.Repository.AnswerRepository;
import KnowXchange.Backend.Application.Repository.CourseRepository;

import java.util.ArrayList;


@Controller
@CrossOrigin("*")
@RequestMapping(path="/ReviewController")
public class ReviewController {
	
	@Autowired
    private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	//____________________________________________________________________________________________________________________

		/*
		AGREGAR RESENA
		 */
		
		
		
		
		@PostMapping(path="/addReviewByPathVariable/{description}/{grade}/{opiningUserId}/{reviewedCourseId}")
			  public @ResponseBody Review addQuestionByPathVariable(
					  @PathVariable(value = "description")String description,
					  @PathVariable(value = "grade")Double grade,
					  @PathVariable(value = "opiningUserId")Integer opiningUserId,
					  @PathVariable(value = "reviewedCourseId")Integer reviewedCourseId
			      ) throws Exception {
			
			if(!(grade>=0.0 && grade <=5.0)) {
				throw new Exception("La valoracion del curso debe ser de 1 a 5");
			}
			
			   
			Review review = new Review();
		    review.setGrade(grade);
		    
		    review.setDescription(description);
		    review.setOpiningUser(userRepository.findById(opiningUserId).get());
		    
		    Course curso_calificado = courseRepository.findById(reviewedCourseId).get();
		    curso_calificado.updateCourseScore(grade);
		    courseRepository.save(curso_calificado);
		    
		    review.setReviewed_Course(curso_calificado);
		    reviewRepository.save(review);
		    review.setReviewed_Course(null);
		    return review;
		    //return "Saved";
			
			   
			}
			
		
		@PostMapping(path="/addReviewByRequestParameter")
			  public @ResponseBody Review addQuestionByRequestParameter(
					  @RequestParam String description,
					  @RequestParam Double grade,
					  @RequestParam Integer opiningUserId,
					  @RequestParam Integer reviewedCourseId
			      ) throws Exception {
			   
			if(!(grade>=0.0 && grade <=5.0)) {
				throw new Exception("La valoracion del curso debe ser de 1 a 5");
			}
			
			Review review = new Review();
		    review.setGrade(grade);
		    review.setDescription(description);
		    review.setOpiningUser(userRepository.findById(opiningUserId).get());
		    
		    Course curso_calificado = courseRepository.findById(reviewedCourseId).get();
		    curso_calificado.updateCourseScore(grade);
		    courseRepository.save(curso_calificado);
		    
		    review.setReviewed_Course(curso_calificado);
		    reviewRepository.save(review);
		    review.setReviewed_Course(null);
		    return review;
		    //return "Saved";
			}
		
		//________________________________________________________________________________________________________
	
		
		

		//________________________________________________________________________________________________________

		//OBTENER RESENA POR ID DEL OBJETO


		@GetMapping(path="/getByIdByPathVariable/{id}")
		  public @ResponseBody Review getReviewByIdByPathVariable(@PathVariable(value = "id")Integer id) {
			  Review r = reviewRepository.findById(id).get();
			  r.setReviewed_Course(null);
			  return r;
			  //System.out.println(x);
			  //return x;
			  //return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException());
		}

		@GetMapping(path="/getByIdByRequestParameter")
		  public @ResponseBody Review getReviewByIdByRequestParameter(
				  @RequestParam Integer id) {
			Review r = reviewRepository.findById(id).get();
			  r.setReviewed_Course(null);
			  return r;
		}


		//____________________________________________________________________________________
		
		
		
		
		

		//____________________________________________________________________________________

			//BORRAR-ELIMINAR UNA RESENA
			  //se recibe el id 
			
			
			
			@DeleteMapping(path = "/deleteByPathVariable/{id}")
			public @ResponseBody String deleteQuestionByPathVariable(@PathVariable(value = "id")Integer id) {
				  Review reviewReceived = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException());
				  double rollback_grade = reviewReceived.getGrade();
				  
				  Course curso_actualizado = reviewReceived.getReviewed_Course();
				  curso_actualizado.calculateCourseScoreReviewDeletion(rollback_grade);
				  courseRepository.save(curso_actualizado);
				  
				  reviewRepository.delete(reviewReceived);
				  return "Deleted";
			}
			
			
			@DeleteMapping(path = "/deleteByRequestParameter")
			public @ResponseBody String deleteByRequestParameter(@RequestParam Integer id) {
				  Review reviewReceived = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException());
				  double rollback_grade = reviewReceived.getGrade();
				  
				  Course curso_actualizado = reviewReceived.getReviewed_Course();
				  curso_actualizado.calculateCourseScoreReviewDeletion(rollback_grade);
				  courseRepository.save(curso_actualizado);
				  
				  reviewRepository.delete(reviewReceived);
				  return "Deleted";
			}
			//__________________________________________________________________________________
			

			
			

			//__________________________________________________________________________________

				//OBTENER TODASLAS RESENAS DE UN CURSO. 
				   //recibe el id del curso de interes
				
				
				
				@GetMapping(path = "/getReviewsByPathVariable/{id}")
				public @ResponseBody ArrayList<Review> getQuestionAnswersByPathVariable(@PathVariable(value = "id")Integer id) {
					  ArrayList<Review> reviews = new ArrayList<>();
					
						for(Review c : reviewRepository.findAll()) {
							if(c.getReviewed_Course().getId() == id) {
								c.setReviewed_Course(null);
								reviews.add(c);				
							}
						}	
						return reviews;
				}
				
				
				@GetMapping(path = "/getReviewsByRequestParameter")
				public @ResponseBody ArrayList<Review> getQuestioAnswersByRequestParameter(@RequestParam Integer id) {
					ArrayList<Review> reviews = new ArrayList<>();
					
					for(Review c : reviewRepository.findAll()) {
						if(c.getReviewed_Course().getId() == id) {
							c.setReviewed_Course(null);
							reviews.add(c);				
						}
					}	
					return reviews;
				}
			//___________________________________________________________________________________
				
				
				
				
				
				//________________________________________________________________________________________________________

		//FUNCION PARA REVISAR SI UN USUARIO EVALUO UN CURSO SI O NO
				
				
				@GetMapping(path="/didUserPostedReviewByPathVariable/{opiningUserId}/{reviewedCourseId}")
					  public @ResponseBody boolean didUserPostedReview(
							  @PathVariable(value = "opiningUserId")Integer opiningUserId,
							  @PathVariable(value = "reviewedCourseId")Integer reviewedCourseId
					      ) {
					   
						
				for(Review c : reviewRepository.findAll()) {
					if(c.getOpiningUser().getId() == opiningUserId && c.getReviewed_Course().getId() == reviewedCourseId) {
						return true;		
					}
				}	
		
		return false;  
					}
				
				
				
				@GetMapping(path="/didUserPostedReviewByRequestParameter")
				  public @ResponseBody boolean addQuestionByRequestParameter(
						  @RequestParam Integer opiningUserId,
						  @RequestParam Integer reviewedCourseId
				      ){
					   
						
				for(Review c : reviewRepository.findAll()) {
					if(c.getOpiningUser().getId() == opiningUserId && c.getReviewed_Course().getId() == reviewedCourseId) {
						return true;		
					}
				}	
		
		return false;  
					}
				
				
					

				//____________________________________________________________________________________
				
				
				
	

}
