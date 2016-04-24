package com.SoftServe.ita.Ch_039.Adapters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DateTimeForJPAAnalysisAdapter  implements AttributeConverter<DateTime, String> {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

    @Override
    public String convertToDatabaseColumn(DateTime dateTime) {
        return dateTime.toString(formatter);
    }

    @Override
    public DateTime convertToEntityAttribute(String date) {
        return formatter.parseDateTime(date);
    }
}
