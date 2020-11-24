package KnowXchange.Backend.Application.Model;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {
	@Id
	@Column(name = "id_of_review")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer reviewId;
	
	@Column(name = "grade")
	private double grade;
	
	@Column(length = 9000)
	private String description;
	
	
}
