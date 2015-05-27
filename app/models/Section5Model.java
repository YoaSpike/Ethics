package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Section5")
public class Section5Model extends Model{

	public Section5Model(){
		super();
	}

	public Section5Model(int id){
		super();
		this.id = id;
	}

	@Id
	@Valid
	public int id;
	
	public static Finder<Integer,Section5Model> find = new Finder<Integer,Section5Model>(
        Integer.class, Section5Model.class
    );
	
	@Constraints.Required
	public boolean areConflictsOfInterest = false;
	public String conflictsOfInterest = null;
	
}