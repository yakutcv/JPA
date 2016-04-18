package com.SoftServe.ita.Ch_039.IO.Adapters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeForXmlAdapter extends XmlAdapter<String, DateTime> {

    DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

    public DateTime unmarshal(String value) throws Exception {
        DateTime dateTime = fmt.parseDateTime(value);
        return dateTime;

    }

    public String marshal(DateTime value) throws Exception {
        DateTimeFormatter fmt2 = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
        return value.toString(fmt2);
    }

}
