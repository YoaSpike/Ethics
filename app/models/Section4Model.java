package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Section4")
public class Section4Model extends Model{

	public Section4Model(){
		super();
	}

	public Section4Model(int id){
		
	}

	@Id
	@Valid
	public int id;

	@OneToOne(optional=false, cascade=CascadeType.ALL,
		 mappedBy="section4", targetEntity=ApplicationModel.class)
	public ApplicationModel applicationModel;
	
	public static Finder<Integer,Section4Model> find = new Finder<Integer,Section4Model>(
        Integer.class, Section4Model.class
    );

	@Constraints.Required
	public boolean doesYourResearchInvolveWomenWhoArePregnant = false;

	public String doesYourResearchInvolveWomenWhoArePregnantText;

	}