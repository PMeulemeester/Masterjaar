/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Pieter
 */
public class Concert implements Serializable {
    
    private String titel;
    private double prijsNormaal;
    private double prijsReductie;
    
    
    public Concert(String titel, double prijsNormaal, double prijsReductie) {
        this.titel = titel;
        this.prijsNormaal = prijsNormaal;
        this.prijsReductie = prijsReductie;
    }
    
    public String getTitel() {
        return titel;
    }
    
    public void setTitel(String value) {
        this.titel = value;
    }

    public double getPrijsNormaal() {
        return prijsNormaal;
    }

    public void setPrijsNormaal(double prijsNormaal) {
        this.prijsNormaal = prijsNormaal;
    }

    public double getPrijsReductie() {
        return prijsReductie;
    }

    public void setPrijsReductie(double prijsReductie) {
        this.prijsReductie = prijsReductie;
    }
    
    
}
