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
@RequestMapping(path="/questionController")
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
			      ) {
			   
			Review review = new Review();
		    review.setGrade(grade);
		    review.setDescription(description);
		    review.setOpiningUser(userRepository.findById(opiningUserId).get());
		    review.setReviewed_Course(courseRepository.findById(reviewedCourseId).get());
		    reviewRepository.save(review);
		    return review;
		    //return "Saved";
			   
			}
			
		
		@PostMapping(path="/addReviewByRequestParameter")
			  public @ResponseBody Review addQuestionByRequestParameter(
					  @RequestParam String description,
					  @RequestParam Double grade,
					  @RequestParam Integer opiningUserId,
					  @RequestParam Integer reviewedCourseId
			      ) {
			   
				
			Review review = new Review();
		    review.setGrade(grade);
		    review.setDescription(description);
		    review.setOpiningUser(userRepository.findById(opiningUserId).get());
		    review.setReviewed_Course(courseRepository.findById(reviewedCourseId).get());
		    reviewRepository.save(review);
		    return review;
		    //return "Saved";
			}
		
		//________________________________________________________________________________________________________
	
		
		

		//________________________________________________________________________________________________________

		//OBTENER RESENA POR ID DEL OBJETO


		@GetMapping(path="/getByIdByPathVariable/{id}")
		  public @ResponseBody Review getReviewByIdByPathVariable(@PathVariable(value = "id")Integer id) {
			  return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException());
		}

		@GetMapping(path="/getByIdByRequestParameter")
		  public @ResponseBody Review getReviewByIdByRequestParameter(
				  @RequestParam Integer id) {
			return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException());
		}


		//____________________________________________________________________________________
		
		
		
		
		

		//____________________________________________________________________________________

			//BORRAR-ELIMINAR UNA RESENA
			  //se recibe el id 
			
			
			
			@DeleteMapping(path = "/deleteByPathVariable/{id}")
			public @ResponseBody String deleteQuestionByPathVariable(@PathVariable(value = "id")Integer id) {
				  Review reviewReceived = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException());
				  reviewRepository.delete(reviewReceived);
				  return "Deleted";
			}
			
			
			@DeleteMapping(path = "/deleteByRequestParameter")
			public @ResponseBody String deleteByRequestParameter(@RequestParam Integer id) {
				  Review reviewReceived = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException());
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
	

}
