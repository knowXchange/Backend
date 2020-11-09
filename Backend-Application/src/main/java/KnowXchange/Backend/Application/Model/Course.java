package KnowXchange.Backend.Application.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
    private String title;
	
	private String description;
	
	private Long tokensCost;

	//---------------------------------------------------------------------------------
	//Un curso se enmarca en un area del conocimiento
	@ManyToOne
	@JoinColumn(name = "Branch_id")
    private FieldBranch fieldbranchPointer;
	//---------------------------------------------------------------------------------
	
	
	//---------------------------------------------------------------------------------
	//un curso consta de muchas clases
	@JsonIgnore
	@OneToMany( mappedBy = "course" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Lesson> lessons;
	//---------------------------------------------------------------------------------
	
	@ManyToOne
	@JoinColumn(name = "user_owner_id")
	private User userOwner;
	
	@JsonIgnore
	@OneToMany( mappedBy = "courseTaken" )
	private List<Takes> reserves;
	
	@JsonIgnore
	@OneToMany( mappedBy = "courseTackled" )
	private List<Tackle> tackles;
	
	public Course() {
		
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getTokensCost() {
		return this.tokensCost;
	}
	
	public void setTokensCost(Long tokensCost) {
		this.tokensCost = tokensCost;
	}
	
	public List<Lesson> getLessons(){
		return this.lessons;
	}
	
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	
	public User getUserOwner() {
		return this.userOwner;
	}
	
	public void setUserOwner(User userOwner) {
		this.userOwner = userOwner;
	}
	
	public List<Takes> getListTakes() {
		return this.reserves;
	}
	
	public void setListTakes(List<Takes> reserves) {
		this.reserves = reserves;
	}
	
	public List<Tackle> getListTackles() {
		return this.tackles;
	}
	
	public void setListTackles(List<Tackle> tackles) {
		this.tackles = tackles;
	}
	
	
	
	public void setBranch (FieldBranch fieldbranchPointer) {
		this.fieldbranchPointer = fieldbranchPointer;
	}
	
	public Integer getFieldId() {
		return this.fieldbranchPointer.getFieldFK();
	}
}
