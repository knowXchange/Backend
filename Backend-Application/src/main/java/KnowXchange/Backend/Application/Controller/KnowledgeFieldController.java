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
import KnowXchange.Backend.Application.Model.KnowledgeField;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.TackleRepository;
import KnowXchange.Backend.Application.Repository.FieldBranchRepository;
import KnowXchange.Backend.Application.Repository.KnowledgeFieldRepository;
import KnowXchange.Backend.Application.Repository.TutorshipRepository;

@Controller
@CrossOrigin("*")
@RequestMapping(path="/KnowledgeFieldController")
public class KnowledgeFieldController {
	@Autowired
    private  KnowledgeFieldRepository KnowledgeFieldRepository;
	
	@Autowired
    private LessonRepository lessonRepository;
	
	@Autowired
	private TackleRepository tackleRepository;
	
	@Autowired
	private TutorshipRepository tutorshipRepository;
	
	@Autowired
    private FieldBranchRepository FieldBranchRepository;
	
	
	
	@PostMapping(path = "/addKnowledgeField")
	public @ResponseBody String addNewKnowledgeField(@RequestParam String title) {
	    KnowledgeField KnowledgeField = new KnowledgeField();
		KnowledgeField.setTitle(title);
		KnowledgeFieldRepository.save(KnowledgeField);
		return "Created";
	}
	
	@GetMapping(path="/getAllKnowledgeFields")
	public @ResponseBody Iterable<KnowledgeField> getAllKnowledgeFields() {
	    return KnowledgeFieldRepository.findAll();
	}
	
	@GetMapping(path="/getKnowledgeFieldById/{id}")
	public @ResponseBody KnowledgeField getKnowledgeFieldById(@PathVariable(value = "id")Integer id) {
	    return KnowledgeFieldRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	//----------------------------
	//Funcion que devuelve un arreglo con los nombres de los temas
	
	@GetMapping(path="/getAllKnowledgeFieldNames")
	
	public @ResponseBody String[] getAllKnowledgeFieldNames(){
		
		long l = KnowledgeFieldRepository.count();
		String TemasOfertados[] = new String[(int)l];
		int index = 0;
		for (KnowledgeField t : KnowledgeFieldRepository.findAll()) {
			TemasOfertados[index] = t.getTitle();
			index++;
		}
		
		return TemasOfertados;
	}
	
	//-------------------------
	
	//----------------------------
	//Funcion que devuelve un arreglo con los nombres de los temas
	
	
	/*
	public static KnowledgeField getKnowledgeFieldbyId(int id){
		
		for (KnowledgeField t : KnowledgeFieldRepository.findAll()) {
			if(t.getId() == id) {
				return t;
			}
			
		}
		
		return null;
	}*/
	
	//-------------------------
	
	
	
	@PutMapping(path="modifyKnowledgeFieldById/{id}")
	public @ResponseBody String setTutorshipById(@PathVariable(value = "id")Integer id,@RequestParam String title) {
		KnowledgeField KnowledgeField = KnowledgeFieldRepository.findById(id).orElseThrow(() -> new RuntimeException());
		KnowledgeField.setTitle(title);
		return "Modified";
	}
	
	@DeleteMapping("deleteKnowledgeField/{id}")
	public @ResponseBody String deleteKnowledgeField(@PathVariable(value = "id")Integer id){
		KnowledgeField KnowledgeField = KnowledgeFieldRepository.findById(id).orElseThrow(() -> new RuntimeException());
		KnowledgeFieldRepository.delete(KnowledgeField);
		return "Deleted";
	}
}