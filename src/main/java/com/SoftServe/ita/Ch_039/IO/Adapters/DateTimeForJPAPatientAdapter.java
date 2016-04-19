package com.SoftServe.ita.Ch_039.IO.Adapters;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class DateTimeForJPAPatientAdapter implements AttributeConverter<DateTime, String> {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

    @Override
    public String convertToDatabaseColumn(DateTime dateTime) {
        return dateTime.toString(formatter);
    }

    @Override
    public DateTime convertToEntityAttribute(String date) {
        return formatter.parseDateTime(date);
    }
}
