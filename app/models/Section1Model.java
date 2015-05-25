package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import business.application.Role;

@Entity
@Table(name="Section1")
public class Section1Model extends Model{
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

	@Constraints.Required
	public String projectTitle = null;

	public String name1 = null, name2 = null, name3 = null,
		 name4 = null, name5 = null;

	public String school1 = null, school2 = null, school3 = null,
		 school4 = null, school5 = null;

	public int staffStudentID1 = 0, staffStudentID2 = 0, staffStudentID3 = 0,
		staffStudentID4 = 0, staffStudentID5 = 0;

	public Role role1 = null, role2 = null, role3 = null,
		role4 = null, role5 = null;

	public boolean candidacy1 = false, candidacy2 = false, candidacy3 = false,
		candidacy4 = false, candidacy5 = false;

	public boolean integrityTrainingComplete1 = false, integrityTrainingComplete12 = false,
		integrityTrainingComplete3 = false, integrityTrainingComplete4 = false,
		integrityTrainingComplete5 = false;

	public boolean contactUsePricipalInvestigator6 = false;

	public String contactName = null;

	public int contactStaffID = 0;

	public String contactSchoolCentreArea = null;

	public int contactTelephone = 0;

	public String contactEmail = null;
}
