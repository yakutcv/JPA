/*
package com.SoftServe.ita.Ch_039.IO.Adapters;


import com.sun.javafx.Utils;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;
import javax.persistence.AttributeConverter;
import org.joda.time.DateTime;


import java.text.SimpleDateFormat;
import java.util.Date;

*/
/*DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");*//*




@javax.persistence.Converter(autoApply = true)
public class DateTimeForJPAAdapter implements AttributeConverter<DateTime, Date> {

    SimpleDateFormat fmt = new SimpleDateFormat().format(attribute.toDate())
    public Date convertToDatabaseColumn(DateTime attribute) {
        return new SimpleDateFormat("dd/MM/yyyy").parse(attribute.toString());


    }

    @Override
    public DateTime convertToEntityAttribute(Date dbData) {
        return new DateTime(dbData.getTime());
    }
}
*/
