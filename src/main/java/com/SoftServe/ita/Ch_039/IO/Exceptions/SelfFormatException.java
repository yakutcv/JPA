package com.SoftServe.ita.Ch_039.IO.Exceptions;

import java.io.IOException;

public class SelfFormatException extends IOException {

    public SelfFormatException(ExceptionList exceptionType) {
        switch (exceptionType){
            case INVALID_NAME: {
                System.err.println("Invalid format NAME. Please, enter correct value");
                this.printStackTrace();
                break;
            }
            case INVALID_LAST_NAME: {
                System.err.println("Invalid format LAST NAME. Please, enter correct value");
                this.printStackTrace();
                break;
            }
            case INVALID_BIRTH_DATE: {
                System.err.println("Invalid format BIRTH DATE. Please, enter correct value");
                this.printStackTrace();
                break;
            }
            case INVALID_ANALYZES_TYPE:{
                System.err.println("Invalid format ANALYZES TYPE. Please, enter correct value");
                this.printStackTrace();
                break;
            }
            case INVALID_ANALYZES_DATE:{
                System.err.println("Invalid format ANALYZES DATE. Please, enter correct value");
                this.printStackTrace();
                break;
            }
            case DEFAULT: {
                System.err.println("Invalid format INPUT VALUES. Please, enter correct value");
                this.printStackTrace();
                break;
            }
        }
    }
}
