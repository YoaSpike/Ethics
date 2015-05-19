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
public class Section4 extends Model{

	public Section4(){
		super();
	}

	public Section4(int id){
		
	}

	@Id
	@Valid

	public static Finder<Integer,Section4> find = new Finder<Integer,Section4>(
        Integer.class, Section4.class
    );

	@Constraints.Required
	public boolean doesYourResearchInvolveWomenWhoArePregnant = false;

	public String doesYourResearchInvolveWomenWhoArePregnantText;

	}