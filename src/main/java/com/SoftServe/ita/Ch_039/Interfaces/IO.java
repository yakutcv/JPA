package com.SoftServe.ita.Ch_039.Interfaces;

import com.SoftServe.ita.Ch_039.Model.Logic.Hospital;
import com.SoftServe.ita.Ch_039.IO.Exceptions.SelfFormatException;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IO {

    public void writeHospital(Hospital hospital, String file) throws JAXBException, FileNotFoundException, IOException;

    public Hospital readHospital(String file) throws JAXBException, FileNotFoundException, IOException, ClassNotFoundException, SelfFormatException;
}
