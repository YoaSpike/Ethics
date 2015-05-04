package views.formdata

import java.util.Locale;
import java.text.ParseException;
import play.data.format.Formatters;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


class JodaDateFormatter extends Formatters.SimpleFormatter[DateTime] {
    override
    def parse(input: String, l: Locale) : DateTime = {
        (
            DateTimeFormat
            .forPattern("yyyy-MM-dd")
            .withLocale(l)
            .parseDateTime(input)
        )
    }

    override
    def print(date: DateTime, l: Locale) : String = {
        date.toString("yyyy-MM-dd", l)
    }
}
