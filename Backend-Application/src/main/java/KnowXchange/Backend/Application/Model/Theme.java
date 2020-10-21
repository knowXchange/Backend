package KnowXchange.Backend.Application.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name = "theme")
public class Theme {
	
	@Id
	@Column(name = "theme_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;
	
	@JsonIgnore
	@OneToMany( mappedBy = "themeTackled" )
	private List<Tackle> tackles;
	
	@JsonIgnore
	@OneToMany( mappedBy = "theme" )
	private List<Tutorship> tutorships;
	

	//******
	//un tema esta dentro de una rama
		@ManyToOne
		@JoinColumn(name = "FieldBranch_id")
	    private FieldBranch fieldbranch;
		//*****
	
	public Theme() {
		
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
	
	public Lesson getLesson() {
		return this.lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	public List<Tackle> getTackles(){
		return this.tackles;
	}
	
	public void setTackles(List<Tackle> tackles){
		this.tackles = tackles;
	}
	
	public List<Tutorship> getTutorships(){
		return this.tutorships;
	}
	
	public void setTutorships(List<Tutorship> tutorships){
		this.tutorships = tutorships;
	}
	
	
	public Integer getBranchFK() {
		return this.fieldbranch.getId();
	}
	
	public void setBranch( FieldBranch fieldbranch) {
		this.fieldbranch = fieldbranch;
	}
}
