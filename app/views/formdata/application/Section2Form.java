package views.formdata.application;

import javax.validation.*;
import play.data.validation.*;

public class Section2Form {
    // fields
    @Valid
    public String potential_risk_or_harm;

    @Valid
    public String risk_management_strategy;

    @Valid
    public boolean given_incentives;
    @Valid
    public String incentives_details;
}
