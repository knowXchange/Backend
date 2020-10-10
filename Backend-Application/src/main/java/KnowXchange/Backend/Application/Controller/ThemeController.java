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
import KnowXchange.Backend.Application.Model.Theme;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.TackleRepository;
import KnowXchange.Backend.Application.Repository.ThemeRepository;
import KnowXchange.Backend.Application.Repository.TutorshipRepository;

@Controller
@CrossOrigin("*")
@RequestMapping(path="/themeController")
public class ThemeController {
	@Autowired
    private ThemeRepository themeRepository;
	
	@Autowired
    private LessonRepository lessonRepository;
	
	@Autowired
	private TackleRepository tackleRepository;
	
	@Autowired
	private TutorshipRepository tutorshipRepository;
	
	@PostMapping(path = "/addTheme")
	public @ResponseBody String addNewTutorship(@RequestParam String title) {
	    Theme theme = new Theme();
		theme.setTitle(title);
		themeRepository.save(theme);
		return "Created";
	}
	
	@GetMapping(path="/getAllThemes")
	public @ResponseBody Iterable<Theme> getAllThemes() {
	    return themeRepository.findAll();
	}
	
	@GetMapping(path="/getThemeById/{id}")
	public @ResponseBody Theme getThemeById(@PathVariable(value = "id")Integer id) {
	    return themeRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	@PutMapping(path="modifyThemeById/{id}")
	public @ResponseBody String setTutorshipById(@PathVariable(value = "id")Integer id,@RequestParam String title) {
		Theme theme = themeRepository.findById(id).orElseThrow(() -> new RuntimeException());
		theme.setTitle(title);
		return "Modified";
	}
	
	@DeleteMapping("deleteTheme/{id}")
	public @ResponseBody String deleteTheme(@PathVariable(value = "id")Integer id){
		Theme theme = themeRepository.findById(id).orElseThrow(() -> new RuntimeException());
		themeRepository.delete(theme);
		return "Deleted";
	}
}
