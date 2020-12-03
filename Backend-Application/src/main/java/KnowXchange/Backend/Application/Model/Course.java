package KnowXchange.Backend.Application.Model;

import java.sql.Clob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	
    
   //@Column(length = 16000)
	private String description;
	
	private Long tokensCost;

	
	private Integer number_of_reviews;
	private Double score_accumulator;
	private Double average_score;
	
	
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
	
	//---------------------------------------------------------------------------------
	//un curso es calificado en varias resenas
	@JsonIgnore
	@OneToMany( mappedBy = "reviewedCourse_pointer" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Review> courseReviews;
	//---------------------------------------------------------------------------------
	
	@ManyToOne
	@JoinColumn(name = "user_owner_id")
	private User userOwner;
	
	@JsonIgnore
	@OneToMany( mappedBy = "courseTaken" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Takes> reserves;
	
	@JsonIgnore
	@OneToMany( mappedBy = "courseTackled"  , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
	
	
	
	
	
	
	/*
	public Clob getDescription() {
		return this.description;
	}
	
	public void setDescription(Clob description) {
		this.description = description;
	}*/
	
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
	
	
	public Integer getNumber_of_reviews() {
		return this.number_of_reviews;
	}
	
	
	public void setNumber_of_reviews(Integer number_of_reviews) {
		this.number_of_reviews = number_of_reviews;
	}
	
	
	public Double getAverageScore() {
		return this.average_score;
	}
	
	
	public void setAverageScore(Double average_score) {
		this.average_score = average_score;
	}
	
	public Double getScore_accumulator() {
		return this.score_accumulator;
	}
	
	
	public void setScore_accumulator(Double score_accumulator) {
		this.score_accumulator = score_accumulator;
	}
	
	
	
	
	public void updateCourseScore(Double score) {
		this.number_of_reviews = this.number_of_reviews + 1;
		this.score_accumulator = this.score_accumulator + score;
		this.average_score = ((double)(this.score_accumulator)/(double)(this.number_of_reviews))  ;
	}
	
	public void calculateCourseScoreReviewDeletion(Double score) {
		this.number_of_reviews = this.number_of_reviews - 1;
		this.score_accumulator = this.score_accumulator - score;
		this.average_score = ((double)(this.score_accumulator)/(double)(this.number_of_reviews))  ;
	}
	
	
}
