/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KnowXchange.Backend.Application.Controller;
import KnowXchange.Backend.Application.Model.Course;
import KnowXchange.Backend.Application.Model.Takes;
import KnowXchange.Backend.Application.Model.User;
import KnowXchange.Backend.Application.Repository.CourseRepository;
import KnowXchange.Backend.Application.Repository.TakesRepository;
import KnowXchange.Backend.Application.Repository.UserRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import KnowXchange.Backend.Application.Repository.AnswerRepository;
import KnowXchange.Backend.Application.Model.Answer;

@Controller
@CrossOrigin("*")
@RequestMapping(path="/userController")
public class UserController {
  @Autowired 
  private UserRepository userRepository;
  
  @Autowired
  private CourseRepository courseRepository;
  
  @Autowired
  private TakesRepository takesRepository;
  
  @Autowired
  private AnswerRepository answerRepository;
  
  
  
  private PasswordEncoder passwordEncoder = encoder();

  @Bean
  public PasswordEncoder encoder() {
	  return new BCryptPasswordEncoder();
  }
    
  
  //____________________________________________________________________________________
  //METODOS CREAR PERFIL BY SANTIAGO
  
  @PutMapping(path = "/modifyUser/{id}")
  public @ResponseBody String modifyUser(@PathVariable(value = "id")Integer id,@RequestParam String email, @RequestParam String description) {
	  User userReceived = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  userReceived.setEmail(email);
	  userReceived.setDescription(description);
	  userRepository.save(userReceived);
	  return "Modified";
  }
  @PutMapping(path = "/modifyPassword/{id}")
  public @ResponseBody String modifyPassword(@PathVariable(value = "id")Integer id,@RequestParam String password) {
	  User userReceived = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  userReceived.setPassword(passwordEncoder.encode(password));
	  userRepository.save(userReceived);
	  return "Modified";
  }
  
  
  //____________________________________________________________________________________
  
  
  
  
  
  
  
  
  @PostMapping(path="/add")
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String email
      ,@RequestParam String password
      ,@RequestParam Long tokens
      ,@RequestParam String description) {

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    n.setPassword(passwordEncoder.encode(password));
    n.setTokens(tokens);
    n.setDescription(description);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/getAll")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }

  @GetMapping(path="/getById/{id}")
  public @ResponseBody User getUserById(@PathVariable(value = "id")Integer id) {
	  User userObtained = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  userObtained.setCourses(null);
	  return userObtained;
  }
  
  /*
   //ACTUALIZADO ARRIBA
  @PutMapping(path = "/modifyUser/{id}")
  public @ResponseBody String modifyUser(@PathVariable(value = "id")Integer id,@RequestBody User user) {
	  User userReceived = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  userReceived.setName(user.getName());
	  userReceived.setPassword(user.getPassword());
	  userReceived.setEmail(user.getEmail());
	  userReceived.setDescription(user.getDescription());
	  return "Modified";
  }*/
  
  @DeleteMapping("DeleteUser/{id}")
  public @ResponseBody String deleteUser(@PathVariable(value = "id")Integer id) {
	  User userReceived = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  userRepository.delete(userReceived);
	  return "Deleted";
  }
  
  @GetMapping(path="/get-login")
  public @ResponseBody int getLoginValidation( @RequestParam String name, @RequestParam String password) {
    // This returns a JSON or XML with the users
	  Iterable<User> temp = userRepository.findAll();
	  for(User u: temp) {
		  if(u.getName().equals(name) && passwordEncoder.matches(password,u.getPassword()))
				  return u.getId();
	  }
	  return 0;
	  
  }
  
  @PutMapping(path = "/register-into-course")
  public @ResponseBody String registerIntoCourse(@RequestParam Integer userId,@RequestParam Integer courseId) {
	  String ans = "";
	  boolean wasFound = false;
	  User userObtained = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
	  Course courseObtained = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException());
	  if(userObtained.getTokens()<courseObtained.getTokensCost()) {
		  return "Tokens insuficientes";
	  }else {
	  
	  for(Takes t:this.takesRepository.findAll()) {
		  if(t.getUserAssistant().getId() == userId && t.getCourseTaken().getId() == courseId) {
			  wasFound = true;
			  break;
		  }
	  }
	  if(!wasFound) {
		  Takes take = new Takes();
		  take.setCourseTaken(courseObtained);
		  take.setUserAssistant(userObtained);
		  this.takesRepository.save(take);
		  
		  //	Al usuario que inscribe se le descuentan los tokens que cuesta el curso
		  userObtained.spendTokens(courseObtained.getTokensCost());
		  userRepository.save(userObtained);
		  
		  //Al tutor del curso inscrito se le agregan los tokens pago
		  User ownerOfCourse =  courseObtained.getUserOwner();
		  ownerOfCourse.getTokenPayment(courseObtained.getTokensCost());
		  userRepository.save(ownerOfCourse);
		  
		  ans = "Registered";
	  }else {
		  ans = "The Student is Already Registered";
	  }
	  return ans;
	  }
  }
  
  
  @GetMapping(path="/getUserPostedAnswersByPathVariable/{id}")
  public @ResponseBody ArrayList<Answer> getUserPostedAnswersByPathVariable(@PathVariable(value = "id")Integer id) {
	  ArrayList<Answer> answers = new ArrayList<>();
		
		for(Answer c : answerRepository.findAll()) {
			if(c.getPersonAnswering().getId() == id) {
				answers.add(c);				
			}
		}
		
		return answers;
  }
  
  
  
  
  
	@GetMapping(path = "/getUserPostedAnswersByRequestParameter")
	public @ResponseBody ArrayList<Answer> getUserPostedAnswersByRequestParameter(@RequestParam Integer id) {
		ArrayList<Answer> answers = new ArrayList<>();
		
		for(Answer c : answerRepository.findAll()) {
			if(c.getPersonAnswering().getId() == id) {
				answers.add(c);				
			}
		}
		
		return answers;
	}
   
}