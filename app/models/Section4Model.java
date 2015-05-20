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
		super();
		this.id = id;
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
	public boolean involvePregnantWomen16 = false;
	public String involvePregnantWomen16Text = null;

	public boolean stepsTakenEnsureWellbeing16a = false;
	public String stepsTakenEnsureWellbeing16aText = null;

	public boolean informationAboutResearchBeSeparate16b = false;
	public String informationAboutResearchBeSeparate16bText = null;
	
	@Constraints.Required
	public boolean researchInvolveChildren17 = false;
	public String researchInvolveChildren17Text = null;

	public boolean haveAWorkingChildrenCard17a = false;
	public String haveAWorkingChildrenCard17aText = null;

	@Constraints.Required
	public boolean researchInvolvePeopleInDependent18 = false;
	public String researchInvolvePeopleInDependent18Text = null;

	@Constraints.Required
	public boolean researchInvolvePeopleHighlyDependent19 = false;
	public String researchInvolvePeopleHighlyDependent19Text = null;

	@Constraints.Required
	public boolean researchInvolvePeopleWithACognitive20 = false;
	public String researchInvolvePeopleWithACognitive20Text = null;

	@Constraints.Required
	public boolean researchInvolvePeopleInIllegal21 = false;
	public String researchInvolvePeopleInIllegal21Text = null;

	@Constraints.Required
	public boolean researchInvolveAboriginal22 = false;
	public String researchInvolveAboriginal22Text = null;

	public String estimatedProportionOfAboriginal22a = null;
	
	public boolean aboriginalStatusOfParticipant22b = false;
	public String aboriginalStatusOfParticipant22bText = null;
}