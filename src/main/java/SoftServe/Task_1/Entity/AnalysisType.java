package SoftServe.Task_1.Entity;

import javax.xml.bind.annotation.XmlEnumValue;
import java.io.Serializable;

/**
 * Created by ayasintc on 3/29/2016.
 */
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
    @XmlEnumValue(value = "Allergy")
    ALLERGY_2,
    @XmlEnumValue(value = "Patient don's passed any analyzes")
    DEFAULT
}
