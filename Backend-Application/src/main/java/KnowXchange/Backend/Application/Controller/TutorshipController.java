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

import KnowXchange.Backend.Application.Model.Tutorship;
import KnowXchange.Backend.Application.Model.User;
import KnowXchange.Backend.Application.Repository.TutorshipRepository;
import KnowXchange.Backend.Application.Repository.UserRepository;

@Controller // This means that this class is a Controller
@CrossOrigin("*")
@RequestMapping(path="/tutorshipController")
public class TutorshipController {
	@Autowired
    private TutorshipRepository tutorshipRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@PostMapping(path = "/addTutorship")
	public @ResponseBody String addNewTutorship(@RequestParam Integer userTellerId
			  ,@RequestParam Integer userListenerId
			  ,@RequestParam String title
		      ,@RequestParam Long tokensCost
		      ,@RequestParam Long tokens
		      ,@RequestParam String description) {
		User userTellerFound = userRepository.findById(userTellerId).orElseThrow(() -> new RuntimeException());
		User userListenerFound = userRepository.findById(userListenerId).orElseThrow(() -> new RuntimeException());
		Tutorship tutorship = new Tutorship();
		tutorship.setUserTeller(userTellerFound);
		tutorship.setUserListener(userListenerFound);
		tutorship.setTitle(title);
		tutorship.setTokensCost(tokensCost);
		tutorshipRepository.save(tutorship);
		return "Created";
	}
	
	@GetMapping(path="/getAllTutorships")
	public @ResponseBody Iterable<Tutorship> getAllTutorships() {
	    return tutorshipRepository.findAll();
	}
	
	@GetMapping(path="/getTutorshipById/{id}")
	public @ResponseBody Tutorship getTutorshipById(@PathVariable(value = "id")Integer id) {
	    return tutorshipRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	@PutMapping(path="modifyTutorshipById/{id}")
	public @ResponseBody String setTutorshipById(@PathVariable(value = "id")Integer id,@RequestParam String title,@RequestParam Long tokenCost) {
		Tutorship tutorship = tutorshipRepository.findById(id).orElseThrow(() -> new RuntimeException());
		tutorship.setTitle(title);
		tutorship.setTokensCost(tokenCost);
		return "Modified";
	}
	
	@DeleteMapping("deleteTutorship/{id}")
	public @ResponseBody String deleteTutorship(@PathVariable(value = "id")Integer id){
		Tutorship tutorship = tutorshipRepository.findById(id).orElseThrow(() -> new RuntimeException());
		tutorshipRepository.delete(tutorship);
		return "Deleted";
	}
	
}
