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
@Table(name = "lesson")
public class Lesson {
	@Id
	@Column(name = "lesson_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	private String description;
	
	
	//----------------------------------------------------------------------------
	//una clase pertenece a un inico curso, el curso puede tener muchas clases
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	//----------------------------------------------------------------------------
	
	//---------------------------------------------------------------------------------
	//un curso puede tener muchas preguntas
//	@JsonIgnore
//	@OneToMany( mappedBy = "lesson_pointer" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Question> questions;
	//---------------------------------------------------------------------------------
	
	@JsonIgnore
	@OneToMany( mappedBy = "lesson")
	private List<Theme> themes;
	
	
	
	
	
	public Lesson() {
		
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
	
	public Course getCourse() {
		return this.course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public List<Theme> getThemes(){
		return this.themes;
	}
	
	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}
}
