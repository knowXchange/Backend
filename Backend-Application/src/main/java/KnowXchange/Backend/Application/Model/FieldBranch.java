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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name = "FieldBranch")
public class FieldBranch {
	@Id
	@Column(name = "FieldBranch_id")
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	
	
	//******
	//Una rama pertenece a un area del conocimiento
	@ManyToOne
	@JoinColumn(name = "field_id")
    private KnowledgeField field;
	//*****
	
	//*******
	//UNA RAMA TIENE VARIOS TEMAS
	 	@JsonIgnore
	    @OneToMany( mappedBy = "fieldbranch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Theme> themes_list;
	 //******
	 	
		//***************
	 	//Una rama es tratada en muchos cursos
	    @JsonIgnore
	    @OneToMany( mappedBy = "fieldbranchPointer" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Course> courses;
		//**************
	
	    
	public FieldBranch() {
		
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
	
	
	public Integer getFieldFK() {
		return this.field.getId();
	}
	
	public KnowledgeField getField() {
		return this.field;
	}
	
	public void setField( KnowledgeField field) {
		this.field = field;
	}
	
	public List<Theme> getThemes( ) {
		return this.themes_list;
	}
	
	/*
	public Integer getFK_KnowledgeField() {
		return this.FK_KnowledgeField;
	}
	
	public void setFK_KnowledgeField(Integer FK_KnowledgeField) {
		this.FK_KnowledgeField = FK_KnowledgeField;
	}
	*/
	
}
