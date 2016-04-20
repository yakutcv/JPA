package com.SoftServe.ita.Ch_039.Entity;


import com.SoftServe.ita.Ch_039.IO.Adapters.DateTimeForJPAPatientAdapter;
import com.SoftServe.ita.Ch_039.IO.Adapters.DateTimeForXmlAdapter;
import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@XmlRootElement(name="Patient")
@XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(name="Patient", propOrder = {
        "id",
        "name",
        "lastName",
        "birthDate",
        "listAnalyzes"
})
public class Patient implements Comparable<Patient>,Serializable {

    @Transient
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(name="id")
    private long id=1;

    @Column
    @Basic(optional = false)
    @NotNull
    @SerializedName("Name")
    @XmlElement
    private String name = "Default name";

    @Column(name="last_name")
    @NotNull
    @Basic(optional = false)
    @SerializedName("Last name")
    @XmlElement
    private String lastName = "Default lastName";



    @Column(name = "Birthday")
    @Basic(optional = false)
    @SerializedName("Birthday")
    @XmlJavaTypeAdapter(DateTimeForXmlAdapter.class)
    @XmlElement
    @Convert(converter = DateTimeForJPAPatientAdapter.class)
    @Temporal(TemporalType.DATE)
    private DateTime birthDate = new DateTime(2014,3,28,15,00);

    @SerializedName("List of Analyzes")
    @XmlElementWrapper(name="List_of_Analyzes")
    @XmlElement(name="Analysis")
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Analysis> listAnalyzes = new ArrayList<>();

    public List<Analysis> getListAnalyzes() {
        return listAnalyzes;
    }

    public void addAnalysis(Analysis analysis) {
        this.listAnalyzes.add(analysis);
        if(analysis.getPatient()!=this){
            analysis.setPatient(this);
        }
    }


    @Column
    private boolean status = true;

    //default constructor
    public Patient() {

    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public Patient(long id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }



//    @XmlElementWrapper(name = "Analysis")

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getBirthDateInString(){
        return birthDate.toString(formatter);
    }

    public int getAge(){
        DateTime date = new DateTime();
        return Years.yearsBetween(birthDate, date).getYears();
    }

    public long getId() {
        return id;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setListAnalyzes(List<Analysis> listAnalyzes) {
        this.listAnalyzes = listAnalyzes;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //method to format incoming name
    private String formatName (String value) {
        String tmp = value.toLowerCase();
        String formatName;
        return formatName = tmp.substring(0,1).toUpperCase() + tmp.substring(1, tmp.length());
    }

    //get Full Name
    public String getFullName () {
        return formatName(getName()) + " " + formatName(getLastName());
    }

    //builder
    public static PatientBuilder newPatientBuilder () {
        return new Patient().new PatientBuilder();
    }

    @Override
    public int compareTo(Patient o) {
        return this.getLastName().compareTo(o.getLastName());
    }

    public class PatientBuilder {

        private PatientBuilder() {
        }

        public PatientBuilder setBirthDate(String birthDate) {
            Patient.this.birthDate = formatter.parseDateTime(birthDate);
            return this;
        }

        public PatientBuilder setLastName(String lastName) {
            Patient.this.lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1, lastName.length()).toLowerCase();
            return this;
        }

        public PatientBuilder setName(String name) {
            Patient.this.name = name.substring(0,1).toUpperCase() + name.substring(1, name.length()).toLowerCase();
            return this;

        }

        public PatientBuilder setId(long id) {
            Patient.this.id = id;
            return this;
        }
        public PatientBuilder setAnalysis(Analysis analyzes) {
            Patient.this.listAnalyzes.add(analyzes);
            return this;
        }

        public PatientBuilder setStatus(boolean status) {
            Patient.this.status = status;
            return this;
        }

        public PatientBuilder setAnalyzes(List<Analysis> analyzes) {
            Patient.this.listAnalyzes=analyzes;
            return this;
        }

        public Patient build() {
            return Patient.this;
        }
    }


  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (!birthDate.equals(patient.birthDate)) return false;
        if (!lastName.equals(patient.lastName)) return false;
        if (!name.equals(patient.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthDate.hashCode();
        return result;
    }*/


    //first override
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Analysis value: listAnalyzes) {
            builder.append(value);
        }
        String analyzes = builder.toString();
        return "Patient - "+ getFullName() +
                ", age - " + getAge() + " years." + "\n" +
                "The total number of Analyzes: " + getListAnalyzes().size() + "\n" +
                "All Analyzes:" + "\n" +
                analyzes;
    }

/*    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

        return "Patient{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate.toString(format) +
                ", listAnalyzes=" + listAnalyzes +
                '}';
    }*/
}