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
	
	

}
