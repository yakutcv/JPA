package com.SoftServe.ita.Ch_039.Model.Entity;

import javax.xml.bind.annotation.XmlEnumValue;
import java.io.Serializable;

public enum AnalysisType implements Serializable {
    @XmlEnumValue(value = "Blood")
    BLOOD,
    @XmlEnumValue(value = "Urine")
    URINE,
    @XmlEnumValue(value = "Biopsy")
    BIOPSY,
    @XmlEnumValue(value = "Hormones")
    HORMONES,
    @XmlEnumValue(value = "RH_factor")
    RH_FACTOR,
    @XmlEnumValue(value = "Allergy")
    ALLERGY,
    @XmlEnumValue(value = "Allergy_2")
    ALLERGY_2,
    @XmlEnumValue(value = "Karyotyping")
    KARYOTYPING,
    @XmlEnumValue(value = "Cancer")
    CANCER,
    @XmlEnumValue(value = "Hepatitis")
    HEPATITIS
}
