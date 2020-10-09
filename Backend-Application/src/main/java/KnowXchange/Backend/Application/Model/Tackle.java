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
@Table(name = "tackle")
public class Tackle {
	
	@Id
	@Column(name = "tackle_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "theme_id")
	private Theme themeTackled;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course courseTackled;
	
	public Tackle() {
		
	}
	
	public Theme getThemeTackled() {
		return this.themeTackled;
	}
	
	public void setThemeTackled(Theme themeTackled) {
		this.themeTackled = themeTackled;
	}
	
	public Course getCourseTackled() {
		return this.courseTackled;
	}
	
	public void setCourseTackled(Course courseTackled) {
		this.courseTackled = courseTackled;
	}
}
