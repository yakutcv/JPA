package SoftServe.Task_1.Entity;

import SoftServe.Task_1.IO.Adapters.DateTimeForXmlAdapter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Objects;


@XmlRootElement(name="Analysis")
@XmlType(propOrder = {
        "type",
        "date",
        "report"
})
public class Analysis implements Comparable<Analysis> {

    @XmlAttribute(name="id")
    private long id;
    //@JsonAdapter(DateTimeForJSONAdapter.class)
    @XmlJavaTypeAdapter(DateTimeForXmlAdapter.class)
    @XmlElement(name = "date")
    private DateTime date = new DateTime(2014,3,28,15,00);

    @XmlElement(name="report")
    private String report = "Default report";

    @XmlElement(name="type")
    private AnalysisType type = AnalysisType.DEFAULT;

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

    public Analysis(){

    }

    public DateTime getDate() {
        return date;
    }

    public String getDateInString() {
        return date.toString(formatter);
    }

    public long getId() {
        return id;
    }

    public String getReport() {
        return report;
    }


    public AnalysisType getType() {
        return type;
    }

    public static AnalysisBuilder newAnalysisBuilder () {
        return new Analysis().new AnalysisBuilder();
    }


    @Override
    public int compareTo(Analysis o) {
        return this.getDate().compareTo(o.getDate());
    }

    public class AnalysisBuilder {

        private AnalysisBuilder(){
        }

        public AnalysisBuilder setReport(String report) {
            Analysis.this.report = report;
            return this;
        }

        public AnalysisBuilder setType(AnalysisType type) {
            Analysis.this.type = type;
            return this;
        }

        public AnalysisBuilder setDate(String date) {
            Analysis.this.date = formatter.parseDateTime(date);
            return this;
        }

        public AnalysisBuilder setId(long id) {
            Analysis.this.id = id;
            return this;
        }

        public Analysis build() {
            return Analysis.this;
        }
    }

    @Override
    public String toString() {
        return "Type of analysis - " + type +  ", Date - " + date.toString(formatter) + ", Report - " + report + "." +"\n";
    }




}
