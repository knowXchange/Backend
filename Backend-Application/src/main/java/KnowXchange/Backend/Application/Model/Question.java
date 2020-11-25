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
@Table(name = "Question")
public class Question {
	
	

	@Id
	@Column(name = "question_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
    private String Topic;
    
    private String Text;
	
	//----------------------------------------------------------------------------
	//un recurso esta asociado a una clase en concreto
    
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson_pointer;
	//----------------------------------------------------------------------------
	
	
	//----------------------------------------------------------------------------
	//una pregunta es hecha por un usuario
    
	@ManyToOne
	@JoinColumn(name = "asking_user_id")
	private User asking_user_pointer;
	//----------------------------------------------------------------------------

	//---------------------------------------------------------------------------------
	//una pregunta puede tener varias respuestas
	@JsonIgnore
	@OneToMany( mappedBy = "solvedQuestion_pointer" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Answer> answers;
	//---------------------------------------------------------------------------------


	
	public Integer getId() {
		return this.id;
	}
	
	
	public String getTopic() {
		return this.Topic;
	}
	
	public String getText() {
		return this.Text;
	}
	
	
	
	public void setTopic(String topic) {
		this.Topic = topic;
	}
	
	
	public void setText(String text) {
		this.Text = text;
	}
	
	
	
	public void SetAsking_user(User user) {
		this.asking_user_pointer = user;
	}
	
	public User getAsking_user() {
		return this.asking_user_pointer;
	}
	
	
	
	public void setLesson(Lesson lesson) {
		this.lesson_pointer = lesson;
	}

	public Lesson getLesson() {
		return this.lesson_pointer;
	}
	
	public List<Answer> getAnswers(){
		return this.answers;
	}
	
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}


