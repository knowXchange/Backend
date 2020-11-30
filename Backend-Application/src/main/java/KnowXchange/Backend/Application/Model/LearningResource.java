package KnowXchange.Backend.Application.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "LearningResource")
public class LearningResource {
	

	@Id
	@Column(name = "LearningResource_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

    private String Type;
    
    private String Name;
    
    @Column(length = 9000)
    private String Link;
    
    

	//----------------------------------------------------------------------------
	//Hay muchos recursos que aportan a una clase-leccion
    
	@ManyToOne
	@JoinColumn(name = "supported_lesson_id")
	private Lesson supported_lesson_pointer;
	//----------------------------------------------------------------------------
    
    
	
    
	public Integer getId() {
		return this.id;
	}
	
	
	
	public String getType() {
		return this.Type;
	}
	
	public void setType(String type) {
		this.Type = type;
	}
	
	
	
	
	public String getName() {
		return this.Name;
	}
	
	public void setName(String name) {
		this.Name = name;
	}
	
	
	
	
	
	
	public String getLink() {
		return this.Link;
	}
	
	public void setLink(String link) {
		this.Link = link;
	}
	
	
	
	
	
	public Lesson getSupportedLesson() {
		return this.supported_lesson_pointer;
	}
	
	public void setSupportedLesson(Lesson supported_lesson_pointer) {
		this.supported_lesson_pointer = supported_lesson_pointer;
	}
    

}
