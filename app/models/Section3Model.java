package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Section3")
public class Section3Model extends Model{
	
	public Section3Model(){
		super();
	}

	public Section3Model(int id){
		super();
		this.id = id;
	} 

	@Id
	@Valid
	public int id;

	@OneToOne(optional=false, cascade=CascadeType.ALL,
		 mappedBy="section3", targetEntity=ApplicationModel.class)
	public ApplicationModel applicationModel;

	public static Finder<Integer,Section3Model> find = new Finder<Integer,Section3Model>(
        Integer.class, Section3Model.class
    );

	@Constraints.Required
	public boolean isYourStudyAClinicalTrail13 = false;

	public boolean willAPlaceboBeUsedGroupByUsed13a  = false;
	public String willAPlaceboBeUsedGroupByUsedText13a = null;

	public boolean hasThisTrailBeenRegistered13b = false;
	public String hasThisTrailBeenRegisteredText13b = null;

	public boolean facilitiesExpertiseSufficient13c = false;
	public String facilitiesExpertiseSufficient13cText = null;

	public boolean doesYourStatementMakeClear13d = false;
	
	@Constraints.Required
	public boolean doesYourResearchUseHealthInformation14 = false;
	public String doesYourResearchUseHealthInformationText14 = null;
	
	@Constraints.Required
	public boolean doesYourResearchInvolveHumanGenetics15 = false;
	public String doesYourResearchInvolveHumanGeneticsText15 = null;
}