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
@Table(name = "Answer")
public class Answer {
	

	@Id
	@Column(name = "answer_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

    private String Text;
    
    
	//----------------------------------------------------------------------------
	//Hay muchas respuestas que intentan solucionar una pregunta
    
	@ManyToOne
	@JoinColumn(name = "solvedQuestion_id")
	private Question solvedQuestion_pointer;
	//----------------------------------------------------------------------------
	
	//----------------------------------------------------------------------------
	//un usuario puede contribuir respondiendo muchas preguntas
    
	@ManyToOne
	@JoinColumn(name = "personAnswering_id")
	private User personAnswering_pointer;
	//----------------------------------------------------------------------------
	
	
	
	public Integer getId() {
		return this.id;
	}
	
	
	public String getText() {
		return this.Text;
	}
	
	
	public void setText(String text) {
		this.Text = text;
	}
	
	
	
	public void setSolvedQuestion(Question solvedQuestion_pointer) {
		this.solvedQuestion_pointer = solvedQuestion_pointer;
	}

	public Question getSolvedQuestion() {
		return this.solvedQuestion_pointer;
	}
	
	
	
	public void setPersonAnswering(User personAnswering_pointer ) {
		this.personAnswering_pointer = personAnswering_pointer ;
	}

	public User getPersonAnswering() {
		return this.personAnswering_pointer;
	}
	
	
	
	

	

}
