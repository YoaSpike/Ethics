package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Section7")
public class Section7Model extends Model{

	public Section7Model(){
		super();
	}

	public Section7Model(int id){
		super();
		this.id = id;
	}

	@Id
	@Valid
	public int id;
	
	public static Finder<Integer,Section7Model> find = new Finder<Integer,Section7Model>(
        Integer.class, Section7Model.class
    );
	
	public String[] coInvestigatorNames = new String[6];
	public String[] coInvestigatorSigs = new String[6];
	public Date[] coInvestigatorSigDates = new Date[6];
	
	@Constraints.required
	public String principleInvestigatorName;
	@Constraints.required
	public Date principleInvestigatorSigDate;
	@Constraints.required
	public String principleInvestigatorSig;
	
	@Constraints.required
	public String hosName;
	@Constraints.required
	public Date hosSigDate;
	@Constraints.required
	public String hosSig;
	
}