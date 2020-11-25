


package KnowXchange.Backend.Application.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Appuser")
public class User {
  @Id
  @Column(name = "id_of_user")
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

  private String password;
  
  private Long tokens;
  
  private String description;
  
  @JsonIgnore
  @OneToMany( mappedBy = "userListener" )
  private List<Tutorship> tutorshipsReceived;
  
  @JsonIgnore
  @OneToMany( mappedBy = "userTeller" )
  private List<Tutorship> tutorshipsSolved;
  
	//---------------------------------------------------------------------------------
	//un curso puede tener muchas preguntas
	@JsonIgnore
	@OneToMany( mappedBy = "asking_user_pointer" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Question> asked_questions;
	//---------------------------------------------------------------------------------
	
  

	//---------------------------------------------------------------------------------
	//un usuario puede responder muchas preguntas publicando repuestas
	@JsonIgnore
	@OneToMany( mappedBy = "personAnswering_pointer" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Answer> postedAnswers;
	//---------------------------------------------------------------------------------

	
	
  @JsonIgnore
  @OneToMany( mappedBy = "userOwner" )
  private List<Course> courses;
  
  @JsonIgnore
  @OneToMany( mappedBy = "userAssistant" )
  private List<Takes> reserves;
  
  public User() {
	  
  }
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPassword() {
	    return password;
  }
  
  public void setPassword(String password) {
	    this.password = password;
  }

  public Long getTokens() {
	  return tokens;
  }
  
  public void setTokens(Long tokens) {
	  this.tokens = tokens;
  }
  
  public String getDescription() {
	  return this.description;
  }
  
  public void setDescription(String description) {
	  this.description = description;
  }
  
  public List<Tutorship> getTutorshipsReceived(){
	  return this.tutorshipsReceived;
  }

  public void setTutorshipsReceived(List<Tutorship> tutorshipsReceived){
	  this.tutorshipsReceived = tutorshipsReceived;
  }
  
  
  
  
  
  public List<Answer> getPostedAnswers(){
	  return this.postedAnswers;
  }

  public void setPostedAnswers(List<Answer> postedAnswers){
	  this.postedAnswers = postedAnswers;
  }
  
  
  
  
  
  
}