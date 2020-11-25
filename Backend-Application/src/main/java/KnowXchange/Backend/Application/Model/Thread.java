package KnowXchange.Backend.Application.Model;
import java.util.ArrayList;

public class Thread {
	private Question question;
	private ArrayList<Answer> answers;

	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(ArrayList<Answer> answers) {
		this.question = question;
	}
	
	
}
