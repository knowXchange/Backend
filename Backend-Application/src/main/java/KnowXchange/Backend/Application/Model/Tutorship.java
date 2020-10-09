package KnowXchange.Backend.Application.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tutorship")
public class Tutorship {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	private Long tokensCost;
	
	@ManyToOne
	@JoinColumn(name = "if_of_user_listener")
	private User userListener;
	
	@ManyToOne
	@JoinColumn(name = "id_of_user_teller")
	private User userTeller;
	
	@ManyToOne
	@JoinColumn(name = "theme_id")
	private Theme theme;
	
	public Tutorship() {
		
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getTokensCost() {
		return this.tokensCost;
	}
	
	public void setTokensCost(Long tokensCost) {
		this.tokensCost = tokensCost;
	}
	
	public User getUserListener() {
		return this.userListener;
	}
	
	public void setUserListener(User userListener) {
		this.userListener = userListener;
	}
	
	public User getUserTeller() {
		return this.userTeller;
	}
	
	public void setUserTeller(User userTeller) {
		this.userTeller = userTeller;
	}
}
