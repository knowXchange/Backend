package KnowXchange.Backend.Application.Model;

import javax.persistence.*;

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
@Table(name = "Review")
public class Review {
	
	
	@Id
	@Column(name = "Review_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	//(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	
	private double grade;
	
	//@Column(length = 9000)
	private String description;
	
	
	
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
		
		
	
	public Integer getId() {
		return this.id;
	}

	public Double getGrade() {
		return this.grade;
	}
	
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	
	
	public String getDescription() {
		return this.description;
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
