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

	public boolean willAPlaceboBeUsedGroupByUsed13a;

	public String willAPlaceboBeUsedGroupByUsedText13a = "hello";

	public boolean hasThisTrailBeenRegistered13b;

	public String hasThisTrailBeenRegisteredText13b;

	public boolean areTheFacilitiesExpertiseAndExperienceAvailableSufficient13c;

	public String areTheFacilitiesExpertiseAndExperienceAvailableSufficientText13c;

	public boolean doesYourParticipantInformationStatementMakeClear13d;

	public boolean doesYourResearchUseHealthInformation14;

	public String doesYourResearchUseHealthInformationText14;

	public boolean doesYourResearchInvolveHumanGenetics15;

	public String doesYourResearchInvolveHumanGeneticsText15;
}
