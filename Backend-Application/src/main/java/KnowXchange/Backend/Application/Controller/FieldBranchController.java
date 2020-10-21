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
import KnowXchange.Backend.Application.Model.FieldBranch;
import KnowXchange.Backend.Application.Model.KnowledgeField;
import KnowXchange.Backend.Application.Repository.LessonRepository;
import KnowXchange.Backend.Application.Repository.TackleRepository;
import KnowXchange.Backend.Application.Repository.FieldBranchRepository;
import KnowXchange.Backend.Application.Repository.KnowledgeFieldRepository;
import KnowXchange.Backend.Application.Repository.TutorshipRepository;
import KnowXchange.Backend.Application.Controller.KnowledgeFieldController;

import org.springframework.data.jpa.repository.Query;
import java. util. ArrayList;
import java.util.Collection;


@Controller
@CrossOrigin("*")
@RequestMapping(path="/FieldBranchController")
public class FieldBranchController {
	@Autowired
    private FieldBranchRepository FieldBranchRepository;
	
	@Autowired
    private LessonRepository lessonRepository;
	
	@Autowired
	private TackleRepository tackleRepository;
	
	@Autowired
	private TutorshipRepository tutorshipRepository;
	
	@Autowired
    private KnowledgeFieldRepository KnowledgeFieldRepository;
	
	
	
	
	@PostMapping(path = "/addFieldBranch")
	public @ResponseBody String addNewFieldBranch(@RequestParam String title,@RequestParam Integer FK_KnowledgeField) {
	    FieldBranch FieldBranch = new FieldBranch();
		FieldBranch.setTitle(title);
		//FieldBranch.setField(KnowledgeFieldController.getKnowledgeFieldbyId(FK_KnowledgeField));
		FieldBranch.setField(getKnowledgeFieldbyId(FK_KnowledgeField));
		
		//System.out.println();
		
		//FieldBranch.setFieldFK(FK_KnowledgeField);
		FieldBranchRepository.save(FieldBranch);
		return "Created";
	}
	
	@GetMapping(path="/getAllFieldBranchs")
	public @ResponseBody Iterable<FieldBranch> getAllFieldBranchs() {
	    return FieldBranchRepository.findAll();
	}
	
	@GetMapping(path="/getFieldBranchById/{id}")
	public @ResponseBody FieldBranch getFieldBranchById(@PathVariable(value = "id")Integer id) {
	    return FieldBranchRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	//----------------------------
	//Funcion que devuelve un arreglo con los nombres de los temas
	
	@GetMapping(path="/getAllFieldBranchNames")
	
	public @ResponseBody String[] getAllFieldBranchNames(){
		
		long l = FieldBranchRepository.count();
		String TemasOfertados[] = new String[(int)l];
		int index = 0;
		for (FieldBranch t : FieldBranchRepository.findAll()) {
			TemasOfertados[index] = t.getTitle();
			index++;
		}
		
		return TemasOfertados;
	}
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------
	/*
	@GetMapping(path="/getBranchNamesOfaField")
	
	public @ResponseBody String[] getBranchNamesOfaField(@RequestParam String FieldName){
		
		//Se itera por las ramas
		long l = FieldBranchRepository.count();
		String RamasOfertadasDelArea[] = new String[(int)l];
		int index = 0;
		
		//Se recolectan las ramas cuyo campo es el de interes
		for (FieldBranch t : FieldBranchRepository.findAll()) {
			
			if( t.getFieldFK() == getFieldIdFromTitle(FieldName)) {
				RamasOfertadasDelArea[index] = t.getTitle();
				index++;
			}
		}
		
		String arreglo[] = new String[index + 1];
		int l2 = arreglo.length;
		for(int i =0;i<l2;i++) {
			arreglo[i] = RamasOfertadasDelArea[i];
		}
		return arreglo;
	}*/
	
	
	
@GetMapping(path="/getBranchNamesOfaField")
	
	public @ResponseBody String[] getBranchNamesOfaField(@RequestParam Integer FieldId){
		
		//Se itera por las ramas
		long l = FieldBranchRepository.count();
		String RamasOfertadasDelArea[] = new String[(int)l];
		int index = 0;
		
		//Se recolectan las ramas cuyo campo es el de interes
		for (FieldBranch t : FieldBranchRepository.findAll()) { 
			
			if( t.getFieldFK() == FieldId) {
				RamasOfertadasDelArea[index] = t.getTitle();
				index++;
			}
		}
		
		String arreglo[] = new String[index + 1];
		int l2 = arreglo.length;
		for(int i =0;i<l2;i++) {
			arreglo[i] = RamasOfertadasDelArea[i];
		}
		return arreglo;
	}
	
	
	

	
	
	


	@GetMapping(path="/getFieldIdFromTitle")
	public @ResponseBody Integer getFieldIdFromTitle(@RequestParam String FieldName){
		
		//Se itera por las Areas
		
		int solucion = -1;
		//Se recolecta el id de la area de interes
		for (KnowledgeField t : KnowledgeFieldRepository.findAll()) {
			
			if(t.getTitle().equalsIgnoreCase(FieldName)) {
				solucion = t.getId();
				System.out.println("______________-\n" + t.getBranches().size() + t.getBranches().get(0).getTitle());
				
				
			}
			
		}
		
		
		return solucion;
		
	}
	
	//__________________________________________________________________________________________
	
	/*
	 * 
 ______ _ _ _              ______                      
|  ___(_) | |             | ___ \                     
| |_   _| | |_ _ __ ___   | |_/ /__ _ _ __ ___   __ _ 
|  _| | | | __| '__/ _ \  |    // _` | '_ ` _ \ / _` |
| |   | | | |_| | | (_) | | |\ \ (_| | | | | | | (_| |
\_|   |_|_|\__|_|  \___/  \_| \_\__,_|_| |_| |_|\__,_|

	 */
	
	@GetMapping(path="/FilterBranchesByKnowledgeField")
	public @ResponseBody ArrayList<String> FilterBranchesByKnowledgeField(@RequestParam String FieldName){
		
		
		ArrayList<String> filtro = new ArrayList();
		
		//Se itera por las Areas
		
		int solucion = -1;
		//Se recolecta el id de la area de interes
		for (KnowledgeField t : KnowledgeFieldRepository.findAll()) {
			
			
			
			if(t.getTitle().equalsIgnoreCase(FieldName)) {
				solucion = t.getId();
				//System.out.println("______________-\n" + t.getBranches().size() + t.getBranches().get(0).getTitle());
				
				
				int l = t.getBranches().size();	
				for(int i = 0;i<l;i++) {
					filtro.add(t.getBranches().get(i).getTitle());
				}
				
			}
			
		}
		
		
		return filtro;
	}
	
	//___________________________________________________________________________________________________
	
	//------------------------------------------------------------------------------------------------------------------
	
	
	
	@PutMapping(path="modifyFieldBranchById/{id}")
	public @ResponseBody String setTutorshipById(@PathVariable(value = "id")Integer id,@RequestParam String title) {
		FieldBranch FieldBranch = FieldBranchRepository.findById(id).orElseThrow(() -> new RuntimeException());
		FieldBranch.setTitle(title);
		return "Modified";
	}
	
	@DeleteMapping("deleteFieldBranch/{id}")
	public @ResponseBody String deleteFieldBranch(@PathVariable(value = "id")Integer id){
		FieldBranch FieldBranch = FieldBranchRepository.findById(id).orElseThrow(() -> new RuntimeException());
		FieldBranchRepository.delete(FieldBranch);
		return "Deleted";
	}
	
	
	//-------------------------
	//Funcion que regresa el area del conocimiento con un id dado
	public KnowledgeField getKnowledgeFieldbyId(int id){
		KnowledgeField answer = null;
		for (KnowledgeField t : KnowledgeFieldRepository.findAll()) {
			if(t.getId() == id) {
				answer = t;
			}
			
		}
			
		return answer;
	}
	//------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public static void() {
		String hql = "SELECT E.firstName FROM Employee E";
		Query query = createQuery(hql);
		List results = query.list();
	}*/
	/*
	 @Query("select new com.example.EmployeeAndCompanyDTO(e.id, e.name, e.age, e.company.name) from Employee e")
	   Collection<KnowledgeField> employeeAndCompanyView();
	*/
	
/*
	public List<User> loadAllUserDescriptors(UserSortField sortField, boolean ascending) {
	 
	    String queryString = "select new pl.tomaszdziurko.UserDescriptor(u.name, u.surname, " +
	            " country.name, country.population, " +
	            " city.name) from User u left join u.city as city left join u.country as country " +
	            " order by " + sortField.getField() + (ascending ? " asc " : " desc ");
	    return entityManager.createQuery(queryString).getResultList();
	}
	*/
	
}