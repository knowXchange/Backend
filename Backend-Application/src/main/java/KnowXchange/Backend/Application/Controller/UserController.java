/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KnowXchange.Backend.Application.Controller;
import KnowXchange.Backend.Application.Model.User;
import KnowXchange.Backend.Application.Repository.UserRepository;
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

@Controller
@CrossOrigin("*")
@RequestMapping(path="/userController")
public class UserController {
  @Autowired 
  private UserRepository userRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String email
      ,@RequestParam String password
      ,@RequestParam Long tokens
      ,@RequestParam String description) {

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    n.setPassword(password);
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
	  return userRepository.findById(id).orElseThrow(() -> new RuntimeException());
  }
  
  @PutMapping(path = "/modifyUser/{id}")
  public @ResponseBody String modifyUser(@PathVariable(value = "id")Integer id,@RequestBody User user) {
	  User userReceived = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  userReceived.setName(user.getName());
	  userReceived.setPassword(user.getPassword());
	  userReceived.setEmail(user.getEmail());
	  userReceived.setDescription(user.getDescription());
	  return "Modified";
  }
  
  @DeleteMapping("DeleteUser/{id}")
  public @ResponseBody String deleteUser(@PathVariable(value = "id")Integer id) {
	  User userReceived = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
	  userRepository.delete(userReceived);
	  return "Deleted";
  }
}