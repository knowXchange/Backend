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
@Table(name = "takes")
public class Takes {
	
	@Id
	@Column(name = "takes_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_assistant_id")
	private User userAssistant;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course courseTaken;
	
	private String state;

	public Takes() {
		
	}
	
	public User getUserAssistant() {
		return this.userAssistant;
	}
	
	public void setUserAssistant(User userAssistant) {
		this.userAssistant = userAssistant;
	}
	
	public Course getCourseTaken() {
		return this.courseTaken;
	}
	
	public void setCourseTaken(Course courseTaken) {
		this.courseTaken = courseTaken;
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}
