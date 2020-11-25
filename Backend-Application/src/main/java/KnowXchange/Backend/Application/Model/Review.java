package KnowXchange.Backend.Application.Model;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {
	
	
	@Id
	@Column(name = "id_of_review")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer reviewId;
	
	
	
	//----------------------------------------------------------------------------
		//una resena es escrita por un usuario
	    
		@ManyToOne
		@JoinColumn(name = "opining_user_id")
		private User opiningUser_pointer;
		//----------------------------------------------------------------------------
		
		
		//----------------------------------------------------------------------------
			//una resena califica a un curso
		    
			@ManyToOne
			@JoinColumn(name = "reviewed_Course_id")
			private Course reviewedCourse_pointer;
			//----------------------------------------------------------------------------
		
		
	
	@Column(name = "grade")
	private double grade;
	
	@Column(length = 9000)
	private String description;
	
	
	public Double getGrade() {
		return this.grade;
	}
	
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	
	
	public Double getDescription() {
		return this.grade;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public User getOpiningUser() {
		return this.opiningUser_pointer;
	}
	
	public void setOpiningUser(User opiningUser) {
		this.opiningUser_pointer = opiningUser;
	}
	
	
	
	public Course getReviewed_Course() {
		return this.reviewedCourse_pointer;
	}
	
	public void setReviewed_Course(Course reviewedCourse) {
		this.reviewedCourse_pointer = reviewedCourse;
	}
	
	
}
