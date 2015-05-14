package views.formdata.application;

import java.util.List;
import javax.validation.*;

import play.data.validation.Constraints.*;

import org.joda.time.DateTime;


public class Section7Form {
    @Valid
    String pi_name;

    @Valid
    DateTime pi_sig_date;

    @Valid
    String pi_sig;

    @Valid
    public String hos_name;

    @Valid
    DateTime hos_sig_date;

    @Valid
    String hos_sig;

    @Valid
    List<CISubForm> cis;

    public static class CISubForm {
        @Valid
        String name;

        @Valid
        DateTime date;

        @Valid
        String sig;
    }
}
