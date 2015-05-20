package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Section1")
public class Section1Model extends Model{

	public enum Role{
		CI, supervisor, student
	}

	public Section1Model(){
		super();
	}

	public Section1Model(int id){
		super();
		this.id = id;
	} 

	@Id
	@Valid
	public int id;

	@OneToOne(optional=false, cascade=CascadeType.ALL,
		 mappedBy="section1", targetEntity=ApplicationModel.class)
	public ApplicationModel applicationModel;

	public static Finder<Integer,Section1Model> find = new Finder<Integer,Section1Model>(
        Integer.class, Section1Model.class
    );

	//@Constraints.Required
	public String projectTitle = null;

	public String name1, name2, name3, name4, name5 = null;

	//public String 
	public String school1, school2, school3, school4, school5 = null;

	public int staffStudentID1, staffStudentID2, staffStudentID3, 
		staffStudentID4, staffStudentID5 = 0;

	public Role role1, role2, role3, role4, role5 = null;
	
	public boolean candidacy1, candidacy2, candidacy3, candidacy4,
		candidacy5 = false;

	public boolean integrityTrainingComplete1, integrityTrainingComplete12, 
		integrityTrainingComplete3, integrityTrainingComplete4, 
		integrityTrainingComplete5 = false;	

	public boolean contactUsePricipalInvestigator6 = false;

	public String contactName = null;

	public int contactStaffID = 0;

	public String contactSchoolCentreArea = null;

	public int contactTelephone = 0;

	public String contactEmail = null;
}