package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.*;

import play.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Section6")
public class Section6Model extends Model{

	public Section6Model(){
		super();
	}

	public Section6Model(int id){
		super();
		this.id = id;
	}

	@Id
	@Valid
	public Integer id;
	
	public static Finder<Integer,Section6Model> find = new Finder<Integer,Section6Model>(
        Integer.class, Section6Model.class
    );
	
	// None of these fields are strictly necessary unless attachments are valid. Does that mean we don't need @Constraints.required?
	
	// For all following Boolean fields, null represents N/A and is hence the default.
	
	public Boolean researchProposalAttached = null;
	public Integer researchProposalVersion;
	public Date researchProposalDate;
	
	public Boolean reruitmentMaterialAttached = null;
	public Integer recruitmentMaterialVersion;
	public Date recruitmentMaterialDate;
	
	public Boolean participantInformationAttached = null;
	public Integer participantInformationVersion;
	public Date participantInformationDate;
	
	public Boolean parentInformationAttached = null;
	public Integer parentInformationVersion;
	public Date parentInformationDate;
	
	public Boolean childInformationAttached = null;
	public Integer childInformationVersion;
	public Date childInformationDate;
	
	// public Boolean[] questionnairesInstrumentsAttached = new Boolean[] {null,null,null,null} ; // There are up to four entries in the form for questionnaires/survey instruments
	// public Integer[] questionnairesInstrumentsVersions = new Integer[4];
	// public Date[] questionnairesInstrumentsDates = new Date[4]; // Don't need to initialise, only allocate. Default Date constructor is to the current time, which makes no sense anyway.
	
	public Boolean translationsAttached = null;
	public Integer translationsVersion;
	public Date translationsDate;
	
	// public Boolean[] recruitmentMaterialsAttached = new Boolean[] {null,null,null,null}; // I don't know why this is separate from recruitmentMaterial
	// public Integer[] recruitmentMaterialsVersions = new Integer[4];
	// public Date[] recruitmentMaterialsDates = new Date[4];
	
	public Boolean productInformationAttached = null;
	public Integer productInformationVersion;
	public Date productInformationDate;
	
	public Boolean workingWithChildrenAttached = null;
	public Integer workingWithChildrenVersion;
	public Date workingWithChildrenDate;
	
	// The full term is 'SOL Research Integrity Professional Development Program training certificate', but let's be reasonable.
	public Boolean integrityCertAttached = null;
	public Integer integrityCertVersion;
	public Date integrityCertDate;
	
}