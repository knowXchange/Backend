package KnowXchange.Backend.Application.Controller;

import java.util.ArrayList;

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

import KnowXchange.Backend.Application.Model.KnowledgeField;
import KnowXchange.Backend.Application.Model.Theme;
import KnowXchange.Backend.Application.Model.FieldBranch;
import KnowXchange.Backend.Application.Repository.FieldBranchRepository;
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
	
	@Autowired
    private FieldBranchRepository FieldBranchRepository;
	
	@PostMapping(path = "/addTheme")
	public @ResponseBody String addNewTheme(@RequestParam String title, @RequestParam Integer fk_id_Branch ) {
	    Theme theme = new Theme();
		theme.setTitle(title);
		theme.setBranch(getFieldBranchbyId(fk_id_Branch));
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
	
	//----------------------------
	//Funcion que devuelve un arreglo con los nombres de los temas
	@GetMapping(path="/getAllThemeNames")
	
	public @ResponseBody String[] getAllThemeNames(){
		
		long l = themeRepository.count();
		String TemasOfertados[] = new String[(int)l];
		int index = 0;
		for (Theme t : themeRepository.findAll()) {
			TemasOfertados[index] = t.getTitle();
			index++;
		}
		
		return TemasOfertados;
	}
	
	//-------------------------
	
	//-------------------------
	//Funcion que regresa el el tema con un id dado
	public Theme getThemebyId(int id){
		Theme answer = null;
		for (Theme t : themeRepository.findAll()) {
			if(t.getId() == id) {
				answer = t;
			}
			
		}
		
		
		return answer;
	}
	
	
	
	//Funcion que regresa la rama con un id dado
	public FieldBranch getFieldBranchbyId(int id){
		FieldBranch answer = null;
		for (FieldBranch t : FieldBranchRepository.findAll()) {
			if(t.getId() == id) {
				answer = t;
			}
			
		}
		
		
		return answer;
	}
	//------------------------
	
	
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
	
	
	
	
	
	
	
	
	
	
	

	//__________________________________________________________________________________________
	
	/*
______ _ _ _               _____                    
|  ___(_) | |             |_   _|                   
| |_   _| | |_ _ __ ___     | | ___ _ __ ___   __ _ 
|  _| | | | __| '__/ _ \    | |/ _ \ '_ ` _ \ / _` |
| |   | | | |_| | | (_) |   | |  __/ | | | | | (_| |
\_|   |_|_|\__|_|  \___/    \_/\___|_| |_| |_|\__,_|

	 */
	
	@GetMapping(path="/FilterThemesByBranch")
	public @ResponseBody ArrayList<String> FilterBranchesByKnowledgeField(@RequestParam String BranchName){
		
		
		ArrayList<String> filtro = new ArrayList();
		
		//Se itera por las Ramas
		
		//Se recolectan los temas
		for (FieldBranch t : FieldBranchRepository.findAll()) {
			
			
			
			if(t.getTitle().equalsIgnoreCase(BranchName)) {
				
				
				int l = t.getThemes().size();	
				for(int i = 0;i<l;i++) {
					filtro.add(t.getThemes().get(i).getTitle());
				}
				
			}
			
		}
		
		
		return filtro;
	}
	
	//___________________________________________________________________________________________________
}
