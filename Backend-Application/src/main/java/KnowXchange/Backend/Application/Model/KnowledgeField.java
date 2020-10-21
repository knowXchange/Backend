package KnowXchange.Backend.Application.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@Table(name = "KnowledgeField")
public class KnowledgeField {
	
	@Id
	@Column(name = "KnowledgeField_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	
	//***************
    @JsonIgnore
    @OneToMany( mappedBy = "field" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FieldBranch> branches;
	//**************
    
	
	public KnowledgeField() {
		
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
	
	
	public List<FieldBranch> getBranches() {
		return this.branches;
	}
	
	
}
