package SoftServe.Task_1.Interfaces;

import SoftServe.Task_1.IO.Exceptions.SelfFormatException;
import SoftServe.Task_1.Logic.Hospital;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IO {

    public void writeHospital(Hospital hospital, String file) throws JAXBException, FileNotFoundException, IOException;

    public Hospital readHospital(String file) throws JAXBException, FileNotFoundException, IOException, ClassNotFoundException, SelfFormatException;
}
